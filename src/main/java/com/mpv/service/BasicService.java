package com.mpv.service;

import java.util.List;

public interface BasicService<T>{
    void add(T t);
    void update(T t);
    void deleteById(long id);
    List<T> getAll();
    T getById(long id);
}
