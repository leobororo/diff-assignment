package com.waes.diff.solver;

import static com.google.common.collect.Sets.newLinkedHashSet;

import com.waes.diff.domain.Diff;
import com.waes.diff.domain.DiffItem;
import com.waes.diff.enums.DiffStatus;
import java.util.Base64;
import java.util.Set;

/** Class responsible for solving the diff */
public class DiffSolver {

  /**
   * Solves the diff, updates the {@link DiffStatus} and calculates the collection of {@link
   * DiffItem}
   *
   * @param diff {@link Diff}
   * @return {@link DiffStatus}
   */
  public static Diff solve(final Diff diff) {
    String left = decode(diff.getLeft());
    String right = decode(diff.getRight());

    if (left.length() == right.length()) {
      Set<DiffItem> solution = newLinkedHashSet();

      boolean priorIsDifferent = false;
      int length = 0;
      int offset = 0;

      for (int i = 0; i < left.length(); i++) {
        if (left.charAt(i) != right.charAt(i)) {
          if (priorIsDifferent) {
            length++;
          } else {
            offset = i;
            length = 1;
            priorIsDifferent = true;
          }
        } else {
          if (priorIsDifferent) {
            addNewDiffItem(solution, length, offset);
          }
          priorIsDifferent = false;
        }
      }

      if (priorIsDifferent) {
        addNewDiffItem(solution, length, offset);
      }

      setSolution(diff, solution);
    } else {
      diff.setStatus(DiffStatus.DIFFERENT_SIZES);
    }

    return diff;
  }

  /**
   * Decodes the text from base64 encoding
   *
   * @param left {@link String}
   * @return {@link String}
   */
  private static String decode(String left) {
    return new String(Base64.getDecoder().decode(left));
  }

  /**
   * Creates a new instance of {@link DiffItem} and adds it to the collection inside the diff
   * instance
   *
   * @param solution {@link Set<DiffItem>}
   * @param length int
   * @param offset int
   */
  private static void addNewDiffItem(Set<DiffItem> solution, int length, int offset) {
    DiffItem diffItem = DiffItem.builder().offset(offset).length(length).build();
    solution.add(diffItem);
  }

  /**
   * Sets the solution in the diff document
   *
   * @param diff {@link Diff}
   * @param solution {@link Set<DiffItem>}
   */
  private static void setSolution(Diff diff, Set<DiffItem> solution) {
    if (solution.size() == 0) {
      diff.setStatus(DiffStatus.EQUALS);
    } else {
      diff.setStatus(DiffStatus.NOT_EQUALS);
    }

    diff.setDiffItems(solution);
  }
}
