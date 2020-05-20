package ru.azamat.testwebriotapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.azamat.testwebriotapi.entity.UserAccount;
import ru.azamat.testwebriotapi.entity.UserLeagueOfLegendsAccount;

public interface UserAccountRepository extends JpaRepository <UserAccount, String> {

  UserLeagueOfLegendsAccount findOneByGameAccountId(String gameAccountId);
}
