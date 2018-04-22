package view;

import controller.actions.Create;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ivand on 12.04.2018.
 */
public class NewRecordWindow {
    private static final Logger LOG = Logger.getLogger(NewRecordWindow.class);

    //Заголовок окна
    private static final String FRAME_TEXT = "Создание новой записи...";

    //Заголовки столбцов
    private static final String TITLE = "Название";
    private static final String AUTHOR = "Автор";
    private static final String DATE_OF_PUBLICATION = "Дата издания (дд.мм.гггг)";
    private static final String BOOK_READ = "Книга прочитана";

    private final static JLabel TITLE_LABEL = new JLabel(TITLE);
    private final static JLabel AUTHOR_LABEL = new JLabel(AUTHOR);
    private final static JLabel DATE_LABEL = new JLabel(DATE_OF_PUBLICATION);
    private final static JLabel IS_READ_LABEL = new JLabel(BOOK_READ);

    //Кнопки
    private static final String ADD_RECORD = "Добавить запись";
    private static final String CANCEL = "Главное меню";
    private static final JButton CREATE_NEW_RECORD_BUTTON = new JButton(ADD_RECORD);
    private static final JButton EXIT_BUTTON = new JButton(CANCEL);

    //Предупреждения
    private static final String INPUT_TITLE = "Введите название";
    private static final String INPUT_AUTHOR = "Введите автора";
    private static final String RECORD_ADDED_SUCCESSFULLY = "Запись добавлена!";

    private static JLabel titleWarnLabel;
    private static JLabel authorWarnLabel;
    private static JLabel dateWarnLabel;
    private static JLabel statusTextLabel;

    //Текстовая трока для передачи в класс BookModel
    private static String resultDate;

    //Форматирование даты
    private static final String DATE_FORMAT_PATTERN_INPUT = "dd.MM.yyyy";
    private static final String DATE_FORMAT_PATTERN_FOR_DB = "yyyy-MM-dd";
    private static final String INCORRECT_DATE_FORMAT = "Неверный формат даты.";

    //Поля ввода
    private static JTextField titleTextField;
    private static JTextField authorTextField;
    private static JTextField dateOfPublicationTextField;
    private static JCheckBox isReadCheckBox;

    private static final JPanel INPUT_DATA_PANEL = new JPanel();
    private static final JPanel BUTTON_PANEL = new JPanel();

    private JFrame frame;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SIZE_WIDTH = 800;
    private static final int SIZE_HEIGHT = 200;
    private static final int LOCATION_X = (SCREEN_SIZE.width - SIZE_WIDTH) / 2;
    private static final int LOCATION_Y = (SCREEN_SIZE.height - SIZE_HEIGHT) / 2;

    //Лог
    private static final String LOG_NULL_POINT = "Необходимо заполнить поле: ";

    public static void showGUI() {
        NewRecordWindow newRecordForm = new NewRecordWindow();
        newRecordForm.initComponents();
        newRecordForm.buildGui();
        newRecordForm.initActions();
    }

    private void initComponents() {
        GridLayout gridLayout = new GridLayout(3, 4, 15, 5);

        INPUT_DATA_PANEL.setLayout(gridLayout);

        titleWarnLabel = new JLabel();
        authorWarnLabel = new JLabel();
        dateWarnLabel = new JLabel();

        statusTextLabel = new JLabel();
        statusTextLabel.setHorizontalAlignment(SwingConstants.CENTER);

        titleTextField = new JTextField();
        authorTextField = new JTextField();
        dateOfPublicationTextField = new JTextField();
        isReadCheckBox = new JCheckBox();

        CREATE_NEW_RECORD_BUTTON.setBackground(Color.green);
        EXIT_BUTTON.setBackground(Color.lightGray);
    }

