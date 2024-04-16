package com.example.filmLoader.service;

import com.example.filmLoader.kafka.FilmProducer;
import com.example.filmLoader.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmProducer filmProducer;

    public void delete(String path){
        filmRepository.delete(path);
    }

    public void save(String token, MultipartFile file){
        String dir = filmRepository.save(token,file);
        filmProducer.send(new ProducerRecord<>("film",token,dir));
    }
}
