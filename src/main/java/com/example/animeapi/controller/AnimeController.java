package com.example.animeapi.controller;

import com.example.animeapi.domain.Anime;
import com.example.animeapi.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AnimeController {

@RestController
@RequestMapping("/animes")
public class MovieController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public List<Anime> findAllMovies() {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }
}
}
