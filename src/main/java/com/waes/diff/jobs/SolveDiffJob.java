package com.waes.diff.jobs;

import com.waes.diff.domain.Diff;
import com.waes.diff.enums.DiffStatus;
import com.waes.diff.repositories.DiffRepository;
import com.waes.diff.solver.DiffSolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** Job that looks for diffs that are ready for processing and solves them */
@Component
@Profile("worker")
@Slf4j
public class SolveDiffJob {

  private DiffRepository diffRepository;

  @Autowired
  public SolveDiffJob(DiffRepository diffRepository) {
    this.diffRepository = diffRepository;
  }

  /**
   * This method looks for diffs whose {@link DiffStatus} is READY_TO_PROCESS and then uses the
   * {@link DiffSolver} to solve the diff
   */
  @Scheduled(fixedRate = 5000)
  public void solveReadyToProcessDiffs() {
    List<Diff> diffsToProcess = diffRepository.findByStatus(DiffStatus.READY_TO_PROCESS);

    diffsToProcess
        .stream()
        .forEach(
            diff -> {
              log.info("Solving diff with id = " + diff.getId());
              DiffSolver.solve(diff);
              diffRepository.save(diff);
            });
  }
}
