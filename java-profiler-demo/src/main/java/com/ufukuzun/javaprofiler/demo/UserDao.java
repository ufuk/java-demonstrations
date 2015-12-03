package com.ufukuzun.javaprofiler.demo;

public class UserDao {

    public int countByUsername(String username) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return 0;
    }

    public void saveNew(String username) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

}
