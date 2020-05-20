package ru.azamat.testwebriotapi.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.azamat.testwebriotapi.entity.Game;
import ru.azamat.testwebriotapi.repository.GameRepository;

@Service
@AllArgsConstructor
public class GameService {

  @Autowired
  private final GameRepository gameRepository;

  public List<String> getAllNames() {
    List<String> gameNames = new ArrayList<>();
    for (Game game : gameRepository.findAll()) {
      gameNames.add(game.getName());
    }
    return gameNames;
  }
}
