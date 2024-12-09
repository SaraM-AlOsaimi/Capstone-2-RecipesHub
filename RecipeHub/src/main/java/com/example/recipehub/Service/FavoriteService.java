package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Favorite;
import com.example.recipehub.Repository.FavoriteRepository;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public List<Favorite> getAllFavorite(){
        return favoriteRepository.findAll();
    }

    public void addFavorite(Favorite favorite){
        if(!userRepository.existsById(favorite.getUserId())){
            throw new ApiException("User not found");
        }
        if(!recipeRepository.existsById(favorite.getRecipeId())){
            throw new ApiException("Recipe not found");
        }
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Integer id){
        Favorite favorite = favoriteRepository.findFavoriteById(id);
        if (favorite==null){
            throw new ApiException("Favorite not found");
        }
        favoriteRepository.delete(favorite);
    }
    //------------------------------

    // 3-Endpoint :
    // get list of Favorites for one user by UserId
    public List<Favorite> getFavoritesByUserId(Integer userId){
        List<Favorite> favorites = favoriteRepository.getFavoritesByUserId(userId);
        if (favorites==null){
            throw new ApiException("No Favorites for User");
        }
        return favorites;
    }

}
