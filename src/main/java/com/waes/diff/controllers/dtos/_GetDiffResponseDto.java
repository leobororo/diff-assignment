package com.waes.diff.controllers.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.waes.diff.enums.DiffStatus;
import java.util.List;
import org.immutables.value.Value;

/** Class to GetDiffResponseDto */
@Value.Immutable
@JsonDeserialize
@Value.Style(
    typeAbstract = "_*",
    typeImmutable = "*",
    visibility = Value.Style.ImplementationVisibility.PUBLIC)
public abstract class _GetDiffResponseDto {

  abstract DiffStatus getStatus();

  abstract List<GetDiffItemResponseDto> getDiffs();
}
