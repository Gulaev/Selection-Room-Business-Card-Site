package com.gulaev.Selection_Room.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "youtube_video")
public class YoutubeVideo {

  public YoutubeVideo(String videoUrl, String title, String description) {
    this.videoUrl = videoUrl;
    this.title = title;
    this.description = description;
  }

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  @Column(name="video_url")
  private String videoUrl;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  public YoutubeVideo() {
  }
}
