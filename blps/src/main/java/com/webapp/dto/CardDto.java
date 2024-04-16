package com.webapp.dto;

import com.webapp.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CardDto {
    private Long userId;
    private String number;
    private int month;
    private int year;
    private String name;
    private String surname;
    private Integer cvc;
    private Long amount;
}
