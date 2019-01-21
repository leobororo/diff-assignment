package com.waes.diff.controllers.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

/** Class to GetDiffItemResponseDto */
@Value.Immutable
@JsonDeserialize
@Value.Style(
    typeAbstract = "_*",
    typeImmutable = "*",
    visibility = Value.Style.ImplementationVisibility.PUBLIC)
public abstract class _GetDiffItemResponseDto {

  abstract Integer getOffset();

  abstract Integer getLength();
}
