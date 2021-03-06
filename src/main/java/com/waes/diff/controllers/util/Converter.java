package com.waes.diff.controllers.util;

import static com.google.common.collect.Sets.newLinkedHashSet;

import com.waes.diff.controllers.dtos.GetDiffItemResponseDto;
import com.waes.diff.controllers.dtos.GetDiffResponseDto;
import com.waes.diff.domain.Diff;
import java.util.Set;

public class Converter {

  private Converter() {}

  public static GetDiffResponseDto convertDiffEntityToDto(Diff diffResult) {
    Set<GetDiffItemResponseDto> diffs = newLinkedHashSet();

    diffResult
        .getDiffItems()
        .stream()
        .forEach(
            diffItem -> {
              GetDiffItemResponseDto diffItemDto =
                  GetDiffItemResponseDto.builder()
                      .length(diffItem.getLength())
                      .offset(diffItem.getOffset())
                      .build();

              diffs.add(diffItemDto);
            });

    return GetDiffResponseDto.builder().status(diffResult.getStatus()).diffs(diffs).build();
  }
}
