package org.vti.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.studentmanagement.entity.PersonalInformation;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
}