    private void initActions() {
        CREATE_NEW_RECORD_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields()) {
                    new Create().executeAction();
                    NewRecordWindow.setLabelText(NewRecordWindow.getStatusTextLabel(), RECORD_ADDED_SUCCESSFULLY);
                    NewRecordWindow.setLabelColor(NewRecordWindow.getStatusTextLabel(), Color.GREEN);
                    NewRecordWindow.clearTextFieldsAndWarnings();
                    LOG.info(RECORD_ADDED_SUCCESSFULLY);
                }
            }
        });

        EXIT_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void buildGui() {
        INPUT_DATA_PANEL.add(TITLE_LABEL);
        INPUT_DATA_PANEL.add(AUTHOR_LABEL);
        INPUT_DATA_PANEL.add(DATE_LABEL);
        INPUT_DATA_PANEL.add(IS_READ_LABEL);
        INPUT_DATA_PANEL.add(titleTextField);
        INPUT_DATA_PANEL.add(authorTextField);
        INPUT_DATA_PANEL.add(dateOfPublicationTextField);
        INPUT_DATA_PANEL.add(isReadCheckBox);
        INPUT_DATA_PANEL.add(titleWarnLabel);
        INPUT_DATA_PANEL.add(authorWarnLabel);
        INPUT_DATA_PANEL.add(dateWarnLabel);

        BUTTON_PANEL.add(EXIT_BUTTON);
        BUTTON_PANEL.add(CREATE_NEW_RECORD_BUTTON);

        frame = new JFrame();
        frame.setTitle(FRAME_TEXT);
        frame.setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        frame.setVisible(true);
        frame.getContentPane().add(BorderLayout.NORTH, INPUT_DATA_PANEL);
        frame.getContentPane().add(BorderLayout.CENTER, statusTextLabel);
        frame.getContentPane().add(BorderLayout.SOUTH, BUTTON_PANEL);
    }

    public static JTextField getTitleTextField() {
        return titleTextField;
    }

    public static JTextField getAuthorTextField() {
        return authorTextField;
    }

    public static JTextField getDateOfPublicationTextField() {
        return dateOfPublicationTextField;
    }

    public static JCheckBox getIsReadCheckBox() {
        return isReadCheckBox;
    }


    public static JLabel getStatusTextLabel() {
        return statusTextLabel;
    }

    public static String getResultDate() {
        return resultDate;
    }

    public static void setLabelText(JLabel label, String string) {
        label.setText(string);
    }

    public static void setLabelColor(JLabel label, Color color) {
        label.setForeground(color);
    }

    public static void clearTextFieldsAndWarnings() {
        setLabelText(titleWarnLabel, null);
        setLabelText(authorWarnLabel, null);
        setLabelText(dateWarnLabel, null);

        titleTextField.setText(null);
        authorTextField.setText(null);
        dateOfPublicationTextField.setText(null);

        isReadCheckBox.setSelected(false);
    }

    private boolean checkFields() {
        checkAuthor();
        checkTitle();
        checkDateOfPublication();
        return checkDateOfPublication() && checkTitle() && checkAuthor();
    }

    private boolean checkDateOfPublication() {
        String inputDate = NewRecordWindow.getDateOfPublicationTextField().getText();
        SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat(DATE_FORMAT_PATTERN_INPUT);
        SimpleDateFormat simpleDateFormatForDB = new SimpleDateFormat(DATE_FORMAT_PATTERN_FOR_DB);
        simpleDateFormatInput.setLenient(false);
        try {
            resultDate = simpleDateFormatForDB.format(simpleDateFormatInput.parse(inputDate));
            NewRecordWindow.setLabelText(dateWarnLabel, null);
            return true;
        } catch (ParseException e) {
            NewRecordWindow.setLabelText(dateWarnLabel, INCORRECT_DATE_FORMAT);
            NewRecordWindow.setLabelColor(dateWarnLabel, Color.red);
            LOG.error(INCORRECT_DATE_FORMAT);
            return false;
        }
    }

    private boolean checkTitle() {
        return checkTextField(TITLE, titleTextField, titleWarnLabel, INPUT_TITLE);
    }

    private boolean checkAuthor() {
        return checkTextField(AUTHOR, authorTextField, authorWarnLabel, INPUT_AUTHOR);
    }

    private boolean checkTextField(String header, JTextField jTextField, JLabel label, String warning) {
        String inputTitle = jTextField.getText();
        if (inputTitle.trim().length() > 0) {
            label.setText(null);
            return true;
        } else {
            NewRecordWindow.setLabelText(label, warning);
            NewRecordWindow.setLabelColor(label, Color.red);
            LOG.error(LOG_NULL_POINT + header);
            return false;
        }
    }
}