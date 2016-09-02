package com.lucas.account.repository;

import com.lucas.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by maquina0 on 04/08/2016.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
