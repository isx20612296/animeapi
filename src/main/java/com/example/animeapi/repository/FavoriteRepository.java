package com.example.animeapi.repository;

import com.example.animeapi.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    //<T> List<T> findByUserid(UUID id, Class<T> type);
    List<Favorite> findByUserid(UUID id);
}
