package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.time.LocalTime;

@Check(constraints = "meal_type='Breakfast' or meal_type='Lunch' or meal_type='Dinner' or meal_type='Snack'")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealPlanning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User ID is empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Recipe ID is empty")
    @Column(columnDefinition = "int not null")
    private Integer recipeId;

    @NotNull(message = "Meal start date is empty")
    @FutureOrPresent(message = "Meal start date must not be in the past")
    private LocalDate planStartDate;

    @NotNull(message = "Meal end date is empty")
    @FutureOrPresent(message = "Meal end date must not be in the past")
    private LocalDate planEndDate;

    @NotNull(message = "Meal time is empty")
    private LocalTime mealTime;

    @NotEmpty(message = "Meal type is empty")
    @Pattern(regexp = "Breakfast|Lunch|Dinner|Snack",
            message = "Meal type must be one of: Breakfast, Lunch, Dinner, Snack")
    @Column(columnDefinition = "varchar(15) not null")
    private String mealType;

    @NotNull(message = "Portions are empty")
    @Positive(message = "Portions must be a positive number")
    private Integer portions;

}
