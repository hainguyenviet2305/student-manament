package org.vti.studentmanagement.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.vti.studentmanagement.entity.Gender;

import java.time.LocalDate;

@Getter
@Setter
public class StudentForm {
    @NotBlank(message = "Fullname must not be blank")
    @Length(max = 255, message = "Fullname has maximum 255 characters")
    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "Average score must not be blank")
    @Min(value = 0, message = "Average score must be greater than or equal to 0")
    @Max(value = 10, message = "Average score must be less than or equal to 10")
    private Double average_score;
}
