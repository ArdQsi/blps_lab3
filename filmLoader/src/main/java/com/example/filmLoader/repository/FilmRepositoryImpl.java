package com.example.filmLoader.repository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FilmRepositoryImpl implements FilmRepository{

    @Value("${FILM_DIRECTORY}")
    private String upload_dir;

    public void delete(String path){
        Path uploadPath = Paths.get(path);
        try {
            Files.delete(uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(uploadPath.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    @Override
    public String save(String token, MultipartFile file) {
        Path uploadPath = Paths.get(upload_dir, token, file.getOriginalFilename());
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        File targetFile = new File(uploadPath.toUri());
        file.transferTo(targetFile);
        return uploadPath.toString();
    }
}
