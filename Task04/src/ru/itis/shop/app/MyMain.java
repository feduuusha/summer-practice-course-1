package ru.itis.shop.app;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.repositories.impl.UsersRepositoryJdbcImpl;
import ru.itis.shop.util.DriverManagerDataSource;

import javax.sql.DataSource;

public class MyMain {
    public static void main(String[] args) {
        DataSource dataSource =
                new DriverManagerDataSource("org.postgresql.Driver",
                        "jdbc:postgresql://localhost:5432/data",
                        "fedor", "");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        usersRepository.save(new User(5, "Mihail", "mma@gmail.com", "itisTop1337"));
        System.out.println(usersRepository.findAll());
        usersRepository.update(new User(5, "Ilon", "teslaInterprice@ilon.mask", "neuroLink0"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(5);
        System.out.println(usersRepository.findAll());
        usersRepository.save(new User(1, "Robert", "robert-plaudis@mail.ru", "Platina300" ));
        System.out.println(usersRepository.findById(5));
        System.out.println(usersRepository.findById(1));
        usersRepository.delete(1);
        usersRepository.delete(1);
        System.out.println(usersRepository.findAll());

    }
}
