package com.lambro2510.service.service;

import com.lambro2510.service.Utils.DateUtils;
import com.lambro2510.service.Utils.Helper;
import com.lambro2510.service.Utils.RequestUtils;
import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.Statistic;
import com.lambro2510.service.entity.types.TextAccurate;
import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.entity.types.TextTone;
import com.lambro2510.service.factory.LanguageAiComponent;
import com.lambro2510.service.response.ApiResponse.ShopeeItemResponse;
import com.lambro2510.service.response.ApiResponse.ShoppeeRatingData;
import com.lambro2510.service.response.LanguageDataResponse;
import com.lambro2510.service.response.PageResponse;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Log4j2
public class DataTrainingService extends BaseService {

  @Value("${data.async}")
  private boolean asyncData;

  @Autowired
  @Lazy
  LanguageAiComponent languageAiComponent;

  public void createLanguageDataTraining(CreateLanguageDataTrainingDto dto) {
      try{
        String[] dataTrain = dto.getText().split(",");
        for(String text : dataTrain) {
          LanguageDataTraining dataTraining = createData(text, dto.getStatus(), dto.getPercent(), dto.getType(), dto.getTone());
          languageDataTrainingRepository.save(dataTraining);
        }
      }catch (Exception ex){
        log.error(ex.getMessage());
      }
  }

  public List<LanguageDataTraining> getAllTrainingData(int limit) {
    Pageable pageable = PageRequest.of(0, limit);
    return languageDataTrainingRepository.findAll(pageable).getContent();
  }

  private LanguageDataTraining createData(String text, TextStatus status, Double percent, String type, TextTone tone) {
    return LanguageDataTraining.builder()
        .text(text.trim())
        .status(status)
        .percent(percent)
        .type(type)
        .tone(tone)
        .createdAt(DateUtils.getNow())
        .build();
  }

