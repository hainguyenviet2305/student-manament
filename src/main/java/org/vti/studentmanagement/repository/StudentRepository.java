package org.vti.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.studentmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
