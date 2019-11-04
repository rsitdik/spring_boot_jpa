package com.itvdn.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    public String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public static String makeFriends(User user1, User user2) {
        return String.format("%s and %s are friends!", user1.getName(), user2.getName());
    }

    public static void getUserName() {
        throw new UnsupportedOperationException("No implementation!!!");
    }

    public static void waitInQueue() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
