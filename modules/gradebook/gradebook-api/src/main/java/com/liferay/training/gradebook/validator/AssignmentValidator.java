package com.liferay.training.gradebook.validator;

import com.liferay.training.gradebook.exception.AssignmentValidationException;

import java.util.Date;

/**
 * @author Mario Cvjetojevic
 */
public interface AssignmentValidator {

    /**
     * Validates an Assignment
     *
     * @param title
     * @param description
     * @param dueDate
     * @throws AssignmentValidationException
     */
    public void validate(
            String title, String description, Date dueDate)
            throws AssignmentValidationException;
}