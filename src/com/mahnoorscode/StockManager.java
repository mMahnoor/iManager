package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockManager extends JFrame implements ActionListener {
    private JButton btn_login, btn_signIn;
    StockManager(){
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        getContentPane().setLayout(null);

        //ImageIcon homePage = new ImageIcon("C:\\Users\\iManager_support\\iManager\\images\\welcome.jpeg");
        ImageIcon homePage = new ImageIcon("images/welcome.jpeg");

        Image img = homePage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        homePage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(homePage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);

        /*JLabel title_label = new JLabel();
        title_label.setBounds(fullScreenSize.width/2-130,fullScreenSize.height/2-400,500,500);
        title_label.setText("<html><body><center>Welcome<br>To<br>iManager</body></html>");
        title_label.setFont(new Font("Verdana", Font.BOLD, 50));
        title_label.setForeground(new Color(88, 24, 69 ));
*/
        //ImageIcon wlc = new ImageIcon("C:\\Users\\iManager_support\\iManager\\images\\wlc1.png");
        ImageIcon wlc = new ImageIcon("images/wlc1.png");
        Image w = wlc.getImage();
        Image med = w.getScaledInstance(500,300,Image.SCALE_SMOOTH);
        wlc = new ImageIcon(med);
        JLabel title_label = new JLabel(wlc);
        //title_label.setIcon(wlc);
        title_label.setLayout(null);
        title_label.setBounds(0,100,fullScreenSize.width,fullScreenSize.height/2-100);
        add(title_label);


        btn_login = new JButton("LOGIN");
        btn_signIn = new JButton("SIGN IN");

        btn_login.setFont(new Font("Verdana", Font.BOLD, 20));
        btn_signIn.setFont(new Font("Verdana", Font.BOLD, 20));


        btn_login.setContentAreaFilled(false);
        btn_signIn.setContentAreaFilled(false);
        btn_signIn.setBorderPainted(false);
        btn_login.setBorderPainted(false);

        btn_login.setBounds(fullScreenSize.width/2-100,520,200,50);
        btn_signIn.setBounds(fullScreenSize.width/2-100,590,200,50);

        add(btn_login);
        add(btn_signIn);

        btn_login.addActionListener(this);
        btn_signIn.addActionListener(this);

        add(title_label);

        getContentPane().add(lScreen);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btn_login){
            dispose();
            new Login();
        }
        if(e.getSource() == btn_signIn){
            //new SignIn();
        }

    }
}
