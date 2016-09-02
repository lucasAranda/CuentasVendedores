package com.lucas.account.service;

/**
 * Created by maquina0 on 04/08/2016.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
