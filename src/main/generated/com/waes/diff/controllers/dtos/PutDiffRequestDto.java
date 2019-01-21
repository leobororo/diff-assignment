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

/** Class to PutDiffRequestDto */
@Generated(from = "_PutDiffRequestDto", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
public final class PutDiffRequestDto extends com.waes.diff.controllers.dtos._PutDiffRequestDto {
  private final String data;

  private PutDiffRequestDto(String data) {
    this.data = data;
  }

  /** @return The value of the {@code data} attribute */
  @JsonProperty("data")
  @Override
  public String getData() {
    return data;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link
   * _PutDiffRequestDto#getData() data} attribute. An equals check used to prevent copying of the
   * same value by returning {@code this}.
   *
   * @param value A new value for data
   * @return A modified copy of the {@code this} object
   */
  public final PutDiffRequestDto withData(String value) {
    String newValue = Objects.requireNonNull(value, "data");
    if (this.data.equals(newValue)) return this;
    return new PutDiffRequestDto(newValue);
  }

  /**
   * This instance is equal to all instances of {@code PutDiffRequestDto} that have equal attribute
   * values.
   *
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof PutDiffRequestDto && equalTo((PutDiffRequestDto) another);
  }

  private boolean equalTo(PutDiffRequestDto another) {
    return data.equals(another.data);
  }

  /**
   * Computes a hash code from attributes: {@code data}.
   *
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + data.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code PutDiffRequestDto} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("PutDiffRequestDto")
        .omitNullValues()
        .add("data", data)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   *
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding
   *     infrastructure
   */
  @Generated(from = "_PutDiffRequestDto", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json extends com.waes.diff.controllers.dtos._PutDiffRequestDto {
    String data;

    @JsonProperty("data")
    public void setData(String data) {
      this.data = data;
    }

    @Override
    public String getData() {
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
  static PutDiffRequestDto fromJson(Json json) {
    PutDiffRequestDto.Builder builder = PutDiffRequestDto.builder();
    if (json.data != null) {
      builder.data(json.data);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link _PutDiffRequestDto} value. Uses accessors to get values
   * to initialize the new immutable instance. If an instance is already immutable, it is returned
   * as is.
   *
   * @param instance The instance to copy
   * @return A copied immutable PutDiffRequestDto instance
   */
  public static PutDiffRequestDto copyOf(_PutDiffRequestDto instance) {
    if (instance instanceof PutDiffRequestDto) {
      return (PutDiffRequestDto) instance;
    }
    return PutDiffRequestDto.builder().from(instance).build();
  }

  /**
   * Creates a builder for {@link PutDiffRequestDto PutDiffRequestDto}.
   *
   * @return A new PutDiffRequestDto builder
   */
  public static PutDiffRequestDto.Builder builder() {
    return new PutDiffRequestDto.Builder();
  }

  /**
   * Builds instances of type {@link PutDiffRequestDto PutDiffRequestDto}. Initialize attributes and
   * then invoke the {@link #build()} method to create an immutable instance.
   *
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or
   * collection, but instead used immediately to create instances.</em>
   */
  @Generated(from = "_PutDiffRequestDto", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_DATA = 0x1L;
    private long initBits = 0x1L;

    private String data;

    private Builder() {}

    /**
     * Fill a builder with attribute values from the provided {@code _PutDiffRequestDto} instance.
     * Regular attribute values will be replaced with those from the given instance. Absent optional
     * values will not replace present values.
     *
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(_PutDiffRequestDto instance) {
      Objects.requireNonNull(instance, "instance");
      data(instance.getData());
      return this;
    }

    /**
     * Initializes the value for the {@link _PutDiffRequestDto#getData() data} attribute.
     *
     * @param data The value for data
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("data")
    public final Builder data(String data) {
      this.data = Objects.requireNonNull(data, "data");
      initBits &= ~INIT_BIT_DATA;
      return this;
    }

    /**
     * Builds a new {@link PutDiffRequestDto PutDiffRequestDto}.
     *
     * @return An immutable instance of PutDiffRequestDto
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public PutDiffRequestDto build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new PutDiffRequestDto(data);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_DATA) != 0) attributes.add("data");
      return "Cannot build PutDiffRequestDto, some of required attributes are not set "
          + attributes;
    }
  }
}
