package com.example.animeapi.repository;

import com.example.animeapi.domain.model.Favorites;
import com.example.animeapi.domain.model.projection.ProjectionAnimeSimple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorites, UUID> {
    <T> T findByUserid(UUID id, Class<T> type);
}
