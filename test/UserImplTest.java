import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;
import model.User;
import model.UserImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'UserImpl' class.
 */
public class UserImplTest {
  Portfolio p;
  User user;

  @Before
  public void setUp() {
    this.p = new PortfolioImpl("Education");
    this.user = new UserImpl();
  }

  @Test
  public void testCreatePortfolio() throws IOException {

    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    assertEquals(1, u.getNumberOfPortfolio());

  }

  @Test
  public void testCreateFlexiblePortfolio() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    assertEquals(1, u.getNumberOfFlexiblePortfolio());
  }


  @Test
  public void testGetNumberOfPortfolio1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    assertEquals(1, u.getNumberOfPortfolio());
  }

  @Test
  public void testGetNumberOfFlexiblePortfolio1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    assertEquals(1, u.getNumberOfFlexiblePortfolio());
  }

  @Test
  public void testGetNumberOfPortfolio2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createPortfolio(pTemp2);

    assertEquals(2, u.getNumberOfPortfolio());
  }

  @Test
  public void testGetNumberOfFlexiblePortfolio2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);


    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(2, u.getNumberOfFlexiblePortfolio());
  }

  @Test
  public void testGetPortfolioName1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createPortfolio(pTemp2);

    assertEquals("p1", u.getPortfolioName(0));
    assertEquals("p2", u.getPortfolioName(1));
  }

  @Test
  public void testGetFlexiblePortfolioName1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals("p1", u.getFlexiblePortfolioName(0));
    assertEquals("p2", u.getFlexiblePortfolioName(1));
  }

  @Test
  public void testGetPortfolioName2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createPortfolio(pTemp2);

    assertEquals("p2", u.getPortfolioName(1));
  }

  @Test
  public void testGetFlexiblePortfolioName2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals("p2", u.getFlexiblePortfolioName(1));
  }

  @Test
  public void testCheckPortfolioExists1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createPortfolio(pTemp2);

    assertEquals(true, u.checkPortfolioExists("p1"));
  }

  @Test
  public void testCheckFlexiblePortfolioExists1() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(true, u.checkFlexiblePortfolioExists("p1"));
  }

  @Test
  public void testCheckPortfolioExists2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    Stock s = new StockImpl(sName, quan);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createPortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createPortfolio(pTemp2);

    assertEquals(false, u.checkPortfolioExists("p3"));
  }

  @Test
  public void testCheckFlexiblePortfolioExists2() throws IOException {
    String sName = "AAPL";
    Float quan = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s = new StockImpl(sName, quan, date1, comFee);

    Portfolio pTemp = new PortfolioImpl("p1");
    pTemp.addStock(s);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(false, u.checkFlexiblePortfolioExists("p3"));
  }

  @Test
  public void testAddPortfolio() throws IOException {
    ArrayList<String> stockSymbols = new ArrayList<>();
    ArrayList<Float> stockQuantity = new ArrayList<>();

    stockSymbols.add("AAPL");
    stockQuantity.add(10f);
    stockSymbols.add("AMZN");
    stockQuantity.add(50f);
    stockSymbols.add("TSLA");
    stockQuantity.add(20f);

    Portfolio pTemp = new PortfolioImpl("p1");


    User u = new UserImpl();
    u.addPortfolio(stockSymbols, stockQuantity, "p1");

    assertEquals(true, u.checkPortfolioExists("p1"));

  }

  @Test
  public void testAddFlexiblePortfolio() throws IOException {
    ArrayList<String> stockSymbols = new ArrayList<>();
    ArrayList<Float> stockQuantity = new ArrayList<>();
    ArrayList<String> stockDates = new ArrayList<>();
    ArrayList<Integer> stockCom = new ArrayList<>();

    stockSymbols.add("AAPL");
    stockQuantity.add(10f);
    stockDates.add("2001-09-18");
    stockCom.add(100);
    stockSymbols.add("AMZN");
    stockQuantity.add(50f);
    stockDates.add("2005-01-04");
    stockCom.add(100);
    stockSymbols.add("TSLA");
    stockQuantity.add(20f);
    stockDates.add("2003-01-05");
    stockCom.add(100);

    Portfolio pTemp = new PortfolioImpl("p1");


    User u = new UserImpl();
    u.addFlexiblePortfolio(stockSymbols, stockQuantity, stockDates, stockCom, "p1");

    assertEquals(true, u.checkFlexiblePortfolioExists("p1"));

  }

  @Test
  public void testDisplayFlexiblePortfolios() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee);

    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);

    String sName2 = "TSLA";
    Float quan2 = 50f;
    String date2 = "2002-01-03";
    int comFee2 = 50;

    Stock s2 = new StockImpl(sName2, quan2, date2, comFee2);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    List<Portfolio> p = new ArrayList<>();
    p.add(pTemp1);
    p.add(pTemp2);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(p, u.displayFlexiblePortfolios());
  }

  @Test
  public void testDisplayPortfolios() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    Stock s1 = new StockImpl(sName1, quan1);

    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);

    String sName2 = "TSLA";
    Float quan2 = 50f;
    Stock s2 = new StockImpl(sName2, quan2);

    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    List<Portfolio> p = new ArrayList<>();
    p.add(pTemp1);
    p.add(pTemp2);

    User u = new UserImpl();
    u.createPortfolio(pTemp1);
    u.createPortfolio(pTemp2);

    assertEquals(p, u.displayPortfolios());
  }

  @Test
  public void testDisplaySinglePortfolio() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    Stock s1 = new StockImpl(sName1, quan1);

    String sName2 = "TSLA";
    Float quan2 = 50f;
    Stock s2 = new StockImpl(sName2, quan2);

    HashMap<String, Float> stock1 = new HashMap<>();
    stock1.put("AAPL", 100f);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);

    HashMap<String, Float> stock2 = new HashMap<>();
    stock2.put("AMZN", 50f);
    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    User u = new UserImpl();
    u.createPortfolio(pTemp1);

    assertEquals(stock1, u.getPortfolio("p1"));
    //assertEquals(pTemp2, p.get(1));
  }

  //test?
  @Test
  public void testDisplayFlexibleSinglePortfolio() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);


    String sName2 = "TSLA";
    Float quan2 = 50f;
    String date2 = "2005-09-18";
    int comFee2 = 50;
    Stock s2 = new StockImpl(sName2, quan2, date2, comFee2);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);


    /*Map<String, Map<String, Float>> stock2 = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap.put("AMZN", 50f);
    stock2.put("2005-09-18",innerMap1);
    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

     */

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);

    assertEquals(stock1, u.getFlexiblePortfolio("p1"));
    //assertEquals(pTemp2, p.get(1));
  }

  @Test
  public void testValuationForPortfolio1() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    Stock s1 = new StockImpl(sName1, quan1);

    HashMap<String, Integer> stock1 = new HashMap<>();
    stock1.put("AAPL", 100);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);


    String sName2 = "TSLA";
    Float quan2 = 50f;
    Stock s2 = new StockImpl(sName2, quan2);

    HashMap<String, Integer> stock2 = new HashMap<>();
    stock2.put("TSLA", 50);
    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    User u = new UserImpl();
    u.createPortfolio(pTemp1);
    u.createPortfolio(pTemp2);

    assertEquals(16024.0, u.getValuationForPort("p1", "2021-11-29")
            .get(0), 0.01);
    assertEquals(56849.5, u.getValuationForPort("p2", "2021-11-29")
            .get(0), 0.01);

  }

  @Test
  public void testValuationForFlexiblePortfolio1() throws ParseException, IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);


    String sName2 = "TSLA";
    Float quan2 = 50f;
    String date2 = "2005-09-18";
    int comFee2 = 50;
    Stock s2 = new StockImpl(sName2, quan2, date2, comFee2);

    Map<String, Map<String, Float>> stock2 = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap.put("AMZN", 50f);
    stock2.put("2005-09-18", innerMap1);
    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(16024.0, u.getValuationForFlexiblePort("p1",
            "2021-11-29").get(0), 0.01);
    assertEquals(56849.5, u.getValuationForFlexiblePort("p2",
            "2021-11-29").get(0), 0.01);

  }

  @Test
  public void testCostBasisForFlexiblePortfolio1() throws ParseException, IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);


    String sName2 = "TSLA";
    Float quan2 = 50f;
    String date2 = "2005-09-18";
    int comFee2 = 50;
    Stock s2 = new StockImpl(sName2, quan2, date2, comFee2);

    Map<String, Map<String, Float>> stock2 = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap.put("AMZN", 50f);
    stock2.put("2005-09-18", innerMap1);
    Portfolio pTemp2 = new PortfolioImpl("p2");
    pTemp2.addStock(s2);

    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    u.createFlexiblePortfolio(pTemp2);

    assertEquals(1628.0001220703125, u.calculateCostBasis("p1",
            "2021-11-29").get(0), 0.01);

  }

  @Test
  public void testAddToFlexiblePortfolio() throws IOException {
    ArrayList<String> stockSymbols = new ArrayList<>();
    ArrayList<Float> stockQuantity = new ArrayList<>();
    ArrayList<String> stockDates = new ArrayList<>();
    ArrayList<Integer> stockCom = new ArrayList<>();

    stockSymbols.add("AAPL");
    stockQuantity.add(10f);
    stockDates.add("2001-09-18");
    stockCom.add(50);
    stockSymbols.add("AMZN");
    stockQuantity.add(50f);
    stockDates.add("2005-01-04");
    stockCom.add(100);
    stockSymbols.add("TSLA");
    stockQuantity.add(20f);
    stockDates.add("2003-01-05");
    stockCom.add(60);

    Portfolio pTemp = new PortfolioImpl("p1");


    User u = new UserImpl();
    u.addToFlexiblePortfolio(stockSymbols, stockQuantity, stockDates, stockCom, "p1");

    assertEquals(true, u.checkFlexiblePortfolioExists("p1"));

  }

  @Test
  public void testCheckIfStockExists() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);
    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    assertEquals(true, u.checkIfStockExistsInFlexiblePortfolio(sName1, "p1"));

  }

  @Test
  public void testCheckSellDate() throws ParseException, IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 50;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);
    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    assertEquals(true, u.checkSellStockDate("2002-09-18",
            "p1", sName1));

  }

  @Test
  public void testCheckStockQty() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 50;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);
    User u = new UserImpl();
    u.createFlexiblePortfolio(pTemp1);
    assertEquals(true, u.checkSellStockQty(10f, "p1", sName1));
  }

  @Test
  public void testCheckPerformance() throws IOException {
    String sName1 = "AAPL";
    Float quan1 = 100f;
    String date1 = "2001-09-18";
    int comFee1 = 100;
    Stock s1 = new StockImpl(sName1, quan1, date1, comFee1);

    Map<String, Map<String, Float>> stock1 = new HashMap<>();
    Map<String, Float> innerMap = new HashMap<>();
    innerMap.put("AAPL", 100f);
    stock1.put("2001-09-18", innerMap);
    Portfolio pTemp1 = new PortfolioImpl("p1");
    pTemp1.addStock(s1);
    User u = new UserImpl();
    LinkedHashMap<String, Integer> starMap = new LinkedHashMap<>();
    starMap.put("2020-01-04", 2);
    starMap.put("2020-01-05", 3);
    starMap.put("2020-01-06", 2);
    u.createFlexiblePortfolio(pTemp1);
    u.getPerformance("2020-01-04", "2020-01-06", "p1");
    assertEquals(3, starMap.size());
  }


}