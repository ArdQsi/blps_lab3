package com.webapp.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement
public class Users {
    private List<UserEntity> user;

    public Users(){
        user = new ArrayList<>();
    }
}
