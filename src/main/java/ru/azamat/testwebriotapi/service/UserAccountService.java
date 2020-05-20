package ru.azamat.testwebriotapi.service;

import static ru.azamat.testwebriotapi.enumeration.GameEnum.LEAGUE_OF_LEGENDS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.azamat.testwebriotapi.entity.UserLeagueOfLegendsAccount;
import ru.azamat.testwebriotapi.entity.UserAccount;
import ru.azamat.testwebriotapi.exception.UserAccountAlreadyExistException;
import ru.azamat.testwebriotapi.exception.UserAccountNotFoundException;
import ru.azamat.testwebriotapi.repository.GameRepository;
import ru.azamat.testwebriotapi.repository.UserAccountRepository;

@Service
@AllArgsConstructor
public class UserAccountService {

  @Autowired
  private final UserAccountRepository repository;
  @Autowired
  private final GameRepository gameRepository;


  private static final String URL_RIOTAPI_GET_BY_NAME = "https://ru.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

  public UserAccount getUserAccountFromGameApiByName(String gameName, String nickname){
    UserAccount userAccount = null;
    switch (gameName) {
      case "League Of Legends":
        userAccount = getLeagueOfLegendsAccountFromGameApiByName(nickname);
        break;
    }
    if (userAccount == null) {
      throw new UserAccountNotFoundException(nickname);
    }
    return userAccount;
  }

  public UserAccount getLeagueOfLegendsAccountFromGameApiByName(String name) {
    UserLeagueOfLegendsAccount userLeagueOfLegendsAccount = null;
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-riot-token", gameRepository.findOneByName("League Of Legends").getApiAccessKey());
    HttpEntity<String> entity = new HttpEntity<>(headers);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(URL_RIOTAPI_GET_BY_NAME + name,
                                                                HttpMethod.GET, entity, String.class);
    String result = response.getBody();
    ObjectMapper mapper = new ObjectMapper();
    try {
      userLeagueOfLegendsAccount = mapper.readValue(result, UserLeagueOfLegendsAccount.class);
      userLeagueOfLegendsAccount.setGameAccountId(userLeagueOfLegendsAccount.getPuuid());
      userLeagueOfLegendsAccount.setGamerLevel(userLeagueOfLegendsAccount.getSummonerLevel());
      if (repository.findOneByGameAccountId(userLeagueOfLegendsAccount.getGameAccountId()) == null) {
        userLeagueOfLegendsAccount.setGameId(gameRepository.findOneByName(LEAGUE_OF_LEGENDS.getName()).getId());
		//ниже код верификации может быть сгенерирован позже во время прохождения верификации
        userLeagueOfLegendsAccount.setVerificationCode(getRandomVerificationCode());
        System.out.println(userLeagueOfLegendsAccount);
        repository.save(userLeagueOfLegendsAccount);
      } else {
        throw new UserAccountAlreadyExistException(userLeagueOfLegendsAccount.getName());
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return userLeagueOfLegendsAccount;
  }

  public String getRandomVerificationCode() {
    int leftLimit = 65;
    int rightLimit = 122;
    int targetStringLength = 10;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int)
          (random.nextFloat() * (rightLimit - leftLimit + 1));
      if (randomLimitedInt <= 90 || randomLimitedInt >= 97) {
        buffer.append((char) randomLimitedInt);
      }
    }
    return buffer.toString();
  }
}
