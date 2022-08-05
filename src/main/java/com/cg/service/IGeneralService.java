package com.cg.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    T getById(Long id);

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
