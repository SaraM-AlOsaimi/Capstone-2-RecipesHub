package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.User;
import com.example.recipehub.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/recipes-hub/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
       userService.addUser(user);
       return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id ,@RequestBody User user){
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id ){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    //---------------------------------

    // get Users by Created date
    @GetMapping("/get/users-created-after/{date}")
    public ResponseEntity<?> findUserByCreatedDateAfter(@PathVariable LocalDateTime date){
        return ResponseEntity.status(200).body(userService.findUserByCreatedDateAfter(date));
    }

    // Endpoint 11 :
    // user add balance to his account
    @PutMapping("/add-balance/id/{id}/amount/{amount}")
    public ResponseEntity<?> useraddBalance(@PathVariable Integer id,@PathVariable Double amount){
        userService.useraddBalance(id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Balance added Successfully"));
    }


}
