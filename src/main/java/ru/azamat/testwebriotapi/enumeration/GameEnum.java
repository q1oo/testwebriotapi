package ru.azamat.testwebriotapi.enumeration;

public enum GameEnum {
  LEAGUE_OF_LEGENDS ("League Of Legends"),
  DOTA2 ("Dota2"),
  FORTNITE ("Fortnite")

  ;


  private final String name;

  GameEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
