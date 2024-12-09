package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.UserHealth;
import com.example.recipehub.Service.UserHealthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes-hub/user-health")
@RequiredArgsConstructor
public class UserHealthController {

    private final UserHealthService userHealthService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsersHealth(){
        return ResponseEntity.status(200).body(userHealthService.getAllUsersHealth());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUserHealth(@RequestBody @Valid UserHealth userHealth, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userHealthService.addUserHealth(userHealth);
        return ResponseEntity.status(200).body(new ApiResponse("User Health added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserHealth(@PathVariable Integer id,@RequestBody @Valid UserHealth userHealth, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userHealthService.updateUserHealth(id,userHealth);
        return ResponseEntity.status(200).body(new ApiResponse("User Health Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserHealth(@PathVariable Integer id){
        userHealthService.deleteUserHealth(id);
        return ResponseEntity.status(200).body(new ApiResponse("User health deleted"));
    }

    //-----------------------

    @GetMapping("/get-users-have/{diabetes}")
    public ResponseEntity<?> getUserThatHasDiabetes(@PathVariable Boolean diabetes){
        return ResponseEntity.status(200).body(userHealthService.getUserThatHasDiabetes(diabetes));
    }

}
