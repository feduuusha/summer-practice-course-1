package ru.itis.shop.users.repositories.impl;

import ru.itis.shop.users.models.User;
import ru.itis.shop.users.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    // language=sql
    public static final String SQL_INSERT_INTO_USER = "insert into \"user\" (id, first_name, password, email) values (?, ?, ?, ?);";
    private static final String SQL_SELECT_ALL = "select * from  \"user\" ;";
    public static final String SQL_UPDATE_USER = "update \"user\" set first_name = ?, password = ?, email = ? where id = ?;";
    public static final String SQL_DELETE_FROM_USER = "delete  from \"user\" where id = ?";
    public static final String SQL_SELECT_FROM_USER_WHERE_ID = "select * from \"user\" where id = ?";

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_USER)) {
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_FROM_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> optional = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optional = Optional.of(new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                                resultSet.getString("email"), resultSet.getString("password")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return optional;
    }
}
