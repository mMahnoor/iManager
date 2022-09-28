package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;

public class ProductSaleChart extends JFrame {

    ProductSaleChart(String name){
        setSize(1000,600);

        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JPanel p0 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        p0.setBounds(0,0,1000,80);
        p1.setBounds(80,100,900,400);
        p2.setBounds(80,500,900,30);
        JPanel p3 = new JPanel();
        p3.setBounds(50,100,30,410);
        p4.setBounds(0,530,1000,30);
        p5.setBounds(10,70,100,50);

        //title
        JLabel lt = new JLabel("");
        lt.setText("<html><body><center>Top 10 Selling Products<br>(Year-"+name+")</body></html>");
        lt.setFont(new Font("Sans Serif",Font.BOLD|Font.ITALIC,30));
        lt.setForeground(Color.GRAY);
        lt.setBounds(0,0,1000,80);

        //label for axes
        JLabel lx = new JLabel("Products");
        lx.setFont(new Font("Sans Serif",Font.ITALIC,20));
        JLabel ly = new JLabel("Quantities");
        ly.setFont(new Font("Sans Serif",Font.ITALIC,20));

        p0.add(lt);
        p4.add(lx);
        p5.add(ly);

        p0.setOpaque(false);
        p0.setVisible(true);

        p1.setOpaque(false);
        p1.setVisible(true);

        p4.setOpaque(false);
        p5.setOpaque(false);

        p2.setOpaque(true);
        p2.setBackground(new Color(179, 179, 255));
        p2.setVisible(true);

        p3.setOpaque(true);
        p3.setBackground(new Color(179, 179, 255));
        p3.setVisible(true);

        add(p0);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);


        dbConnection db = new dbConnection();
        String[][] obj = db.showTopSale(10,name);
        if(obj[0] != null) {
            int n = db.largest;

            p1.setLayout(new GridLayout(1, n, 4, 0));

            p2.setLayout(new GridLayout(1, n, 4, 0));
            p3.setLayout(new GridLayout(11, 1, 0, 0));
            int q = n / 10;
            for (int i = n; i >= 0; i -= q) {

                JLabel lq = new JLabel(Integer.toString(i), SwingConstants.CENTER);
                p3.add(lq);
            }

            for (int i = 0; i < 10; i++) {
                JLabel l1 = new JLabel(obj[i][0], SwingConstants.CENTER);
                l1.setFont(new Font("Ariel",Font.ITALIC,10));

                JProgressBar p = new JProgressBar(SwingConstants.VERTICAL, 0, n);
                p.setValue(Integer.parseInt(obj[i][1]));
                p.setStringPainted(true);
                p.setVisible(true);

                p1.add(p);
                p2.add(l1);
            }
        }

        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 230, 255));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
