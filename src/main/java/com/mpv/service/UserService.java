package com.mpv.service;
//
import com.mpv.dao.*;
import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements BasicService<User> {

    @Autowired
    private BasicDao<User> userDao;

    @Transactional
    @Override
    public void add(User user) {
        System.out.println("from service: " + user);
        userDao.add(user);
    }

//    @Transactional
    @Override
    public User update(User user) {
        return userDao.update(user);
    }

//    @Transactional
    @Override
    public User delete(User user) {
        return userDao.delete(user);
    }

//    @Transactional
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
