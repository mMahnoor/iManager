package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

public class viewStock extends JFrame implements ActionListener {

    JTextField tSl, tProduct, tQuantity, tMRP, tDP, tSearch;
    JButton btn_Add, btn_Update, btn_Del, btn_Clr, btn_Refresh,btn_search, btn_back;
    JTable tableStock;
    DefaultTableModel tableModel;
    String Serial, Product, Quantity, MRP, DP;

    viewStock(){
        //set frame size
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        setLayout(null);

        // set background
        ImageIcon loginPage = new ImageIcon("images/stock.jpeg");
        Image img = loginPage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        loginPage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(loginPage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);

        //title label
        JLabel lStock = new JLabel("Stock",SwingConstants.CENTER);
        lStock.setFont(new Font("Sans Serif",Font.BOLD,40));
        lStock.setBounds(0,10,fullScreenSize.width,60);
        add(lStock);

        //panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        //panel bounds
        p1.setBounds(40,200,190,220);
        p2.setBounds(230,200,230,220);
        p3.setBounds(480,200,130,220);
        p4.setBounds(700,150,fullScreenSize.width-550,fullScreenSize.height-300);

        //setting layout null
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);

        //labels
        JLabel lSl = new JLabel("PRODUCT CODE:");
        lSl.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lProduct = new JLabel("NAME:");
        lProduct.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lQuantity = new JLabel("QTY:");
        lQuantity.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lMRP = new JLabel("MRP(tk):");
        lMRP.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lDP = new JLabel("DP(tk):");
        lDP.setFont(new Font("Sans Serif",Font.BOLD,20));

        //search

        tSearch = new JTextField("Enter Your Product Name...");
        tSearch.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tSearch.setBackground(new java.awt.Color(0,0,0,1));
        tSearch.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tSearch.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tSearch.setBounds(700,80,420,50);

