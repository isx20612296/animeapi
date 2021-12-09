package com.example.animeapi.repository;

import com.example.animeapi.domain.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID>{
    @Query("select name from Anime")
    List<String> getAnimes();

    Anime findByName(String name);
}