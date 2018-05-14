package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver extends JFrame {
    JTextArea area = new JTextArea("Put Text Here");
    JTextArea results = new JTextArea("Results Show Here");
    JButton alphabeticalMatchButton = new JButton("Match Prefix and Sort");
    JButton wordCountRangeButton = new JButton("Get Words Between Count Range");
    JTextField prefix = new JTextField("Prefix");
    JTextField min = new JTextField("Min");
    JTextField max = new JTextField("Max");

    public Driver() {
        setTitle("Driver");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 6));
        alphabeticalMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    results.setText(
                            new Parser(area.getText()).alphabeticalMatchSort(
                                    prefix.getText()
                            ).toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(alphabeticalMatchButton);
        add(prefix);
        wordCountRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    results.setText(new Parser(area.getText())
                            .wordsBetweenTwoCounts(
                                    Integer.parseInt(min.getText()),
                                    Integer.parseInt(max.getText())
                            ).toString());
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }

            }
        });
        add(wordCountRangeButton);
        add(min);
        add(max);
        add(new Component() {
        });
        add(new Component() {
        });
        add(new Component() {
        });
        add(new Component() {
        });
        add(new Component() {
        });

        area.setRows(5);
        add(area);
        add(new Component() {
        });
        add(new Component() {
        });
        add(new Component() {
        });
        results.setRows(5);
        add(results);
    }

    public static void main(String[] args) {
        Driver ex = new Driver();
        ex.setVisible(true);
    }
}

