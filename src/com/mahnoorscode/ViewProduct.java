package com.mahnoorscode;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProduct extends JFrame implements ActionListener {

    JButton btn_ok, btn_back, btn_new;
    JPanel p1, p2, p3;
    JLabel lName, lCode, lMRP, lImg;

    JTextField tp;
    public ViewProduct(){
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);

        setTitle("View Product");
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(null);

        p1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                int width = getWidth();
                int height = getHeight();

                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                //Draws the rounded panel with borders.
                graphics.setColor(new Color(241, 235, 245));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                graphics.setColor(new Color(0, 77, 77));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
            }
        };
        //LineBorder line = new LineBorder(Color.DARK_GRAY, 2, true);
        //p1.setBorder(line);
        p1.setBounds(50, 250, 500, 300);
        p1.setLayout(null);
        p1.setOpaque(false);

        p1.setLayout(new GridLayout(3, 1, 0, 4));

        add(p1);

        //dbConnection db = new dbConnection();
        //String[] img = db.ProductInfo(p);
        p3 = new JPanel();
        p3.setBounds(200, 20, 800, 50);
        p3.setLayout(null);
        p3.setOpaque(false);
        p3.setLayout(new GridLayout(1, 3, 8, 0));

        add(p3);

        JLabel lp = new JLabel("Product Name:");
        lp.setFont(new Font("Sans Serif", Font.BOLD, 30));
        lp.setForeground(Color.GRAY);
        tp = new JTextField();
        tp.setBackground(new Color(237, 232, 239 ));
        tp.setFont(new Font("Serif",Font.PLAIN,20));
        tp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));

        btn_ok = new JButton("DETAILS");
        btn_ok.setFont(new Font("Serif", Font.BOLD, 20));
        btn_ok.setOpaque(true);
        btn_ok.setBackground(new Color(200,196,193,255 ));
        btn_ok.setContentAreaFilled(true);
        btn_ok.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        btn_ok.addActionListener(this);

        p3.add(lp);
        p3.add(tp);
        p3.add(btn_ok);
//233,223,205,255
        //235,230,224,255
        //254,247,228,255
        //224,221,216,255
        lName = new JLabel("View detail here...",SwingConstants.CENTER);
        lName.setFont(new Font("Sans Serif", Font.BOLD, 15));
        lCode = new JLabel("",SwingConstants.CENTER);
        lCode.setFont(new Font("Sans Serif", Font.BOLD, 15));
        lMRP = new JLabel("",SwingConstants.CENTER);
        lMRP.setFont(new Font("Sans Serif", Font.BOLD, 15));

        p1.add(lCode);
        p1.add(lName);
        p1.add(lMRP);

        p2 = new JPanel();
        p2.setOpaque(true);
        p2.setBackground(new Color(237, 232, 239 ));
        p2.setBounds(600,130,750,500);
        add(p2);

        lImg = new JLabel();
        lImg.setBounds(600,100,700,500);
        lImg.setLayout(null);
        p2.add(lImg);

        btn_new = new JButton("ADD NEW");
        btn_new.setFont(new Font("Serif", Font.BOLD, 20));
        btn_new.setOpaque(true);
        btn_new.setContentAreaFilled(true);
        btn_new.setBackground(new Color(200,196,193,255 ));
        btn_new.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        btn_new.setBounds(350,640,150,50);
        btn_new.addActionListener(this);

        btn_back = new JButton("BACK");
        btn_back.setFont(new Font("Serif", Font.BOLD, 20));
        btn_back.setOpaque(true);
        btn_back.setContentAreaFilled(true);
        btn_back.setBackground(new Color(200,196,193,255  ));
        btn_back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        btn_back.setBounds(50,640,150,50);
        btn_back.addActionListener(this);

        add(btn_back);
        add(btn_new);

        getContentPane().setBackground(new Color(224,221,216,255));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_ok) {

            dbConnection db = new dbConnection();
            String[] img = db.ProductInfo(tp.getText());

            lName.setText("PRODUCT NAME: "+img[1]);
            lCode.setText("PRODUCT CODE: "+tp.getText());
            lMRP.setText("MRP(TK):  "+img[2]);

            ImageIcon icon = new ImageIcon(img[0]);
            Image ig = icon.getImage();
            Image temp = ig.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
            icon = new ImageIcon(temp);
            lImg.setIcon(icon);

        }

        if(e.getSource()==btn_back){
            dispose();
            new home();
        }
        if(e.getSource()==btn_new){
            new ProductInfo();
        }
    }

}
//YIUYIYLIKYIUYKYKYKYKBYKKKUYIYK
