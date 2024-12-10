package com.example.recipehub.Controller;


import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.SeasonalIngredient;
import com.example.recipehub.Service.SeasonalIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes-hub/seasonal-ingredient")
@RequiredArgsConstructor
public class SeasonalIngredientController {

    private final SeasonalIngredientService seasonalIngredientService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllSeasonalIngredients(){
        return ResponseEntity.status(200).body(seasonalIngredientService.getAllSeasonalIngredients());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSeasonalIngredient(@RequestBody SeasonalIngredient seasonalIngredient ){
        seasonalIngredientService.addSeasonalIngredient(seasonalIngredient);
       return ResponseEntity.status(200).body(new ApiResponse("Seasonal Ingredient added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSeasonalIngredient(@PathVariable Integer id,@RequestBody SeasonalIngredient seasonalIngredient){
        seasonalIngredientService.updateSeasonalIngredient(id,seasonalIngredient);
        return ResponseEntity.status(200).body(new ApiResponse("Seasonal Ingredient updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSeasonalIngredient(@PathVariable Integer id){
        seasonalIngredientService.deleteSeasonalIngredient(id);
        return ResponseEntity.status(200).body(new ApiResponse("Seasonal Ingredient deleted"));
    }

}
