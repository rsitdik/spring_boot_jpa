package com.itvdn.model;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String name;
    private String surname;
    private Integer years;

    public User() {
    }

    public User(String name, String surname, Integer years) {
        this.name = name;
        this.surname = surname;
        this.years = years;
    }

    public static String makeFriends(User user1, User user2) {
        return String.format("%s and %s are friends!", user1.getName(), user2.getName());
    }

    public static String getUserName() {
        throw new UnsupportedOperationException("No implementation!!!");
    }

    public static void waitInQueue() {
        try {
            Thread.sleep(30L);
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}
