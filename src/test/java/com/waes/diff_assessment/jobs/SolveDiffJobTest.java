package com.waes.diff_assessment.jobs;

import static com.google.common.collect.Sets.newHashSet;
import static com.waes.diff_assessment.enums.DiffStatus.READY_TO_PROCESS;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.waes.diff_assessment.domain.Diff;
import com.waes.diff_assessment.domain.DiffItem;
import com.waes.diff_assessment.enums.DiffStatus;
import com.waes.diff_assessment.repositories.DiffRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SolveDiffJobTest {

  public static final String ID = "1000";
  public static final String SAMPLE_BASE_64_TEXT = "SGFwcHkgTmV3IFllYXIgbXkgZnJpZW5kIQ==";

  @Mock private DiffRepository diffRepository;

  private SolveDiffJob solverDiffJob;

  @Before
  public void before() {
    solverDiffJob = new SolveDiffJob(diffRepository);
  }

  @Test
  public void solveReadyToProcessDiffsShouldSolveDiff() {
    Diff diff = createDiff();
    when(diffRepository.findByStatus(READY_TO_PROCESS)).thenReturn(newArrayList(diff));

    solverDiffJob.solveReadyToProcessDiffs();

    verify(diffRepository, times(1)).save(diff);
  }

  private Diff createDiff() {
    DiffItem diffItem1 = createDiffItem(0, 1);
    DiffItem diffItem2 = createDiffItem(20, 10);

    Diff diff =
        Diff.builder()
            .id(ID)
            .status(DiffStatus.NOT_EQUALS)
            .left(SAMPLE_BASE_64_TEXT)
            .right(SAMPLE_BASE_64_TEXT)
            .diffItems(newHashSet(diffItem1, diffItem2))
            .build();

    return diff;
  }

  private DiffItem createDiffItem(int offset, int length) {
    return DiffItem.builder().length(length).offset(offset).build();
  }
}
