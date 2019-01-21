package com.waes.diff.controllers.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.http.HttpStatus;

/** Class to ErrorDto */
@Value.Immutable
@JsonDeserialize
@Value.Style(
    typeAbstract = "_*",
    typeImmutable = "*",
    visibility = Value.Style.ImplementationVisibility.PUBLIC)
public abstract class _ErrorDto {

  abstract HttpStatus getStatus();

  abstract String getError();
}
