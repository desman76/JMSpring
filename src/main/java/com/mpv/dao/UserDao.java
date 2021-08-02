package com.mpv.dao;

import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//@Repository
@Component
public class UserDao implements BasicDao<User> {

    @Autowired
    EntityManagerFactory entityManager;

    @Transactional
    @Override
    public void add(User user) {
        System.out.println("from dao: " + user);
        getEntityManager().persist(user);
//        EntityTransaction tx = getEntityManager().getTransaction();
//        Query q = getEntityManager().createNativeQuery("INSERT INTO users (name, weight) VALUES (?,?)")
//                .setParameter(1, user.getName())
//                .setParameter(2, user.getWeight());
//                q.executeUpdate();
    }

    @Override
    public User update(User user) {
        getEntityManager().refresh(user);
        return user;
    }

    @Override
    public User delete(User user) {
        getEntityManager().remove(user);
        return user;
    }

//    @Transactional
    @Override
    public List<User> getAll() {
        return getEntityManager().createQuery("FROM User").getResultList();
    }

    protected EntityManager getEntityManager() {
        return entityManager.createEntityManager();
    }
}
