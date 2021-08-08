package com.mpv.service;

import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EntityLoader {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService.add(new User("user_1", 70));
        userService.add(new User("user_2", 80));
        userService.add(new User("user_3", 90));
        System.out.println("table created");
    }
}
