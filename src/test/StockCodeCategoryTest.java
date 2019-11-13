import model.StockCode;
import model.StockCodeCategory;
import network.ReadWebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockCodeCategoryTest {
    StockCodeCategory stockCodeCategory;

    @BeforeEach
    public void runBefore() {
        stockCodeCategory = new StockCodeCategory("sccat");
    }

    @Test
    public void testAddComponent() throws IOException {
        StockCode fb = new StockCode("FB");
        StockCode goog = new StockCode("GOOG");
        StockCodeCategory tech = new StockCodeCategory("tech");

        stockCodeCategory.addComponent(fb);
        stockCodeCategory.addComponent(tech);
        assertTrue(stockCodeCategory.getStockCodeComponents().contains(fb));
        assertTrue(stockCodeCategory.getStockCodeComponents().contains(tech));

        stockCodeCategory.print();
    }
}
