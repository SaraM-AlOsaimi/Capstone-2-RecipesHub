package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Favorite;
import com.example.recipehub.Service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes-hub/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllFavorite(){
      return ResponseEntity.status(200).body(favoriteService.getAllFavorite());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody @Valid Favorite favorite, Errors errors){
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
       favoriteService.addFavorite(favorite);
       return ResponseEntity.status(200).body(new ApiResponse("Favorite added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Integer id){
       favoriteService.deleteFavorite(id);
       return ResponseEntity.status(200).body(new ApiResponse("Favorite deleted"));
    }
    //-----------------------------------------------------------

    // 3-Endpoint :
    // get list of Favorites for one user by UserId
    @GetMapping("/favorites-for-user/{id}")
    public ResponseEntity<?> getFavoritesByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(favoriteService.getFavoritesByUserId(id));
    }

}
