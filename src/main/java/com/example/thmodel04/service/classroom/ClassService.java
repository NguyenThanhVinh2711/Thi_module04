package com.example.thmodel04.service.classroom;


import com.example.thmodel04.model.Classroom;
import com.example.thmodel04.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements IClassService {
    @Autowired
    IClassRepository classRepository;
    @Override
    public Iterable<Classroom> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Classroom save(Classroom city) {
        return classRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        classRepository.deleteById(id);
    }
}
