package com.understand.anothertestredis.repository;

import java.util.List;

public interface MyCrudOperations<T>  {
    T save(T t);
    T findByKey(String key);
    List<T> findAll();
}
