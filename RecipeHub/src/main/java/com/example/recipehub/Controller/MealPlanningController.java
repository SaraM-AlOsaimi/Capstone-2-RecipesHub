package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.MealPlanning;
import com.example.recipehub.Service.MealPlanningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe-hub/meal-planning")
@RequiredArgsConstructor
public class MealPlanningController {

    private final MealPlanningService mealPlanningService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllMealsPlanning(){
        return ResponseEntity.status(200).body(mealPlanningService.getAllMealsPlanning());
    }

    ;
    // 9- Endpoint :
    @PostMapping("/mealsPlan")
    public ResponseEntity<?> addMealPlan(@RequestBody MealPlanning mealPlanning){
        mealPlanningService.addMealPlan(mealPlanning);
        return ResponseEntity.status(200).body(new ApiResponse("Plan added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMealPlan(@PathVariable Integer id,@RequestBody MealPlanning mealPlanning){
        mealPlanningService.updateMealPlan(id,mealPlanning);
        return ResponseEntity.status(200).body(new ApiResponse("Plan Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMealPlan(@PathVariable Integer id){
        mealPlanningService.deleteMealPlan(id);
        return ResponseEntity.status(200).body(new ApiResponse("Plan deleted"));
    }

    // 10- Endpoint :
    @PostMapping("/mealsPlan/renew/{id}/{newStartDate}/{newEndDate}")
    public ResponseEntity<?> renewMealPlan(@PathVariable Integer id, @PathVariable LocalDate newStartDate, @PathVariable LocalDate newEndDate) {
            mealPlanningService.renewMealPlan(id, newStartDate, newEndDate);
            return ResponseEntity.status(200).body(new ApiResponse("Meal plan renewed successfully"));
        }


        @GetMapping("/get/{userId}")
        public ResponseEntity<?> getMealPlansByUserId(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(mealPlanningService.getMealPlansByUserId(userId));
        }

}

