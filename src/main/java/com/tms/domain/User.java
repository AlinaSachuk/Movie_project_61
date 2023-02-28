package com.tms.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[A-Z]*}")
    private String login;

    @Size(min = 5, max = 10)
    private String password;
    private Date created;
    private Date changed;

    @Email
    private String email;

    private String telephoneNumber;
    private boolean isDeleted;
    private Date birthdate;

}
