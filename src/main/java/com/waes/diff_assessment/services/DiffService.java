package com.waes.diff_assessment.services;

import com.waes.diff_assessment.domain.Diff;

public interface DiffService {

  Diff addLeftText(String id, String text);

  Diff addRightText(String id, String text);

  Diff getDiffResult(String id);
}
