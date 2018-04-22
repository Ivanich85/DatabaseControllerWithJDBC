package controller.actions;

import controller.databaseaccess.DBUtilities;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ivand on 01.04.2018.
 */
public class Delete extends Action {

    private static final String DELETE = "Удаляем запись...";
    private static final String SQL_QUERY_DELETE = "DELETE FROM summary WHERE id = %d;";
    private static final String SQL_QUERY_READ = "SELECT * FROM summary;";
    private static final String INPUT_BOOK_ID = "Введите ID книги: ";
    private static final String INCORRECT_ID_FORMAT = "Введены невеные данные - ID должен быть числом";
    private static final String COLUMN_LABEL = "id";
    private static final String THERE_IS_NO_BOOK_IN_THE_TABLE =
            "Книга с таким ID отсутствует в таблице\nВозврат в главное меню...\n";
    private static final String DELETE_CONFIRMATION = "Y";
    private static final String DELETE_REJECTION = "N";
    private static final String CONFIRM_DELETE =
            "Для удаления введите \'" + DELETE_CONFIRMATION + "\', для отказа от удаления - \'" + DELETE_REJECTION + "\'";
    private static final String INCORRECT_DELETE_CONFIRMATION_INPUT = "Введено некорректное значение\n";
    private static final String RECORD_DELETE = "Запись удалена!\n";
    private static final String DELETE_WAS_NOT_CONFIRMED = "Удаление книги не подтверждено\n";

    //Логи
    private static final Logger log = Logger.getLogger(Delete.class);
    private static final String LOG_BOOK_DELETED = "Книга удалена из таблицы";
    private static final String LOG_DELETE_WAS_NOT_CONFIRMED = "Пользователь не подтвердил удаление книги";
    private static final String BOOK_DELETE_ERROR = "Ошибка при удалении ";

    private int customerId;

    @Override
    public void executeAction() {
        System.out.println(DELETE);
        boolean isIdFound = false;
        try (Connection connection = DriverManager.getConnection
                (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY_READ)) {
            setCustomerId();
            while (resultSet.next()) {
                int searchedId = resultSet.getInt(COLUMN_LABEL);
                if (searchedId == customerId) {
                    isIdFound = true;
                    if (confirmDelete()) {
                        String finalQuery = String.format(SQL_QUERY_DELETE, customerId);
                        statement.executeUpdate(finalQuery);
                        System.out.println(RECORD_DELETE);
                        log.info(LOG_BOOK_DELETED);
                    } else {
                        System.out.println(DELETE_WAS_NOT_CONFIRMED);
                        log.info(LOG_DELETE_WAS_NOT_CONFIRMED);
                    }
                    break;
                }
            }
            if (!isIdFound) {
                System.out.println(THERE_IS_NO_BOOK_IN_THE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(BOOK_DELETE_ERROR + e.getMessage());
        }
    }

    //Проверка формата id
    private void setCustomerId() {
        System.out.println(INPUT_BOOK_ID);
        Scanner scanner = new Scanner(System.in);
        try {
            this.customerId = scanner.nextInt();
        } catch (InputMismatchException imEx) {
            System.out.println(INCORRECT_ID_FORMAT);
            setCustomerId();
        }
    }

    //Подтверждение удаления записи
    private boolean confirmDelete() {
        System.out.println(CONFIRM_DELETE);
        Scanner scanner = new Scanner(System.in);
        String acceptAnswer = scanner.nextLine();
        if (acceptAnswer.toUpperCase().equals(DELETE_CONFIRMATION)) {
            return true;
        } else if (acceptAnswer.toUpperCase().equals(DELETE_REJECTION)) {
            return false;
        } else {
            System.out.println(INCORRECT_DELETE_CONFIRMATION_INPUT);
            confirmDelete();
        }
        return false;
    }
}