package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ProductGUIController;
import com.oop_cw.pase_01.model.User;
import com.oop_cw.pase_01.model.CurrentUser;
import com.oop_cw.pase_01.model.UserList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserLoginDisplay extends JFrame {
    private JTextField txtUsername;
    private JPasswordField pswField;
    private JButton btnLogin;
    private JButton btnBypass;
    UserList userList = UserList.getInstance();
    CurrentUser currentUserInstance;
    public UserLoginDisplay() {

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(600, 80));
        northPanel.setBorder(new EmptyBorder(20, 120, 20, 0));
        northPanel.setBackground(new Color(53, 94, 59, 255));


        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        centerPanel.setPreferredSize(new Dimension(600, 50));
        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.setPreferredSize(new Dimension(600, 100));

        JPanel centerPanel1 = new JPanel(new GridLayout(1, 2));
//        centerPanel1.setBackground(new Color(53, 94, 59, 255));

        JPanel centerPanel1Sub1 = new JPanel(new BorderLayout());
        centerPanel1Sub1.setBorder(new EmptyBorder(10, 180, 10, 0));
        JPanel centerPanel1Sub2 = new JPanel(new BorderLayout());
        centerPanel1Sub2.setBorder(new EmptyBorder(9, 0, 9, 100));

        JPanel centerPanel2 = new JPanel(new GridLayout(1, 2));
//        centerPanel2.setBackground(new Color(53, 94, 59, 255));

        JPanel centerPanel2Sub1 = new JPanel(new BorderLayout());
        centerPanel2Sub1.setBorder(new EmptyBorder(10, 180, 10, 0));
        JPanel centerPanel2Sub2 = new JPanel(new BorderLayout());
        centerPanel2Sub2.setBorder(new EmptyBorder(9, 0, 9, 100));

        JLabel lblWelcomeNote = new JLabel("PAGOMA SHOPPING CENTER");
        Font fontResize1 = lblWelcomeNote.getFont().deriveFont(20f);
        lblWelcomeNote.setFont(fontResize1);
        lblWelcomeNote.setForeground(Color.WHITE);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setFont(lblUsername.getFont().deriveFont(15f));
        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setFont(lblUsername.getFont().deriveFont(15f));

        txtUsername = new JTextField();
        Font fontResize2 = txtUsername.getFont().deriveFont(15f);
        txtUsername.setFont(fontResize2);
        txtUsername.setColumns(10);
        pswField = new JPasswordField();
        pswField.setFont(fontResize2);
        pswField.setColumns(10);

        JPanel southPanel1 = new JPanel(new BorderLayout());
        southPanel1.setBorder(new EmptyBorder(10, 180, 10, 180));
//        southPanel1.setBackground(new Color(53, 94, 59, 255));
        JPanel southPanel2 = new JPanel(new BorderLayout());
        southPanel2.setBorder(new EmptyBorder(10, 180, 10, 180));
//        southPanel2.setBackground(new Color(53, 94, 59, 255));

        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(53, 94, 59, 255));
//        btnLogin.setBackground(Color.WHITE);
        btnLogin.setForeground(Color.WHITE);
//        btnLogin.setForeground(Color.BLACK);
        btnBypass = new JButton("LOGIN WITHOUT AN ACCOUNT");
        btnBypass.setBackground(new Color(53, 94, 59, 255));
//        btnBypass.setBackground(Color.WHITE);
        btnBypass.setForeground(Color.WHITE);
//        btnBypass.setForeground(Color.BLACK);

        btnLogin.addActionListener(new LoginBtnEventListener());
        btnBypass.addActionListener(new LoginBtnEventListener());

        northPanel.add(lblWelcomeNote);
        centerPanel1Sub1.add(lblUsername);
        centerPanel1Sub2.add(txtUsername);

        centerPanel2Sub1.add(lblPassword);
        centerPanel2Sub2.add(pswField);

        centerPanel1.add(centerPanel1Sub1);
        centerPanel1.add(centerPanel1Sub2);

        centerPanel2.add(centerPanel2Sub1);
        centerPanel2.add(centerPanel2Sub2);

        southPanel1.add(btnLogin);
        southPanel2.add(btnBypass);

        centerPanel.add(centerPanel1);
        centerPanel.add(centerPanel2);

        southPanel.add(southPanel1);
        southPanel.add(southPanel2);

        mainContainer.add(northPanel, BorderLayout.NORTH);
        mainContainer.add(centerPanel, BorderLayout.CENTER);
        mainContainer.add(southPanel, BorderLayout.SOUTH);
    }

    public class LoginBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userList.loadUserList();
//            CurrentUser currentUserInstance = CurrentUser.getInstance();
            int iterationCount = 0;
            boolean condition = true;

            if (e.getSource() == btnLogin) {

                for (User user: userList.getUserList()) {
                    iterationCount++;
                    if (Objects.equals(user.getUsername(), txtUsername.getText().toLowerCase())) {
                        condition = false;
                        if (Objects.equals(user.getPassword(),
                                String.valueOf(pswField.getPassword()).toLowerCase())) {

                            currentUserInstance = new CurrentUser(user.getUsername().toLowerCase(),
                                    user.getPassword().toLowerCase(), user.getPurchaseHistory());
                            currentUserInstance.setUserStatusTrue();
//                            CurrentUser currentUser = new CurrentUser(user.getUsername(),
//                                    user.getPassword(), user.getPurchaseHistory());
                            ProductGUIController productGUIController = new ProductGUIController();
                            dispose();
                        } else {
                            System.out.print("Incorrect Password!\n" +
                                    "\nEnter the option : ");
                        }
                    } else if (userList.getUserList().size()-1 == iterationCount && condition) {
                        System.out.print("User does not exists!\n" +
                                "\nEnter the option : ");
                    }
                }
            } else if (e.getSource() == btnBypass) {
                currentUserInstance = new CurrentUser();
                currentUserInstance.setUserStatusFalse();
                ProductGUIController productGUIController = new ProductGUIController();
                dispose();
            }

        }
    }
}
