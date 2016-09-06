package com.lucas.account.service;

import com.lucas.account.model.User;

import java.util.List;

/**
 * Created by maquina0 on 04/08/2016.
 */
public interface UserService {
    User findById(Long id);

    void save(User user);

    User findByUserName(String username);
    User findOneBySheet(String sheet);

    List<User> findAll();

    void delete(Long id);
}
