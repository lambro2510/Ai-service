package com.lambro2510.service.factory;

import com.lambro2510.service.Utils.Helper;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.response.LanguageDataResponse;
import com.lambro2510.service.service.DataTrainingService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.ObjectStreamUtils;
import opennlp.tools.util.TrainingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Log4j2
public class LanguageAiComponent {

  @Autowired
  @Lazy
  DataTrainingService dataTrainingService;

  @Autowired
  @Lazy
  DocumentCategorizerME documentCategorizerME;

  @Bean
  public DocumentCategorizerME initModule() throws IOException {
    return createModule();
  }

  public void updateModule() throws IOException {
    documentCategorizerME = createModule();
  }

  public DocumentCategorizerME createModule() throws IOException {
    List<LanguageDataTraining> dataTrainings = dataTrainingService.getAllTrainingData(20000);
    List<DocumentSample> documentSamples = new ArrayList<>();

    for (LanguageDataTraining dataTraining : dataTrainings) {
      String[] text = dataTraining.getText().split("");
      String category = dataTraining.getStatus().toString();
      Map<String, Object> extraInformation = new HashMap<>();
//      extraInformation.put("percent", dataTraining.getPercent());
      extraInformation.put("status", dataTraining.getStatus());
//      extraInformation.put("accurate", dataTraining.getAccurate());
//      extraInformation.put("type", dataTraining.getType());
//      extraInformation.put("tone", dataTraining.getTone());
      DocumentSample sample = new DocumentSample(category, text, extraInformation);
      documentSamples.add(sample);
    }
    if(dataTrainings.size() < 50){
      return null;
    }
    // Tạo đối tượng ObjectStream từ dữ liệu huấn luyện
    ObjectStream<DocumentSample> sampleStream = ObjectStreamUtils.createObjectStream(documentSamples);

    TrainingParameters params = new TrainingParameters();
    params.put(TrainingParameters.CUTOFF_PARAM, "2");
    params.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");

    // Huấn luyện mô hình
    DoccatFactory factory = new DoccatFactory();

    // Huấn luyện mô hình
    DoccatModel model = DocumentCategorizerME.train("vi", sampleStream, params, factory);

    return new DocumentCategorizerME(model);
  }


  public LanguageDataResponse getStatus(String inputData) {
    String[] text = inputData.split("");
    double[] outcomes = documentCategorizerME.categorize(text);
    String status = documentCategorizerME.getBestCategory(outcomes);
    double percent = 0;
    TextStatus textStatus = null;
    for(double v : outcomes){
      if(v > percent){
        percent = v;
      }
    }
    log.info("Outcome --> inputData --> : {{}}", outcomes);
    textStatus = Arrays.stream(TextStatus.values()).filter(data -> data.toString().equals(status)).toList().get(0);
    return new LanguageDataResponse(null ,inputData, textStatus,textStatus.getDescription(), percent, Helper.isMaxValueOutcome(outcomes, 0.3));
  }

}
