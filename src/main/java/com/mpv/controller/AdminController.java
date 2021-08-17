package com.mpv.controller;

import com.mpv.model.Role;
import com.mpv.model.User;
import com.mpv.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private BasicService<User> userService;

    @Autowired
    private BasicService<Role> roleService;

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getUsers(Model model) {
        List<User> all = userService.getAll();
        System.out.println(all);
        model.addAttribute("users", all);
        return "allUsers";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "addPage";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "admin", required = false) String admin) {
        if (admin != null) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        user.addRole(roleService.getByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user data: " + user);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin",
                user.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_ADMIN")));
        return "editPage";
    }

    @PatchMapping
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(value = "admin", required = false) String admin) {
        System.out.println("UFDM: " + user);
        if (admin != null) {
            user.addRole(roleService.getByName("ROLE_ADMIN"));
        }
        user.addRole(roleService.getByName("ROLE_USER"));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
