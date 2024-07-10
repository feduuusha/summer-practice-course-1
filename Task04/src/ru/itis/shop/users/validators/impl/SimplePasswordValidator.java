package ru.itis.shop.users.validators.impl;

import ru.itis.shop.users.validators.PasswordValidator;

public class SimplePasswordValidator implements PasswordValidator {

    @Override
    public void validate(String password) throws IllegalArgumentException {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Incorrect password");
        }
        for (int i = 0; i < password.length(); ++i) {
            if (password.charAt(i) <= '9' && password.charAt(i) >= '0') {
                return;
            }
        }
        throw new IllegalArgumentException("Incorrect password");
    }
}
