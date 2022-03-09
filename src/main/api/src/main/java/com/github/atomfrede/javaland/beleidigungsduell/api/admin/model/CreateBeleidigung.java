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
 * CreateBeleidigung
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-09T09:08:42.583667+01:00[Europe/Berlin]")
public class CreateBeleidigung   {

  @JsonProperty("beleidigungs_template")
  private String beleidigungsTemplate;

  @JsonProperty("antwort_template")
  private String antwortTemplate;

  public CreateBeleidigung beleidigungsTemplate(String beleidigungsTemplate) {
    this.beleidigungsTemplate = beleidigungsTemplate;
    return this;
  }

  /**
   * Beleidigender Satz, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. 
   * @return beleidigungsTemplate
  */
  @NotNull 
  @Schema(name = "beleidigungs_template", example = "Du kämpfst wie ein dummer %s!", description = "Beleidigender Satz, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. ", required = true)
  public String getBeleidigungsTemplate() {
    return beleidigungsTemplate;
  }

  public void setBeleidigungsTemplate(String beleidigungsTemplate) {
    this.beleidigungsTemplate = beleidigungsTemplate;
  }

  public CreateBeleidigung antwortTemplate(String antwortTemplate) {
    this.antwortTemplate = antwortTemplate;
    return this;
  }

  /**
   * Richtige Antwort auf die Beleidigung, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. 
   * @return antwortTemplate
  */
  @NotNull 
  @Schema(name = "antwort_template", example = "Wie passend. Du kämpfst wie eine %s.", description = "Richtige Antwort auf die Beleidigung, wo bei `%s` als platzhalter für eine zufällige Beleidigung verwendet werden kann. ", required = true)
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
    CreateBeleidigung createBeleidigung = (CreateBeleidigung) o;
    return Objects.equals(this.beleidigungsTemplate, createBeleidigung.beleidigungsTemplate) &&
        Objects.equals(this.antwortTemplate, createBeleidigung.antwortTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beleidigungsTemplate, antwortTemplate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateBeleidigung {\n");
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

