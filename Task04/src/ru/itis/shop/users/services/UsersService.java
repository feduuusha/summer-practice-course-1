package ru.itis.shop.users.services;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.validators.EmailValidator;
import ru.itis.shop.users.validators.NameValidator;
import ru.itis.shop.users.validators.PasswordValidator;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersService {

    private final EmailValidator emailValidator;
    private final NameValidator nameValidator;
    private final PasswordValidator passwordValidator;
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository, EmailValidator emailValidator, NameValidator nameValidator, PasswordValidator passwordValidator) {
        this.emailValidator = emailValidator;
        this.usersRepository = usersRepository;
        this.nameValidator = nameValidator;
        this.passwordValidator = passwordValidator;
    }

    public void register(String name, String email, String password) {
        nameValidator.validate(name);
        emailValidator.validate(email);
        passwordValidator.validate(password);
        usersRepository.save(new User(name, email, password));
    }
}
