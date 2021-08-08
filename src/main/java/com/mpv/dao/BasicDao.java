package com.mpv.dao;

import java.util.List;

public interface BasicDao<T> {
    void add(T t);
    void update(T t);
    void deleteById(long id);
    List<T> getAll();
    T getById(long id);
}
