package com.webapp.kafka;

import com.webapp.model.FilmEntity;
import com.webapp.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FilmConsumer {

    private  final FilmRepository filmRepository;

    @KafkaListener(topics = "film", groupId = "film_conumer")
    public void listener1(ConsumerRecord<String, String> record){
        FilmEntity film = filmRepository.findFilmByToken(record.key());
        film.setPath(record.value());
        filmRepository.save(film);
        System.out.println("Пришло сообщение: " + record.key() + " " + record.value());
    }
}
