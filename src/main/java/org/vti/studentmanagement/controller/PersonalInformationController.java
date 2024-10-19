package org.vti.studentmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vti.studentmanagement.entity.PersonalInformation;
import org.vti.studentmanagement.form.PersonalInformationForm;
import org.vti.studentmanagement.service.PersonalInformationService;
import org.vti.studentmanagement.validation.StudentIdExists;

@RestController
@Validated
@RequestMapping("/api/students")
public class PersonalInformationController {

    @Autowired
    private PersonalInformationService personalInformationService;

    @GetMapping("/personal-informations")
    public ResponseEntity<Page<PersonalInformation>> getAllPersonalInformations(Pageable pageable) {
        Page<PersonalInformation> personalInformations = personalInformationService.findAll(pageable);
        return new ResponseEntity<>(personalInformations, HttpStatus.OK);
    }

    @GetMapping("/personal-informations/{id}")
    public ResponseEntity<PersonalInformation> getPersonalInformationById(@PathVariable Long id) {
        return personalInformationService.findById(id)
                .map(personalInformation -> new ResponseEntity<>(personalInformation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{studentId}/personal-informations")
    public ResponseEntity<PersonalInformation> createPersonalInformation(@PathVariable("studentId") @StudentIdExists Long studentId, @RequestBody @Valid PersonalInformationForm personalInformation) {
        PersonalInformation savedPersonalInformation = personalInformationService.save(studentId, personalInformation);
        return new ResponseEntity<>(savedPersonalInformation, HttpStatus.CREATED);
    }

    @PutMapping("/personal-informations/{id}")
    public ResponseEntity<PersonalInformation> updatePersonalInformation(@PathVariable Long id, @RequestBody @Valid PersonalInformationForm personalInformationDetails) {
        try {
            PersonalInformation updatedPersonalInformation = personalInformationService.update(id, personalInformationDetails);
            return new ResponseEntity<>(updatedPersonalInformation, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/personal-informations/{id}")
    public ResponseEntity<Void> deletePersonalInformation(@PathVariable Long id) {
        personalInformationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}