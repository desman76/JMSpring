package com.mpv.controller;

import com.mpv.model.User;
import com.mpv.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private BasicService<User> userService;

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @GetMapping("/admin/getAllUsers")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
//        UserDetails user_1 = userService.loadUserByUsername("user_1");
//        System.out.println(user_1.getUsername() + " : " + user_1.getPassword());
        return "allUsers";
    }

    @GetMapping("/admin/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "addPage";
    }

    @PostMapping("/admin/add")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/getAllUsers";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute(user);
        return "userInfoPage";
    }

    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute() User user) {
        userService.update(user);
        return "redirect:/admin/getAllUsers";
    }

//    @PostMapping("/delete")
//    public String delete(@RequestParam(value = "id") long id) {
//        userService.deleteById(id);
//        return "redirect:/";
//    }

    @GetMapping("/admin/delete/{id}")
    public String delete2(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return "redirect:/admin/getAllUsers";
    }

//    @GetMapping("/test/{name}")
//    public String getTestPage(@PathVariable("name") String name, Model model) {
//        model.addAttribute("user", userDetailsService.loadUserByUsername(name));
//        return "hello";
//    }

//    @GetMapping("/getInfo/{name}")
//    public String getUserInfo(@PathVariable("name") String name, Model model) {
//        model.addAttribute("user", userDetailsService.loadUserByUsername(name));
//        return "userInfoPage";
//    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal,
                              Model model) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userInfoPage";
    }

    @GetMapping("/admin")
    public String getAdminInfo(Principal principal,
                              Model model) {
        model.addAttribute("user", userDetailsService.loadUserByUsername(principal.getName()));
        return "userInfoPage";
    }

//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }
}
