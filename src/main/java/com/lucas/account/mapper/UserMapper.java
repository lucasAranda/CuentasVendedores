package com.lucas.account.mapper;

import com.lucas.account.dto.DTOUser;
import com.lucas.account.model.Role;
import com.lucas.account.model.User;
import com.lucas.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maquina0 on 29/08/2016.
 */
@Component
public class UserMapper {

    @Autowired
    RoleService roleService;

    public User mapToUser(DTOUser dto){
        User user = new User();
        user.setId(dto.getId() != null ? dto.getId() : null);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setSheet(dto.getSheet());
        user.setNombre(dto.getNombre());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(dto.getRole()));
        user.setRoles(roles);
        return user;
    }

    public DTOUser mapToDTO(User user){
        DTOUser dto = null;
        if(user != null) {
            dto = new DTOUser();
            dto.setId(user.getId() != null ? user.getId() : null);
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setSheet(user.getSheet());
            dto.setNombre(user.getNombre());
            for (Role role: user.getRoles()) {
                dto.setRole(role.getName());
                break;
            }
        }
        return dto;
    }

    public List<DTOUser> mapListToDTO(List<User> users){
        List<DTOUser> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(mapToDTO(user));
        }
        return userList;
    }
}
