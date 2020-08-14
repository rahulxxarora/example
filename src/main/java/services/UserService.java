package services;

import exceptions.UserNameAlreadyTakenException;
import exceptions.UserNotPresentException;

public interface UserService {

    void register(String userName) throws UserNameAlreadyTakenException;
    boolean login(String username) throws UserNotPresentException;
}