  public LanguageDataResponse getStatusOfText(String text, List<LanguageDataResponse> dataResponses, boolean isSave) {
    if (dataResponses == null) {
      dataResponses = new ArrayList<>();
    }
    LanguageDataResponse data = languageAiComponent.getStatus(text);
    Statistic statistic = statisticRepository.findByKey("language_statistic");
    if (statistic == null) {
      statistic = new Statistic();
      statistic.setKey("language_statistic");
    }
    ObjectId id = new ObjectId();
    if ((data.isCorrect() || data.getPercent() > 0.8)) {
      LanguageDataTraining dataTraining = createData(text, data.getStatus(), data.getPercent(), "ALL", TextTone.NORMAL);
      dataTraining.setId(id);
      dataResponses.add(dataTraining.partnerToResponse());
      boolean update = false;
      if (data.getStatus() == TextStatus.GOOD || data.getStatus() == TextStatus.VERY_GOOD) {
        update = statistic.addGood(1);
      }
      if (data.getStatus() == TextStatus.NORMAL) {
        update = statistic.addNormal(1);
      }
      if (data.getStatus() == TextStatus.POOR || data.getStatus() == TextStatus.VERY_POOR) {
        update = statistic.addPoor(1);
      }
      try {
        if (update) {
          languageDataTrainingRepository.save(dataTraining);
          statisticRepository.save(statistic);
        }

      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    } else {
      dataResponses.add(data);
    }
    return data;
  }

  public void trainingSubText(String text, List<LanguageDataResponse> dataResponses, boolean isSave) {
    List<String> subTexts = Helper.splitTextIntoSentences(text);
    if (subTexts.size() == 1) return;
    for (String subText : subTexts) {
      getStatusOfText(subText, dataResponses, isSave);
    }
  }

  public void updateTrainingData() {
    List<LanguageDataTraining> dataTrainings = languageDataTrainingRepository.findAll();
    for (LanguageDataTraining dataTraining : dataTrainings) {
      dataTraining.setPercent(1D);
      dataTraining.setCreatedAt(DateUtils.getNow());
    }
    languageDataTrainingRepository.saveAll(dataTrainings);
  }

  public void updateAccurateTrainingData() {
    List<LanguageDataTraining> dataTrainings = languageDataTrainingRepository.findAll();
    for (LanguageDataTraining dataTraining : dataTrainings) {
      checkTextAccurate(dataTraining);
    }
    languageDataTrainingRepository.saveAll(dataTrainings);
  }

  public void clearDuplicateData() {
    List<LanguageDataTraining> dataTrainings = languageDataTrainingRepository.findAll();

    for (LanguageDataTraining dataTraining : dataTrainings) {
      List<LanguageDataTraining> trainings = languageDataTrainingRepository.findByText(dataTraining.getText());

      if (trainings.size() > 1) {
        LanguageDataTraining newestData = trainings.get(0);
        List<LanguageDataTraining> dataToDelete = new ArrayList<>();

        // Tìm bản ghi mới nhất trong các bản ghi trùng lặp
        for (LanguageDataTraining oldData : trainings) {
          if (oldData.getCreatedAt() > newestData.getCreatedAt()) {
            newestData = oldData;
          }
        }

        // Thêm các bản ghi cần xóa vào danh sách
        for (LanguageDataTraining oldData : trainings) {
          if (!oldData.getId().equals(newestData.getId())) {
            dataToDelete.add(oldData);
          }
        }

        // Xóa các bản ghi trùng lặp
        languageDataTrainingRepository.deleteAll(dataToDelete);
      }
    }
  }

  public void checkTextAccurate(LanguageDataTraining dataTraining) {
    Double percent = dataTraining.getPercent();
    if (percent <= 1.0 && percent > 0.9) {
      dataTraining.setAccurate(TextAccurate.PER100);
    } else if (percent <= 0.9 && percent > 0.8) {
      dataTraining.setAccurate(TextAccurate.PER90);
    } else if (percent <= 0.8 && percent > 0.7) {
      dataTraining.setAccurate(TextAccurate.PER80);
    } else if (percent <= 0.7 && percent > 0.6) {
      dataTraining.setAccurate(TextAccurate.PER70);
    } else if (percent <= 0.6 && percent > 0.5) {
      dataTraining.setAccurate(TextAccurate.PER60);
    } else if (percent <= 0.5 && percent > 0.4) {
      dataTraining.setAccurate(TextAccurate.PER50);
    } else if (percent <= 0.4 && percent > 0.3) {
      dataTraining.setAccurate(TextAccurate.PER40);
    } else if (percent <= 0.3 && percent > 0.2) {
      dataTraining.setAccurate(TextAccurate.PER30);
    } else if (percent <= 0.2 && percent > 0.1) {
      dataTraining.setAccurate(TextAccurate.PER20);
    } else if (percent <= 0.1) {
      dataTraining.setAccurate(TextAccurate.PER10);
    } else {
      dataTraining.setAccurate(TextAccurate.PER0);
    }
  }

  public void autoTraining() {
    if(!asyncData){
      return;
    }

    for (int offset = 0; offset < 50; offset++) {
      getFeed(offset);
    }
  }

  public void getFeed(int offset) {
    ShopeeItemResponse shoppeeItems = RequestUtils.getShoppeeItem(offset);

    if (shoppeeItems.getData().getFeeds() == null || shoppeeItems.getData().getFeeds().isEmpty()) {
      return;
    }

    for (ShopeeItemResponse.Feed feed : shoppeeItems.getData().getFeeds()) {
//        Thread thread = new Thread(() -> {
          getRating(feed, 0);
//        });
//        thread.start();
    }
    getFeed(++offset);
  }

  public void getRating(ShopeeItemResponse.Feed feed, int offset) {

    ShoppeeRatingData shoppeeRatingData = RequestUtils.getRatingData(feed.getItemCard().getItem().getItemid(), feed.getItemCard().getItem().getShopid(), offset);
    if (shoppeeRatingData.getData().getRatings() == null || shoppeeRatingData.getData().getRatings().isEmpty()) {
      return;
    }
    for (ShoppeeRatingData.RatingData.Rating rating : shoppeeRatingData.getData().getRatings()) {
      try {
        String comment = rating.getComment();
        TextStatus status = getStatus(rating.getRatingStar());
        List<LanguageDataResponse> dataResponses = new ArrayList<>();
//        List<LanguageDataResponse> responses = getStatusOfText(comment, dataResponses, false);
//        for(LanguageDataResponse response : responses){
//            if (status.equals(response.getStatus())){
//              getStatusOfText(response.getText(), new ArrayList<>(), true);
//            }
//        }
      } catch (Exception ex) {
        log.error(ex);
      }
    }

  }


  public TextStatus getStatus(Integer star) {
    Map<Integer, TextStatus> statusMap = new HashMap<>();
    statusMap.put(1, TextStatus.VERY_POOR);
    statusMap.put(2, TextStatus.POOR);
    statusMap.put(3, TextStatus.NORMAL);
    statusMap.put(4, TextStatus.GOOD);
    statusMap.put(5, TextStatus.VERY_GOOD);
    TextStatus status = statusMap.get(star);
    if (status == null) throw new RuntimeException("Error");
    return status;
  }

  public PageResponse getAllDataTraining(String text, Pageable pageable) {
    if(Objects.isNull(text)){
      return PageResponse.createFrom(languageDataTrainingRepository.findAll(pageable));
    }else{
      return PageResponse.createFrom(languageDataTrainingRepository.findByText(text, pageable));

    }
  }

  public void updateTrainingDataDetail(String id, TextStatus status, TextTone tone) {
    LanguageDataTraining languageDataTraining = languageDataTrainingRepository.findById(new ObjectId(id)).get();
    languageDataTraining.setStatus(status);
    languageDataTraining.setTone(tone);
    languageDataTrainingRepository.save(languageDataTraining);
  }
}
