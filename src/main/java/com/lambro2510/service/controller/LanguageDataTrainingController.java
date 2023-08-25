package com.lambro2510.service.controller;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.entity.types.TextTone;
import com.lambro2510.service.response.LanguageDataResponse;
import com.lambro2510.service.service.DataTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/ai/language")
@RestController
public class LanguageDataTrainingController {

  @Autowired private DataTrainingService dataTrainingService;

  @PostMapping("update-percent")
  public ResponseEntity<Boolean> updatePercent(){
    dataTrainingService.updateTrainingData();
    return ResponseEntity.ok(true);
  }

  @PostMapping("update-accurate")
  public ResponseEntity<Boolean> updateAccurate(){
    dataTrainingService.updateAccurateTrainingData();
    return ResponseEntity.ok(true);
  }

  @PostMapping("clean")
  public ResponseEntity<Boolean> cleanData(){
    dataTrainingService.clearDuplicateData();
    return ResponseEntity.ok(true);
  }

  @PostMapping("")
  public ResponseEntity<Boolean> createData(@RequestBody List<CreateLanguageDataTrainingDto> dto){
    dataTrainingService.createLanguageDataTraining(dto);
    return ResponseEntity.ok(true);
  }

  @GetMapping("status")
  public ResponseEntity<List<LanguageDataTraining>> getStatusOfText(@RequestParam String text){
    return ResponseEntity.ok(dataTrainingService.getStatusOfText(text, null));
  }

  @GetMapping("")
  public ResponseEntity<?> getAllData(
      @RequestParam(required = false) String text,
      Pageable pageable){
    return ResponseEntity.ok(dataTrainingService.getAllDataTraining(text, pageable));
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateData(
      @PathVariable String id,
      @RequestParam TextStatus status,
      @RequestParam TextTone tone

      ){
    dataTrainingService.updateTrainingDataDetail(id,status, tone);
    return ResponseEntity.ok(true);
  }
}
