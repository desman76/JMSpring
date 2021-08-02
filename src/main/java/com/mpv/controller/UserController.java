package com.mpv.controller;

import com.mpv.model.User;
import com.mpv.service.BasicService;
import com.mpv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    BasicService<User> userService;

    @GetMapping("")
    public String getUsers(Model model ) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User("Bill", 80);
        System.out.println("from controller: " + user);
        userService.add(user);
        model.addAttribute("user", user);
        return "user";
    }
}
