package com.waes.diff_assessment.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.http.HttpStatus;

/** Class to ErrorDto */
@Generated(from = "_ErrorDto", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
public final class ErrorDto extends com.waes.diff_assessment.controllers.dtos._ErrorDto {
  private final HttpStatus status;
  private final String error;

  private ErrorDto(HttpStatus status, String error) {
    this.status = status;
    this.error = error;
  }

  /** @return The value of the {@code status} attribute */
  @JsonProperty("status")
  @Override
  public HttpStatus getStatus() {
    return status;
  }

  /** @return The value of the {@code error} attribute */
  @JsonProperty("error")
  @Override
  public String getError() {
    return error;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link _ErrorDto#getStatus()
   * status} attribute. A value equality check is used to prevent copying of the same value by
   * returning {@code this}.
   *
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final ErrorDto withStatus(HttpStatus value) {
    if (this.status == value) return this;
    HttpStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new ErrorDto(newValue, this.error);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link _ErrorDto#getError() error}
   * attribute. An equals check used to prevent copying of the same value by returning {@code this}.
   *
   * @param value A new value for error
   * @return A modified copy of the {@code this} object
   */
  public final ErrorDto withError(String value) {
    String newValue = Objects.requireNonNull(value, "error");
    if (this.error.equals(newValue)) return this;
    return new ErrorDto(this.status, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ErrorDto} that have equal attribute values.
   *
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ErrorDto && equalTo((ErrorDto) another);
  }

  private boolean equalTo(ErrorDto another) {
    return status.equals(another.status) && error.equals(another.error);
  }

  /**
   * Computes a hash code from attributes: {@code status}, {@code error}.
   *
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + status.hashCode();
    h += (h << 5) + error.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code ErrorDto} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("ErrorDto")
        .omitNullValues()
        .add("status", status)
        .add("error", error)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   *
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding
   *     infrastructure
   */
  @Generated(from = "_ErrorDto", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json extends com.waes.diff_assessment.controllers.dtos._ErrorDto {
    HttpStatus status;
    String error;

    @JsonProperty("status")
    public void setStatus(HttpStatus status) {
      this.status = status;
    }

    @JsonProperty("error")
    public void setError(String error) {
      this.error = error;
    }

    @Override
    public HttpStatus getStatus() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String getError() {
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
  static ErrorDto fromJson(Json json) {
    ErrorDto.Builder builder = ErrorDto.builder();
    if (json.status != null) {
      builder.status(json.status);
    }
    if (json.error != null) {
      builder.error(json.error);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link _ErrorDto} value. Uses accessors to get values to
   * initialize the new immutable instance. If an instance is already immutable, it is returned as
   * is.
   *
   * @param instance The instance to copy
   * @return A copied immutable ErrorDto instance
   */
  public static ErrorDto copyOf(_ErrorDto instance) {
    if (instance instanceof ErrorDto) {
      return (ErrorDto) instance;
    }
    return ErrorDto.builder().from(instance).build();
  }

  /**
   * Creates a builder for {@link ErrorDto ErrorDto}.
   *
   * @return A new ErrorDto builder
   */
  public static ErrorDto.Builder builder() {
    return new ErrorDto.Builder();
  }

  /**
   * Builds instances of type {@link ErrorDto ErrorDto}. Initialize attributes and then invoke the
   * {@link #build()} method to create an immutable instance.
   *
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or
   * collection, but instead used immediately to create instances.</em>
   */
  @Generated(from = "_ErrorDto", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_STATUS = 0x1L;
    private static final long INIT_BIT_ERROR = 0x2L;
    private long initBits = 0x3L;

    private HttpStatus status;
    private String error;

    private Builder() {}

    /**
     * Fill a builder with attribute values from the provided {@code _ErrorDto} instance. Regular
     * attribute values will be replaced with those from the given instance. Absent optional values
     * will not replace present values.
     *
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(_ErrorDto instance) {
      Objects.requireNonNull(instance, "instance");
      status(instance.getStatus());
      error(instance.getError());
      return this;
    }

    /**
     * Initializes the value for the {@link _ErrorDto#getStatus() status} attribute.
     *
     * @param status The value for status
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(HttpStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Initializes the value for the {@link _ErrorDto#getError() error} attribute.
     *
     * @param error The value for error
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("error")
    public final Builder error(String error) {
      this.error = Objects.requireNonNull(error, "error");
      initBits &= ~INIT_BIT_ERROR;
      return this;
    }

    /**
     * Builds a new {@link ErrorDto ErrorDto}.
     *
     * @return An immutable instance of ErrorDto
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ErrorDto build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ErrorDto(status, error);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      if ((initBits & INIT_BIT_ERROR) != 0) attributes.add("error");
      return "Cannot build ErrorDto, some of required attributes are not set " + attributes;
    }
  }
}
