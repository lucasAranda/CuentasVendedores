package com.lucas.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by maquina0 on 01/09/2016.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if (error != null){
            model.addAttribute("error", "Your username and password are invalid.");
        }

        if (logout != null){
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model){ return "admin/admin"; }
}
