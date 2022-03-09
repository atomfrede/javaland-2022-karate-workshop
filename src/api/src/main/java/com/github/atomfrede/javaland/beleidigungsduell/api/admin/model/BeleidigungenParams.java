package com.github.atomfrede.javaland.beleidigungsduell.api.admin.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Representation of the provided search parameters.
 */

@Schema(name = "BeleidigungenParams", description = "Representation of the provided search parameters.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-09T09:22:11.953756+01:00[Europe/Berlin]")
public class BeleidigungenParams   {

  @JsonProperty("limit")
  private Integer limit;

  @JsonProperty("offset")
  private Integer offset;

  public BeleidigungenParams limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Value of input parameter 'limit'.
   * @return limit
  */
  
  @Schema(name = "limit", description = "Value of input parameter 'limit'.", required = false)
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public BeleidigungenParams offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Value of input parameter 'offset'.
   * @return offset
  */
  
  @Schema(name = "offset", description = "Value of input parameter 'offset'.", required = false)
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeleidigungenParams beleidigungenParams = (BeleidigungenParams) o;
    return Objects.equals(this.limit, beleidigungenParams.limit) &&
        Objects.equals(this.offset, beleidigungenParams.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeleidigungenParams {\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

