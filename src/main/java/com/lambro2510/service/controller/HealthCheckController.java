package com.lambro2510.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/health-check")
@RestController
public class HealthCheckController {
  @GetMapping("")
  public ResponseEntity<Boolean> healthCheck(){
    return ResponseEntity.ok(true);
  }
}
