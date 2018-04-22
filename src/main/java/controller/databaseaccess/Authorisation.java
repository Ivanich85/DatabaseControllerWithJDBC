package controller.databaseaccess;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ivand on 08.04.2018.
 */
public class Authorisation {

    private final static String URL = "jdbc:mysql://localhost:3306/my_books";
    private static final String CONNECTION_WITH_DATABASE = "Соединение с базой данных...";
    private static final String INCORRECT_USER_OR_PASSWORD = "Неверное имя пользователя или пароль. Соединение не установлено!";

    private static String user;
    private static String password;

    public static void connectToDB() {
        try {
            System.out.println(CONNECTION_WITH_DATABASE);
            //Проверяем корреткность имени пользователя и пароля
            DriverManager.getConnection
                    (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
        } catch (SQLException e) {
            System.out.println(INCORRECT_USER_OR_PASSWORD);
            System.exit(0);
        }
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setUser(JTextField textField) {
        user = textField.getText();
    }

    public static void setPassword(JPasswordField passwordField) {
        password = String.valueOf(passwordField.getPassword());
    }

}
