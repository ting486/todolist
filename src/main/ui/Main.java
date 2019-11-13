package ui;

import model.StockCode;
import model.StockCodeCategory;
import network.ReadWebPage;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ToDoList runThisList = new ToDoList();
        //ReadWebPage readWebPage = new ReadWebPage();
        StockCodeManager stockCodeManager = new StockCodeManager("stock code manager");

        //readWebPage.readUrl("AAPL");
        generateStockCodeManager(stockCodeManager).print();
        runThisList.run();
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

