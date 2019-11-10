package com.itvdn.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String position;
    private String phone;
    private String login;
    private String password;
    @Column(name = "role_id")
    private Integer rolId;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public Employee(String name, String position, String phone) {
        this.name = name;
        this.position = position;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return String.join(", ", new String[]{
                "Employee №: ", String.valueOf(id), name, position, phone
        });
    }
}
