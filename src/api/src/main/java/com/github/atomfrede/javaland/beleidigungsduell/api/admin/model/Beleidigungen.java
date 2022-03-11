package com.github.atomfrede.javaland.beleidigungsduell.api.admin.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.Beleidigung;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.BeleidigungenParams;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Beleidigungen
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-11T16:58:44.288980+01:00[Europe/Berlin]")
public class Beleidigungen   {

  @JsonProperty("_total_count")
  private Long totalCount;

  @JsonProperty("_params")
  private BeleidigungenParams params;

  @JsonProperty("items")
  @Valid
  private List<Beleidigung> items = null;

  public Beleidigungen totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Gesamtzahl der Beleidigungs-Datensätze.
   * @return totalCount
  */
  
  @Schema(name = "_total_count", description = "Gesamtzahl der Beleidigungs-Datensätze.", required = false)
  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public Beleidigungen params(BeleidigungenParams params) {
    this.params = params;
    return this;
  }

  /**
   * Get params
   * @return params
  */
  @Valid 
  @Schema(name = "_params", required = false)
  public BeleidigungenParams getParams() {
    return params;
  }

  public void setParams(BeleidigungenParams params) {
    this.params = params;
  }

  public Beleidigungen items(List<Beleidigung> items) {
    this.items = items;
    return this;
  }

  public Beleidigungen addItemsItem(Beleidigung itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Liste von Beleidigungs-Datensätzen.
   * @return items
  */
  @Valid 
  @Schema(name = "items", description = "Liste von Beleidigungs-Datensätzen.", required = false)
  public List<Beleidigung> getItems() {
    return items;
  }

  public void setItems(List<Beleidigung> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Beleidigungen beleidigungen = (Beleidigungen) o;
    return Objects.equals(this.totalCount, beleidigungen.totalCount) &&
        Objects.equals(this.params, beleidigungen.params) &&
        Objects.equals(this.items, beleidigungen.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, params, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Beleidigungen {\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

