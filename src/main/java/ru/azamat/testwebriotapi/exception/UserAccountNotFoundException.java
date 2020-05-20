package ru.azamat.testwebriotapi.exception;

public class UserAccountNotFoundException extends RuntimeException {
  public UserAccountNotFoundException(String name) {
    super("Could not find user_account '" + name + "'.");
  }
}
