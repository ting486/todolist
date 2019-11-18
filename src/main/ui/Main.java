package ui;

import model.StockCode;
import model.StockCodeCategory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;


public class Main extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    public Main() throws IOException, ParseException {
        super("To Do List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        DisplayPanel dp = new DisplayPanel();
        add(dp);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws IOException, ParseException {
        StockCodeManager stockCodeManager = new StockCodeManager("stock code manager");
        generateStockCodeManager(stockCodeManager).print();

        //ToDoList runThisList = new ToDoList();
        //runThisList.run();

        new Main();
    }

    private static StockCodeManager generateStockCodeManager(StockCodeManager scm) {
        StockCodeCategory tech = new StockCodeCategory("tech");
        StockCode aapl = new StockCode("AAPL");
        StockCode goog = new StockCode("GOOG");
        StockCode pih = new StockCode("PIH");

        scm.addStockComponent(tech);
        tech.addComponent(aapl);
        tech.addComponent(goog);
        scm.addStockComponent(pih);

        return scm;
    }

}

