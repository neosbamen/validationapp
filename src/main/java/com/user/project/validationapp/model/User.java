package com.user.project.validationapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be blank")
    private String name;
    @Email(message = "not a valid email address")
    private String email;
    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    private String password;
}
