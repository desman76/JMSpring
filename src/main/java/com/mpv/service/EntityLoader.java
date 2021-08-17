package com.mpv.service;

import com.mpv.model.Role;
import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class EntityLoader {

    private BasicService<User> userService;
    private BasicService<Role> roleService;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        User user1 = new User("user_1", "1");
        user1.addRole(userRole);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));

        User user2 = new User("user_2", "2");
        user2.addRole(adminRole);
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));

        User user3 = new User("user_3", "3");
        user3.getRoles().add(userRole);
        user3.getRoles().add(adminRole);
        user3.setPassword(passwordEncoder.encode(user3.getPassword()));

        roleService.add(userRole);
        roleService.add(adminRole);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        System.out.println("table created");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
