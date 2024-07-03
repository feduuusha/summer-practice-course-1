package ru.itis.shop.app;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;
import ru.itis.shop.users.repositories.impl.UsersRepositoryFileImpl;

import java.util.UUID;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class Main {
    // сборка компонентов системы и клиентский код
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");
        String id = UUID.randomUUID().toString();
        User user = new User(id, "Kolya", "kolya@gmail.com", "kOlya007");
        usersRepository.save(user);
        System.out.println(usersRepository.findAll());
        usersRepository.delete(user.getId());
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(id));
        User newUser = new User(id, "Petya", "petya@gmail.com", "password123");
        usersRepository.save(user);
        usersRepository.update(newUser);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(id));
    }
}
