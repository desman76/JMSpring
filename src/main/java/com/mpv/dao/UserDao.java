package com.mpv.dao;

import com.mpv.model.Role;
import com.mpv.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDao implements BasicDao<User> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User user) {
//        Role role = entityManager.find(Role.class, 1L);
//        user.addRole(role);
//        user.setEnabled(true);
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {
        System.out.println("id: " + id);
        entityManager.createQuery("DELETE FROM User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("FROM User WHERE username=:name")
                .setParameter("name", name).getSingleResult();
    }

    public void addRole() {

    }

}
