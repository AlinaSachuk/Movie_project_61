package com.tms.domain;

import com.tms.annotation.FirstCharacter8;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_table_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    @Pattern(regexp = "[A-Z]*}")
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 10)
    private String password;

    @Column(name = "created")
    private Date created;

    @Column(name = "changed")
    private Date changed;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "telephone")
    @FirstCharacter8
    private String telephoneNumber;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "birthday_date")
    private Date birthdate;
}