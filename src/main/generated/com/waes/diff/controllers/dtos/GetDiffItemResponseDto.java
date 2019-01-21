package com.waes.diff.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/** Class to GetDiffItemResponseDto */
@Generated(from = "_GetDiffItemResponseDto", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
public final class GetDiffItemResponseDto
    extends com.waes.diff.controllers.dtos._GetDiffItemResponseDto {
  private final Integer offset;
  private final Integer length;

  private GetDiffItemResponseDto(Integer offset, Integer length) {
    this.offset = offset;
    this.length = length;
  }

  /** @return The value of the {@code offset} attribute */
  @JsonProperty("offset")
  @Override
  public Integer getOffset() {
    return offset;
  }

  /** @return The value of the {@code length} attribute */
  @JsonProperty("length")
  @Override
  public Integer getLength() {
    return length;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link
   * _GetDiffItemResponseDto#getOffset() offset} attribute. An equals check used to prevent copying
   * of the same value by returning {@code this}.
   *
   * @param value A new value for offset
   * @return A modified copy of the {@code this} object
   */
  public final GetDiffItemResponseDto withOffset(Integer value) {
    Integer newValue = Objects.requireNonNull(value, "offset");
    if (this.offset.equals(newValue)) return this;
    return new GetDiffItemResponseDto(newValue, this.length);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link
   * _GetDiffItemResponseDto#getLength() length} attribute. An equals check used to prevent copying
   * of the same value by returning {@code this}.
   *
   * @param value A new value for length
   * @return A modified copy of the {@code this} object
   */
  public final GetDiffItemResponseDto withLength(Integer value) {
    Integer newValue = Objects.requireNonNull(value, "length");
    if (this.length.equals(newValue)) return this;
    return new GetDiffItemResponseDto(this.offset, newValue);
  }

  /**
   * This instance is equal to all instances of {@code GetDiffItemResponseDto} that have equal
   * attribute values.
   *
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof GetDiffItemResponseDto && equalTo((GetDiffItemResponseDto) another);
  }

  private boolean equalTo(GetDiffItemResponseDto another) {
    return offset.equals(another.offset) && length.equals(another.length);
  }

  /**
   * Computes a hash code from attributes: {@code offset}, {@code length}.
   *
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + offset.hashCode();
    h += (h << 5) + length.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code GetDiffItemResponseDto} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("GetDiffItemResponseDto")
        .omitNullValues()
        .add("offset", offset)
        .add("length", length)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   *
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding
   *     infrastructure
   */
  @Generated(from = "_GetDiffItemResponseDto", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json extends com.waes.diff.controllers.dtos._GetDiffItemResponseDto {
    Integer offset;
    Integer length;

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
      this.offset = offset;
    }

    @JsonProperty("length")
    public void setLength(Integer length) {
      this.length = length;
    }

    @Override
    public Integer getOffset() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Integer getLength() {
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
  static GetDiffItemResponseDto fromJson(Json json) {
    GetDiffItemResponseDto.Builder builder = GetDiffItemResponseDto.builder();
    if (json.offset != null) {
      builder.offset(json.offset);
    }
    if (json.length != null) {
      builder.length(json.length);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link _GetDiffItemResponseDto} value. Uses accessors to get
   * values to initialize the new immutable instance. If an instance is already immutable, it is
   * returned as is.
   *
   * @param instance The instance to copy
   * @return A copied immutable GetDiffItemResponseDto instance
   */
  public static GetDiffItemResponseDto copyOf(_GetDiffItemResponseDto instance) {
    if (instance instanceof GetDiffItemResponseDto) {
      return (GetDiffItemResponseDto) instance;
    }
    return GetDiffItemResponseDto.builder().from(instance).build();
  }

  /**
   * Creates a builder for {@link GetDiffItemResponseDto GetDiffItemResponseDto}.
   *
   * @return A new GetDiffItemResponseDto builder
   */
  public static GetDiffItemResponseDto.Builder builder() {
    return new GetDiffItemResponseDto.Builder();
  }

  /**
   * Builds instances of type {@link GetDiffItemResponseDto GetDiffItemResponseDto}. Initialize
   * attributes and then invoke the {@link #build()} method to create an immutable instance.
   *
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or
   * collection, but instead used immediately to create instances.</em>
   */
  @Generated(from = "_GetDiffItemResponseDto", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_OFFSET = 0x1L;
    private static final long INIT_BIT_LENGTH = 0x2L;
    private long initBits = 0x3L;

    private Integer offset;
    private Integer length;

    private Builder() {}

    /**
     * Fill a builder with attribute values from the provided {@code _GetDiffItemResponseDto}
     * instance. Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     *
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(_GetDiffItemResponseDto instance) {
      Objects.requireNonNull(instance, "instance");
      offset(instance.getOffset());
      length(instance.getLength());
      return this;
    }

    /**
     * Initializes the value for the {@link _GetDiffItemResponseDto#getOffset() offset} attribute.
     *
     * @param offset The value for offset
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("offset")
    public final Builder offset(Integer offset) {
      this.offset = Objects.requireNonNull(offset, "offset");
      initBits &= ~INIT_BIT_OFFSET;
      return this;
    }

    /**
     * Initializes the value for the {@link _GetDiffItemResponseDto#getLength() length} attribute.
     *
     * @param length The value for length
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("length")
    public final Builder length(Integer length) {
      this.length = Objects.requireNonNull(length, "length");
      initBits &= ~INIT_BIT_LENGTH;
      return this;
    }

    /**
     * Builds a new {@link GetDiffItemResponseDto GetDiffItemResponseDto}.
     *
     * @return An immutable instance of GetDiffItemResponseDto
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public GetDiffItemResponseDto build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new GetDiffItemResponseDto(offset, length);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_OFFSET) != 0) attributes.add("offset");
      if ((initBits & INIT_BIT_LENGTH) != 0) attributes.add("length");
      return "Cannot build GetDiffItemResponseDto, some of required attributes are not set "
          + attributes;
    }
  }
}
