package com.mpv.controller;

import com.mpv.model.User;
import com.mpv.service.BasicService;
import com.mpv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    BasicService<User> userService;

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user) {
        return "addPage";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute(user);
        return "userPage";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute() User user) {
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute() User user) {
        userService.delete(user);
        return "redirect:/";
    }
}
