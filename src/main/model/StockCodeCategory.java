package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockCodeCategory extends StockCodeComponent {
    List<StockCodeComponent> stockCodeComponents;

    public StockCodeCategory(String name) {
        super(name);
        stockCodeComponents = new ArrayList<>();
    }

    @Override
    public void print() throws IOException {
        for (StockCodeComponent scc : stockCodeComponents) {
            scc.print();
        }
    }

    public void addComponent(StockCodeComponent scc) {
        stockCodeComponents.add(scc);
    }

    public List<StockCodeComponent> getStockCodeComponents() {
        return stockCodeComponents;
    }
}
