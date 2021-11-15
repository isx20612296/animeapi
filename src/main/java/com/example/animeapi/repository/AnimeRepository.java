package com.example.animeapi.repository;

import com.example.animeapi.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID>{

}
