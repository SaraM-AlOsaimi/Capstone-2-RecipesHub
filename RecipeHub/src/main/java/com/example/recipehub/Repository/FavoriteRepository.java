package com.example.recipehub.Repository;

import com.example.recipehub.Model.Favorite;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    Favorite findFavoriteById(Integer id);

    @Query("select f from Favorite f where f.userId=?1")
    List<Favorite> getFavoritesByUserId(Integer userId);

}
