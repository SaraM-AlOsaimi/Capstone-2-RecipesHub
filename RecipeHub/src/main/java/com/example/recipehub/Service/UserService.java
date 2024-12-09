package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Model.User;
import com.example.recipehub.Model.UserHealth;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserHealthRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserHealthRepository userHealthRepository;
    private final RecipeRepository recipeRepository;

    // CRUD

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public void addUser(User user){
       user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void updateUser(Integer id , User user){
       User oldUser = userRepository.findUserById(id);
       if(oldUser==null){
           throw new ApiException("User not found");
       }
       oldUser.setName(user.getName());
       oldUser.setUsername(user.getUsername());
       oldUser.setEmail(user.getEmail());
       oldUser.setPassword(user.getPassword());
       userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user1 = userRepository.findUserById(id);
        if(user1 == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user1);
    }

    //---------------------

    // find Users by created-at after
    public List<User> findUserByCreatedDateAfter(LocalDateTime dateTime){
       List<User> users = userRepository.findUserByCreatedAtAfter(dateTime);
       if(users==null){
           throw new ApiException("No users found");
       }
       return users;
    }

    // find users created account between two dates
    public List<User> findUserByCreatedBetween(LocalDateTime firstDate,LocalDateTime secDate ){
       List<User> users = userRepository.findUserByCreatedAtBetween(firstDate,secDate);
        if(users==null){
            throw new ApiException("No users found");
        }
        return users;
    }

    // Endpoint 11 :
    // user add balance to his account
    public void useraddBalance(Integer id,Double amount){
     User user = userRepository.findUserById(id);
     if(user==null){
         throw new ApiException("User not found");
     }
     if(amount <= 0){
        throw new ApiException("Invalid amount");
     }
     user.setBalance(user.getBalance() + amount);
     userRepository.save(user);
    }

}
