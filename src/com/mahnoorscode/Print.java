package com.mahnoorscode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Print implements Printable {
    final Component comp;

    public Print(Component comp){
        this.comp = comp;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page) throws PrinterException {
        if (page > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double comp_height = dim.getHeight();
        double comp_width = dim.getWidth();

        // get the bounds of the printable area
        double printer_height = format.getImageableHeight();
        double printer_width = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = printer_width / comp_width;
        double yRatio = printer_height / comp_height;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
}
