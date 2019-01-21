package com.waes.diff.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class DiffItem {

  private Integer offset;

  private Integer length;
}
