package com.waes.diff_assessment.repositories;

import com.waes.diff_assessment.domain.Diff;
import com.waes.diff_assessment.enums.DiffStatus;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiffRepository extends MongoRepository<Diff, String> {

  /**
   * Method that retrieves all diffs with a specific {@link DiffStatus}
   *
   * @param status {@link DiffStatus}
   * @return List<Diff>
   */
  List<Diff> findByStatus(DiffStatus status);
}
