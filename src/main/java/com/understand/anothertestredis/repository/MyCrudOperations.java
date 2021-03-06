package com.understand.anothertestredis.repository;

import java.util.List;

public interface MyCrudOperations<T, K>  {
    T save(T t);
    T findByKey(K key);
    List<T> findAll();
}
