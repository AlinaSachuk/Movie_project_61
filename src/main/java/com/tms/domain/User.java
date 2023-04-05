package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tms.annotation.FirstCharacter8;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_table")
@ToString(exclude = {"subscription", "movieList", "commentList"})
@EqualsAndHashCode(exclude = {"subscription", "movieList", "commentList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_table_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    @Pattern(regexp = "[A-Z]*")
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

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_id", referencedColumnName = "id")
    private Subscription subscription;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "l_user_movie",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private Set<Movie> movieList = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Comment> commentList = new HashSet<>();
}