package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class ProductInfo extends JFrame implements ActionListener {
    JTextField tProduct, tName, tMRP, tImgAd, tSearch;
    JButton btn_Add, btn_Del, btn_search, btn_upload, btn_clr;
    JTable tableProductInfo;
    DefaultTableModel tableModel;
    String ProductCode, Name, MRP, ImgAddress, Img;


    ProductInfo() {
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);
        setTitle("ProductInfo");
        setLayout(null);

        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        ImageIcon InfoPage = new ImageIcon("images/sofa1.jpg");
        Image img = InfoPage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width, fullScreenSize.height, Image.SCALE_SMOOTH);
        InfoPage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(InfoPage);
        lScreen.setLayout(null);
        lScreen.setBounds(0, 0, fullScreenSize.width, fullScreenSize.height);


        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p1.setBounds(80,200,200,180);
        p2.setBounds(280,200,280,180);
        p3.setBounds(80,550,480,50);
        p4.setBounds(700,150,fullScreenSize.width-420,fullScreenSize.height-300);

        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);

        JLabel lProductCode = new JLabel("PRODUCT CODE:");
        lProductCode.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lName = new JLabel("PRODUCT NAME:");
        lName.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lMRP = new JLabel("MRP(TK):");
        lMRP.setFont(new Font("Sans Serif",Font.BOLD,20));
        JLabel lImgAd = new JLabel("IMAGE:");
        lImgAd.setBounds(80,385,200,50);
        lImgAd.setFont(new Font("Sans Serif",Font.BOLD,20));

        tSearch = new JTextField("Enter Your Product Name...");
        tSearch.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tSearch.setBackground(new java.awt.Color(0,0,0,1));
        tSearch.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tSearch.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));
        tSearch.setBounds(700,80,420,50);

        btn_search = new JButton("SEARCH");
        btn_search.setBounds(1120,80,100,50);
        btn_search.setFont(new Font("Serif",Font.BOLD,15));
        btn_search.setBackground(new Color(255, 230, 255));
        btn_search.setContentAreaFilled(true);
        btn_search.setOpaque(true);
        btn_search.setForeground(Color.BLACK);
        btn_search.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));
        //add search components
        add(tSearch);
        add(btn_search);

