import org.junit.Test;

import model.Stock;
import model.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'StockImpl' model.
 * Testing by passing in static values of quantity,ticker.
 */
public class StockImplTest {

  @Test
  public void testStockSymbol() {
    float quantity = 25f;
    String stockSymbol = "AAPL";
    Stock s = new StockImpl(stockSymbol, quantity);
    assertEquals("AAPL", s.getStockSymbol());
  }

  @Test
  public void testStockDate() {
    Float quantity = 25f;
    String stockSymbol = "AAPL";
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(stockSymbol, quantity, date1, comFee);
    assertEquals("2001-09-18", s.getStockDate());
  }

  @Test
  public void testEmptyStockDate() {
    float quantity = 25f;
    String stockSymbol = "AAPL";
    String date1 = "";
    int comFee = 50;
    Stock s = new StockImpl(stockSymbol, quantity, date1, comFee);
    assertEquals("", s.getStockDate());
  }

  @Test
  public void testEmptyStockSymbol() {
    int quantity = 25;
    String stockSymbol = "";
    Stock s = new StockImpl(stockSymbol, quantity);
    assertEquals("", s.getStockSymbol());
  }

  @Test
  public void testStockQuantity() {
    Float quantity = 25f;
    String stockSymbol = "AAPL";
    Stock s = new StockImpl(stockSymbol, quantity);
    assertEquals(25f, s.getStockQuantity(), 0.1);
  }

  @Test
  public void testZeroStockQuantity() {
    Float quantity = 0f;
    String stockSymbol = "AAPL";
    Stock s = new StockImpl(stockSymbol, quantity);
    assertEquals(0.00, s.getStockQuantity(), 0.1);
  }

  @Test
  public void testSetQuantity() {
    Float quantity = 25f;
    String stockSymbol = "AAPL";
    String date1 = "2001-09-18";
    int comFee1 = 50;
    Stock s = new StockImpl(stockSymbol, quantity, date1, comFee1);

    Float quantity1 = 30f;
    String stockSymbol1 = "AAPL";
    String date2 = "2001-09-18";
    int comFee2 = 60;
    Stock s1 = new StockImpl(stockSymbol1, quantity1, date2, comFee2);
    s.setStockQuantity(quantity1);
    assertEquals(s1.getStockQuantity(), s.getStockQuantity(), 0.1);
  }

}