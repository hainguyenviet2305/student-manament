package org.vti.studentmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.vti.studentmanagement.repository.StudentRepository;

public class StudentIdExistsValidator implements ConstraintValidator<StudentIdExists, Long> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentIdExistsValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return studentRepository.existsById(id);
    }
}
