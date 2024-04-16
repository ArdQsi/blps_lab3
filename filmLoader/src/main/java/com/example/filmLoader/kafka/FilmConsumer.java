package com.example.filmLoader.kafka;

import com.example.filmLoader.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FilmConsumer {

    private final FilmService filmService;

    @KafkaListener(topics = "del_film", groupId = "film_consumer")
    public void listener1(ConsumerRecord<Long, String> record){
        System.out.println("Пришло сообщение: " + record.key() + " " + record.value());
        filmService.delete(record.value());
    }
}
