package ru.itis.shop.users.validators;

public class SimpleNameValidator implements NameValidator {
    @Override
    public void validate(String name) throws IllegalArgumentException {
        if (name.length() <= 1) {
            throw new IllegalArgumentException("Incorrect name");
        }
    }
}
