package com.mpv.dao;

import com.mpv.model.Role;
import com.mpv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class UserDao implements BasicDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
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
    public User getByName(String name) {
        return (User) entityManager.createQuery("FROM User as u left join fetch u.roles WHERE u.username=:name ")
                .setParameter("name", name).getSingleResult();
    }
}
