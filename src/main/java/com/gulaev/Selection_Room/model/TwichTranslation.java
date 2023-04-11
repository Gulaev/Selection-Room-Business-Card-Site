package com.gulaev.Selection_Room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "twich")
public class TwichTranslation {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  private final String url = "https://www.twitch.tv/selectionroom";

  @Column(name = "name")
  private String translationName;

  @Column(name = "descriptiopn")
  private String translationDescription;
}
