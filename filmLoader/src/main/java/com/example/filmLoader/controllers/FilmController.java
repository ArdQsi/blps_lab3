package com.example.filmLoader.controllers;

import com.example.filmLoader.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rutube.ru")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/uploadFilm/{token}")
    public ResponseEntity<String> uploadFilm(@PathVariable String token, @RequestParam("file") MultipartFile file){
        filmService.save(token, file);
        return ResponseEntity.ok("Фильм сохранен!");
    }

}
