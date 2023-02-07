package com.tms.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date created;
    private Date changed;
    private String email;
    private String telephoneNumber;
    private boolean isDeleted;
    private Date birthdate;

}
