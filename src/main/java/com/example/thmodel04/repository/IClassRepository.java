package com.example.thmodel04.repository;


import com.example.thmodel04.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRepository extends JpaRepository<Classroom, Long> {
}
