package com.mpv.dao;

import java.util.List;

public interface BasicDao<T> {
    void add(T t);
    T update(T t);
    T delete(T t);
    List<T> getAll();
}
