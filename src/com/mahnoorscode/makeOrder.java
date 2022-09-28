package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class makeOrder extends JFrame implements ActionListener {
    private final JButton btn_add, btn_remove, btn_order, btn_received, btn_clear,  btn_back, btn_show, btn_history;
    JTextField tItem, tQnt, tDisc, tPrice, tTotal;
    JTable tableOrder;
    String Product, Quantity, Price, Discount;
    DefaultTableModel model;

    makeOrder(){
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        setLayout(null);

        // set background
        ImageIcon loginPage = new ImageIcon("images/table.jpg");
        Image img = loginPage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        loginPage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(loginPage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);

        //title label
        JLabel lMemo = new JLabel("Order",SwingConstants.CENTER);
        lMemo.setFont(new Font("Sans Serif",Font.BOLD,40));
        lMemo.setBounds(0,0,fullScreenSize.width,50);
        add(lMemo);

        //panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();

        p1.setBounds(80,110,150,200);
        p2.setBounds(230,110,230,200);
        p3.setBounds(480,110,100,200);
        p4.setBounds(100,440,210,150);
        p5.setBounds(330,440,250,150);
        p6.setBounds(690,110,800,680);


        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);
        p5.setLayout(null);
        p6.setLayout(null);

        //Labels
        JLabel lItem = new JLabel("PRODUCT:");
        lItem.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lQnty = new JLabel("QUANTITY:");
        lQnty.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lPrice = new JLabel("PRICE:");
        lPrice.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lDiscount = new JLabel("DISCOUNT(%):");
        lDiscount.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lTotal = new JLabel("TOTAL AMOUNT(৳):");
        lTotal.setFont(new Font("Sans Serif",Font.BOLD,20));
        lTotal.setForeground(Color.BLACK);

        //text fields
        tItem = new JTextField();
        tItem.setBackground(new Color(237, 250, 240));
        tItem.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tItem.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tQnt = new JTextField();
        tQnt.setBackground(new Color(237, 250, 240));
        tQnt.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tQnt.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tDisc = new JTextField();
        tDisc.setBackground(new Color(237, 250, 240));
        tDisc.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tDisc.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tPrice = new JTextField();
        tPrice.setBackground(new Color(237, 250, 240));
        tPrice.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tPrice.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tTotal = new JTextField();
        tTotal.setBackground(new Color(237, 250, 240));
        tTotal.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tTotal.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));



        //buttons
        btn_add = new JButton("ADD");
        btn_add.setFont(new Font("Serif",Font.BOLD,15));
        btn_add.setOpaque(true);
        btn_add.setContentAreaFilled(true);
        btn_add.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_received = new JButton("RECEIVED");
        btn_received.setBounds(200,640,140,50);
        btn_received.setFont(new Font("Serif",Font.BOLD,15));
        btn_received.setOpaque(true);
        btn_received.setContentAreaFilled(true);
        btn_received.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_show = new JButton("ORDERED LIST");
        btn_show.setBounds(360,640,140,50);
        btn_show.setFont(new Font("Serif",Font.BOLD,15));
        btn_show.setOpaque(true);
        btn_show.setContentAreaFilled(true);
        btn_show.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_history = new JButton("HISTORY");
        btn_history.setBounds(520,640,140,50);
        btn_history.setFont(new Font("Serif",Font.BOLD,15));
        btn_history.setOpaque(true);
        btn_history.setContentAreaFilled(true);
        btn_history.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));


        btn_remove = new JButton("REMOVE");
        btn_remove.setFont(new Font("Serif",Font.BOLD,15));
        btn_remove.setOpaque(true);
        btn_remove.setContentAreaFilled(true);
        btn_remove.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_order = new JButton("ORDER");
        btn_order.setFont(new Font("Serif",Font.BOLD,15));
        btn_order.setOpaque(true);
        btn_order.setContentAreaFilled(true);
        btn_order.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_clear = new JButton("CLEAR");
        btn_clear.setFont(new Font("Serif",Font.BOLD,15));
        btn_clear.setOpaque(true);
        btn_clear.setContentAreaFilled(true);
        btn_clear.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_back = new JButton("BACK");
        btn_back.setBounds(50,640,140,50);
        btn_back.setFont(new Font("Serif",Font.BOLD,15));
        btn_back.setOpaque(true);
        btn_back.setContentAreaFilled(true);
        btn_back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));


        //action buttons
        btn_add.addActionListener(this);
        btn_remove.addActionListener(this);
        btn_order.addActionListener(this);
        btn_received.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_back.addActionListener(this);
        btn_show.addActionListener(this);
        btn_history.addActionListener(this);

        //panel bounds
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);

        p1.setLayout(new GridLayout(4,1,0,2));
        p2.setLayout(new GridLayout(4,2,0,2));
        p3.setLayout(new GridLayout(4,1,0,6));
        p4.setLayout(new GridLayout(3,1,0,2));
        p5.setLayout(new GridLayout(3,1,0,2));
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
        add(btn_received);
        add(btn_back);
        add(btn_show);
        add(btn_history);

        Object[][] data= {};
        String[] fieldName= {"Product Code","Qty","Price(৳)","(%)"};

        model = new DefaultTableModel(data,fieldName);

        tableOrder = new JTable(model);
        JScrollPane sp = new JScrollPane(tableOrder);
        sp.setBounds(0,0,680,580);
        p6.add(sp);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);

        tableOrder.setFont(new Font("Serif", Font.PLAIN, 20));
        tableOrder.setRowHeight(30);

        tableOrder.getColumnModel().getColumn(0).setPreferredWidth(300);
        tableOrder.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableOrder.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableOrder.getColumnModel().getColumn(3).setPreferredWidth(50);

        JTableHeader tHead = tableOrder.getTableHeader();
        tHead.setFont(new Font("Serif", Font.BOLD, 20));
        tHead.setForeground(Color.WHITE);
        tHead.setBackground(Color.DARK_GRAY);
        tHead.setReorderingAllowed(false);
        tHead.setPreferredSize(new Dimension(40,40));

        tableOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = tableOrder.getSelectedRow();

                //sl = (String) model.getValueAt(rowIndex, 0);//row, column
                Product = (String) model.getValueAt(rowIndex, 0);
                Quantity = (String) model.getValueAt(rowIndex, 1);
                Price = (String) model.getValueAt(rowIndex, 2);
                Discount = (String) model.getValueAt(rowIndex, 3);

                tItem.setText(Product);
                tQnt.setText(Quantity);
                tPrice.setText(Price);
                tDisc.setText(Discount);
            }
        });

        p1.add(lItem);
        p2.add(tItem);
        p1.add(lQnty);
        p2.add(tQnt);
        p1.add(lPrice);
        p2.add(tPrice);
        p1.add(lDiscount);
        p2.add(tDisc);
        p4.add(lTotal);
        p5.add(tTotal);

        p3.add(btn_add);
        p3.add(btn_remove);
        p3.add(btn_order);
        p3.add(btn_clear);

        getContentPane().add(lScreen);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == btn_add){
            SMLibrary lib = new SMLibrary();
            if(tItem.getText().isBlank() || tQnt.getText().isBlank() || tPrice.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Product/Quantity/Price field empty.","Try Again",JOptionPane.ERROR_MESSAGE);
            }

            else if(lib.validateProductCode(tItem.getText())) {
                Product = tItem.getText();
                Quantity = tQnt.getText();
                Price = tPrice.getText();
                Discount = tDisc.getText();

                Object[] newRow = {Product, Quantity, Price, Discount};
                model.addRow(newRow);

            }
            else{
                JOptionPane.showMessageDialog(this,"Wrong Product Code","Try Again",JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == btn_remove){
            model.removeRow(tableOrder.getSelectedRow());
        }
        if(e.getSource() == btn_order){
            int confirm = JOptionPane.showOptionDialog(this,"Are you sure you want to save the current order?","Confirm?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if(confirm != 1) {
                int n = tableOrder.getRowCount();
                double total = 0.0;
                String[][] order = new String[n][];
                for (int i = 0; i < n; i++) {
                    String name = (String) model.getValueAt(i,0);
                    String q = (String) model.getValueAt(i, 1);
                    String dp = (String) model.getValueAt(i, 2);
                    String discount = (String) model.getValueAt(i, 3);
                    order[i] = new String[]{name, q};
                    double temp = Double.parseDouble(dp);
                    if(!tDisc.getText().isBlank()){
                        double disc = Double.parseDouble(dp)*(Double.parseDouble(discount)/100);
                        temp -= disc;
                    }
                    temp *= Integer.parseInt(q);
                    total += temp;

                }
                dbConnection db = new dbConnection();
                db.SaveOrder(order);
                tTotal.setText(Double.toString(total));
            }
        }
        //button received action
        if(e.getSource()==btn_received){
            new Received_Item();
        }
        if(e.getSource() == btn_clear){
            tItem.setText("");
            tQnt.setText("");
            tPrice.setText("");
            tDisc.setText("");
            tTotal.setText("");
        }

        if(e.getSource() == btn_show){
            String recent = "Ordered Item";
            new showOrderTable(recent);
        }
        if(e.getSource() == btn_history){
            String history = "History";
            new showOrderTable(history);
        }
        if(e.getSource() == btn_back){
            dispose();
            new home();
        }
    }
}
