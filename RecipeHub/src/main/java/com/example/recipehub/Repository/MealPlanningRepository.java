package com.example.recipehub.Repository;

import com.example.recipehub.Model.MealPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MealPlanningRepository extends JpaRepository<MealPlanning,Integer> {

    MealPlanning findMealPlanningById(Integer id);

}
