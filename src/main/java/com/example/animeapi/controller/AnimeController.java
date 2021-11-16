package com.example.animeapi.controller;

import com.example.animeapi.domain.Anime;
import com.example.animeapi.repository.AnimeRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public List<Anime> findAllAnimes() {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }

    //TODO(get i delete, treballar amb les ids)

//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
//        Anime anime = animeRepository.findById(id).orElse(null);
//
//        if (anime == null) return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(anime.contenttype))
//                .contentLength(anime.data.length)
//                .body(anime.data);
//    }

}
