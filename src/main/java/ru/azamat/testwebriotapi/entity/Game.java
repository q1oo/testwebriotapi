package ru.azamat.testwebriotapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "games")
public class Game {
  @Id
  private int id;
  private String name;
  private String apiAccessKey;
}
