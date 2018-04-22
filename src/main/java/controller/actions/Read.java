package controller.actions;

import controller.databaseaccess.DBUtilities;
import org.apache.log4j.Logger;
import consoleview.TableViewer;

import java.sql.*;

/**
 * Created by ivand on 25.03.2018.
 */
public class Read extends Action {

    private static final String READ_RECORDS = "Выводим данные...";
    private static final String SQL_QUERY = "SELECT * FROM summary;";

    //Логи
    private static final Logger log = Logger.getLogger(Read.class);
    private static final String LOG_TABLE_SHOWN = "Таблица выведена на экран";
    private static final String LOG_TABLE_SHOWN_ERROR = "Ошибка вывода таблицы ";

    @Override
    public void executeAction() {
        System.out.println(READ_RECORDS);
        try (Connection connection = DriverManager.getConnection
                (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {
            TableViewer.showDatabase(resultSet);
            log.info(LOG_TABLE_SHOWN);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(LOG_TABLE_SHOWN_ERROR + e.getMessage());
        }
        System.out.println();
    }
}