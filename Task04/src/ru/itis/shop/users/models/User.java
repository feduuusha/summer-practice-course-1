package ru.itis.shop.users.models;

/**
 * Shop
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class User {
    private static int serialId = 1;
    private Integer id;
    private final String name;
    private final String email;
    private final String password;

    public User(Integer id, String name, String email, String password) {
        this(name, email, password);
        this.id = id;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = serialId++;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
