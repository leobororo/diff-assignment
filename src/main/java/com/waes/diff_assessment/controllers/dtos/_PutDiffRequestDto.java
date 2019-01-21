package com.waes.diff_assessment.controllers.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

/** Class to PutDiffRequestDto */
@Value.Immutable
@JsonDeserialize
@Value.Style(
    typeAbstract = "_*",
    typeImmutable = "*",
    visibility = Value.Style.ImplementationVisibility.PUBLIC)
public abstract class _PutDiffRequestDto {

  abstract String getData();
}
