package ru.azamat.testwebriotapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.azamat.testwebriotapi.entity.UserAccount;
import ru.azamat.testwebriotapi.service.GameService;
import ru.azamat.testwebriotapi.service.UserAccountService;

@Controller
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private GameService gameService;

  @GetMapping("/lolinfo")
  public String getUserAccountNickname(Model model) {
    UserAccount userAccount = new UserAccount();
    model.addAttribute("userAccount", userAccount);
    List<String> listGame = gameService.getAllNames();
    model.addAttribute("listGame", listGame);
    return "lolinfo";
  }

  @PostMapping("/lolinfo")
  public String addNewUserAccount(@ModelAttribute("userAccount") UserAccount userAccount) {
    userAccountService.getUserAccountFromGameApiByName(userAccount.getGameName(), userAccount.getName());
    return "redirect:/test";
  }
}
