package view;

import javax.swing.*;

/**
 * Created by ivand on 03.05.2018.
 */
public class DBDataTablePanel extends JPanel {

    public String[][] array = new String[][]{{"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"}, {"1", "Изучаем Java", "Кэти Сиерра", "2015", "да"},
            {"2", "Spring in action", "Dan Abnett", "2010", "нет"} };
    public String[] columnsHeader = new String[]{"Id", "Title", "Author", "Year of publication", "Is read?"};

    public DBDataTablePanel(){
        JTable table = new JTable(array, columnsHeader);
        table.setBounds(0, 0, 1280, 900);
        this.add(new JScrollPane(table));
        setVisible(true);
    }
}
