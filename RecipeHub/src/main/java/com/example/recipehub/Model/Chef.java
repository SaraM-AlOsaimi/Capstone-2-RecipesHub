package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is empty")
    @Size(min = 3, message = "name length should be more than 2 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String name ;

    @NotEmpty(message = "username is empty")
    @Size(min = 6, message = "username length should be more than 5 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String username;

    @NotEmpty(message = "password is empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$" , message = "Your password must be between 8 and 16 characters long,contain at least one lowercase letter, one uppercase letter, and one number.")
    @Size(min = 8,max = 16 , message = "Password must be between 8 and 16 characters long.")
    @Column(columnDefinition = "varchar(16) not null")
    private String password;

    @NotBlank(message = "Email is blank")
    @Email(message = "Enter a valid email format")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @PastOrPresent(message = "created_at must not be in the future")
    @Column(columnDefinition = "TIMESTAMP" , updatable = false)
    private LocalDateTime createdAt;

    @NotEmpty(message = "role is empty")
    @Pattern(regexp = "^chef$" , message = "role must be chef")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotEmpty(message = "profile is empty")
    @Size(max = 100 , message = "profile max length is 100 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String profile;

    // so if user buy recipes from him get the money in his balance
    private Double balance;

}
