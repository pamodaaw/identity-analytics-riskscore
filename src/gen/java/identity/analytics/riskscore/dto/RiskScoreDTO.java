package identity.analytics.riskscore.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class RiskScoreDTO  {
  
  
  @NotNull
  private String eventID = null;
  
  @NotNull
  private Integer score = null;

  public RiskScoreDTO(String eventID, Integer score) {
    this.eventID = eventID;
    this.score = score;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("eventID")
  public String getEventID() {
    return eventID;
  }
  public void setEventID(String eventID) {
    this.eventID = eventID;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("score")
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RiskScoreDTO {\n");
    
    sb.append("  eventID: ").append(eventID).append("\n");
    sb.append("  score: ").append(score).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
