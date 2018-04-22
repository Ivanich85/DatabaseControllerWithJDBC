package view;

import controller.actions.*;
import controller.actions.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by ivand on 11.04.2018.
 */
public class MainMenuWindow {

    private static final String FRAME_TEXT = "DatabaseControllerWithJDBC";
    private static final String HEADER_TEXT = "Добро пожаловать в главное меню!";
    private static final String CREATE_BUTTON_TEXT = "Добавить запись";
    private static final String READ_BUTTON_TEXT = "Вывести данные";
    private static final String UPDATE_BUTTON_TEXT = "Редактировать запись";
    private static final String DELETE_BUTTON_TEXT = "Удалить запись";
    private static final String EXIT_BUTTON_TEXT = "Выход из программы";

    private static final JLabel LABEL_HEADER = new JLabel(HEADER_TEXT);

    private JFrame frame;

    private static final JPanel PANEL_ACTION_BUTTONS = new JPanel();
    private static final JPanel PANEL_EXIT_BUTTON = new JPanel();
    private static final JPanel PANEL_MAIN = new JPanel();

    private static final JButton BUTTON_CREATE = new JButton(CREATE_BUTTON_TEXT);
    private static final JButton BUTTON_READ = new JButton(READ_BUTTON_TEXT);
    private static final JButton BUTTON_UPDATE = new JButton(UPDATE_BUTTON_TEXT);
    private static final JButton BUTTON_DELETE = new JButton(DELETE_BUTTON_TEXT);
    private static final JButton BUTTON_EXIT = new JButton(EXIT_BUTTON_TEXT);

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SIZE_WIDTH = 900;
    private static final int SIZE_HEIGHT = 600;
    private static final int LOCATION_X = (SCREEN_SIZE.width - SIZE_WIDTH) / 2;
    private static final int LOCATION_Y = (SCREEN_SIZE.height - SIZE_HEIGHT) / 2;

    public static void showGUI() {
        MainMenuWindow actionsGUI = new MainMenuWindow();
        actionsGUI.initComponents();
        actionsGUI.initActions();
        actionsGUI.buildGui();
    }

    private void initComponents() {
        LABEL_HEADER.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initActions() {
        BUTTON_CREATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewRecordWindow.showGUI();
            }
        });

        BUTTON_READ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAction(new Read());
            }
        });

        BUTTON_UPDATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAction(new Update());
            }
        });

        BUTTON_DELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAction(new Delete());
            }
        });

        BUTTON_EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void buildGui() {
        PANEL_ACTION_BUTTONS.add(BUTTON_CREATE);
        PANEL_ACTION_BUTTONS.add(BUTTON_READ);
        PANEL_ACTION_BUTTONS.add(BUTTON_UPDATE);
        PANEL_ACTION_BUTTONS.add(BUTTON_DELETE);

        PANEL_EXIT_BUTTON.add(BUTTON_EXIT);

        PANEL_MAIN.add(BorderLayout.WEST, PANEL_ACTION_BUTTONS);
        PANEL_MAIN.add(BorderLayout.EAST, PANEL_EXIT_BUTTON);

        frame = new JFrame();
        frame.setTitle(FRAME_TEXT);
        frame.setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        frame.setVisible(true);
        frame.getContentPane().add(BorderLayout.NORTH, LABEL_HEADER);
        frame.getContentPane().add(BorderLayout.SOUTH, PANEL_MAIN);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static void doAction(Action action) {
        action.executeAction();
    }
}
