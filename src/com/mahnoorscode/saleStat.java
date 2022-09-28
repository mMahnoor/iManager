package com.mahnoorscode;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class saleStat extends JFrame implements ActionListener {
    JButton btn_saleProfit, btn_tSaleProduct, showSaleBar, showProfitBar, btn_back;
    JLabel lt;
    JTable tableProfit, table_Sale;
    DefaultTableModel tableModel;
    JPanel pProfit, p2;
    private int flag = 0;

    saleStat(){
        setTitle("SALE INFORMATION");
        Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(fullScreenSize);

        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(null);

        ImageIcon homePage = new ImageIcon("images/Sofa-323.jpg");
        Image img = homePage.getImage();
        Image temp = img.getScaledInstance(fullScreenSize.width,fullScreenSize.height,Image.SCALE_SMOOTH);
        homePage = new ImageIcon(temp);
        JLabel lScreen = new JLabel(homePage);
        lScreen.setLayout(null);
        lScreen.setBounds(0,0,fullScreenSize.width,fullScreenSize.height);


        JPanel p1 = new JPanel();

        p1.setBounds(100,250,310,220);
        p1.setLayout(null);
        p1.setOpaque(false);

        p1.setLayout(new GridLayout(5,1,0,4));

        add(p1);

        lt = new JLabel("",SwingConstants.CENTER);
        lt.setBounds(600,130,600,50);
        lt.setFont(new Font("Sans Serif",Font.BOLD,40));
        add(lt);

        btn_saleProfit = new JButton("PROFIT TABLE");
        btn_saleProfit.setFont(new Font("Serif",Font.BOLD,20));
        btn_saleProfit.setOpaque(true);
        btn_saleProfit.setContentAreaFilled(true);
        btn_saleProfit.setBackground(Color.LIGHT_GRAY);
        btn_saleProfit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));
        btn_tSaleProduct = new JButton("TOP SELLING PRODUCTS");
        btn_tSaleProduct.setFont(new Font("Serif",Font.BOLD,20));
        btn_tSaleProduct.setOpaque(true);
        btn_tSaleProduct.setContentAreaFilled(true);
        btn_tSaleProduct.setBackground(Color.LIGHT_GRAY);
        btn_tSaleProduct.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        showProfitBar = new JButton("PROFIT BAR");
        showProfitBar.setFont(new Font("Serif",Font.BOLD,20));
        showProfitBar.setOpaque(true);
        showProfitBar.setContentAreaFilled(true);
        showProfitBar.setBackground(Color.LIGHT_GRAY);
        showProfitBar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        showSaleBar = new JButton("TOP SALE BAR");
        showSaleBar.setFont(new Font("Serif",Font.BOLD,20));
        showSaleBar.setOpaque(true);
        showSaleBar.setContentAreaFilled(true);
        showSaleBar.setBackground(Color.LIGHT_GRAY);
        showSaleBar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        btn_back = new JButton("BACK");
        btn_back.setFont(new Font("Serif",Font.BOLD,20));
        btn_back.setOpaque(true);
        btn_back.setContentAreaFilled(true);
        btn_back.setBackground(Color.LIGHT_GRAY);
        btn_back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2,true));

        //action listener
        btn_saleProfit.addActionListener(this);
        btn_tSaleProduct.addActionListener(this);
        showSaleBar.addActionListener(this);
        showProfitBar.addActionListener(this);
        btn_back.addActionListener(this);

        p1.add(btn_saleProfit);
        p1.add(btn_tSaleProduct);
        p1.add(showSaleBar);
        p1.add(showProfitBar);
        p1.add(btn_back);

        getContentPane().add(lScreen);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==btn_saleProfit){
            if(flag==0) {
                pProfit = new JPanel();
                pProfit.setBounds(600, 200, 600, 500);
                pProfit.setLayout(null);
                pProfit.setOpaque(false);
                add(pProfit);

                lt.setText("Profit Table");
                dbConnection db = new dbConnection();
                int r = db.getTableRowCount("profit_table");

                String[][] data = new String[r][];
                //dbConnection db = new dbConnection();

                String[] fieldName = {"Serial", "Profit", "Date"};
                tableModel = new DefaultTableModel(db.showProfit(data), fieldName);

                tableProfit = new JTable(tableModel);
                JScrollPane scPane = new JScrollPane(tableProfit);
                scPane.setBounds(0, 0, 600, 500);
                scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                pProfit.add(scPane);
                //pProfit.setBackground(Color.LIGHT_GRAY);
                //scPane.setBackground(Color.LIGHT_GRAY);
                scPane.getViewport().setBackground(Color.LIGHT_GRAY);

                tableProfit.setFont(new Font("Serif", Font.PLAIN, 18));
                tableProfit.setRowHeight(30);

                tableProfit.setCellSelectionEnabled(false);
                tableProfit.setColumnSelectionAllowed(false);

                tableProfit.setGridColor(Color.CYAN);
                tableProfit.setBackground(Color.LIGHT_GRAY);

                JTableHeader tHead = tableProfit.getTableHeader();
                tHead.setFont(new Font("Serif", Font.BOLD, 20));
                tHead.setOpaque(false);
                //tHead.setBackground(Color.GRAY);
                tHead.setForeground(Color.DARK_GRAY);
                tHead.setReorderingAllowed(false);
                tHead.setPreferredSize(new Dimension(40, 40));
                ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

                //tableProfit.getColumnModel().getColumn(0).setPreferredWidth(300);

                flag = 1;
            } else if(flag==2 || flag==1){
                remove(p2);
                revalidate();
                //repaint();
                pProfit = new JPanel();
                pProfit.setBounds(600, 200, 600, 500);
                pProfit.setLayout(null);
                pProfit.setOpaque(false);
                add(pProfit);
                pProfit.setVisible(true);

                lt.setText("Profit Table");

                dbConnection db = new dbConnection();

                int n = db.getTableRowCount("profit_table");
                String[][] data = new String[n][];
                String[] fieldName = {"Serial", "Profit", "Date"};
                tableModel = new DefaultTableModel(db.showProfit(data), fieldName);

                tableProfit = new JTable(tableModel);
                JScrollPane scPane = new JScrollPane(tableProfit);
                scPane.setBounds(0, 0, 600, 500);
                scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                pProfit.add(scPane);
                //pProfit.setBackground(Color.LIGHT_GRAY);
                //scPane.setBackground(Color.LIGHT_GRAY);
                scPane.getViewport().setBackground(Color.LIGHT_GRAY);

                tableProfit.setFont(new Font("Serif", Font.PLAIN, 18));
                tableProfit.setRowHeight(30);

                tableProfit.setCellSelectionEnabled(false);
                tableProfit.setColumnSelectionAllowed(false);

                tableProfit.setGridColor(Color.CYAN);
                tableProfit.setBackground(Color.LIGHT_GRAY);

                JTableHeader tHead = tableProfit.getTableHeader();
                tHead.setFont(new Font("Serif", Font.BOLD, 20));
                tHead.setOpaque(false);
                //tHead.setBackground(Color.GRAY);
                tHead.setForeground(Color.DARK_GRAY);
                tHead.setReorderingAllowed(false);
                tHead.setPreferredSize(new Dimension(40, 40));
                ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

                tableProfit.getColumnModel().getColumn(0).setPreferredWidth(300);

                flag = 1;
            }
        }

        if(e.getSource()==btn_tSaleProduct){
            if(flag==0) {
                SMLibrary lib = new SMLibrary();
                p2 = new JPanel();
                p2.setBounds(600, 200, 600, 500);
                p2.setLayout(null);
                p2.setOpaque(false);
                add(p2);

                String name=JOptionPane.showInputDialog(this,"Enter Year:","Sale Table",JOptionPane.INFORMATION_MESSAGE);

                if(name!=null && lib.validateQuantities(name)) {
                    lt.setText("Sale (Year: " + name + ")");

                    dbConnection db = new dbConnection();
                    int n = db.getTableRowCount("sold");
                    String[] fieldName = {"Product", "Quantity"};
                    tableModel = new DefaultTableModel(db.showTopSale(n, name), fieldName);

                    table_Sale = new JTable(tableModel);
                    table_Sale.setFont(new Font("Serif", Font.PLAIN, 18));
                    table_Sale.setRowHeight(30);

                    table_Sale.setCellSelectionEnabled(false);
                    table_Sale.setColumnSelectionAllowed(false);

                    JScrollPane scPane = new JScrollPane(table_Sale);
                    scPane.setBounds(0, 0, 600, 500);
                    scPane.getViewport().setBackground(Color.LIGHT_GRAY);
                    scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    p2.add(scPane);

                    table_Sale.setGridColor(Color.CYAN);
                    table_Sale.setBackground(Color.LIGHT_GRAY);

                    JTableHeader tHead = table_Sale.getTableHeader();
                    tHead.setFont(new Font("Serif", Font.BOLD, 20));
                    tHead.setForeground(Color.DARK_GRAY);
                    tHead.setReorderingAllowed(false);
                    tHead.setPreferredSize(new Dimension(40, 40));
                    ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

                    table_Sale.getColumnModel().getColumn(0).setPreferredWidth(300);

                    flag = 2;
                } else{
                    JOptionPane.showMessageDialog(null,"empty or invalid entry","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else if(flag==1||flag==2) {
                getContentPane().remove(pProfit);
                revalidate();
                //repaint();
                p2 = new JPanel();
                p2.setBounds(600, 200, 600, 500);
                p2.setLayout(null);
                p2.setOpaque(false);
                p2.setVisible(true);
                add(p2);

                SMLibrary lib = new SMLibrary();

                String name = JOptionPane.showInputDialog(this, "Enter Year:", "Sale Table", JOptionPane.INFORMATION_MESSAGE);

                if (name != null && lib.validateQuantities(name)) {
                    lt.setText("Sale (Year: " + name + ")");

                    dbConnection db = new dbConnection();
                    int n = db.getTableRowCount("sold");
                    String[] fieldName = {"Product", "Quantity"};
                    tableModel = new DefaultTableModel(db.showTopSale(n, name), fieldName);

                    table_Sale = new JTable(tableModel);
                    table_Sale.setFont(new Font("Serif", Font.PLAIN, 18));
                    table_Sale.setRowHeight(30);

                    table_Sale.setCellSelectionEnabled(false);
                    table_Sale.setColumnSelectionAllowed(false);

                    JScrollPane scPane = new JScrollPane(table_Sale);
                    scPane.setBounds(0, 0, 600, 500);
                    scPane.getViewport().setBackground(Color.LIGHT_GRAY);
                    //scPane.getViewport().setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
                    scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    p2.add(scPane);
                    scPane.setVisible(true);
                    table_Sale.setVisible(true);

                    table_Sale.setGridColor(Color.CYAN);
                    table_Sale.setBackground(Color.LIGHT_GRAY);

                    JTableHeader tHead = table_Sale.getTableHeader();
                    tHead.setFont(new Font("Serif", Font.BOLD, 20));
                    tHead.setForeground(Color.DARK_GRAY);
                    tHead.setReorderingAllowed(false);
                    tHead.setPreferredSize(new Dimension(40, 40));
                    ((DefaultTableCellRenderer) tHead.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

                    table_Sale.getColumnModel().getColumn(0).setPreferredWidth(300);

                    flag = 2;
                }else{
                    JOptionPane.showMessageDialog(null,"empty or invalid entry","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource()==showSaleBar){
            SMLibrary lib = new SMLibrary();
            String name=JOptionPane.showInputDialog(this,"Enter Year:","Top Sale Chart",JOptionPane.INFORMATION_MESSAGE);
            if (name != null && lib.validateQuantities(name)) {
                new ProductSaleChart(name);
            }else{
                JOptionPane.showMessageDialog(null,"empty or invalid entry","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==showProfitBar){
            SMLibrary lib = new SMLibrary();
            String name=JOptionPane.showInputDialog(this,"Enter Year:","Profit Bar Chart",JOptionPane.INFORMATION_MESSAGE);
            if (name != null && lib.validateQuantities(name)) {
                new profitChart(name);
            }else{
                JOptionPane.showMessageDialog(null,"empty or invalid entry","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==btn_back){
            dispose();
            new home();
        }

    }
}
