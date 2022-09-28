package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;

public class profitChart extends JFrame {

    profitChart(String name){
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
        p3.setBounds(8,100,72,410);
        p4.setBounds(0,530,1000,30);
        p5.setBounds(10,60,100,50);

        //title label
        JLabel lt = new JLabel("Profit Statistics");
        lt.setFont(new Font("Sans Serif",Font.BOLD,30));
        lt.setForeground(Color.GRAY);
        lt.setBounds(0,0,1000,80);

        //label for axes
        JLabel lx = new JLabel("Year - "+name);
        lx.setBounds(0,530,1000,30);
        lx.setFont(new Font("Sans Serif",Font.ITALIC | Font.BOLD,20));
        JLabel ly = new JLabel("Profit(%)");
        ly.setFont(new Font("Sans Serif",Font.ITALIC|Font.BOLD,20));

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
        p2.setBackground(new Color(136, 217, 247));
        p2.setVisible(true);

        p3.setOpaque(true);
        p3.setBackground(new Color(136, 217, 247));
        p3.setVisible(true);

        add(p0);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);


        dbConnection db = new dbConnection();
        Double[] obj1 = db.showProfitChart(name);
        if(obj1[0] != null) {

            String[] obj2 = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            String s = Integer.toString(db.largest);
            int r, n;
            String s1;
            String sub = s.substring(1);
            if (Integer.parseInt(sub) != 0) {
                r = Character.getNumericValue(s.charAt(0));
                r++;
                s1 = Integer.toString(r);
                for (int i = 0; i < s.length() - 1; i++) {
                    s1 += "0";
                }
                n = Integer.parseInt(s1);
            } else {
                n = db.largest;
            }

            p1.setLayout(new GridLayout(1, n, 4, 0));

            p2.setLayout(new GridLayout(1, n, 4, 0));
            p3.setLayout(new GridLayout(11, 1, 0, 20));
            int q = n / 10;
            for (int i = n; i >= 0; i -= q) {

                JLabel lq = new JLabel(Integer.toString(i), SwingConstants.CENTER);
                lq.setFont(new Font("ariel", Font.ITALIC, 12));
                p3.add(lq);
            }

            for (int i = 0; i < 12; i++) {
                JLabel l1 = new JLabel(obj2[i], SwingConstants.CENTER);

                int k = obj1[i].intValue();
                JProgressBar p = new JProgressBar(SwingConstants.VERTICAL, 0, n);
                p.setValue(k);
                p.setStringPainted(true);
                p.setVisible(true);

                p1.add(p);
                p2.add(l1);
            }
        }


        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(197, 226, 237));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}

