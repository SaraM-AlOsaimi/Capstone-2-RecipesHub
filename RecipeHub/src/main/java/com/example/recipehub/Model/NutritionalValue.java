package com.example.recipehub.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalValue {

    @PositiveOrZero(message = "Calories must be zero or positive")
    @Column(columnDefinition = "double default 0")
    private Double calories;

    @PositiveOrZero(message = "Fat must be zero or positive")
    @Column(columnDefinition = "double default 0")
    private Double fat;

    @PositiveOrZero(message = "Carbs must be zero or positive")
    @Column(columnDefinition = "double default 0")
    private Double carbs;

    @PositiveOrZero(message = "Protein must be zero or positive")
    @Column(columnDefinition = "double default 0")
    private Double protein;

}
