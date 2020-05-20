package ru.azamat.testwebriotapi.exception;

public class UserAccountAlreadyExistException extends RuntimeException {
  public UserAccountAlreadyExistException(String name) {
    super("User_account '" + name + "' already exists." );
  }
}
