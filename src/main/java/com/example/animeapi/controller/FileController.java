package com.example.animeapi.controller;

import com.example.animeapi.domain.File;
import com.example.animeapi.domain.User;
import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.dto.MessageResponse;
import com.example.animeapi.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.util.ArrayList;
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
    public ResponseEntity<?> findAllFiles(){
        List<FileResponse> llistaFiles = new ArrayList<FileResponse>();

        fileRepository.findAll().forEach(file -> {
            llistaFiles.add(new FileResponse(file.fileid, file.contenttype));
        });

        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaFiles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {
        File file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat el arxiu amb id '"+ id +"'"));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping
    public ResponseEntity<? extends Object> upload(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            File file = new File();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();
            fileRepository.save(file);
            return new ResponseEntity<FileResponse>(new FileResponse(file.fileid, file.contenttype), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<? extends Object> deleteAllFile(){
        fileRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteFile(@PathVariable UUID id){

        File file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'arxiu amd id '" + id + "'"));

        fileRepository.delete(file);

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.getMessage("S'ha eliminat l'arxiu amd id '" + id + "'"));

    }

    class FileResponse {
        public UUID fileid;
        public String contenttype;

        public FileResponse(UUID fileid, String contenttype) {
            this.fileid = fileid;
            this.contenttype = contenttype;
        }
    }
}
