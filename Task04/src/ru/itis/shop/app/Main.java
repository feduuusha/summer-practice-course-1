package ru.itis.shop.app;

import ru.itis.shop.users.controllers.UsersUIConsole;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.repositories.impl.UsersRepositoryJdbcImpl;
import ru.itis.shop.users.services.UsersService;
import ru.itis.shop.users.validators.EmailValidator;
import ru.itis.shop.users.validators.NameValidator;
import ru.itis.shop.users.validators.PasswordValidator;
import ru.itis.shop.users.validators.impl.SimpleEmailValidator;
import ru.itis.shop.users.validators.impl.SimpleNameValidator;
import ru.itis.shop.users.validators.impl.SimplePasswordValidator;
import ru.itis.shop.util.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class Main {
    // сборка компонентов системы и клиентский код
    public static void main(String[] args) {
        DataSource dataSource =
                new DriverManagerDataSource("org.postgresql.Driver",
                        "jdbc:postgresql://localhost:5432/javalab_2024_db",
                        "postgres", "qwerty007");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        EmailValidator emailValidator = new SimpleEmailValidator();
        PasswordValidator passwordValidator = new SimplePasswordValidator();
        NameValidator nameValidator = new SimpleNameValidator();
        UsersService usersService = new UsersService(usersRepository, emailValidator, nameValidator, passwordValidator);
        UsersUIConsole ui = new UsersUIConsole(usersService);
        ui.printRegistrationMenu();
    }
}
