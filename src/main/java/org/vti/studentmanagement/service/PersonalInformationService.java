package org.vti.studentmanagement.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vti.studentmanagement.entity.PersonalInformation;
import org.vti.studentmanagement.entity.Student;
import org.vti.studentmanagement.form.PersonalInformationForm;
import org.vti.studentmanagement.repository.PersonalInformationRepository;
import org.vti.studentmanagement.repository.StudentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonalInformationService {
    private PersonalInformationRepository personalInformationRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public Page<PersonalInformation> findAll(Pageable pageable) {
        return personalInformationRepository.findAll(pageable);
    }

    public Optional<PersonalInformation> findById(Long id) {
        return personalInformationRepository.findById(id);
    }

//    public PersonalInformation save(Long studentId, PersonalInformation personalInformation) {
//        // Tìm kiếm student theo student_id
//        var student = studentRepository.findById(studentId).orElse(null);
//
//        if (student == null) {
//            throw new RuntimeException("Student not found with id " + studentId);
//        }
//
//        // Thiết lập student cho personalInformation
//        personalInformation.setStudent(student);
//
//        // Lưu personalInformation
//        return personalInformationRepository.save(personalInformation);
//    }

    public PersonalInformation save(Long studentId, PersonalInformationForm personalInformation) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found with id " + studentId);
        }
        Student student = studentOptional.get();
        PersonalInformation personalInformationToSave = modelMapper.map(personalInformation, PersonalInformation.class);
        personalInformationToSave.setStudent(student);
        return personalInformationRepository.save(personalInformationToSave);
    }

    public PersonalInformation update(Long id, PersonalInformationForm personalInformationDetails) {
        Optional<PersonalInformation> personalInformationOptional = personalInformationRepository.findById(id);
        if (personalInformationOptional.isPresent()) {
            PersonalInformation existingPersonalInformation = personalInformationOptional.get();
            modelMapper.map(personalInformationDetails, existingPersonalInformation);

            return personalInformationRepository.save(existingPersonalInformation);
        } else {
            throw new RuntimeException("PersonalInformation not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        personalInformationRepository.deleteById(id);
    }
}