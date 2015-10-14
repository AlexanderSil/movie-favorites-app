package com.movie.favorite.dao.api;


import com.movie.favorite.domain.BasicObject;

import java.util.Collection;

public interface BasicDao<T extends BasicObject> {

    T saveOrUpdate(T entity);

    T findById(Long id);

    Collection<T> findAll();

    void delete(T entity);
}
