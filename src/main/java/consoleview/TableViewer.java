package consoleview;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by ivand on 05.04.2018.
 */
public class TableViewer {

    //Ширина столбцов в таблице (для форматирования)
    private static final String SMALL_STRING_WIDTH = " %-10s ";
    private static final String MIDDLE_STRING_WIDTH = " %-25s ";
    private static final String LARGE_STRING_WIDTH = " %-50s ";

    //Имена столбцов в таблице (для форматирования)
    private static final String ID = "id";
    private static final String IS_READ = "is_read";
    private static final String DATE_OF_PUBLICATION = "date_of_publication";

    private static final String TABLE_IS_EMPTY_MESSAGE = "В таблице нет записей";

    public static void showDatabase(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            //Заголовок таблицы
            formatTableRecord(resultSetMetaData, numberOfColumns, null);
            //Тело таблицы
            do {
                formatTableRecord(resultSetMetaData, numberOfColumns, resultSet);
            } while (resultSet.next());
        } else {
            System.out.println(TABLE_IS_EMPTY_MESSAGE);
        }
    }

    //Форматируем ширину колонок
    private static void formatTableRecord(ResultSetMetaData metaData, int columns, ResultSet resSet) throws SQLException {
        String formatter;
        for (int i = 1; i <= columns; i++) {
            if (metaData.getColumnName(i).equals(ID) || metaData.getColumnName(i).equals(IS_READ)) {
                formatter = SMALL_STRING_WIDTH;
            } else if (metaData.getColumnName(i).equals(DATE_OF_PUBLICATION)) {
                formatter = MIDDLE_STRING_WIDTH;
            } else {
                formatter = LARGE_STRING_WIDTH;
            }
            if (resSet != null) {
                System.out.printf(formatter, resSet.getObject(i));
            } else {
                System.out.printf(formatter, metaData.getColumnName(i));
            }
        }
        System.out.println();
    }

}
