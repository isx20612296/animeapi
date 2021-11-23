package com.example.animeapi.controller;

import com.example.animeapi.domain.File;
import com.example.animeapi.domain.User;
import com.example.animeapi.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    private FileRepository fileRepository;
    FileController(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @GetMapping("/")
    public List<File> findAllFiles(){ return fileRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        File file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            File file = new File();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();

            return fileRepository.save(file).fileid.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<? extends Object> deleteAllFile(){
        fileRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Tots els fitxers han sigut eliminats");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteFile(@PathVariable UUID id){

        File file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: file no trobat");

        fileRepository.delete(file);

        return ResponseEntity.status(HttpStatus.OK).body("User eliminat");

    }
}
