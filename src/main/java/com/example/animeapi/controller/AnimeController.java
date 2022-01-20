package com.example.animeapi.controller;

import com.example.animeapi.domain.model.Anime;
import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.dto.MessageResponse;
import com.example.animeapi.domain.model.Favorite;
import com.example.animeapi.domain.model.projection.ProjectionAnime;
import com.example.animeapi.repository.AnimeRepository;
import com.example.animeapi.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

//    @Autowired
//    private UserRegisterRequest userRegisterRequest;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnimes() {
        List<ProjectionAnime> llistaAnimeResposta = animeRepository.findBy();
        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaAnimeResposta));
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody AnimeRequest animeRequest) {
        Anime anime = new Anime(animeRequest.name, animeRequest.description, animeRequest.type, animeRequest.year, animeRequest.image);
        Anime animeFind = animeRepository.findByName(animeRequest.name);
        if (animeFind != null) return ResponseEntity.status(HttpStatus.CONFLICT).body(MessageResponse.getMessage("Ja existeix un anime amb el nom '" + animeRequest.name +"'"));
        return ResponseEntity.status(HttpStatus.OK).body(animeRepository.save(anime));
    }

//    @PostMapping("/")
//    public Anime createAnime(@RequestBody UserRegisterRequest userRegisterRequest){
//        UserRepository userRepository;
//        if (userRepository.findB())
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id) {
        ProjectionAnime anime = animeRepository.findByAnimeid(id, ProjectionAnime.class);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'anime amb id '" + id + "'"));

        return new ResponseEntity<ProjectionAnime>(anime, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){

        Anime anime = animeRepository.findById(id).orElse(null);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'anime amb id '" + id + "'"));

        animeRepository.delete(anime);

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.getMessage("S'ha eliminat l'anime amd id '" + id + "'"));

    }

}

class AnimeRequest {
    public String name;
    public String description;
    public String type;
    public String year;
    public String image;

    public AnimeRequest(String name, String description, String type, String year, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.year = year;
        this.image = image;
    }
}
