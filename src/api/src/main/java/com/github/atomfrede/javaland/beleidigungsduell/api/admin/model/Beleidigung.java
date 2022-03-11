package com.github.atomfrede.javaland.beleidigungsduell.api.admin.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Representation of a group containing all available information.
 */

@Schema(name = "Beleidigung", description = "Representation of a group containing all available information.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-11T16:58:44.288980+01:00[Europe/Berlin]")
public class Beleidigung   {

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("beleidigungs_id")
  private UUID beleidigungsId;

  @JsonProperty("antwort_id")
  private UUID antwortId;

  @JsonProperty("beleidigungs_template")
  private String beleidigungsTemplate;

  @JsonProperty("antwort_template")
  private String antwortTemplate;

  public Beleidigung id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 0
   * @return id
  */
  @Valid @DecimalMin("0") 
  @Schema(name = "id", required = false)
  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Beleidigung beleidigungsId(UUID beleidigungsId) {
    this.beleidigungsId = beleidigungsId;
    return this;
  }

  /**
   * Get beleidigungsId
   * @return beleidigungsId
  */
  @Valid 
  @Schema(name = "beleidigungs_id", required = false)
  public UUID getBeleidigungsId() {
    return beleidigungsId;
  }

  public void setBeleidigungsId(UUID beleidigungsId) {
    this.beleidigungsId = beleidigungsId;
  }

  public Beleidigung antwortId(UUID antwortId) {
    this.antwortId = antwortId;
    return this;
  }

  /**
   * Get antwortId
   * @return antwortId
  */
  @Valid 
  @Schema(name = "antwort_id", required = false)
  public UUID getAntwortId() {
    return antwortId;
  }

  public void setAntwortId(UUID antwortId) {
    this.antwortId = antwortId;
  }

  public Beleidigung beleidigungsTemplate(String beleidigungsTemplate) {
    this.beleidigungsTemplate = beleidigungsTemplate;
    return this;
  }

  /**
   * Beleidigender Satz, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. 
   * @return beleidigungsTemplate
  */
  
  @Schema(name = "beleidigungs_template", example = "Du kämpfst wie ein dummer %s!", description = "Beleidigender Satz, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. ", required = false)
  public String getBeleidigungsTemplate() {
    return beleidigungsTemplate;
  }

  public void setBeleidigungsTemplate(String beleidigungsTemplate) {
    this.beleidigungsTemplate = beleidigungsTemplate;
  }

  public Beleidigung antwortTemplate(String antwortTemplate) {
    this.antwortTemplate = antwortTemplate;
    return this;
  }

  /**
   * Richtige Antwort auf die Beleidigung, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. 
   * @return antwortTemplate
  */
  
  @Schema(name = "antwort_template", example = "Wie passend. Du kämpfst wie eine %s.", description = "Richtige Antwort auf die Beleidigung, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. ", required = false)
  public String getAntwortTemplate() {
    return antwortTemplate;
  }

  public void setAntwortTemplate(String antwortTemplate) {
    this.antwortTemplate = antwortTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Beleidigung beleidigung = (Beleidigung) o;
    return Objects.equals(this.id, beleidigung.id) &&
        Objects.equals(this.beleidigungsId, beleidigung.beleidigungsId) &&
        Objects.equals(this.antwortId, beleidigung.antwortId) &&
        Objects.equals(this.beleidigungsTemplate, beleidigung.beleidigungsTemplate) &&
        Objects.equals(this.antwortTemplate, beleidigung.antwortTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, beleidigungsId, antwortId, beleidigungsTemplate, antwortTemplate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Beleidigung {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    beleidigungsId: ").append(toIndentedString(beleidigungsId)).append("\n");
    sb.append("    antwortId: ").append(toIndentedString(antwortId)).append("\n");
    sb.append("    beleidigungsTemplate: ").append(toIndentedString(beleidigungsTemplate)).append("\n");
    sb.append("    antwortTemplate: ").append(toIndentedString(antwortTemplate)).append("\n");
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