        btn_search = new JButton("SEARCH");
        btn_search.setBounds(1140,80,100,50);
        btn_search.setFont(new Font("Serif",Font.BOLD,15));
        btn_search.setContentAreaFilled(false);
        btn_search.setBorderPainted(true);
        btn_search.setForeground(Color.BLACK);
        btn_search.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));
        //add search components
        add(tSearch);
        add(btn_search);

        //buttons
        btn_Add = new JButton("ADD");
        btn_Add.setFont(new Font("Serif",Font.BOLD,15));
        btn_Add.setForeground(Color.BLACK);
        btn_Add.setBackground(new Color(234, 237, 237));
        btn_Add.setOpaque(true);
        btn_Add.setBorderPainted(false);

        btn_Update = new JButton("UPDATE");
        btn_Update.setFont(new Font("Serif",Font.BOLD,15));
        btn_Update.setForeground(Color.BLACK);
        btn_Update.setBackground(new Color(234, 237, 237));
        btn_Update.setOpaque(true);
        btn_Update.setBorderPainted(false);

        btn_Del = new JButton("DELETE");
        btn_Del.setFont(new Font("Serif",Font.BOLD,15));
        btn_Del.setForeground(Color.BLACK);
        btn_Del.setBackground(new Color(234, 237, 237));
        btn_Del.setOpaque(true);
        btn_Del.setBorderPainted(false);

        btn_Refresh = new JButton("REFRESH");
        btn_Refresh.setFont(new Font("Serif",Font.BOLD,15));
        btn_Refresh.setForeground(Color.BLACK);
        btn_Refresh.setBackground(new Color(234, 237, 237));
        btn_Refresh.setOpaque(true);
        btn_Refresh.setBorderPainted(false);

        btn_Clr = new JButton("CLEAR");
        btn_Clr.setFont(new Font("Serif",Font.BOLD,15));
        btn_Clr.setForeground(Color.BLACK);
        btn_Clr.setBackground(new Color(234, 237, 237));
        btn_Clr.setOpaque(true);
        btn_Clr.setBorderPainted(false);
        //btn_Clr.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));

        btn_back = new JButton("BACK");
        btn_back.setBounds(50,640,100,50);
        btn_back.setFont(new Font("Serif",Font.BOLD,15));
        btn_back.setContentAreaFilled(false);
        btn_back.setBorderPainted(true);
        btn_back.setForeground(Color.BLACK);
        btn_back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));

        //action buttons
        btn_Add.addActionListener(this);
        btn_Update.addActionListener(this);
        btn_Del.addActionListener(this);
        btn_Refresh.addActionListener(this);
        btn_Clr.addActionListener(this);
        btn_search.addActionListener(this);
        btn_back.addActionListener(this);

        //text fields
        tSl = new JTextField();
        //tSl.setBackground(new java.awt.Color(0,0,0,1));
        tSl.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tSl.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tProduct = new JTextField();
       // tProduct.setBackground(new java.awt.Color(0,0,0,1));
        tProduct.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tProduct.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tQuantity = new JTextField();
        //tQuantity.setBackground(new java.awt.Color(0,0,0,2));
        tQuantity.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tQuantity.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tMRP = new JTextField();
        //tMRP.setBackground(new java.awt.Color(0,0,0,2));
        tMRP.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tMRP.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tDP = new JTextField();
       // tDP.setBackground(new java.awt.Color(0,0,0,2));
        tDP.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tDP.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        //set panel opaque
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);

        p1.setLayout(new GridLayout(5,1,0,2));
        p2.setLayout(new GridLayout(5,2,0,2));
        p3.setLayout(new GridLayout(5,1,0,4));

        add(p1);
        add(p2);
        add(p3);
        add(p4);

        //table config
        dbConnection db = new dbConnection();
        int r = db.getTableRowCount("whole_stock");
        String[][] data= new String[r][];

        String[] fieldName= {"P.Code","Product Name","Quantity","MRP(TK)","DP(TK)"};
        tableModel = new DefaultTableModel(db.stock(data),fieldName);

        tableStock = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(tableStock);
        sp.setBounds(0,0,fullScreenSize.width-750,fullScreenSize.height-300);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        p4.add(sp);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);

        tableStock.setFont(new Font("Serif", Font.PLAIN, 15));
        tableStock.setRowHeight(30);

        tableStock.setGridColor(Color.CYAN);
        tableStock.setBackground(Color.LIGHT_GRAY);

        JTableHeader tHead = tableStock.getTableHeader();
        tHead.setFont(new Font("Serif", Font.BOLD, 20));
        tHead.setForeground(Color.DARK_GRAY);
        tHead.setReorderingAllowed(false);
        tHead.setPreferredSize(new Dimension(40,40));

        tableStock.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableStock.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableStock.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableStock.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableStock.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableStock.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tableStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableStock.getSelectedRow();

                Serial = (String) tableModel.getValueAt(row,0);
                Product = (String) tableModel.getValueAt(row, 1);
                Quantity = (String) tableModel.getValueAt(row, 2);
                MRP = (String) tableModel.getValueAt(row, 3);
                DP = (String) tableModel.getValueAt(row, 4);

                tSl.setText(Serial);
                tProduct.setText(Product);
                tQuantity.setText(Quantity);
                tMRP.setText(MRP);
                tDP.setText(DP);
            }
        });

        p1.add(lSl);
        p2.add(tSl);
        p1.add(lProduct);
        p2.add(tProduct);
        p1.add(lQuantity);
        p2.add(tQuantity);
        p1.add(lMRP);
        p2.add(tMRP);
        p1.add(lDP);
        p2.add(tDP);

        p3.add(btn_Add);
        p3.add(btn_Update);
        p3.add(btn_Del);
        p3.add(btn_Refresh);
        p3.add(btn_Clr);

        add(btn_back);

        getContentPane().add(lScreen);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_Add) {
            SMLibrary lib = new SMLibrary();
            if (tProduct.getText().isBlank() || tQuantity.getText().isBlank() || tMRP.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Product/Quantity/Price field empty.", "Try Again", JOptionPane.ERROR_MESSAGE);
            } else if (lib.validateProductCode(tSl.getText()) && lib.validateQuantity(tQuantity.getText()) && lib.validateQuantities(tMRP.getText()) && lib.validateQuantities(tDP.getText())) {
                Serial = tSl.getText();
                Product = tProduct.getText();
                Quantity = tQuantity.getText();
                MRP = tMRP.getText();
                DP = tDP.getText();

                Object[] newRow = {Serial, Product, Quantity, MRP, DP};
                tableModel.addRow(newRow);
                dbConnection db = new dbConnection();
                db.addStock(Serial,Product,Quantity,MRP,DP);

            } else {
                JOptionPane.showMessageDialog(this, "Wrong Entity", "Correction", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == btn_Del) {
            if(tSl.getText().isBlank() && tProduct.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Unidentified Delete call...","ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showOptionDialog(this, "Selected Record will be deleted.\nAre you sure you want to delete the record?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm != 1) {
                    Serial = tSl.getText();
                    Product = tProduct.getText();

                    tableModel.removeRow(tableStock.getSelectedRow());
                    dbConnection db = new dbConnection();
                    db.delFromStock(Serial, Product);
                }
            }
        }

        if (e.getSource() == btn_Update) {
            if(tSl.getText().isBlank() && tProduct.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Unidentified Update call...","ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showOptionDialog(this, "Selected Record will be updated.\n", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm != 1) {
                    Serial = tSl.getText();
                    Product = tProduct.getText();
                    Quantity = tQuantity.getText();
                    MRP = tMRP.getText();
                    DP = tDP.getText();

                    int row = tableStock.getSelectedRow();

                    tableModel.setValueAt(Serial, row, 0);
                    tableModel.setValueAt(Product, row, 1);
                    tableModel.setValueAt(Quantity, row, 2);
                    tableModel.setValueAt(MRP, row, 3);
                    tableModel.setValueAt(DP, row, 4);

                    dbConnection db = new dbConnection();
                    db.UpdateStock(Serial, Product, Quantity, MRP, DP);
                }
            }
        }

        if (e.getSource() == btn_Refresh) {
            dispose();
            new viewStock();
        }

        if(e.getSource()==btn_search){
            dbConnection db = new dbConnection();
            db.searchTable("whole_stock", tSearch.getText());
            tableStock.changeSelection(db.match,0,true,false);
            tableStock.scrollRectToVisible(tableStock.getCellRect(db.match,0,true));
        }

        if(e.getSource()==btn_Clr){
            tSl.setText("");
            tProduct.setText("");
            tQuantity.setText("");
            tMRP.setText("");
            tDP.setText("");
            tableStock.getSelectionModel().clearSelection();
        }
        if(e.getSource()==btn_back){
            dispose();
            new home();
        }
    }

    public static void main(String[] args) {
        new viewStock();
    }

}
