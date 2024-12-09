package com.example.recipehub.Repository;
import com.example.recipehub.Model.User;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    User findUserByEmail(String email);

    List<User> findUserByCreatedAtAfter(LocalDateTime created_at);

    List<User> findUserByCreatedAtBetween(LocalDateTime createdAtAfter,LocalDateTime createdAtBefore);

}
