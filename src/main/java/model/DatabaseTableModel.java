package model;

import controller.databaseaccess.DBUtilities;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by ivand on 07.05.2018.
 */
public class DatabaseTableModel extends AbstractTableModel {

    private static final String SQL_QUERY = "SELECT * FROM summary;";

    private List columnNames = new ArrayList();
    private List<ArrayList> data = new ArrayList<>();

    public void refreshModel() {
        try (Connection connection = DriverManager.getConnection
                (DBUtilities.getDatabaseUrl(), DBUtilities.getUserName(), DBUtilities.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {
            ResultSetMetaData resultSetGetMetaData = resultSet.getMetaData();
            int columns = resultSetGetMetaData.getColumnCount();

            // Имя столбцов
            for (int i = 1; i <= columns; i++) {
                columnNames.add(resultSetGetMetaData.getColumnName(i));
            }

            // Данные в ячейках
            while (resultSet.next()) {
                ArrayList row = new ArrayList(columns);
                for (int i = 1; i <= columns; i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++) {
            ArrayList subArray = data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++) {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++)
            columnNamesVector.add(columnNames.get(i));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column).toString();
    }
}
