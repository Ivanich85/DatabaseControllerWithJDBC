package controller.actions;

import controller.databaseaccess.DBUtilities;
import model.BookModel;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ivand on 29.03.2018.
 */
public class Update extends Action {

    private static final String UPDATE = "Редактируем строку...";
    private static final String SQL_QUERY_UPDATE =
            "UPDATE summary SET title = '%s', author = '%s', date_of_publication = '%s', is_read = '%s' WHERE id = '%d';";
    private static final String SQL_QUERY_READ = "SELECT * FROM summary;";
    private static final String RECORD_UPDATED = "Данные изменены!\n";
    private static final String INPUT_BOOK_ID = "Введите ID записи: ";
    private static final String INCORRECT_ID_FORMAT = "Введены невеные данные - ID должен быть числом";
    private static final String COLUMN_LABEL = "id";
    private static final String THERE_IS_NO_BOOK_IN_THE_TABLE =
            "Книга с таким ID отсутствует в таблице\nВозврат в главное меню...\n";

    //Логи
    private static final Logger log = Logger.getLogger(Update.class);
    private static final String LOG_BOOK_RECORDS_UPDATED = "Данные в книге обновлены";
    private static final String LOG_BOOK_UPDATED = "Книга успешно изменена";
    private static final String LOG_BOOK_UPDATE_ERROR = "Книга не изменена ";

    private BookModel book;
    private int customerId;

    private void createBook() {
        book = new BookModel();
        book.setTitle();
        book.setAuthor();
        book.setDateOfPublication();
        book.setRead();
        log.info(LOG_BOOK_RECORDS_UPDATED);
    }

    @Override
    public void executeAction() {
        System.out.println(UPDATE);
        boolean isIdFound = false;
        try (Connection connection =
                     DriverManager.getConnection
                             (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY_READ)) {
            setCustomerId();
            while (resultSet.next()) {
                int searchedId = resultSet.getInt(COLUMN_LABEL);
                if (searchedId == customerId) {
                    isIdFound = true;
                    createBook();
                    String finalQuery = String.format
                            (SQL_QUERY_UPDATE, book.getTitle(), book.getAuthor(), book.getDateOfPublication(), book.isRead(), customerId);
                    statement.executeUpdate(finalQuery);
                    System.out.println(RECORD_UPDATED);
                    log.info(LOG_BOOK_UPDATED);
                    break;
                }
            }
            if (!isIdFound) {
                System.out.println(THERE_IS_NO_BOOK_IN_THE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(LOG_BOOK_UPDATE_ERROR + e.getMessage());
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
}