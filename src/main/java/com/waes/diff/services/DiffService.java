package com.waes.diff.services;

import com.waes.diff.domain.Diff;

public interface DiffService {

  Diff addLeftText(String id, String text);

  Diff addRightText(String id, String text);

  Diff getDiffResult(String id);
}