//button ok
        btn_Add = new JButton("ADD");
        btn_Add.setBackground(new Color(255, 230, 255));
        btn_Add.setFont(new Font("Serif",Font.BOLD,15));
        btn_Add.setContentAreaFilled(true);
        btn_Add.setOpaque(true);
        btn_Add.setForeground(Color.BLACK);
        btn_Add.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));

        btn_Del = new JButton("DELETE");
        btn_Del.setFont(new Font("Serif",Font.BOLD,15));
        btn_Del.setBackground(new Color(255, 230, 255));
        btn_Del.setContentAreaFilled(true);
        btn_Del.setOpaque(true);
        btn_Del.setForeground(Color.BLACK);
        btn_Del.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));

        btn_clr = new JButton("CLEAR");
        btn_clr.setFont(new Font("Serif",Font.BOLD,15));
        btn_clr.setBackground(new Color(255, 230, 255));
        btn_clr.setContentAreaFilled(true);
        btn_clr.setOpaque(true);
        btn_clr.setForeground(Color.BLACK);
        btn_clr.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2,true));


        btn_upload = new JButton("UPLOAD");
        btn_upload.setBounds(480,385,80,50);
        btn_upload.setFont(new Font("Serif",Font.BOLD,15));
        btn_upload.setBackground(new Color(255, 230, 255));
        btn_upload.setContentAreaFilled(true);
        btn_upload.setOpaque(true);
        btn_upload.setForeground(Color.BLACK);
        btn_upload.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1,true));

        btn_Add.addActionListener(this);
        btn_Del.addActionListener(this);
        btn_search.addActionListener(this);
        btn_upload.addActionListener(this);
        btn_clr.addActionListener(this);

        tProduct = new JTextField();
        //tProduct.setBackground(new java.awt.Color(0,0,0,1));
        tProduct.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tProduct.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tName = new JTextField();
        //tName.setBackground(new java.awt.Color(0,0,0,2));
        tName.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tName.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tMRP = new JTextField();
        //tMRP.setBackground(new java.awt.Color(0,0,0,2));
        tMRP.setFont(new Font("Sans Serif",Font.PLAIN,20));
        //tMRP.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));

        tImgAd = new JTextField();
        tImgAd.setBounds(280,385,200,50);
        //tImgAd.setBackground(new java.awt.Color(0,0,0,2));
        tImgAd.setFont(new Font("Sans Serif",Font.PLAIN,20));
        tImgAd.setBorder(BorderFactory.createLineBorder(Color. BLACK, 1));


        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);

        p1.setLayout(new GridLayout(3,1,0,2));
        p2.setLayout(new GridLayout(3,1,0,2));
        p3.setLayout(new GridLayout(1,3,100,0));

        add(p1);
        add(p2);
        add(p3);
        add(p4);

        //table config
        dbConnection db = new dbConnection();
        int r = db.getTableRowCount("product_info");
        String[][] data= new String[r][];

        String[] fieldName= {"Product","Name","MRP(Tk)","Image"};
        tableModel = new DefaultTableModel(db.InfoTable(data),fieldName);

        tableProductInfo = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(tableProductInfo);
        sp.setBounds(0,0,fullScreenSize.width-770,fullScreenSize.height-300);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        p4.add(sp);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);

        tableProductInfo.setFont(new Font("Serif", Font.PLAIN, 15));
        tableProductInfo.setRowHeight(30);

        tableProductInfo.setGridColor(new Color(26, 255, 26));
        tableProductInfo.setBackground(Color.LIGHT_GRAY);

        JTableHeader tHead = tableProductInfo.getTableHeader();
        tHead.setFont(new Font("Serif", Font.BOLD, 20));
        tHead.setForeground(Color.DARK_GRAY);
        tHead.setReorderingAllowed(false);
        tHead.setPreferredSize(new Dimension(40,40));

        tableProductInfo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableProductInfo.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableProductInfo.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableProductInfo.getColumnModel().getColumn(3).setPreferredWidth(600);

        tableProductInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        tableProductInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableProductInfo.getSelectedRow();

                ProductCode = (String) tableModel.getValueAt(row, 0);
                Name = (String) tableModel.getValueAt(row, 1);
                MRP = (String) tableModel.getValueAt(row, 2);
                ImgAddress = (String) tableModel.getValueAt(row, 3);

                tProduct.setText(ProductCode);
                tName.setText(Name);
                tMRP.setText(MRP);
                tImgAd.setText(ImgAddress);
            }
        });

        p1.add(lProductCode);
        p2.add(tProduct);
        p1.add(lName);
        p2.add(tName);
        p1.add(lMRP);
        p2.add(tMRP);
        add(lImgAd);
        add(tImgAd);
        add(btn_upload);

        p3.add(btn_Add);
        p3.add(btn_Del);
        p3.add(btn_clr);

        getContentPane().add(lScreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_Add) {
            SMLibrary lib = new SMLibrary();
            if (tProduct.getText().isBlank() || tName.getText().isBlank() || tMRP.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Product/Quantity/Price field empty.", "Try Again", JOptionPane.ERROR_MESSAGE);
            } else if (lib.validateProductCode(tProduct.getText()) && lib.validateQuantities(tMRP.getText())) {
                ProductCode = tProduct.getText();
                Name = tName.getText();
                MRP = tMRP.getText();
                ImgAddress = tImgAd.getText();

                Object[] newRow = {ProductCode, Name, MRP, ImgAddress};
                tableModel.addRow(newRow);
                dbConnection db = new dbConnection();
                db.addInfo(ProductCode, Name, MRP, ImgAddress);

            } else {
                JOptionPane.showMessageDialog(this, "Wrong Entity", "Correction", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == btn_Del) {
            if(tName.getText().isBlank() && tProduct.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Unidentified Delete call...","ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showOptionDialog(this, "Selected Record will be deleted.\nAre you sure you want to delete the record?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm != 1) {
                    ProductCode = tProduct.getText();
                    tableModel.removeRow(tableProductInfo.getSelectedRow());
                    dbConnection db = new dbConnection();
                    db.delFromInfoTable(ProductCode);
                }
            }
        }
        if(e.getSource()==btn_upload){
            JFileChooser fc=new JFileChooser();
            int i=fc.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File f=fc.getSelectedFile();
                String filepath=f.getPath();
                tImgAd.setText(filepath);
            }
        }
        if(e.getSource()==btn_search){
            tableProductInfo.getSelectionModel().clearSelection();
            dbConnection db = new dbConnection();
            db.searchTable("product_info", tSearch.getText());
            tableProductInfo.changeSelection(db.match,0,true,false);
            tableProductInfo.scrollRectToVisible(tableProductInfo.getCellRect(db.match,0,true));
        }
        if(e.getSource()==btn_clr){
            tProduct.setText("");
            tName.setText("");
            tMRP.setText("");
            tImgAd.setText("");
            tableProductInfo.getSelectionModel().clearSelection();
        }

    }

}
