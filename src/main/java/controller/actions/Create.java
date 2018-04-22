package controller.actions;

import controller.databaseaccess.DBUtilities;
import model.BookModel;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ivand on 22.0 3.2018.
 */
public class Create extends Action {
    private static final String SQL_QUERY =
            "INSERT INTO summary (title, author, date_of_publication, is_read) VALUES ('%s', '%s', '%s', '%s');";

    //Логи
    private static final Logger LOG = Logger.getLogger(Create.class);
    private static final String LOG_BOOK_ADDED = "Книга успешно добавлена в таблицу";
    private static final String LOG_BOOK_ADDING_ERROR = "Книга не добавлена ";

    private BookModel book;

    private void createBook() {
        book = new BookModel();
        book.setTitle();
        book.setAuthor();
        book.setDateOfPublication();
        book.setRead();
    }

    @Override
    public void executeAction() {
        createBook();
        try (Connection connection = DriverManager.getConnection
                (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
             Statement statement = connection.createStatement()) {
            String finalQuery = String.format
                    (SQL_QUERY, book.getTitle(), book.getAuthor(), book.getDateOfPublication(), book.isRead());
            statement.executeUpdate(finalQuery);
            LOG.info(LOG_BOOK_ADDED);
        } catch (SQLException sqlex) {
            LOG.error(LOG_BOOK_ADDING_ERROR + sqlex.getMessage());
        }
    }
}