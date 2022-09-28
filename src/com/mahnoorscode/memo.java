package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

public class memo extends JFrame implements ActionListener{

    JButton btn_add, btn_remove, btn_create, btn_cancel, btn_back, btn_balance, btn_print, btn_save;
    JTextField tProduct, tQnt, tPrice, tDiscount, tTotal, tNet, tDate, tAdv, tBal, tTitle, tClientAd, tClientAd1;
    JTable tableMemo;
    String Serial, Product, Quantity, Price, Discount;
    Double[] disc;
    DefaultTableModel model;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


    memo(String total, String disc, String net, String adv, String bal, String[][] tmemo, Double[] Disc){

        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        getContentPane().setLayout(null);
        System.out.println(fullScreenSize);

        JPanel p1 = new JPanel();
        p1.setOpaque(false);
        JPanel p2 = new JPanel();
        p2.setOpaque(false);
        JPanel p3 = new JPanel();
        p3.setOpaque(false);
        JPanel p4 = new JPanel();
        p4.setOpaque(false);
        p4.setLayout(null);


        tTitle = new JTextField("Sales Bill");
        tTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tTitle.setBounds(30,5,900,40);
        tTitle.setFont(new Font("Sans Serif",Font.BOLD,30));
        //tTitle.setBackground(new java.awt.Color(0,0,0,2));


        JLabel lClient = new JLabel("Client's Contact Details:");
        lClient.setFont(new Font("Serif",Font.PLAIN,18));
        lClient.setBounds(30,80,220,40);

        JLabel lTotal = new JLabel("Total Amount(৳):");
        lTotal.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lTDiscount = new JLabel("Discount(৳):");
        lTDiscount.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lNetPrice = new JLabel("Net Payable(৳):");
        lNetPrice.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lAdv = new JLabel("Advance(৳):");
        lAdv.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lBal = new JLabel("Balance(৳):");
        lBal.setFont(new Font("Serif",Font.BOLD,20));

        JLabel lDate = new JLabel("Date:");
        lDate.setFont(new Font("Serif",Font.BOLD,20));

        tClientAd = new JTextField();
        //tClientAd.setHorizontalAlignment(SwingConstants.CENTER);
        tClientAd.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tClientAd.setBackground(new java.awt.Color(0,0,0,1));
        tClientAd.setBounds(250,85,680,35);
        tClientAd.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        tClientAd1 = new JTextField();
        tClientAd1.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tClientAd1.setBackground(new java.awt.Color(0,0,0,1));
        tClientAd1.setBounds(30,120,900,35);
        tClientAd1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        tTotal = new JTextField(total);
        //tTotal.setBackground(new java.awt.Color(0,0,0,2));
        tTotal.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tTotal.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tDiscount = new JTextField(disc);
        //tDiscount.setBackground(new java.awt.Color(0,0,0,2));
        tDiscount.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tDiscount.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tNet = new JTextField(net);
        //tNet.setBackground(new java.awt.Color(0,0,0,2));
        tNet.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tNet.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tAdv = new JTextField(adv);
        //tAdv.setBackground(new java.awt.Color(0,0,0,2));
        tAdv.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tAdv.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tBal = new JTextField(bal);
        //tBal.setBackground(new java.awt.Color(0,0,0,2));
        tBal.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tBal.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tDate = new JTextField();
        tDate.setFont(new Font("Serif",Font.PLAIN,20));

        ImageIcon ICON = new ImageIcon("images/PRINTER.png");
        Image img1 = ICON.getImage();
        Image temp1 = img1.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ICON = new ImageIcon(temp1);

        btn_print = new JButton(ICON);
        btn_print.setFont(new Font("Serif",Font.BOLD,20));
        btn_print.setContentAreaFilled(true);
        btn_print.setBorderPainted(true);
        btn_print.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_print.setBounds(1200,650,60,40);
        add(btn_print);

        ImageIcon ICON1 = new ImageIcon("images/SAVE.png");
        Image img2 = ICON1.getImage();
        Image temp2 = img2.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ICON1 = new ImageIcon(temp2);

        btn_save = new JButton(ICON1);
        btn_save.setFont(new Font("Serif",Font.BOLD,20));
        btn_save.setContentAreaFilled(true);
        btn_save.setBorderPainted(true);
        btn_save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_save.setBounds(1290,650,60,40);
        add(btn_save);


        //add
        //add(p4);
        add(tTitle);
        add(lClient);
        add(tClientAd);
        add(tClientAd1);

        ImageIcon memo = new ImageIcon("images/memo.jpg");
        Image img = memo.getImage();
        Image temp = img.getScaledInstance(350,400,Image.SCALE_SMOOTH);
        memo = new ImageIcon(temp);
        JLabel lScreen = new JLabel(memo);
        lScreen.setLayout(null);
        lScreen.setBounds(980,30,350,400);
        getContentPane().add(lScreen);


        //btn_print.addActionListener(this);

        p1.setBounds(950,480,230,150);
        p2.setBounds(1170,480,180,150);
        //p3.setBounds(480,170,100,200);
        p4.setBounds(30,155,900,700);

        p1.setLayout(new GridLayout(5,1));
        p2.setLayout(new GridLayout(5,1,0,2));
        p3.setLayout(new GridLayout(5,1,0,4));

        //Object[][] data= tmemo;
        String[] fieldName= {"SL","Description","Qty","Unit Price(৳)","Amount"};

        model = new DefaultTableModel(tmemo,fieldName);

        tableMemo = new JTable(model);
        JScrollPane sp = new JScrollPane(tableMemo);
        sp.setBounds(0,0,900,530);
        p4.add(sp);
        sp.setOpaque(false);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.getViewport().setOpaque(false);
        //sp.getViewport().setBackground(Color.lightGray);

        tableMemo.setFont(new Font("Serif", Font.PLAIN, 15));
        tableMemo.setRowHeight(30);
        tableMemo.setOpaque(false);
        //tableMemo.setBackground(Color.black);

        JTableHeader tHead = tableMemo.getTableHeader();
        tHead.setFont(new Font("Serif", Font.BOLD, 20));
        tHead.setForeground(Color.WHITE);
        tHead.setBackground(Color.DARK_GRAY);
        tHead.setReorderingAllowed(false);
        tHead.setPreferredSize(new Dimension(40,40));

        tableMemo.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableMemo.getColumnModel().getColumn(1).setPreferredWidth(350);
        tableMemo.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableMemo.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableMemo.getColumnModel().getColumn(4).setPreferredWidth(100);

        add(p1);
        add(p2);
        //add(p3);
        add(p4);

        p1.add(lTotal);
        p1.add(lTDiscount);
        p1.add(lNetPrice);
        p1.add(lAdv);
        p1.add(lBal);
        p2.add(tTotal);
        p2.add(tDiscount);
        p2.add(tNet);
        p2.add(tAdv);
        p2.add(tBal);

        //listeners
        btn_print.addActionListener(this);
        btn_save.addActionListener(this);

        getContentPane().add(lDate);
        getContentPane().add(tDate);

        lDate.setBounds(710,55,80,30);
        tDate.setBounds(790,55,140,30);
        //tDate.setBackground(new java.awt.Color(0,0,0,1));
        tDate.setFont(new Font("Sans Serif",Font.PLAIN,15));
        tDate.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        Date date = new Date();
        tDate.setText(formatter.format(date));

        this.disc = Disc;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_print) {
            PrinterJob p_job = PrinterJob.getPrinterJob();
            PageFormat format = p_job.defaultPage();
            //format.setOrientation(PageFormat.LANDSCAPE);
            PageFormat newFormat = p_job.pageDialog(format);

            if (format != newFormat) {

                p_job.setPrintable(new Print(this), newFormat);
                if (p_job.printDialog()) {
                    try {
                        p_job.print();
                    } catch (PrinterException p) {
                        p.printStackTrace();
                    }

                }
            }
        }
        if(e.getSource()==btn_save){
            dbConnection db = new dbConnection();
            int n = tableMemo.getRowCount();
            String[][] memo = new String[n][];
            for (int i = 0; i < n; i++) {
                String name = (String) model.getValueAt(i,1);
                String q = (String) model.getValueAt(i, 2);
                System.out.println(q);
                memo[i] = new String[]{name, q};
            }
            //confirm memo
            db.confirmMemo(memo);

            int qn;

            for (int i = 0; i < n; i++) {
                double temp = 0.0;

                String name = (String) model.getValueAt(i,1);
                String mrp = (String) model.getValueAt(i, 3);
                String q = (String) model.getValueAt(i, 2);

                //manage stock
                qn = Integer.parseInt(q);
                temp += Double.parseDouble(mrp);
                //tempT += Double.parseDouble(mrp);

                temp -= temp * (disc[i] / 100);

                temp *= qn;

                //profit
                dbConnection db1 = new dbConnection();
                db1.profitTable(name,temp,qn);

            }

            for (int i = 0; i < n; i++) {
                String PName = (String) model.getValueAt(i,1);
                String QVal = (String) model.getValueAt(i,2);

                dbConnection db2 = new dbConnection();
                db2.saveRecord(PName,QVal);

            }
            JOptionPane.showMessageDialog(null, "Saved");
        }
    }


}
