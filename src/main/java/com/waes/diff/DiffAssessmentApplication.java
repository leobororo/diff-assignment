package com.waes.diff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DiffAssessmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(DiffAssessmentApplication.class, args);
  }
}
