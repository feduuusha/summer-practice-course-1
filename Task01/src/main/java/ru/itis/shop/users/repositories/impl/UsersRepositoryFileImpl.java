package ru.itis.shop.users.repositories.impl;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepositoryFileImpl implements UsersRepository {
    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        // try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(user.getId() + "|" + user.getName() + "|" + user.getEmail() + "|" + user.getPassword());
            writer.newLine();
        } catch (IOException e) { // перехватываю проверяемое исключение
            throw new IllegalStateException(e); // пробрасываем непроверяемое поверх, чтобы остановить цикл работы программы
        }
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] fields = reader.readLine().split("\\|");
                usersList.add(new User(fields[0], fields[1], fields[2], fields[3]));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return usersList;
    }

    @Override
    public void update(User user) {
        List<User> usersList = findAll();
        File fileOfUsers = new File(fileName);
        fileOfUsers.delete();
        try {
            fileOfUsers.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for (User userFromList : usersList) {
            if (userFromList.getId().equals(user.getId())) {
                save(user);
            } else {
                save(userFromList);
            }
        }
    }

    @Override
    public void delete(String id) {
        List<User> usersList = findAll();
        File fileOfUsers = new File(fileName);
        fileOfUsers.delete();
        try {
            fileOfUsers.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for (User user : usersList) {
            if (!user.getId().equals(id)) {
                save(user);
            }
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return findAll().stream().filter((user) -> user.getId().equals(id)).findFirst();
    }
}
