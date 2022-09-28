package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.ResultSet;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener {

    JLabel userLabel,passLabel;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin,btnBack;

    Login(){
        setTitle("Login Form");
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        setLayout(null);

        //ImageIcon loginPage = new ImageIcon("C:\\Users\\iManager_support\\iManager\\images\\login-security.png");
        ImageIcon loginPage = new ImageIcon("images/login.jpeg");
        Image img = loginPage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        loginPage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(loginPage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setBounds(fullScreenSize.width/2-200,fullScreenSize.height/2-200,200,100);
        p2.setBounds(fullScreenSize.width/2,fullScreenSize.height/2-200,300,100);

        p1.setLayout(new GridLayout(2,1));
        p2.setLayout(new GridLayout(2,1));

        p1.setOpaque(false);
        p2.setOpaque(false);
        //p1.setBackground(Color.RED);
        //p2.setBackground(Color.RED);

        getContentPane().add(p1);
        getContentPane().add(p2);

        userLabel = new JLabel("Email: ");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Sans Serif", Font.BOLD,20));
        passLabel = new JLabel("Password: ");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Sans Serif", Font.BOLD,20));

        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton("Login");
        btnBack = new JButton("Back");

        p1.add(userLabel);
        p1.add(passLabel);
        p2.add(txtUser);
        p2.add(txtPass);

        btnBack.setBounds(fullScreenSize.width/2-100,fullScreenSize.height/2+170,180,50);
        btnBack.setFont(new Font("Sans Serif",Font.BOLD,20));
        btnBack.setBackground(Color.BLACK);
        btnBack.setOpaque(true);
        btnBack.setBorderPainted(false);
        btnBack.setForeground(Color.WHITE);

        btnLogin.setBounds(fullScreenSize.width/2-100,fullScreenSize.height/2+100,180,50);
        btnLogin.setFont(new Font("Sans Serif",Font.BOLD,20));
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setForeground(Color.WHITE);

        add(btnBack);
        add(btnLogin);

        btnLogin.addActionListener(this);
        btnBack.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(lScreen);
        setVisible(true);
    }

    boolean validateLogin(String email, String pass){
        String emailEx = "[a-z0-9]+@(gmail|yahoo|outlook).com";
        String passEx = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&_]).{6,20}";
        if(Pattern.matches(emailEx,email) && Pattern.matches(passEx,pass)){
            return true;
        }
        else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnLogin){
            String loginEmail = txtUser.getText();
            String loginPass = txtPass.getText();
            if(validateLogin(loginEmail,loginPass)){
                dbConnection dbCon = new dbConnection();
                String selectSql = "select * from User_Admin_Info";
                dbCon.userInfo(selectSql,loginEmail,loginPass);
                if(dbCon.match==1){
                    dispose();
                    new home();
                }
                else if(dbCon.match==0){
                    JOptionPane.showMessageDialog(null,"Invalid User","Try Again",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid Email/Password","Try Again",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource()==btnBack){
            dispose();
            new StockManager();
        }
    }

}


