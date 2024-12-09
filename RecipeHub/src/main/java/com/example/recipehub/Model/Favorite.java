package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User id is null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Recipe id is null")
    @Column(columnDefinition = "int not null")
    private Integer recipeId;

}
