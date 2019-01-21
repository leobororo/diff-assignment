package com.waes.diff.exceptions;

/** Custom exception that should be thrown when no diff is found for a specific id */
public class DiffNotFoundException extends RuntimeException {

  public DiffNotFoundException(String diffId) {
    super(String.format("Resource with id %s not found", diffId));
  }
}
