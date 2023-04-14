package com.tms.domain.DTO;

import com.tms.annotation.FirstCharacter8;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;


@Data
@AllArgsConstructor
public class UserRegistrationDto {
    private String firstName;

    private String lastName;

    @Pattern(regexp = "[A-Z]*")
    private String login;

    @Size(min = 5, max = 10)
    private String password;

    @Email
    private String email;

    private Date birthdate;

    @FirstCharacter8
    private String telephoneNumber;
}
