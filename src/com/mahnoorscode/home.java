package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame implements ActionListener {

    private final JButton btn_createMemo, btn_order, btn_sale, btn_viewProduct, btn_view, btn_back;

    home(){
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);

        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(null);

        JLabel lHome = new JLabel();
        lHome.setText("<html><body><b><font color = #34495E>HOME</font></b></body></html>");
        lHome.setFont(new Font("times new roman",Font.BOLD,40));
        lHome.setBounds((fullScreenSize.width/2-20),20,150,80);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        p1.setBounds(fullScreenSize.width/2-55,fullScreenSize.height/2-150,200,300);


        add(p1);
        add(lHome);

        ImageIcon homePage = new ImageIcon("images/hatim2.jpg");
        Image img = homePage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        homePage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(homePage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);

        btn_createMemo = new JButton("Create Memo");
        btn_createMemo.setFont(new Font("Serif",Font.BOLD,15));
        btn_createMemo.setOpaque(true);
        btn_createMemo.setContentAreaFilled(true);
        btn_createMemo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_order = new JButton("Order Item");
        btn_order.setFont(new Font("Serif",Font.BOLD,15));
        btn_order.setOpaque(true);
        btn_order.setContentAreaFilled(true);
        btn_order.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_sale = new JButton("Sale Info");
        btn_sale.setFont(new Font("Serif",Font.BOLD,15));
        btn_sale.setOpaque(true);
        btn_sale.setContentAreaFilled(true);
        btn_sale.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_viewProduct = new JButton("View Product");
        btn_viewProduct.setFont(new Font("Serif",Font.BOLD,15));
        btn_viewProduct.setOpaque(true);
        btn_viewProduct.setContentAreaFilled(true);
        btn_viewProduct.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_view = new JButton("View Stock");
        btn_view.setFont(new Font("Serif",Font.BOLD,15));
        btn_view.setOpaque(true);
        btn_view.setContentAreaFilled(true);
        btn_view.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_back = new JButton("Logout");
        btn_back.setFont(new Font("Serif",Font.BOLD,15));
        btn_back.setOpaque(true);
        btn_back.setContentAreaFilled(true);
        btn_back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        p1.setLayout(new GridLayout(6,1,0,4));
        p1.add(btn_createMemo);
        p1.add(btn_order);
        p1.add(btn_sale);
        p1.add(btn_viewProduct);
        p1.add(btn_view);
        p1.add(btn_back);

        btn_createMemo.addActionListener(this);
        btn_order.addActionListener(this);
        btn_sale.addActionListener(this);
        btn_viewProduct.addActionListener(this);
        btn_view.addActionListener(this);
        btn_back.addActionListener(this);

        getContentPane().add(lScreen);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //resize(0, this);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btn_createMemo){
            dispose();
            new createMemo();
        }
        if(e.getSource() == btn_order){
            dispose();
            new makeOrder();
        }
        if(e.getSource() == btn_sale){
            dispose();
            new saleStat();
        }
        if(e.getSource() == btn_viewProduct){
            dispose();
            new ViewProduct();
        }
        if(e.getSource() == btn_view){
            dispose();
            new viewStock();
        }
        if(e.getSource() == btn_back){
            this.dispose();
            new StockManager();
        }
    }

}
