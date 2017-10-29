package com.proiectAcreditare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RegisterGUI extends JFrame {
    JButton exitButton,registerButton;
    JLabel materialLabel, cumparatorLabel, coliLabel;
    JTextField materialField, cumparatorField;
    JList coliJList;
    DefaultListModel coliList = new DefaultListModel();
    Statement myStmt;
    public RegisterGUI(Statement myStmt) {
        this.myStmt = myStmt;

        this.setTitle("Material ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);

        JPanel regPanel = new JPanel();
        regPanel.setBackground(Color.CYAN);
        regPanel.setLayout(new FlowLayout(FlowLayout.LEFT,15,20));

        materialLabel = new JLabel("material");
        materialLabel.setFont(new Font("Arial",Font.BOLD,16));
        regPanel.add(materialLabel);

        materialField = new JTextField(30);
        materialField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(materialField);

        cumparatorLabel = new JLabel("cumparator");
        cumparatorLabel.setFont(new Font("Arial",Font.BOLD,16));
        regPanel.add(cumparatorLabel);

        cumparatorField = new JTextField(30);
        cumparatorField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(cumparatorField);
        coliLabel = new JLabel("Year");
        coliLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(coliLabel);
        Integer[] aniStudiu = {1,2,3,4,5};
        for(Integer an:aniStudiu) {
            coliList.addElement(an);
        }

        coliJList = new JList(coliList);
        coliJList.setFont(new Font("Arial", Font.BOLD, 16));
        coliJList.setVisibleRowCount(5);

        regPanel.add(coliJList);
        mainPanel.add(regPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150,40));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton = new JButton("REGISTER");
        registerButton.setPreferredSize(new Dimension(150,40));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));

        ListenForButton listenForButton = new ListenForButton();
        exitButton.addActionListener(listenForButton);
        registerButton.addActionListener(listenForButton);

        buttonPanel.add(exitButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        this.setVisible(true);
    }
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitButton){
                System.exit(0);
            } else if (e.getSource() == registerButton) {
                Material material = new Material(MainApp.materialCount, materialField.getText(),cumparatorField.getText(),(int)coliJList.getSelectedValue());
                material.saveMaterial(myStmt);
                MainApp.materialCount++;
                new WelcomeGUI(myStmt);
                dispose();
            }
        }
    }
}

