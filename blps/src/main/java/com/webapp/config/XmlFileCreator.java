package com.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class XmlFileCreator implements CommandLineRunner {

    @Value("${LIST_OF_USERS}")
    private String xmlPath;

    @Override
    public void run(String... args) throws Exception {

        File file = new File(xmlPath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Предыдущий файл удален.");
            } else {
                System.out.println("Не удалось удалить предыдущий файл.");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан: " + file.getName());
            } else {
                System.out.println("Файл уже существует.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }

    }
}
