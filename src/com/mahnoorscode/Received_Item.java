package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Received_Item extends JFrame implements ActionListener {
    JButton btn_ok, btn_clear;
    JLabel lt, lq;
    JTextField tp,tq;


    Received_Item() {
        setTitle("Received Item");
        setSize(600,300);
        setResizable(false);

        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        ImageIcon homePage = new ImageIcon("images/stock1.jpeg");
        Image img = homePage.getImage();
        Image temp = img.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        homePage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(homePage);
        lScreen.setLayout(null);
        lScreen.setBounds(0, 0, 600, 300);


        JPanel p1 = new JPanel();
        p1.setBounds(0, 20, 570, 100);
        p1.setLayout(null);
        p1.setOpaque(false);
        p1.setLayout(new GridLayout(2, 2, 0, 4));
        add(p1);
        JPanel p2 = new JPanel();
        p2.setBounds(280, 150, 100, 80);
        p2.setLayout(null);
        p2.setOpaque(false);
        p2.setLayout(new GridLayout(2, 1, 0, 4));
        add(p2);
//text field for product entry
        tp = new JTextField();
        tp.setFont(new Font("Serif",Font.PLAIN,20));
        tp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
//label Product
        lt = new JLabel("P.Code:", SwingConstants.CENTER);
        lt.setFont(new Font("Sans Serif", Font.BOLD, 20));

        tq = new JTextField();
        tq.setFont(new Font("Serif",Font.PLAIN,20));
        tq.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
//label quantity
        lq = new JLabel("Quantity:", SwingConstants.CENTER);
        lq.setFont(new Font("Sans Serif", Font.BOLD, 20));


//button ok
        btn_ok = new JButton("ENTER");
        btn_ok.setFont(new Font("Serif", Font.BOLD, 20));
        btn_ok.setOpaque(true);
        btn_ok.setContentAreaFilled(true);
        btn_ok.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

//button remove
        btn_clear = new JButton("CLOSE");
        btn_clear.setFont(new Font("Serif", Font.BOLD, 20));
        btn_clear.setOpaque(true);
        btn_clear.setContentAreaFilled(true);
        btn_clear.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));


//add to panel
        p1.add(lt);
        p1.add(tp);
        p1.add(lq);
        p1.add(tq);
        p2.add(btn_ok);
        p2.add(btn_clear);

        btn_ok.addActionListener(this);
        btn_clear.addActionListener(this);

        getContentPane().add(lScreen);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_ok) {
            int confirm = JOptionPane.showConfirmDialog(null,"The Product "+tp.getText()+" with Quantity "+tq.getText()+" will be updated as received.","Confirm?",JOptionPane.OK_CANCEL_OPTION);
            if(confirm==JOptionPane.OK_OPTION) {
                dbConnection db = new dbConnection();
                db.orderReceived(tp.getText(), tq.getText());
                tp.setText("");
                tq.setText("");
            }
        }
        if(e.getSource()==btn_clear){
            dispose();
        }

    }
}
