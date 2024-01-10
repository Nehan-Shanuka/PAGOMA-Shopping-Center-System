package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ProductDisplayController;
import com.oop_cw.pase_01.controller.User;
import com.oop_cw.pase_01.model.CurrentUser;
import com.oop_cw.pase_01.model.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserLogin extends JFrame {
    private JTextField txtUsername;
    private JPasswordField pswField;
    private JButton btnLogin;
    private JButton btnBypass;
    UserList userList = UserList.getInstance();
    public UserLogin() {

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new GridLayout(3,1));

        JPanel northPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        JPanel southPanel = new JPanel(new FlowLayout());

        JPanel centerPanel1 = new JPanel(new FlowLayout());
        JPanel centerPanel2 = new JPanel(new FlowLayout());

        JLabel lblWelcomeNote = new JLabel("\n" + "Welcome");
        Font fontResize1 = lblWelcomeNote.getFont().deriveFont(25f);
        lblWelcomeNote.setFont(fontResize1);

        JLabel lblUsername = new JLabel("USERNAME");
        JLabel lblPassword = new JLabel("PASSWORD");

        txtUsername = new JTextField();
        Font fontResize2 = txtUsername.getFont().deriveFont(15f);
        txtUsername.setFont(fontResize2);
        txtUsername.setColumns(10);
        pswField = new JPasswordField();
        pswField.setFont(fontResize2);
        pswField.setColumns(10);

        btnLogin = new JButton("Login");
        btnBypass = new JButton("Non-registered Shopping");

        btnLogin.addActionListener(new LoginBtnEventListener());
        btnBypass.addActionListener(new LoginBtnEventListener());

        northPanel.add(lblWelcomeNote);
        centerPanel1.add(lblUsername);
        centerPanel1.add(txtUsername);
        centerPanel2.add(lblPassword);
        centerPanel2.add(pswField);
        southPanel.add(btnLogin);
        southPanel.add(btnBypass);

        centerPanel.add(centerPanel1);
        centerPanel.add(centerPanel2);

        mainContainer.add(northPanel);
        mainContainer.add(centerPanel);
        mainContainer.add(southPanel);
    }

    public class LoginBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentUser currentUserInstance = CurrentUser.getInstance();

            if (e.getSource() == btnLogin) {
                for (User user: userList.getUserList()) {

                    if (Objects.equals(user.getUsername(), txtUsername.getText())) {
                        if (Objects.equals(user.getPassword(), String.valueOf(pswField.getPassword()))) {
                            currentUserInstance.setUserStatusTrue();
                            ProductDisplayController productDisplayController = new ProductDisplayController();
                            dispose();
                        }
                    } else {
                        System.out.println("User does not exists!");
                    }

                }
            } else if (e.getSource() == btnBypass) {
                currentUserInstance.setUserStatusFalse();
                ProductDisplayController productDisplayController = new ProductDisplayController();
                dispose();
            }

        }
    }
}
