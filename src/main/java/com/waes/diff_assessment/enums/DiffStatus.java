package com.waes.diff_assessment.enums;

/** Enum that represents the statuses the diff processing may be */
public enum DiffStatus {
  NOT_PROCESSED,
  EQUALS,
  DIFFERENT_SIZES,
  NOT_EQUALS,
  READY_TO_PROCESS;
}
