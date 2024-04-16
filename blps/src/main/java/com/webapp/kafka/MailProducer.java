package com.webapp.kafka;

import com.webapp.model.MailEntity;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Data
@Component
public class MailProducer {

    private Properties props;
    private Producer<Long, String> producer;

    @PostConstruct
    public void init() {
        props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer","org.apache.kafka.common.serialization.LongSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void send(ProducerRecord<Long, String> record){
        producer.send(record);
    }


}
