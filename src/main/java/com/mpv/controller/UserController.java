package com.mpv.controller;

import com.mpv.model.User;
import com.mpv.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private BasicService<User> userService;

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
//        UserDetails user_1 = userService.loadUserByUsername("user_1");
//        System.out.println(user_1.getUsername() + " : " + user_1.getPassword());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
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
        return "userInfoPage";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute() User user) {
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete2(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String getTestPage() {
        System.out.println("TEST PAGE");
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
