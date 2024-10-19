package org.vti.studentmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = StudentIdExistsValidator.class
)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentIdExists {
    String message() default "Student id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
