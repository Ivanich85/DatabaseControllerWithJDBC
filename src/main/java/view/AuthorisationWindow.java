package view;

import controller.databaseaccess.Authorisation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthorisationWindow extends JDialog {
    private JPanel contentPane;
    private JPanel panelDataInput;
    private JPanel panelButtons;
    private JButton buttonSignIn;
    private JButton buttonExit;
    private JTextField textFieldUserName;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel userLabel;

    //Размер окна и координаты окна по центру экрана
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int sizeWidth = 420;
    private static int sizeHeight = 280;
    private static int locationX = (screenSize.width - sizeWidth) / 2;
    private static int locationY = (screenSize.height - sizeHeight) / 2;

    private AuthorisationWindow() {
        setContentPane(contentPane);
        setModal(true);
        initActions();
    }

    private void tryToConnectToDB() {
        Authorisation.setUser(textFieldUserName);
        Authorisation.setPassword(passwordField);
        Authorisation.connectToDB();
        dispose();
    }

    private void exitProgram() {
        System.exit(0);
        dispose();
    }

    public static void showWindow() {
        AuthorisationWindow dialog = new AuthorisationWindow();
        dialog.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initActions() {
        buttonSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tryToConnectToDB();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitProgram();
            }
        });
    }

}