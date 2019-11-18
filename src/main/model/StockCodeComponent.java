package model;

import java.io.IOException;

public abstract class StockCodeComponent {
    protected String name;

    public StockCodeComponent(String name) {
        this.name = name;
    }

    // EFFECTS: prints out stock info
    public abstract void print() throws IOException;
}
