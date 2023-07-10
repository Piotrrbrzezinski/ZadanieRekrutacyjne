package com.rekrutacja.zadanie.controllers;

import com.rekrutacja.zadanie.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


}
