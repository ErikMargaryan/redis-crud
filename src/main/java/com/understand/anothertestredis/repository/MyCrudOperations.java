package com.understand.anothertestredis.repository;

import java.util.List;

public interface MyCrudOperations<T, ID>  {
    T save(T t);
    T findByKey(ID key);
    List<T> findAll();
}
