package services.impl;

import data.entities.User;
import exceptions.UserNameAlreadyTakenException;
import exceptions.UserNotPresentException;
import services.UserService;

import java.util.HashMap;

public class UserServiceImpl implements UserService {

    private final HashMap<String, User> users;

    public UserServiceImpl() {
        users = new HashMap<>();
    }

    @Override
    public void register(String userName) throws UserNameAlreadyTakenException {
        if (users.get(userName) == null) {
            synchronized (this) {
                if (users.get(userName) != null) {
                    throw new UserNameAlreadyTakenException();
                }
                User user = new User();
                user.setUsername(userName);
                users.put(userName, user);
            }
        }
        else {
            throw new UserNameAlreadyTakenException();

        }
    }

    @Override
    public boolean login(String username) throws UserNotPresentException {
        if (users.get(username) != null) {
            return true;
        }

        throw new UserNotPresentException();
    }
}
