package com.waes.diff_assessment.domain;

import com.waes.diff_assessment.enums.DiffStatus;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Diff document */
@Document(collection = "diff")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Diff {

  @Id private String id;

  private String left;

  private String right;

  private DiffStatus status;

  private Set<DiffItem> diffItems;
}
