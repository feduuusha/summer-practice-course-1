package ru.itis.shop.users.validators;

public interface PasswordValidator {
    void validate(String password) throws IllegalArgumentException;
}
