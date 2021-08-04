package com.mpv.dao;

import com.mpv.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserDao implements BasicDao<User> {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Transactional
    @Override
    public User delete(User user) {
        User merge = entityManager.merge(user);
        entityManager.remove(merge);
        return user;
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Transactional
    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }
}
