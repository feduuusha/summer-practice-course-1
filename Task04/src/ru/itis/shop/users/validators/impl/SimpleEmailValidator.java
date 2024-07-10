package ru.itis.shop.users.validators.impl;

import ru.itis.shop.users.validators.EmailValidator;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class SimpleEmailValidator implements EmailValidator {

    @Override
    public void validate(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Incorrect email");
        }
    }
}
