package ui;

import model.StockCodeCategory;
import model.StockCodeComponent;
import network.ReadWebPage;

import java.io.IOException;

public class StockCodeManager {
    public StockCodeCategory stockCodeCategory;
    public ReadWebPage readWebPage;

    public StockCodeManager(String name) {
        stockCodeCategory = new StockCodeCategory(name);
        readWebPage = new ReadWebPage();
    }

    public void print() throws IOException {
        stockCodeCategory.print();
    }



    public void addStockComponent(StockCodeComponent scc) {
        stockCodeCategory.addComponent(scc);
    }

}
