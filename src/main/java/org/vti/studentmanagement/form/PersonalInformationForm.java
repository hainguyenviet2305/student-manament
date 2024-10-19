package org.vti.studentmanagement.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.vti.studentmanagement.entity.Gender;
import org.vti.studentmanagement.entity.Relationship;

import java.time.LocalDate;

@Getter
@Setter
public class PersonalInformationForm {
    @NotBlank(message = "Fullname must not be blank")
    @Length(max = 255, message = "Fullname has maximum 255 characters")
    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "Gender must not be null")
    private Relationship relationship;
}
