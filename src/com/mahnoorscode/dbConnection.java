package com.mahnoorscode;


//import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Arrays;


public class dbConnection {

    int match = 0, largest;
    String found = "";
    private Statement st;
    private Connection con;
    private ResultSet rs, rs1;

    public dbConnection(){
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SM_DB","root","SM_PASS");
            st = con.createStatement();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Couldn't connect to SQL server.","Try Again",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveRecord(String s, String qs){
        try {

            SMLibrary lib = new SMLibrary();

            String date = lib.DFormat();

            ResultSet rs = st.executeQuery("SELECT * FROM dbStockManage.sold;");
            int flag = 0,q;
            while (rs.next()){
                if(s.equals(rs.getString("Product_Name")) && date.equals(rs.getString("Sold_Date"))){

                    q = Integer.parseInt(rs.getString("Quantity"));
                    q += Integer.parseInt(qs);

                    st.executeUpdate("UPDATE sold SET Quantity = "+Integer.toString(q)+" WHERE Product_Code = '"+rs.getString("Product_Code")+"' AND Serial = '"+rs.getString("Serial")+"'");
                    PreparedStatement refreshUpdate = con.prepareStatement("SELECT * FROM dbStockManage.sold;");


                    flag=1;
                    break;
                }
            }
            if(flag == 0){
                st.executeUpdate("INSERT INTO `dbStockManage`.`sold` (`Product_Name`, `Quantity`, `Sold_Date`) VALUES ('"+s+"','" + qs + "','" + date + "');");
                PreparedStatement refreshUpdate = con.prepareStatement("SELECT * FROM dbStockManage.whole_stock;");

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void userInfo(String selectSql, String logEmail, String logPass){
        try {
            ResultSet rs = st.executeQuery(selectSql);
            int flag = 0;
            while (rs.next()){
                if(logEmail.equals(rs.getString("Email"))&&logPass.equals(rs.getString("Password"))){
                    JOptionPane.showMessageDialog(null,"Welcome "+rs.getString("UserName"),"Logged in",JOptionPane.INFORMATION_MESSAGE);
                    flag=1;
                    match = 1;
                    break;
                }
            }
            if(flag == 0)
                match = 0;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //method sold
    public void check(String product, String quantity){
        try {
            PreparedStatement preSt = con.prepareStatement("SELECT * FROM whole_stock;");
            rs = preSt.executeQuery();
            int flag = 0;
            while (rs.next()){

                if(product.equals(rs.getString("Product_Name"))){

                    int qnt = Integer.parseInt(rs.getString("Quantity"));
                    int sale_qnt = Integer.parseInt(quantity);
                    String i = rs.getString("Serial");
                    if(qnt<sale_qnt) {
                        int create = JOptionPane.showOptionDialog(null,"Insufficient Product in the Stock.\n"+"Current Stock of "+rs.getString("Product_Name")+" is "+rs.getString("Quantity")+"."+"\nAdd Anyway?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                        if(create != 1){
                            match = 1;
                        }
                        else {
                            match = 0;
                        }
                    } else{
                        match=1;
                    }
                    flag=1;
                    break;
                }

            }
            if(flag == 0)
                JOptionPane.showMessageDialog(null,"Product not found.","Try Again",JOptionPane.ERROR_MESSAGE);

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    //confirm memo created
    public void confirmMemo(String[][] memo) {
        int row = memo.length;
        try {
            for (int i = 0; i < row; i++) {
                PreparedStatement preStat1 = con.prepareStatement("select * from whole_stock");
                rs = preStat1.executeQuery();
                int flag = 0;
                while (rs.next()) {
                    if (memo[i][0].equals(rs.getString("Product_Name"))) {
                        int newQ = Integer.parseInt(rs.getString("Quantity")) - Integer.parseInt(memo[i][1]);
                        System.out.println(newQ);
                        System.out.println(memo[i][0]);
                        System.out.println(Integer.parseInt(rs.getString("Quantity")));
                        PreparedStatement prepUpdate = con.prepareStatement("UPDATE whole_stock SET `Quantity` = '"+newQ+"' WHERE (`Serial` = '"+rs.getString("Serial")+"') and (`Product_Code` = '"+rs.getString("Product_Code")+"');");
                        prepUpdate.executeUpdate();
                        PreparedStatement refreshUpdate = con.prepareStatement("SELECT * FROM whole_stock;");
                        refreshUpdate.executeQuery();

                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    JOptionPane.showMessageDialog(null,"Product "+memo[i][0]+" is not in the stock");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //method retrieve mrp of a product
    public void retPrice(String product){
        try {
            rs = st.executeQuery("SELECT * FROM whole_stock;");
            int flag = 0;
            while (rs.next()) {

                if (product.equals(rs.getString("Product_Name"))) {
                    found = rs.getString("Market_Price_Rate");
                    System.out.println(found);
                    flag = 1;
                    break;
                }

            }
            if(flag==0) JOptionPane.showMessageDialog(null,"Product not found.","Try Again",JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                st.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }

    }

    //retrieve name
   /* public void retName(String code){
        try {
            rs = st.executeQuery("SELECT * FROM dbStockManage.whole_stock;");
            int flag = 0;
            while (rs.next()) {

                if (code.equals(rs.getString("Product_Code"))) {
                    found = rs.getString("Product_Name");
                    flag = 1;
                    break;
                }

            }
            if(flag==0) JOptionPane.showMessageDialog(null,"Product not found.","Try Again",JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                st.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }

    }*/


    //calculate profit
    public void profitTable(String name, double net, int qn) {
        double profit=0.0;
        int flag = 0;
        try {
            PreparedStatement preProfit = con.prepareStatement("select Product_Name,Dealer_Price_Rate from whole_stock");
            rs = preProfit.executeQuery();
            while(rs.next()){
                if(name.equals(rs.getString("Product_Name"))){
                    double actualP = Double.parseDouble(rs.getString("Dealer_Price_Rate"));
                    actualP *= qn;
                    profit = net - actualP;
                    checkProfit(profit);
                    flag = 1;
                    break;
                }

            }
            if(flag == 0){
                JOptionPane.showMessageDialog(null,"Product not found.");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                con.close(); // throws exception here
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }

    }

    //daily profit entry to database
    public void checkProfit(double profit){
        SMLibrary lib = new SMLibrary();
        int trace = 0;

        try {
            rs = st.executeQuery("select * from profit_table;");

            while (rs.next()) {

                if (lib.DFormat().equals(rs.getString("Date"))) {
                    double updateProfit = Double.parseDouble(rs.getString("Net_Profit"));
                    updateProfit += profit;
                    PreparedStatement prepUpdate = con.prepareStatement("UPDATE profit_table SET Net_Profit = "+updateProfit+" WHERE Date = '"+lib.DFormat()+"'");
                    prepUpdate.executeUpdate();
                    trace = 1;
                    break;
                }
            }
            if(trace == 0){
                st.executeUpdate("INSERT INTO profit_table (`Net_Profit`, `Date`) VALUES ('"+profit+"','" + lib.DFormat() + "');");

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //view stock
    public String[][] stock(String[][] obj2){
        int i = 0;
        try{
            PreparedStatement preState = con.prepareStatement("select * from whole_stock");
            rs = preState.executeQuery();

            while(rs.next()){
                String sl = rs.getString("Product_Code");
                String pCode = rs.getString("Product_Name");
                String pQuantity = rs.getString("Quantity");
                String mrp = rs.getString("Market_Price_Rate");
                String dp = rs.getString("Dealer_Price_Rate");

                obj2[i] = new String[] {sl, pCode, pQuantity, mrp, dp};

                i++;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        return obj2;
    }

    //view Info Table
    public String[][] InfoTable(String[][] obj2){
        int i = 0;
        try{
            PreparedStatement preState = con.prepareStatement("select * from product_info");
            rs = preState.executeQuery();

            while(rs.next()){
                String pCode = rs.getString("Product_Code");
                String pName = rs.getString("Name");
                String mrp = rs.getString("MRP");
                String ImgAd = rs.getString("image_address");

                obj2[i] = new String[] {pCode, pName, mrp, ImgAd};

                i++;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        return obj2;
    }

    public void addStock(String sl, String p, String q, String mrp, String dp){
        try {
            PreparedStatement prestate = con.prepareStatement("select * from whole_stock");
            prestate.executeUpdate("INSERT INTO `dbStockManage`.`whole_stock` (`Product_Code`, `Product_Name`, `Quantity`, `Market_Price_Rate`, `Dealer_Price_Rate`) VALUES ('"+sl+"','"+p+"','" + q + "','" + mrp + "','" + dp + "');");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delFromStock(String sl, String p){
        try {
            int i = 1;
            PreparedStatement preStat = con.prepareStatement("select * from whole_stock");
            rs = preStat.executeQuery();
            while (rs.next()){
                if(sl.equals(rs.getString("Product_Code"))) {
                    PreparedStatement prestate1 = con.prepareStatement("DELETE FROM `dbstockmanage`.`whole_stock` WHERE (`Serial` = '" + rs.getString("Serial") + "') and (`Product_Code` = '" + sl + "');");
                    prestate1.executeUpdate();
                }
                PreparedStatement prestate2 = con.prepareStatement("UPDATE `dbStockManage`.`whole_stock` SET `Serial` = '"+i+"' WHERE (`Serial` = '"+rs.getString("Serial")+"') and (`Product_Code` = '"+rs.getString("Product_Code")+"');");
                prestate2.executeUpdate();
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //update Stock
    public void UpdateStock(String sl, String p, String q, String mrp, String dp){
        try {
            PreparedStatement preSt = con.prepareStatement("select * from whole_stock");
            rs = preSt.executeQuery();
            while(rs.next()) {
                if (sl.equals(rs.getString("Product_Code"))) {
                    PreparedStatement preSate1 = con.prepareStatement("UPDATE whole_stock SET Product_Code = '" + sl + "', Product_Name = '" + p + "', Quantity = '" + q + "', Market_Price_Rate = '" + mrp + "', Dealer_Price_Rate = '" + dp + "' WHERE Serial = '" + rs.getString("Serial") + "' AND Product_Code = '" + rs.getString("Product_Code") + "'");
                    preSate1.executeUpdate();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Info table for product details
    public void addInfo(String p, String name, String mrp, String address){
        try {
            PreparedStatement prestate = con.prepareStatement("select * from product_info");
            prestate.executeUpdate("INSERT INTO `dbStockManage`.`Product_Info` (`Product_Code`, `Name`, `MRP`, `image_address`) VALUES ('"+p+"','" + name + "','" + mrp + "','" + address + "');");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delFromInfoTable(String p){
        try {
            int i = 1;
            PreparedStatement prestate1 = con.prepareStatement("select * from product_info");
            rs = prestate1.executeQuery();
            while (rs.next()) {
                if(p.equals(rs.getString("Product_Code"))) {
                    PreparedStatement prestate2 = con.prepareStatement("DELETE FROM `dbstockmanage`.`product_info` WHERE (`Serial` = '" + rs.getString("Serial") + "') and (`Product_Code` = '" + p + "');");
                    prestate2.executeUpdate();
                }
                PreparedStatement prestate = con.prepareStatement("UPDATE `dbStockManage`.`whole_stock` SET `Serial` = '"+i+"' WHERE (`Serial` = '"+rs.getString("Serial")+"') and (`Product_Code` = '"+rs.getString("Product_Code")+"');");
                prestate.executeUpdate();
                i++;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //profit table
    public String[][] showProfit(String[][] obj2){
        int i = 0;
        try{
            PreparedStatement preState = con.prepareStatement("select * from profit_table");
            rs = preState.executeQuery();

            while(rs.next()){
                String sl = rs.getString("Serial");
                String netP = rs.getString("Net_Profit");
                String dt = rs.getString("Date");

                obj2[i] = new String[] {sl, netP, dt};

                i++;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        return obj2;
    }

    //top sale
    public String[][] showTopSale(int n,String name){
        int i = 0, fl = 0;
        String[][] obj = new String[n][];
        String[][] nul = new String[n][];
        try{
            PreparedStatement preTemp = con.prepareStatement("select Sold_Date from sold where YEAR(Sold_Date) LIKE '"+name+"';");
            rs1 = preTemp.executeQuery();
            if(!rs1.isBeforeFirst() && rs1.getRow() == 0){
                Arrays.fill(nul, null);
                fl = 1;

            }else {
                PreparedStatement preState = con.prepareStatement("select Product_Name, Quantity from sold where YEAR(Sold_Date) = '" + name + "' order by Quantity DESC;");
                rs = preState.executeQuery();

                while (rs.next()) {
                    if (i < n) {
                        String p = rs.getString("Product_Name");
                        String q = rs.getString("Quantity");
                        if (i == 0) {
                            largest = Integer.parseInt(q);
                        }
                        obj[i] = new String[]{p, q};
                        i++;
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        if(fl == 0) {
            return obj;
        } else{
            return nul;
        }
    }
    //////****************************///////////////////////////////****************

    //show profit chart
    public Double[] showProfitChart(String name){
        int i = 0;
        double temp = 0.0;
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Double[] obj = new Double[12];
        Double[] old = new Double[12];
        Double[] nul = new Double[12];
        try{
            PreparedStatement preTemp = con.prepareStatement("select Date from profit_table where YEAR(Date) LIKE '"+name+"';");
            rs1 = preTemp.executeQuery();
            if(!rs1.isBeforeFirst() && rs1.getRow() == 0){
                Arrays.fill(nul, null);
                i = 1;

            }else {
                for (int j = 0; j < 12; j++) {
                    PreparedStatement preState = con.prepareStatement("select Net_Profit from profit_table where EXTRACT(YEAR_MONTH From `Date`) = '" + name + month[j] + "';");
                    rs = preState.executeQuery();

                    while (rs.next()) {
                        temp += Double.parseDouble(rs.getString("Net_Profit"));

                    }

                    obj[j] = temp;
                    old[j] = temp;
                    temp = 0.0;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        if(i == 0) {
            Arrays.sort(old);
            largest = old[11].intValue();
            return obj;
        } else{
            return nul;
        }
    }

    //Product information
    public String[] ProductInfo(String product){
        String[] s = new String[3];
        try {
            PreparedStatement prep = con.prepareStatement("select * from product_info where Name = '"+product+"';");
            rs = prep.executeQuery();
            while (rs.next()){
                s = new String[] {rs.getString("image_address"),rs.getString("Name"),rs.getString("MRP")};
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    //save order
    public void SaveOrder(String[][] order){
        int row = order.length;

        SMLibrary lib = new SMLibrary();
        try{
            for(int i = 0; i<row; i++){
                PreparedStatement preStat1 = con.prepareStatement("select * from ordered_item");
                rs = preStat1.executeQuery();
                int flag = 0;
                while(rs.next()) {
                    if (order[i][0].equals(rs.getString("Product_Code"))) {
                        int newQ = Integer.parseInt(rs.getString("Quantity")) + Integer.parseInt(order[i][1]);
                        PreparedStatement preStat2 = con.prepareStatement("Update ordered_item set Quantity = '" + newQ+"' where Serial = '" + rs.getString("Serial") + "' and Product_Code = '"+rs.getString("Product_Code")+"';");
                        preStat2.executeUpdate();
                        flag = 1;
                        break;
                    }
                }
                if(flag==0){
                    PreparedStatement preStat = con.prepareStatement("insert into ordered_item (`Product_Code`, `Quantity`) VALUES ('"+order[i][0]+"', '"+order[i][1]+"');");
                    preStat.executeUpdate();
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    //RECEIVED ITEM DB UPDATE
    public void orderReceived(String s, String q){
        SMLibrary lib = new SMLibrary();
        try {
            int fl = 0;
            PreparedStatement preStat = con.prepareStatement("select * from ordered_item");
            rs = preStat.executeQuery();
            while(rs.next()) {
                if(s.equals(rs.getString("Product_Code"))) {
                    if(rs.getString("Quantity").equals("0")){
                        JOptionPane.showMessageDialog(null, "You didn't make the order for this product yet.");
                    } else {
                        int newQ = Integer.parseInt(rs.getString("Quantity")) - Integer.parseInt(q);
                        PreparedStatement preSate1 = con.prepareStatement("UPDATE ordered_item SET Quantity = '" + newQ + "', Date_Last_Updated = '" + lib.DFormat() + "', Quantity_Updated = '" + q + "' WHERE Serial = '" + rs.getString("Serial") + "'");
                        preSate1.executeUpdate();
                        updateStockOrdered(s, q);
                        JOptionPane.showMessageDialog(null, "Product Quantity Updated.");
                        orderReceivedHistory(s, q);
                        fl = 1;
                        break;
                    }
                }
            }
            if(fl == 0){
                JOptionPane.showMessageDialog(null,"Order not found.","Information",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //update RECEIVED product IN stock
    public void updateStockOrdered(String s, String q){
        try {
            PreparedStatement preStat = con.prepareStatement("select * from whole_stock");
            rs = preStat.executeQuery();
            while (rs.next()) {
                if (s.equals(rs.getString("Product_Code"))) {
                    int newQ = Integer.parseInt(rs.getString("Quantity")) + Integer.parseInt(q);
                    PreparedStatement preStat1 = con.prepareStatement("UPDATE whole_stock SET Quantity = '" + newQ + "' WHERE Serial = '" + rs.getString("Serial") + "' and Product_Code = '" + s + "';");
                    preStat1.executeUpdate();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //search stock
    public void searchTable(String s1, String s2){
        try{
            int found = 0, i=0;
            PreparedStatement preStat = con.prepareStatement("select * from "+s1);
            rs = preStat.executeQuery();
            while(rs.next()){
                if(s2.equals(rs.getString("Product_Code"))){
                    //JOptionPane.showMessageDialog(null,s2+" is found in serial no. "+rs.getString("Serial")+" in the table.");
                    match=i;
                    found=1;
                    break;
                }
                i++;
            }
            if(found==0) {
                JOptionPane.showMessageDialog(null,s2+" not found.","Product not found",JOptionPane.INFORMATION_MESSAGE);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //getting number of rows from a table
    public int getTableRowCount(String s){
        int k = 0;
        try{
            PreparedStatement preStat = con.prepareStatement("select * from "+s);
            rs = preStat.executeQuery();
            while(rs.next()){
                k++;
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return k;
    }

    //view order
    public String[][] showOrder(String[][] obj2){
        int i = 0;
        try{
            PreparedStatement preState = con.prepareStatement("select * from ordered_item");
            rs = preState.executeQuery();

            while(rs.next()){
                String sl = rs.getString("Serial");
                String pCode = rs.getString("Product_Code");
                String pQuantity = rs.getString("Quantity");
                String date_last_updated = rs.getString("Date_Last_Updated");
                String quantity_recieved = rs.getString("Quantity_Updated");

                obj2[i] = new String[] {sl, pCode, pQuantity, date_last_updated, quantity_recieved};

                i++;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        return obj2;
    }
    ///order history
    public void orderReceivedHistory(String s, String q){
        SMLibrary lib = new SMLibrary();

        try {
            PreparedStatement prStat = con.prepareStatement("insert into order_received_history (`Product_Code`, `Quantity`, `Date_Received`) VALUES ('"+s+"', '"+q+"', '"+lib.DFormat()+"');");
            prStat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //view history
    public String[][] showHistory(String[][] obj2){
        int i = 0;
        try{
            PreparedStatement preState = con.prepareStatement("select * from order_received_history");
            rs = preState.executeQuery();

            while(rs.next()){
                String sl = rs.getString("Serial");
                String pCode = rs.getString("Product_Code");
                String name = rs.getString("Name");
                String rQuantity = rs.getString("Quantity");
                String date_received = rs.getString("Date_Received");

                obj2[i] = new String[] {sl, pCode, name, rQuantity, date_received};

                i++;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                rs.close();
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();

            }
        }
        return obj2;
    }
}

