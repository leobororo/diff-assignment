package com.waes.diff_assessment.jobs;

import static com.waes.diff_assessment.enums.DiffStatus.READY_TO_PROCESS;

import com.waes.diff_assessment.domain.Diff;
import com.waes.diff_assessment.enums.DiffStatus;
import com.waes.diff_assessment.repositories.DiffRepository;
import com.waes.diff_assessment.solver.DiffSolver;
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
    List<Diff> diffsToProcess = diffRepository.findByStatus(READY_TO_PROCESS);

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
