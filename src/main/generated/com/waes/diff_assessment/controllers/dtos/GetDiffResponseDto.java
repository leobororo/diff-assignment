package com.waes.diff_assessment.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.waes.diff_assessment.enums.DiffStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/** Class to GetDiffResponseDto */
@Generated(from = "_GetDiffResponseDto", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
public final class GetDiffResponseDto
    extends com.waes.diff_assessment.controllers.dtos._GetDiffResponseDto {
  private final DiffStatus status;
  private final ImmutableList<GetDiffItemResponseDto> diffs;

  private GetDiffResponseDto(DiffStatus status, ImmutableList<GetDiffItemResponseDto> diffs) {
    this.status = status;
    this.diffs = diffs;
  }

  /** @return The value of the {@code status} attribute */
  @JsonProperty("status")
  @Override
  public DiffStatus getStatus() {
    return status;
  }

  /** @return The value of the {@code diffs} attribute */
  @JsonProperty("diffs")
  @Override
  public ImmutableList<GetDiffItemResponseDto> getDiffs() {
    return diffs;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link
   * _GetDiffResponseDto#getStatus() status} attribute. A value equality check is used to prevent
   * copying of the same value by returning {@code this}.
   *
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final GetDiffResponseDto withStatus(DiffStatus value) {
    if (this.status == value) return this;
    DiffStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new GetDiffResponseDto(newValue, this.diffs);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link
   * _GetDiffResponseDto#getDiffs() diffs}.
   *
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final GetDiffResponseDto withDiffs(GetDiffItemResponseDto... elements) {
    ImmutableList<GetDiffItemResponseDto> newValue = ImmutableList.copyOf(elements);
    return new GetDiffResponseDto(this.status, newValue);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link
   * _GetDiffResponseDto#getDiffs() diffs}. A shallow reference equality check is used to prevent
   * copying of the same value by returning {@code this}.
   *
   * @param elements An iterable of diffs elements to set
   * @return A modified copy of {@code this} object
   */
  public final GetDiffResponseDto withDiffs(Iterable<? extends GetDiffItemResponseDto> elements) {
    if (this.diffs == elements) return this;
    ImmutableList<GetDiffItemResponseDto> newValue = ImmutableList.copyOf(elements);
    return new GetDiffResponseDto(this.status, newValue);
  }

  /**
   * This instance is equal to all instances of {@code GetDiffResponseDto} that have equal attribute
   * values.
   *
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof GetDiffResponseDto && equalTo((GetDiffResponseDto) another);
  }

  private boolean equalTo(GetDiffResponseDto another) {
    return status.equals(another.status) && diffs.equals(another.diffs);
  }

  /**
   * Computes a hash code from attributes: {@code status}, {@code diffs}.
   *
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + status.hashCode();
    h += (h << 5) + diffs.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code GetDiffResponseDto} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("GetDiffResponseDto")
        .omitNullValues()
        .add("status", status)
        .add("diffs", diffs)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   *
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding
   *     infrastructure
   */
  @Generated(from = "_GetDiffResponseDto", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json extends com.waes.diff_assessment.controllers.dtos._GetDiffResponseDto {
    DiffStatus status;
    List<GetDiffItemResponseDto> diffs = ImmutableList.of();

    @JsonProperty("status")
    public void setStatus(DiffStatus status) {
      this.status = status;
    }

    @JsonProperty("diffs")
    public void setDiffs(List<GetDiffItemResponseDto> diffs) {
      this.diffs = diffs;
    }

    @Override
    public DiffStatus getStatus() {
      throw new UnsupportedOperationException();
    }

    @Override
    public List<GetDiffItemResponseDto> getDiffs() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding
   *     infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static GetDiffResponseDto fromJson(Json json) {
    GetDiffResponseDto.Builder builder = GetDiffResponseDto.builder();
    if (json.status != null) {
      builder.status(json.status);
    }
    if (json.diffs != null) {
      builder.addAllDiffs(json.diffs);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link _GetDiffResponseDto} value. Uses accessors to get values
   * to initialize the new immutable instance. If an instance is already immutable, it is returned
   * as is.
   *
   * @param instance The instance to copy
   * @return A copied immutable GetDiffResponseDto instance
   */
  public static GetDiffResponseDto copyOf(_GetDiffResponseDto instance) {
    if (instance instanceof GetDiffResponseDto) {
      return (GetDiffResponseDto) instance;
    }
    return GetDiffResponseDto.builder().from(instance).build();
  }

  /**
   * Creates a builder for {@link GetDiffResponseDto GetDiffResponseDto}.
   *
   * @return A new GetDiffResponseDto builder
   */
  public static GetDiffResponseDto.Builder builder() {
    return new GetDiffResponseDto.Builder();
  }

  /**
   * Builds instances of type {@link GetDiffResponseDto GetDiffResponseDto}. Initialize attributes
   * and then invoke the {@link #build()} method to create an immutable instance.
   *
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or
   * collection, but instead used immediately to create instances.</em>
   */
  @Generated(from = "_GetDiffResponseDto", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_STATUS = 0x1L;
    private long initBits = 0x1L;

    private DiffStatus status;
    private ImmutableList.Builder<GetDiffItemResponseDto> diffs = ImmutableList.builder();

    private Builder() {}

    /**
     * Fill a builder with attribute values from the provided {@code _GetDiffResponseDto} instance.
     * Regular attribute values will be replaced with those from the given instance. Absent optional
     * values will not replace present values. Collection elements and entries will be added, not
     * replaced.
     *
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(_GetDiffResponseDto instance) {
      Objects.requireNonNull(instance, "instance");
      status(instance.getStatus());
      addAllDiffs(instance.getDiffs());
      return this;
    }

    /**
     * Initializes the value for the {@link _GetDiffResponseDto#getStatus() status} attribute.
     *
     * @param status The value for status
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(DiffStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Adds one element to {@link _GetDiffResponseDto#getDiffs() diffs} list.
     *
     * @param element A diffs element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addDiffs(GetDiffItemResponseDto element) {
      this.diffs.add(element);
      return this;
    }

    /**
     * Adds elements to {@link _GetDiffResponseDto#getDiffs() diffs} list.
     *
     * @param elements An array of diffs elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addDiffs(GetDiffItemResponseDto... elements) {
      this.diffs.add(elements);
      return this;
    }

    /**
     * Sets or replaces all elements for {@link _GetDiffResponseDto#getDiffs() diffs} list.
     *
     * @param elements An iterable of diffs elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("diffs")
    public final Builder diffs(Iterable<? extends GetDiffItemResponseDto> elements) {
      this.diffs = ImmutableList.builder();
      return addAllDiffs(elements);
    }

    /**
     * Adds elements to {@link _GetDiffResponseDto#getDiffs() diffs} list.
     *
     * @param elements An iterable of diffs elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllDiffs(Iterable<? extends GetDiffItemResponseDto> elements) {
      this.diffs.addAll(elements);
      return this;
    }

    /**
     * Builds a new {@link GetDiffResponseDto GetDiffResponseDto}.
     *
     * @return An immutable instance of GetDiffResponseDto
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public GetDiffResponseDto build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new GetDiffResponseDto(status, diffs.build());
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      return "Cannot build GetDiffResponseDto, some of required attributes are not set "
          + attributes;
    }
  }
}
