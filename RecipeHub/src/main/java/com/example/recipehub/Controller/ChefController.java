package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Chef;
import com.example.recipehub.Service.ChefService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes-hub/chefs")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;


    //----CRUD
    @GetMapping("/get")
    public ResponseEntity<?> getAllChefs(){
        return ResponseEntity.status(200).body(chefService.getAllChefs());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addChef(@RequestBody @Valid Chef chef, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        chefService.addChef(chef);
        return ResponseEntity.status(200).body(new ApiResponse("Chef added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChef(@PathVariable Integer id,@RequestBody @Valid Chef chef, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        chefService.updateChef(id, chef);
        return ResponseEntity.status(200).body(new ApiResponse("Chef Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChef(@PathVariable Integer id){
        chefService.deleteChef(id);
        return ResponseEntity.status(200).body(new ApiResponse("Chef Deleted"));
    }
    //---------------------------------

    // 1-Endpoint :
    // Endpoint to get Chefs who have balance above (pathVariable double balance)
    @GetMapping("/get-chefs-by/{balance}")
    public ResponseEntity<?> getChefsByBalanceAbove(@PathVariable Double balance){
        return ResponseEntity.status(200).body(chefService.getChefsByBalanceAbove(balance));
    }

    // 4- Endpoint :
    // chef do discount 50% on one of his recipes if the quantity 3 or less
    @PutMapping("/apply-discount/chef-id/{id}")
    public ResponseEntity<?> applyDiscountForLowQuantity(@PathVariable Integer id){
        chefService.applyDiscountForLowQuantity(id);
        return ResponseEntity.status(200).body(new ApiResponse("Discount applied"));
    }

    // 15- Endpoint:
    @PostMapping("/chef/{chefId}/recipe/{recipeId}/sold-out")
    public ResponseEntity<String> markRecipeAsSoldOut(@PathVariable Integer chefId, @PathVariable Integer recipeId) {
           chefService.markRecipeAsSoldOut(chefId, recipeId);
            return ResponseEntity.status(200).body("Marked as Sold out");
    }

}
