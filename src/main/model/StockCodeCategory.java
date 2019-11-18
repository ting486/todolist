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

    // EFFECTS: iterates over stockCodeComponents and prints out stock info on each StockCodeComponent
    @Override
    public void print() throws IOException {
        for (StockCodeComponent scc : stockCodeComponents) {
            scc.print();
        }
    }

    // MODIFIES: stockCodeComponents
    // EFFECTS: adds scc to stockCodeComponents
    public void addComponent(StockCodeComponent scc) {
        stockCodeComponents.add(scc);
    }

    // EFFECTS: gets stockCodeComponents
    public List<StockCodeComponent> getStockCodeComponents() {
        return stockCodeComponents;
    }
}
