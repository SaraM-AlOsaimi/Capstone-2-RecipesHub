package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.SeasonalIngredient;
import com.example.recipehub.Repository.SeasonalIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonalIngredientService {

    private final SeasonalIngredientRepository seasonalIngredientRepository;


    public List<SeasonalIngredient> getAllSeasonalIngredients(){
        return seasonalIngredientRepository.findAll();
    }

    public void addSeasonalIngredient(SeasonalIngredient seasonalIngredient){
        seasonalIngredientRepository.save(seasonalIngredient);
    }

    public void updateSeasonalIngredient(Integer id,SeasonalIngredient seasonalIngredient){
        SeasonalIngredient oldSeasonalIngredient = seasonalIngredientRepository.findSeasonalIngredientById(id);
        if(oldSeasonalIngredient==null){
            throw new ApiException("SeasonalIngredient not found");
        }
        oldSeasonalIngredient.setName(seasonalIngredient.getName());
        seasonalIngredientRepository.save(oldSeasonalIngredient);
    }

    public void deleteSeasonalIngredient(Integer id){
        SeasonalIngredient seasonalIngredient = seasonalIngredientRepository.findSeasonalIngredientById(id);
        if(seasonalIngredient==null){
            throw new ApiException("SeasonalIngredient not found");
        }
        seasonalIngredientRepository.delete(seasonalIngredient);
    }

}
