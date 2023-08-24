package com.lambro2510.service.controller;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.response.LanguageDataResponse;
import com.lambro2510.service.service.DataTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/ai/language")
@RestController
public class LanguageDataTrainingController {

  @Autowired private DataTrainingService dataTrainingService;
  @PostMapping("")
  public ResponseEntity<Boolean> createData(@RequestBody List<CreateLanguageDataTrainingDto> dto){
    dataTrainingService.createLanguageDataTraining(dto);
    return ResponseEntity.ok(true);
  }

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

  @GetMapping("")
  public ResponseEntity<LanguageDataResponse> getStatusOfText(@RequestParam String text){
    return ResponseEntity.ok(dataTrainingService.getStatusOfText(text));
  }

}
