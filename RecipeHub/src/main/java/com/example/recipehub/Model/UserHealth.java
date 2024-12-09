package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "height cannot be null")
    @Positive(message = "Height must be positive")
    @Column(columnDefinition = "double not null")
    private Double height; // In cm

    @NotNull(message = "weight cannot be null")
    @Positive(message = "Weight must be positive")
    @Column(columnDefinition = "double not null")
    private Double weight; // In kg

    @NotNull(message = "age cannot be null")
    @PositiveOrZero(message = "Age must be positive or zero")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "gender is empty")
    @Pattern(regexp = "^male|female$" , message = "Gender is only meal or female")
    @Column(columnDefinition = "varchar(6) not null")
    private String gender;

    // Common health conditions
    @NotNull(message = "Diabetes status cannot be null")
    @Column(columnDefinition = "boolean default false")
    private Boolean hasDiabetes;

    @NotNull(message = "Hypertension status cannot be null")
    @Column(columnDefinition = "boolean default false")
    private Boolean hasHypertension;

    @NotNull(message = "Heart disease status cannot be null")
    @Column(columnDefinition = "boolean default false")
    private Boolean hasHeartDisease;

    @NotNull(message = "Asthma status cannot be null")
    @Column(columnDefinition = "boolean default false")
    private Boolean hasAsthma;

    @NotEmpty(message = "Food allergies cannot be null")
    @Column(columnDefinition = "text not null")
    private String foodAllergies;

    @NotEmpty(message = "Other health conditions cannot be null")
    @Column(columnDefinition = "text not null")
    private String otherHealthConditions;

}
