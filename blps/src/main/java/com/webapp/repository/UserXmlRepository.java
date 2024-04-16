package com.webapp.repository;

import com.webapp.model.UserEntity;

public interface UserXmlRepository {
    UserEntity findByUsername(String username);
    UserEntity findByIdUser(Long id);
    void save(UserEntity user);
}
