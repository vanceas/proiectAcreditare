package com.proiectAcreditare;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class ListGUI extends JFrame {
    JButton exitButton, backButton;
    Statement myStmt;
    Vector data, row, columnNames;
    public ListGUI(Statement myStmt) {
        this.myStmt = myStmt;

        this.setTitle("List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);
        JPanel listPanel = new JPanel();
        columnNames = new Vector();
        columnNames.add("id");
        columnNames.add("material");
        columnNames.add("cumparator");
        columnNames.add("nrColi");
        try {
            ResultSet myRs = myStmt.executeQuery("select * from depozit");
            data = new Vector();
            while (myRs.next()) {
                row = new Vector();
                row.add(myRs.getString(1));
                row.add(myRs.getString(2).substring(0, 15));
                row.add(myRs.getString(3).substring(0, 15));
                row.add(myRs.getString(4));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable jTable = new JTable(data, columnNames);
        jTable.setRowHeight(jTable.getRowHeight() + 10);
        jTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JTableHeader header = jTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        listPanel.add(scrollPane);
        mainPanel.add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        ListenForButton listenForButton = new ListenForButton();
        exitButton.addActionListener(listenForButton);
        backButton.addActionListener(listenForButton);

        buttonPanel.add(exitButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        this.setVisible(true);
    }

    private class ListenForButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitButton) {

                System.exit(0);
            } else if (e.getSource() == backButton) {
                new WelcomeGUI(myStmt);
                dispose();
            }
        }
    }
}

