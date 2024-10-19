package org.vti.studentmanagement.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vti.studentmanagement.entity.Student;
import org.vti.studentmanagement.form.StudentForm;
import org.vti.studentmanagement.repository.StudentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }


    public Student save(StudentForm form) {
        var student = modelMapper.map(form, Student.class);
        return studentRepository.save(student);
    }

    public Student update(Long id, StudentForm studentDetails) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();

            modelMapper.map(studentDetails, existingStudent);

            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
