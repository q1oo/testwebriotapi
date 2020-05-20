package ru.azamat.testwebriotapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.azamat.testwebriotapi.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
  Game findOneByName(String name);
}
