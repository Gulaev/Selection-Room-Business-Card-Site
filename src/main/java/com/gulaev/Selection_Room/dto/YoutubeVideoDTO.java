package com.gulaev.Selection_Room.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeVideoDTO {

  @JsonProperty(value = "id")
  private String id;

  @JsonProperty(value = "channelId")
  private String channelId;

  @JsonProperty(value = "title")
  private String title;

  @JsonProperty(value = "description")
  private String description;

  @JsonProperty(value = "publishedAt")
  private String publishedAt;
}
