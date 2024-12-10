package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Chef;
import com.example.recipehub.Service.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addChef(@RequestBody Chef chef){
        chefService.addChef(chef);
        return ResponseEntity.status(200).body(new ApiResponse("Chef added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChef(@PathVariable Integer id,@RequestBody Chef chef){
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



}
