package com.doslab.human_detector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBase {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/Human_detector");
        config.setUsername("system-user");
        config.setPassword("100415");
        config.setMaximumPoolSize(10); // Максимальное количество соединений в пуле
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection(); // Получаем соединение из пула
    }

    public static Boolean is_old(int user_id) {
        String query = "SELECT COUNT(*) FROM users WHERE id = ?";
        try (Connection connection = getConnection(); // Получаем соединение из пула
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Если количество больше 0, значит запись существует
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User get_user(String username) {
        String query = "SELECT id, username, first_name, middle_name, last_name, password_hash, role_id FROM users WHERE username = ?";
        User user = null;

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String middle_name = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String password_hash = resultSet.getString("password_hash");
                int role_id = resultSet.getInt("role_id");

                user = new User(id, username, first_name, middle_name, last_name, password_hash, role_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void add_user(String username, String first_name, String middle_name, String last_name,
            String password_hash, int role_id) {
        String query = "INSERT INTO users (username, first_name, middle_name, last_name, password_hash, role_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, middle_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setString(5, password_hash);
            preparedStatement.setInt(6, role_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Role get_role(int role_id) {
        String query = "SELECT id, name FROM roles WHERE id = ?";
        Role role = null;

        try (Connection connection = getConnection(); // Получаем соединение из пула
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, role_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                role = new Role(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

}