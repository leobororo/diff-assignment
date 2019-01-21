package com.waes.diff_assessment.services;

import static com.google.common.collect.Sets.newHashSet;
import static com.waes.diff_assessment.enums.DiffStatus.READY_TO_PROCESS;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.waes.diff_assessment.domain.Diff;
import com.waes.diff_assessment.domain.DiffItem;
import com.waes.diff_assessment.enums.DiffStatus;
import com.waes.diff_assessment.repositories.DiffRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiffServiceImplTest {

  public static final String ID_EXISTENT_DIFF = "1000";
  public static final String ID_INEXISTENT_DIFF = "2000";
  public static final String SOME_NEW_TEXT = "SGFwcHkgTmV3IFllYXIgbXkgZnJpZW5kIQ==";
  public static final String SOME_LEFT_TEXT = "SGFwcHkgTmV3IFllYXIgbXkgZnJpZW5kIQ==";
  public static final String SOME_RIGHT_TEXT = "a2FwcHkgTnJyclllYXIgbXkgZnJpZW5hYQ==";

  @Mock private DiffRepository diffRepository;

  private DiffServiceImpl diffService;

  @Before
  public void before() {
    diffService = new DiffServiceImpl(diffRepository);
  }

  @Test
  public void addLeftTextWhenResourceExists() {
    Diff diff =
        Diff.builder()
            .id(ID_EXISTENT_DIFF)
            .status(DiffStatus.DIFFERENT_SIZES)
            .left(SOME_LEFT_TEXT)
            .right(SOME_RIGHT_TEXT)
            .diffItems(newHashSet(DiffItem.builder().build()))
            .build();

    when(diffRepository.findById(ID_EXISTENT_DIFF)).thenReturn(Optional.of(diff));
    when(diffRepository.save(diff)).thenReturn(diff);

    diff = diffService.addLeftText(ID_EXISTENT_DIFF, SOME_NEW_TEXT);

    verify(diffRepository, times(1)).findById(ID_EXISTENT_DIFF);
    verify(diffRepository, times(1)).save(diff);

    assertThat(diff.getLeft(), equalTo(SOME_NEW_TEXT));
    assertThat(diff.getStatus(), equalTo(READY_TO_PROCESS));
    assertThat(diff.getDiffItems().size(), equalTo(0));
  }

  @Test
  public void addLeftTextWhenResourceDoesNotExist() {
    when(diffRepository.findById(ID_INEXISTENT_DIFF)).thenReturn(Optional.ofNullable(null));
    when(diffRepository.save(any())).thenReturn(any());

    diffService.addLeftText(ID_INEXISTENT_DIFF, SOME_NEW_TEXT);

    verify(diffRepository, times(1)).findById(ID_INEXISTENT_DIFF);
    verify(diffRepository, times(1)).save(any());
  }

  @Test
  public void addRightTextWhenResourceExists() {
    Diff diff =
        Diff.builder()
            .id(ID_EXISTENT_DIFF)
            .status(DiffStatus.DIFFERENT_SIZES)
            .left(SOME_LEFT_TEXT)
            .right(SOME_RIGHT_TEXT)
            .diffItems(newHashSet(DiffItem.builder().build()))
            .build();

    when(diffRepository.findById(ID_EXISTENT_DIFF)).thenReturn(Optional.of(diff));
    when(diffRepository.save(diff)).thenReturn(diff);

    diff = diffService.addRightText(ID_EXISTENT_DIFF, SOME_NEW_TEXT);

    verify(diffRepository, times(1)).findById(ID_EXISTENT_DIFF);
    verify(diffRepository, times(1)).save(diff);

    assertThat(diff.getRight(), equalTo(SOME_NEW_TEXT));
    assertThat(diff.getStatus(), equalTo(READY_TO_PROCESS));
    assertThat(diff.getDiffItems().size(), equalTo(0));
  }

  @Test
  public void addRightTextWhenResourceDoesNotExist() {
    when(diffRepository.findById(ID_INEXISTENT_DIFF)).thenReturn(Optional.ofNullable(null));
    when(diffRepository.save(any())).thenReturn(any());

    diffService.addRightText(ID_INEXISTENT_DIFF, SOME_NEW_TEXT);

    verify(diffRepository, times(1)).findById(ID_INEXISTENT_DIFF);
    verify(diffRepository, times(1)).save(any());
  }

  @Test
  public void getDiffResult() {
    Diff diff =
        Diff.builder()
            .id(ID_EXISTENT_DIFF)
            .status(DiffStatus.DIFFERENT_SIZES)
            .left(SOME_LEFT_TEXT)
            .right(SOME_RIGHT_TEXT)
            .diffItems(newHashSet(DiffItem.builder().build()))
            .build();

    when(diffRepository.findById(ID_EXISTENT_DIFF)).thenReturn(Optional.of(diff));

    diffService.getDiffResult(ID_EXISTENT_DIFF);

    verify(diffRepository, times(1)).findById(ID_EXISTENT_DIFF);
  }
}
