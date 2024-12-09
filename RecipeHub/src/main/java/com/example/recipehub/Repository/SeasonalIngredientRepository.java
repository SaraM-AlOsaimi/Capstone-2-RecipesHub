package com.example.recipehub.Repository;

import com.example.recipehub.Model.SeasonalIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonalIngredientRepository extends JpaRepository<SeasonalIngredient,Integer> {

    SeasonalIngredient findSeasonalIngredientById(Integer id);
}
