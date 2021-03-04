package com.bbank.bank.controllers;

import com.bbank.bank.domain.Role;
import com.bbank.bank.domain.User;
//import com.bbank.bank.repos.UserRepo;
//import com.bbank.bank.servise.UserService;
import com.bbank.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SingInController {
    @Autowired
    private UserService userService;
    //private UserService userService;

    @GetMapping("/registration")
    public  String registration(Model model) {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Role role, Model model) {
        if (!userService.addUser(user,role)) {
            model.addAttribute("error","error");
            return "registration";

        }
        model.addAttribute("message", "message");
        return "redirect:/login";
    }
}
