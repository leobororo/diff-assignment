package com.waes.diff.solver;

import static com.waes.diff.enums.DiffStatus.DIFFERENT_SIZES;
import static com.waes.diff.enums.DiffStatus.EQUALS;
import static com.waes.diff.enums.DiffStatus.NOT_EQUALS;
import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import com.waes.diff.domain.Diff;
import com.waes.diff.domain.DiffItem;
import java.util.List;
import org.junit.Test;

public class DiffSolverTest {

  private static final String LEFT_TEXT = "SGFwcHkgTmV3IFllYXIgbXkgZnJpZW5kIQ==";
  private static final String RIGHT_TEXT = "a2FwcHkgTnJyclllYXIgbXkgZnJpZW5hYQ==";
  private static final String RIGHT_TEXT_LONGER = "a2FwcHkgTnJyclllYXIgbXkgZnJpZW5hYWE=";

  @Test
  public void solveShouldReturnNotEqualsAndDiffItems() {
    Diff diff = Diff.builder().left(LEFT_TEXT).right(RIGHT_TEXT).build();
    DiffSolver.solve(diff);

    assertThat(diff.getStatus(), equalTo(NOT_EQUALS));

    List<DiffItem> diffItems = newArrayList(diff.getDiffItems());
    assertThat(diffItems.get(0).getOffset(), equalTo(0));
    assertThat(diffItems.get(0).getLength(), equalTo(1));
    assertThat(diffItems.get(1).getOffset(), equalTo(7));
    assertThat(diffItems.get(1).getLength(), equalTo(3));
    assertThat(diffItems.get(2).getOffset(), equalTo(23));
    assertThat(diffItems.get(2).getLength(), equalTo(2));
  }

  @Test
  public void solveShouldReturnDifferentSizes() {
    Diff diff = Diff.builder().left(LEFT_TEXT).right(RIGHT_TEXT_LONGER).build();
    DiffSolver.solve(diff);

    assertThat(diff.getStatus(), equalTo(DIFFERENT_SIZES));
  }

  @Test
  public void solveShouldReturnEquals() {
    Diff diff = Diff.builder().left(LEFT_TEXT).right(LEFT_TEXT).build();
    DiffSolver.solve(diff);

    assertThat(diff.getStatus(), equalTo(EQUALS));
  }
}
