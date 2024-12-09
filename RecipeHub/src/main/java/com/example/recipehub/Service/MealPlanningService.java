package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.MealPlanning;
import com.example.recipehub.Repository.MealPlanningRepository;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanningService {

    private final MealPlanningRepository mealPlanningRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public List<MealPlanning> getAllMealsPlanning(){
        return mealPlanningRepository.findAll();
    }

    // 9- Endpoint :
    public void addMealPlan(MealPlanning mealPlanning) {
            if (!userRepository.existsById(mealPlanning.getUserId())) {
                throw new ApiException("User not found");
            }
            if (!recipeRepository.existsById(mealPlanning.getRecipeId())) {
                throw new ApiException("Recipe not found");
            }
        LocalDate planStartDate = mealPlanning.getPlanStartDate();
        LocalDate planEndDate = mealPlanning.getPlanEndDate();

            mealPlanning.setPlanStartDate(planStartDate);
            mealPlanning.setPlanEndDate(planEndDate);
            mealPlanningRepository.save(mealPlanning);
    }

    public void updateMealPlan(Integer id,MealPlanning mealPlanning){
        MealPlanning oldMealPlan = mealPlanningRepository.findMealPlanningById(id);
        if(oldMealPlan==null){
            throw new ApiException("Plan not found");
        }
        oldMealPlan.setMealType(mealPlanning.getMealType());
        oldMealPlan.setPlanStartDate(mealPlanning.getPlanStartDate());
        oldMealPlan.setPlanEndDate(mealPlanning.getPlanEndDate());
        oldMealPlan.setPortions(mealPlanning.getPortions());
        mealPlanningRepository.save(oldMealPlan);
    }

    public void deleteMealPlan(Integer id){
        MealPlanning mealPlan = mealPlanningRepository.findMealPlanningById(id);
        if(mealPlan==null){
            throw new ApiException("Plan not found");
        }
        mealPlanningRepository.delete(mealPlan);
    }

    // 10- Endpoint :
    public void renewMealPlan(Integer id, LocalDate newStartDate, LocalDate newEndDate) {
        MealPlanning oldMealPlan = mealPlanningRepository.findMealPlanningById(id);
        if (oldMealPlan == null) {
            throw new ApiException("Plan not found");
        }
        if (newStartDate.isBefore(LocalDate.now())) {
            throw new ApiException("The new start date must be after today's date");
        }
        MealPlanning renewedMealPlan = new MealPlanning();
        renewedMealPlan.setRecipeId(oldMealPlan.getRecipeId());
        renewedMealPlan.setMealType(oldMealPlan.getMealType());
        renewedMealPlan.setPortions(oldMealPlan.getPortions());
        renewedMealPlan.setPlanStartDate(newStartDate);
        renewedMealPlan.setPlanEndDate(newEndDate);
        renewedMealPlan.setUserId(oldMealPlan.getUserId());
        renewedMealPlan.setMealTime(oldMealPlan.getMealTime());
        mealPlanningRepository.save(renewedMealPlan);
    }


}
