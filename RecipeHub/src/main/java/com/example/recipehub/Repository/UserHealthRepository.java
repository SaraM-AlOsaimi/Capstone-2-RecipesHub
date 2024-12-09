package com.example.recipehub.Repository;

import com.example.recipehub.Model.UserHealth;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHealthRepository extends JpaRepository<UserHealth,Integer> {

    UserHealth findUserHealthById(Integer id);

    @Query("select u from UserHealth u where u.hasDiabetes=true")
    List<UserHealth> getUserHealthByHasDiabetes(Boolean hasDiabetes);


}
