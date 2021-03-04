package com.bbank.bank.controllers;

import com.bbank.bank.domain.List;
import com.bbank.bank.domain.User;
import com.bbank.bank.repo.ListRepo;
import com.bbank.bank.service.ListService;
import com.bbank.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;


@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private ListRepo listRepo;
    @Autowired
    ListService listService;
    @GetMapping("/")
    public String start(Model model) {
        User user= userService.getUser();
        model.addAttribute("name",user.getName() );
        model.addAttribute("card", user.getCard());
        model.addAttribute("balance", user.getBalance());
        return "main";
    }
    @GetMapping("/main")
    public String main(Model model) {
       User user= userService.getUser();
        model.addAttribute("name",user.getName() );
        model.addAttribute("card", user.getCard());
        model.addAttribute("balance", user.getBalance());
        return "main";
    }
    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("title", "Вход");
        return "settings";
    }
    @PostMapping("/settings")
    public String changepass(Model model, @RequestParam String oldpass,@RequestParam String newpass,@RequestParam String newpass2) {
        User user= userService.getUser();
       if(!userService.changepass(oldpass,newpass,newpass2,user))
       {
           model.addAttribute("error", "error");
       }
       else {model.addAttribute("successfully", "successfully");}

        return "settings";
    }
    @GetMapping("/list")
    public String list(Model model) {
        User user= userService.getUser();
        Collection<List> list = listService.getlist(user);
        model.addAttribute("list", list);
        return "list";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("title", "Вход");
        return "services";
    }
    @PostMapping("/services")
    public String transfer(@RequestParam String card,@RequestParam String expdate,@RequestParam int sum, List list,Model model) {
        User user= userService.getUser();
        try {
            if(!listService.transfer(card,expdate,sum,user,list))
            {
                model.addAttribute("error","error");
            }
            else {model.addAttribute("successfully", "successfully");}
        }
        catch (Exception exception)
        {
            model.addAttribute("error","error");
        }

        return "services";
    }

}