package com.lucas.account.service;

import com.lucas.account.model.Role;

import java.util.List;

/**
 * Created by maquina0 on 26/08/2016.
 */
public interface RoleService {
    List<Role> findAll();
    Role findByName(String name);
}
