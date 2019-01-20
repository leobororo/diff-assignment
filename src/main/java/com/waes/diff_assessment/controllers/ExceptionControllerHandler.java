package com.waes.diff_assessment.controllers;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.waes.diff_assessment.controllers.dtos.ErrorDto;
import com.waes.diff_assessment.exceptions.DataNotDecodeableException;
import com.waes.diff_assessment.exceptions.DiffNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Class to map custom exceptions to suitable Http responses */
@ControllerAdvice
public class ExceptionControllerHandler {

  /**
   * Handles custom exception {@link DiffNotFoundException}. Returns extra information regarding the
   * error and the {@link HttpStatus}
   *
   * @param ex {@link DiffNotFoundException}
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(DiffNotFoundException.class)
  public ResponseEntity handleDiffNotFoundException(DiffNotFoundException ex) {
    ErrorDto error = ErrorDto.builder().error(ex.getMessage()).status(NOT_FOUND).build();
    return ResponseEntity.status(NOT_FOUND).body(error);
  }

  /**
   * Handles custom exception {@link DataNotDecodeableException}. Returns extra information
   * regarding the error and the {@link HttpStatus}
   *
   * @param ex {@link DataNotDecodeableException}
   * @return {@link ResponseEntity}
   */
  @ExceptionHandler(DataNotDecodeableException.class)
  public ResponseEntity handleDataNotDecodeableException(DataNotDecodeableException ex) {
    ErrorDto error = ErrorDto.builder().error(ex.getMessage()).status(UNPROCESSABLE_ENTITY).build();
    return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(error);
  }
}
