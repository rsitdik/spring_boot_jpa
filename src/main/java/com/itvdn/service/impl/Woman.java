package com.itvdn.service.impl;

import com.itvdn.service.User;
import org.springframework.stereotype.Service;

//@Getter
//@Setter
//@ToString
//@Data
@Service
public class Woman implements User {
    private String name;
    private String surname;
    private Integer years;

    public Woman() {
    }

    public Woman(String name) {
        this.name = name;
    }

    public Woman(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Woman(String name, String surname, Integer years) {
        this.name = name;
        this.surname = surname;
        this.years = years;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public void setSurname(String surname) {

    }

    @Override
    public Integer getYears() {
        return null;
    }

    @Override
    public void setYears(Integer years) {

    }
}
