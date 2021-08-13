package com.mpv.service;

import com.mpv.dao.BasicDao;
import com.mpv.dao.RoleDao;
import com.mpv.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements BasicService<Role>{

    @Autowired
    private BasicDao<Role> roleDao;

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteById(long id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getById(long id) {
        return roleDao.getById(id);
    }
}
