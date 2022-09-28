package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class createMemo extends JFrame implements ActionListener {

    private final JButton btn_add, btn_remove, btn_create, btn_done, btn_back, btn_balance, btn_new;
    JTextField tProduct, tQnt, tPrice, tDiscount, tTotal, tNet, tAdv, tBal;
    JTable tableMemo;
    String Serial, Product, Quantity, Price, Discount, Amount;
    String[][] memo;
    DefaultTableModel model;


    createMemo(){
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        getContentPane().setLayout(null);


        JLabel lMemo = new JLabel("Memo",SwingConstants.CENTER);
        lMemo.setFont(new Font("Sans Serif",Font.BOLD,40));
        lMemo.setBounds(0,0,fullScreenSize.width,50);
        add(lMemo);


        //ImageIcon logo = new ImageIcon("C:\\Users\\iManager_support\\iManager\\images\\hatimLogo1.png");
        ImageIcon logo = new ImageIcon("images/hatimLogo1.png");
        Image im = logo.getImage();
        Image t = im.getScaledInstance(350,90,Image.SCALE_SMOOTH);
        logo = new ImageIcon(t);
        JLabel lLogo = new JLabel(logo,SwingConstants.CENTER);
        lLogo.setLayout(null);
        lLogo.setBounds(700,70,650,90);
        lLogo.setBackground(Color.WHITE);
        lLogo.setOpaque(true);
        add(lLogo);
        //lLogo.setIcon(logo,SwingConstants.CENTER);

        JPanel p1 = new JPanel();
        p1.setOpaque(false);
        JPanel p2 = new JPanel();
        p2.setOpaque(false);
        JPanel p3 = new JPanel();
        p3.setOpaque(false);
        JPanel p4 = new JPanel();
        p4.setOpaque(false);
        JPanel p5 = new JPanel();
        p5.setOpaque(false);
        JPanel p6 = new JPanel();
        p6.setOpaque(false);

        JLabel lItem = new JLabel("PRODUCT:");
        lItem.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lQnty = new JLabel("QUANTITY:");
        lQnty.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lPrice = new JLabel("PRICE(৳):");
        lPrice.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lDisc = new JLabel("DISCOUNT(%):");
        lDisc.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lTotal = new JLabel("TOTAL AMOUNT(৳):");
        lTotal.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lNetPrice = new JLabel("NET PAYABLE(৳):");
        lNetPrice.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lAdv = new JLabel("ADVANCE(৳):");
        lAdv.setFont(new Font("Serif",Font.BOLD,20));
        JLabel lBal = new JLabel("BALANCE(৳):");
        lBal.setFont(new Font("Serif",Font.BOLD,20));

        //text fonts
        tProduct = new JTextField();
        //tProduct.setBackground(new java.awt.Color(0,0,0,2));
        tProduct.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tProduct.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tQnt = new JTextField();
        //tQnt.setBackground(new java.awt.Color(0,0,0,2));
        tQnt.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tQnt.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tPrice = new JTextField();
        //tPrice.setBackground(new java.awt.Color(0,0,0,2));
        tPrice.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tPrice.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tDiscount = new JTextField();
        //tDiscount.setBackground(new java.awt.Color(0,0,0,2));
        tDiscount.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tDiscount.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tTotal = new JTextField();
        //tTotal.setBackground(new java.awt.Color(0,0,0,2));
        tTotal.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tTotal.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tNet = new JTextField();
        //tNet.setBackground(new java.awt.Color(0,0,0,2));
        tNet.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tNet.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tAdv = new JTextField();
        //tAdv.setBackground(new java.awt.Color(0,0,0,2));
        tAdv.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tAdv.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tBal = new JTextField();
        //tBal.setBackground(new java.awt.Color(0,0,0,2));
        tBal.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tBal.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        //buttons and button fonts
        btn_add = new JButton("ADD");
        btn_add.setFont(new Font("Serif",Font.BOLD,15));
        //btn_add.setContentAreaFilled(false);
        //btn_add.setBorderPainted(true);
        btn_add.setBackground(Color.LIGHT_GRAY);
        btn_add.setContentAreaFilled(true);
        btn_add.setOpaque(true);
        btn_add.setForeground(Color.BLACK);
        btn_add.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_remove = new JButton("REMOVE");
        btn_remove.setFont(new Font("Serif",Font.BOLD,15));
        btn_remove.setBackground(Color.LIGHT_GRAY);
        btn_remove.setContentAreaFilled(true);
        btn_remove.setOpaque(true);
        btn_remove.setForeground(Color.BLACK);
        btn_remove.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_create = new JButton("CREATE");
        btn_create.setFont(new Font("Serif",Font.BOLD,15));
        btn_create.setBackground(Color.LIGHT_GRAY);
        btn_create.setContentAreaFilled(true);
        btn_create.setOpaque(true);
        btn_create.setForeground(Color.BLACK);
        btn_create.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_done = new JButton("DONE");
        btn_done.setFont(new Font("Serif",Font.BOLD,15));
        btn_done.setBackground(Color.LIGHT_GRAY);
        btn_done.setContentAreaFilled(true);
        btn_done.setOpaque(true);
        btn_done.setForeground(Color.BLACK);
        btn_done.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_balance = new JButton("BALANCE");
        btn_balance.setFont(new Font("Serif",Font.BOLD,15));
        btn_balance.setBackground(Color.LIGHT_GRAY);
        btn_balance.setContentAreaFilled(true);
        btn_balance.setOpaque(true);
        btn_balance.setForeground(Color.BLACK);
        btn_balance.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));

        //new button
        btn_new = new JButton("NEW");
        btn_new.setFont(new Font("Serif",Font.BOLD,20));
        btn_new.setBackground(Color.LIGHT_GRAY);
        btn_new.setContentAreaFilled(true);
        btn_new.setOpaque(true);
        btn_new.setForeground(Color.BLACK);
        btn_new.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_new.setBounds(490,650,100,40);
        add(btn_new);

        //BUTTON BACK
        btn_back = new JButton("BACK");
        btn_back.setFont(new Font("Serif",Font.BOLD,20));
        btn_back.setBackground(Color.LIGHT_GRAY);
        btn_back.setContentAreaFilled(true);
        btn_back.setOpaque(true);
        btn_back.setForeground(Color.BLACK);
        btn_back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
        btn_back.setBounds(50,650,100,40);
        add(btn_back);

        //add listener to buttons
        btn_add.addActionListener(this);
        btn_remove.addActionListener(this);
        btn_create.addActionListener(this);
        btn_done.addActionListener(this);
        btn_back.addActionListener(this);
        btn_balance.addActionListener(this);
        btn_new.addActionListener(this);

        //panel bounds
        p1.setBounds(80,130,150,200);
        p2.setBounds(230,130,230,200);
        p3.setBounds(480,130,100,200);
        p4.setBounds(80,380,210,200);
        p5.setBounds(310,380,270,200);
        p6.setBounds(700,160,850,730);

        //panel layout
        p1.setLayout(new GridLayout(4,1));
        p2.setLayout(new GridLayout(4,1,0,2));
        p3.setLayout(new GridLayout(5,1,0,4));
        p4.setLayout(new GridLayout(4,1));
        p5.setLayout(new GridLayout(4,1,0,2));
        p6.setLayout(null);

        //JTable configuration
        Object[][] data= {};
        String[] fieldName= {"Description","Qty","Unit Price","Amount","(%)"};

        model = new DefaultTableModel(data,fieldName);

        tableMemo = new JTable(model);
        JScrollPane sp = new JScrollPane(tableMemo);
        sp.setBounds(0,0,650,540);

        sp.setOpaque(false);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        p6.add(sp);
        sp.getViewport().setOpaque(false);
        //sp.getViewport().setBackground(Color.lightGray);

        tableMemo.setFont(new Font("Serif", Font.PLAIN, 15));
        tableMemo.setRowHeight(30);
        //tableMemo.setBackground(Color.black);

        JTableHeader tHead = tableMemo.getTableHeader();
        tHead.setFont(new Font("Serif", Font.BOLD, 15));
        tHead.setForeground(Color.BLACK);
        //Color c = new Color(255,102,102);
        //tHead.setBackground(Color.DARK_GRAY);
        tHead.setReorderingAllowed(false);
        tHead.setPreferredSize(new Dimension(40,40));
        ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tableMemo.getColumnModel().getColumn(0).setPreferredWidth(350);
        tableMemo.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableMemo.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableMemo.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableMemo.getColumnModel().getColumn(4).setPreferredWidth(50);
        tableMemo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableMemo.setShowGrid(true);
        tableMemo.setGridColor(Color.GRAY);
        tableMemo.setBackground(Color.LIGHT_GRAY);


        tableMemo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = tableMemo.getSelectedRow();

                Product = (String) model.getValueAt(rowIndex, 0);
                Quantity = (String) model.getValueAt(rowIndex, 1);
                Price = (String) model.getValueAt(rowIndex, 2);
                Amount = (String) model.getValueAt(rowIndex, 3);
                Discount = (String) model.getValueAt(rowIndex, 4);

                tProduct.setText(Product);
                tQnt.setText(Quantity);
                tPrice.setText(Price);
                tDiscount.setText(Discount);
            }
        });


        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

        p1.add(lItem);
        p1.add(lQnty);
        p1.add(lPrice);
        p1.add(lDisc);
        p2.add(tProduct);
        p2.add(tQnt);
        p2.add(tPrice);
        p2.add(tDiscount);
        p3.add(btn_add);
        p3.add(btn_remove);
        p3.add(btn_done);
        p3.add(btn_balance);
        p3.add(btn_create);


        p4.add(lTotal);
        p4.add(lNetPrice);
        p4.add(lAdv);
        p4.add(lBal);
        p5.add(tTotal);
        p5.add(tNet);
        p5.add(tAdv);
        p5.add(tBal);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(249, 242, 236));
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        SMLibrary lib = new SMLibrary();
        int k = 1;
        if(e.getSource() == btn_add){
            if(tProduct.getText().isBlank() || tQnt.getText().isBlank() || tDiscount.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Product/Quantity/Discount field/s empty.","Try Again",JOptionPane.ERROR_MESSAGE);
            }
            if(tPrice.getText().isBlank()){
                dbConnection db = new dbConnection();
                db.retPrice(tProduct.getText());
                tPrice.setText(db.found);
            }
            if(lib.validateQuantities(tQnt.getText()) && lib.validateProductName(tProduct.getText())) {

                Product = tProduct.getText();
                Quantity = tQnt.getText();
                Price = tPrice.getText();
                Discount = tDiscount.getText();
                Amount = Double.toString(Double.parseDouble(Quantity) * Double.parseDouble(Price));

                //manage stock
                dbConnection db = new dbConnection();
                db.check(tProduct.getText(), tQnt.getText());

                if(db.match == 1) {
                    Object[] newRow = {Product, Quantity, Price, Amount, Discount};
                    model.addRow(newRow);
                }
                tProduct.setText("");
                tQnt.setText("");
                tPrice.setText("");
                tDiscount.setText("");

            }
            else{
                JOptionPane.showMessageDialog(this,"Wrong Entry!\nTry Again.","Warning",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == btn_remove){
            model.removeRow(tableMemo.getSelectedRow());
        }
        if(e.getSource() == btn_create){

            if(tableMemo.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"Memo table is empty.","No data found!",JOptionPane.ERROR_MESSAGE);
            }

            else{
                int confirm = JOptionPane.showOptionDialog(this,"Are you sure you want to create the current memo?","Confirm?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(confirm != 1) {
                    //dbConnection db = new dbConnection();
                    int n = tableMemo.getRowCount();
                    System.out.println(n);
                    double sum = 0.0;
                    double total = 0.0;
                    double discT = 0.0;
                    int qn;

                    for (int i = 0; i < n; i++) {
                        double temp = 0.0;
                        double tempT = 0.0;
                        double disc;

                        String name = (String) model.getValueAt(i,0);
                        String mrp = (String) model.getValueAt(i, 2);
                        String q = (String) model.getValueAt(i, 1);

                        //manage stock
                        qn = Integer.parseInt(q);
                        temp += Double.parseDouble(mrp);
                        tempT += Double.parseDouble(mrp);
                        String d = (String) model.getValueAt(i, 4);
                        if (!d.isBlank()) {
                            System.out.println(temp);
                            disc = temp * (Double.parseDouble(d) / 100);
                            System.out.println(disc);
                            temp -= disc;

                            //discount amount
                            disc *= qn;

                            discT += disc;
                        }
                        //without discount
                        tempT *= qn;
                        //model.setValueAt(Double.toString(tempT),i,3);
                        //with discount
                        temp *= qn;

                        //profit
                        //db.profitTable(name,temp,qn);
                        sum += temp;
                        total += tempT;
                    }

                    tTotal.setText(Double.toString(total));
                    tNet.setText(Double.toString(sum));

                    String[][] tmemo = new String[n][];
                    Double[] DISC = new Double[n];
                    for (int i = 0; i < n; i++) {
                        String name = (String) model.getValueAt(i,0);
                        String q = (String) model.getValueAt(i, 1);
                        String rate = (String) model.getValueAt(i, 2);
                        String amount = (String) model.getValueAt(i, 3);
                        String discnt = (String) model.getValueAt(i, 4);
                        tmemo[i] = new String[]{Integer.toString(i+1), name, q, rate, amount};
                        DISC[i] = Double.parseDouble(discnt);
                    }

                    memo mm = new memo(tTotal.getText(),Double.toString(discT),tNet.getText(),tAdv.getText(),tBal.getText(),tmemo,DISC);

                }
            }
        }
        if(e.getSource() == btn_balance){
            if (!tAdv.getText().isBlank()) {
                double left = Double.parseDouble(tNet.getText()) - Double.parseDouble(tAdv.getText());
                tBal.setText(Double.toString(left));
            }
        }
        if(e.getSource() == btn_done){
            int n = tableMemo.getRowCount();
            double sum = 0.0;
            double total = 0.0;
            int qn;

            for (int i = 0; i < n; i++) {
                double temp = 0.0;
                double tempT = 0.0;
                double disc;

                String name = (String) model.getValueAt(i,0);
                String mrp = (String) model.getValueAt(i, 2);
                String q = (String) model.getValueAt(i, 1);

                //manage stock
                qn = Integer.parseInt(q);
                temp += Double.parseDouble(mrp);
                tempT += Double.parseDouble(mrp);
                String d = (String) model.getValueAt(i, 4);
                if (!d.isBlank()) {
                    System.out.println(temp);
                    disc = temp * (Double.parseDouble(d) / 100);
                    System.out.println(disc);
                    temp -= disc;

                }
                //without discount
                tempT *= qn;
                model.setValueAt(Double.toString(tempT),i,3);
                //with discount
                temp *= qn;

                //profit
                //db.profitTable(name,temp,qn);
                sum += temp;
                total += tempT;
            }

            tTotal.setText(Double.toString(total));
            tNet.setText(Double.toString(sum));

        }
        if(e.getSource() == btn_back){
            dispose();
            new home();
        }


        if(e.getSource()==btn_new){
            model = (DefaultTableModel) tableMemo.getModel();
            model.setRowCount(0);

            tProduct.setText("");
            tQnt.setText("");
            tPrice.setText("");
            tDiscount.setText("");
            tTotal.setText("");
            tAdv.setText("");
            tBal.setText("");
            tNet.setText("");
        }
    }

    public static void main(String[] args) {
        new createMemo();
    }

}
