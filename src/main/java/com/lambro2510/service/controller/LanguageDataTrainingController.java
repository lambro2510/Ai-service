package com.lambro2510.service.controller;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
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

}
