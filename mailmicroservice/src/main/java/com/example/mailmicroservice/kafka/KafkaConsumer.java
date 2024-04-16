package com.example.mailmicroservice.kafka;

import com.example.mailmicroservice.model.MailEntity;
import com.example.mailmicroservice.model.UserEntity;
import com.example.mailmicroservice.repository.UserRepository;
import com.example.mailmicroservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final EmailService emailService;
    private final UserRepository userRepository;

    @KafkaListener(topics = "balance_mail", groupId = "mail_conumer")
    public void listener1(ConsumerRecord<Long, String> record){

        UserEntity user = userRepository.findUserById(record.key());

        MailEntity mailEntity = MailEntity.builder().toAddress(record.value())
                .topic("Баланс Rutube").message(user.getFirstname()
                        + ", Ваш баланс пополнен!").build();

        System.out.println("Пришло сообщение: " + record.value());
        System.out.println("Отправляю письмо на: " + mailEntity.getToAddress());

        try {
            emailService.sendSimpleEmail(mailEntity);
        } catch (MailException mailException) {
            System.out.println("Сообщение не ушло груфно(((((");
        }
    }

    @KafkaListener(topics = "reg_mail", groupId = "mail_conumer")
    public void listener2(ConsumerRecord<Long, String> record){

        UserEntity user = userRepository.findUserById(record.key());

        MailEntity mailEntity = MailEntity.builder().toAddress(record.value())
                .topic("Регистрация").message("Спсибо " + user.getFirstname() + " "
                        + user.getLastname() + "! Вы зарегистрировались на rutube.com!").build();

        System.out.println("Пришло сообщение: " + record.value());
        System.out.println("Отправляю письмо на: " + mailEntity.getToAddress());
        try {
            emailService.sendSimpleEmail(mailEntity);
        } catch (MailException mailException) {
            System.out.println("Сообщение не ушло груфно(((((");
        }
    }

    @KafkaListener(topics = "update_sub_mail", groupId = "mail_conumer")
    public void listener3(ConsumerRecord<Long, String> record){

        UserEntity user = userRepository.findUserById(record.key());

        MailEntity mailEntity = MailEntity.builder().toAddress(record.value())
                        .topic("Подписка Rutube").message(user.getFirstname()
                                + "Ваша подписка на Rutube была продлена до: "
                        + user.getSubscriptionEndDate()).build();

        System.out.println("Пришло сообщение: " + record.value());
        System.out.println("Отправляю письмо на: " + mailEntity.getToAddress());
        try {
            emailService.sendSimpleEmail(mailEntity);
        } catch (MailException mailException) {
            System.out.println("Сообщение не ушло груфно(((((");
        }
    }
}
