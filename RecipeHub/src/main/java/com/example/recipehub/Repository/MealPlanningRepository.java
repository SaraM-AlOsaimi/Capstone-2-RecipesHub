package com.example.recipehub.Repository;

import com.example.recipehub.Model.MealPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealPlanningRepository extends JpaRepository<MealPlanning,Integer> {

    MealPlanning findMealPlanningById(Integer id);

    @Query("select m from MealPlanning m where m.userId=?1")
    List<MealPlanning> findMealPlansByUserId(Integer userId);

    List<MealPlanning> findMealPlansByPlanStartDateBetween(LocalDate startDate, LocalDate endDate);
}
