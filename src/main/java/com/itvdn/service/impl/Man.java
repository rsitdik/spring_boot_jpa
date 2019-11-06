package com.itvdn.service.impl;

import com.itvdn.service.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Man implements User {
    private String name;
    private String surname;
    private Integer years;
    private List<User> userList;

    public Man() {
    }

    public Man(String name) {
        this.name = name;
    }

    public Man(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Man(String name, String surname, Integer years) {
        this.name = name;
        this.surname = surname;
        this.years = years;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Integer getYears() {
        return years;
    }

    @Override
    public void setYears(Integer years) {
        this.years = years;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
