package com.lucas.account.web;

import com.lucas.account.dto.DTOUser;
import com.lucas.account.mapper.UserMapper;
import com.lucas.account.model.User;
import com.lucas.account.service.RoleService;
import com.lucas.account.service.SecurityService;
import com.lucas.account.service.UserService;
import com.lucas.account.validator.UserValidator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * Created by maquina0 on 04/08/2016.
 */
@Controller
@RequestMapping(value = "/User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(Model model) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("userList", mapper.writeValueAsString(userMapper.mapListToDTO(userService.findAll())));
        return "admin/userlist";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("userForm", new DTOUser());
        return "admin/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") DTOUser userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleList", roleService.findAll());
            return "admin/registration";
        }

        userService.save(userMapper.mapToUser(userForm));

        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/User/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showUpdateUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("userForm", userMapper.mapToDTO(userService.findById(id)));

        return "admin/registration";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes){
        userService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");

        return "redirect:/User/";
    }

}
