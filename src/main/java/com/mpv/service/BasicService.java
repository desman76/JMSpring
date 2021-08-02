package com.mpv.service;

import java.util.List;

public interface BasicService<T>{
    void add(T t);
    T update(T t);
    T delete(T t);
    List<T> getAll();
}
