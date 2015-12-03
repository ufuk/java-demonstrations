package com.ufukuzun.javaprofiler.demo;

public class UserService {

    private UserDao userDao = new UserDao();

    public void saveNewUser(String username) {
        if (userDao.countByUsername(username) > 0) {
            return;
        }

        userDao.saveNew(username);
    }

}
