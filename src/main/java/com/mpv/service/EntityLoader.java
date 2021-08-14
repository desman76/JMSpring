package com.mpv.service;

import com.mpv.model.Role;
import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class EntityLoader {

    private BasicService<User> userService;
    private BasicService<Role> roleService;

    @PostConstruct
    public void init() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        User user1 = new User("user_1", "70");
//        user1.addRole(userRole);

        User user2 = new User("user_2", "80");
        user2.addRole(adminRole);

        User user3 = new User("user_3", "90");
//        user3.getRoles().add(userRole);
        user3.getRoles().add(adminRole);

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
}
