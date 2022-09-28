package com.mahnoorscode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class showOrderTable extends JFrame {
    JTable tableShow;
    DefaultTableModel model;

    showOrderTable(String s){
        setSize(800,600);

        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JPanel p2 = new JPanel();
        p2.setBounds(0, 60, 800, 500);
        p2.setLayout(null);
        p2.setOpaque(false);
        add(p2);

        JLabel lt = new JLabel(s,SwingConstants.CENTER);
        lt.setBounds(0,0,800,50);
        lt.setForeground(Color.GRAY);
        lt.setFont(new Font("Sans Serif",Font.BOLD|Font.ITALIC,30));
        add(lt);

        if(s.equals("Ordered Item")) {

            dbConnection db = new dbConnection();
            int r = db.getTableRowCount("ordered_item");
            String[][] data = new String[r][];

            String[] fieldName = {"Serial", "Product_Code", "Quantity", "Date_Last_Updated", "Quantity_Received"};
            model = new DefaultTableModel(db.showOrder(data), fieldName);

            tableShow = new JTable(model);
            tableShow.setFont(new Font("Serif", Font.PLAIN, 18));
            tableShow.setRowHeight(30);

            tableShow.setCellSelectionEnabled(false);
            tableShow.setColumnSelectionAllowed(false);

            JScrollPane scPane = new JScrollPane(tableShow);
            scPane.setBounds(0, 0, 800, 500);
            //scPane.getViewport().setBackground(Color.LIGHT_GRAY);
            scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            p2.add(scPane);

            scPane.setOpaque(false);
            scPane.getViewport().setOpaque(false);


            tableShow.setGridColor(Color.CYAN);
            tableShow.setBackground(Color.LIGHT_GRAY);

            JTableHeader tHead = tableShow.getTableHeader();
            tHead.setFont(new Font("Serif", Font.BOLD, 15));
            tHead.setForeground(Color.DARK_GRAY);
            tHead.setReorderingAllowed(false);
            tHead.setPreferredSize(new Dimension(40, 40));
            ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        }
        if(s.equals("History")) {

            dbConnection db = new dbConnection();
            int r = db.getTableRowCount("order_received_history");
            String[][] data = new String[r][];

            String[] fieldName = {"Serial", "Product_Code", "Name", "Quantity", "Date_Received"};
            model = new DefaultTableModel(db.showHistory(data), fieldName);

            tableShow = new JTable(model);
            tableShow.setFont(new Font("Serif", Font.PLAIN, 18));
            tableShow.setRowHeight(30);

            tableShow.setCellSelectionEnabled(false);
            tableShow.setColumnSelectionAllowed(false);

            JScrollPane scPane = new JScrollPane(tableShow);
            scPane.setBounds(0, 0, 800, 500);
            //scPane.getViewport().setBackground(Color.LIGHT_GRAY);
            scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            p2.add(scPane);
            scPane.setOpaque(false);
            scPane.getViewport().setOpaque(false);

            tableShow.setGridColor(Color.CYAN);
            tableShow.setBackground(Color.LIGHT_GRAY);

            JTableHeader tHead = tableShow.getTableHeader();
            tHead.setFont(new Font("Serif", Font.BOLD, 15));
            tHead.setForeground(Color.DARK_GRAY);
            tHead.setReorderingAllowed(false);
            tHead.setPreferredSize(new Dimension(40, 40));
            ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        }
        getContentPane().setBackground(new Color(232, 218, 239));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
