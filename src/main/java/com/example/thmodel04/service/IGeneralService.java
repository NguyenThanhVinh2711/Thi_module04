package com.example.thmodel04.service;

import com.example.thmodel04.model.Student;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    T save (T t);
    void remove(Long id);
}
