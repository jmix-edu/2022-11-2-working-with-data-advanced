package com.company.jmixpm.validation;

import com.company.jmixpm.entity.Project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ValidDateProjectValidator implements ConstraintValidator<ValidDateProject, Project> {

    @Override
    public boolean isValid(Project value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        LocalDateTime startDate = value.getStartDate();
        LocalDateTime endDate = value.getEndDate();
        if (startDate == null || endDate == null) {
            return true;
        }

        return endDate.compareTo(startDate) > 0;
    }
}
