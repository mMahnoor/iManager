package com.mahnoorscode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class SMLibrary {

    boolean validateProductCode(String s){
        String productCodeEx = "[0-9]+";
        //String productNameEx = "[A-Z]+-[0-9]+-[0-9]+-?[0-9]";
        if (Pattern.matches(productCodeEx, s)) {
            return true;
        } else return false;
    }

    boolean validateProductName(String s){
        String productNameEx = "[A-Z]+-[0-9]+-[0-9]+-?[0-9]*";
        if (Pattern.matches(productNameEx, s)) {
            return true;
        } else return false;
    }

    boolean validateQuantities(String s){

        String productCodeEx = "[0-9]+";
        if (Pattern.matches(productCodeEx, s)) {
            return true;
        } else return false;
    }

    boolean validateQuantity(String s){

        String productCodeEx = "-?[0-9]+";
        if (Pattern.matches(productCodeEx, s)) {
            return true;
        } else return false;
    }

    public String DFormat(){
        String new_Date;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        new_Date = formatter.format(date);

        return new_Date;
    }

}
