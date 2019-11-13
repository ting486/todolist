package model;

import network.ReadWebPage;

import java.io.IOException;

public class StockCode extends StockCodeComponent {

    public StockCode(String codeName) {
        super(codeName);
    }

    @Override
    public void print() throws IOException {
        ReadWebPage rwp = new ReadWebPage();
        rwp.readUrl(name);
    }
}
