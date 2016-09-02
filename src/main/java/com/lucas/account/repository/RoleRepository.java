package com.lucas.account.repository;

import com.lucas.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by maquina0 on 04/08/2016.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
