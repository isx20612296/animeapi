package com.example.animeapi.repository;

import com.example.animeapi.domain.model.User;
import com.example.animeapi.domain.model.projection.ProjectionAnimeSimple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    <T> T findByUsername(String name, Class<T> type);
}
