package com.waes.diff.exceptions;

/**
 * Custom exception that should be thrown when the data provided cannot the decoded from base64
 * encoding
 */
public class DataNotDecodeableException extends RuntimeException {

  public DataNotDecodeableException(String data) {
    super(String.format("Invalid data  = %s", data));
  }
}
