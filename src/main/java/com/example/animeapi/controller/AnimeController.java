package com.example.animeapi.controller;

import com.example.animeapi.domain.Anime;
import com.example.animeapi.domain.dto.UserRegisterRequest;
import com.example.animeapi.repository.AnimeRepository;
import com.example.animeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

//    @Autowired
//    private UserRegisterRequest userRegisterRequest;

    @GetMapping("/")
    public List<Anime> findAllAnimes() {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody AnimeRequest animeRequest) {

        Anime anime = new Anime(animeRequest.name, animeRequest.description, animeRequest.type, animeRequest.year, animeRequest.image);
        Anime animeFind = animeRepository.findByName(animeRequest.name);
        if (animeFind != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Ja existeix un anime amb el nom '" + animeRequest.name +"'");
        return ResponseEntity.status(HttpStatus.OK).body(animeRepository.save(anime));
    }

//    @PostMapping("/")
//    public Anime createAnime(@RequestBody UserRegisterRequest userRegisterRequest){
//        UserRepository userRepository;
//        if (userRepository.findB())
//    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getAnime(@PathVariable UUID id) {
        Anime anime = animeRepository.findById(id).orElse(null);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'anime amb id '" + id + "'");

        return new ResponseEntity<Anime>(anime, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteAnime(@PathVariable UUID id){

        Anime anime = animeRepository.findById(id).orElse(null);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'anime amb id '" + id + "'");

        animeRepository.delete(anime);

        return ResponseEntity.status(HttpStatus.OK).body("S'ha eliminat l'anime amd id '" + id + "'");

    }

}

class AnimeRequest {
    public String name;
    public String description;
    public String type;
    public String year;
    public String image;

}
