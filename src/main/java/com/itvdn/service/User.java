package com.itvdn.service;

import java.util.List;

public interface User {
    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    Integer getYears();

    void setYears(Integer years);

    List<User> getUserList();

    void setUserList(List<User> userList);
}
