package com.iplus.studentManagement.repositories;

import com.iplus.studentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No extra methods needed for basic CRUD
}
