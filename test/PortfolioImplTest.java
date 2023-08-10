import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'PortfolioImpl' class.
 */
public class PortfolioImplTest {

  Portfolio port;

  Portfolio sellStock;
  Stock s;

  Stock stock;

  @Before
  public void setUp() {
    this.port = new PortfolioImpl("Health");
    this.s = new StockImpl("AAPL", 20f);
    this.stock = new StockImpl("AAPL", 20f, "2001-09-18", 100);
  }

  @Test
  public void testGetPortfolioName() {
    assertEquals("Health", port.getPortfolioName());
  }

  @Test
  public void testEmptyGetPortfolioName() {
    Portfolio portfolio = new PortfolioImpl("");
    assertEquals("", portfolio.getPortfolioName());
  }

  @Test
  public void testAddStock() throws IOException {
    assertEquals(0, port.getNumberOfStock());
    port.addStock(s);
    assertEquals(1, port.getNumberOfStock());
  }

  @Test
  public void testGetZeroNumberOfStock() {
    assertEquals(0, port.getNumberOfStock());
  }

  @Test
  public void testOneGetNumberOfStocks() throws IOException {
    port.addStock(s);
    assertEquals(1, port.getNumberOfStock());
  }

  @Test
  public void testMultipleGetNumberOfStocks() throws IOException {
    port.addStock(s);
    assertEquals(1, port.getNumberOfStock());
    port.addStock(new StockImpl("GOOGL", 5));
    assertEquals(2, port.getNumberOfStock());
    port.addStock(new StockImpl("CA", 3));
    assertEquals(3, port.getNumberOfStock());
    port.addStock(new StockImpl("AAPL", 300));
    assertEquals(4, port.getNumberOfStock());
  }

  @Test
  public void testSellStocks() throws IOException {
    String sName = "AAPL";
    Float quan1 = 10f;
    String date1 = "2007-01-04";
    int comFee = 100;
    Stock s1 = new StockImpl(sName, quan1, date1, comFee);
    List<Stock> stock1 = new ArrayList<>();
    stock1.add(s1);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.setSellStockList(stock1);
    assertEquals(stock1, pTemp1.displaySellStockList());
  }

  @Test
  public void testDisplayPortfolios() throws IOException {
    String sName1 = "AAPL";
    int quan1 = 100;
    Stock s1 = new StockImpl(sName1, quan1);
    String sName2 = "AMZN";
    int quan2 = 20;
    Stock s2 = new StockImpl(sName2, quan2);
    List<Stock> stock1 = new ArrayList<>();
    stock1.add(s1);
    stock1.add(s2);

    /*
    String sName2 = "AMZN";
    int quan2 = 20;
    Stock s2 = new StockImpl(sName2, quan2);
    List<Stock> stock2 = new ArrayList<>();
    stock2.add(s2);
    */

    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);
    pTemp1.addStock(s2);

    //Portfolio pTemp2 = new PortfolioImpl("p2");
    //pTemp2.addStock(s2);


    assertEquals(stock1, pTemp1.displayPortfolio());


  }
}