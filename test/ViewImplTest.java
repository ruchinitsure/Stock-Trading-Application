import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'ViewImpl' class.
 */

public class ViewImplTest {

  private ByteArrayOutputStream bstream;

  private View view;

  @Before
  public void setUp() {
    PrintStream outStream;
    bstream = new ByteArrayOutputStream();
    outStream = new PrintStream(bstream);
    view = new ViewImpl();
    view.setOutStream(outStream);
  }

  @Test
  public void testDisplayMenu() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    outStream.println();
    outStream.println("Welcome!");
    outStream.println("Please select one of the options below:");
    outStream.println("1.View all portfolios");
    outStream.println("2.View one portfolio");
    outStream.println("3.Add a portfolio");
    outStream.println("4.Get the total value of a portfolio for a certain date");
    outStream.println("5.Upload xml file of portfolio");
    outStream.println("6.Create a flexible portfolio");
    outStream.println("7.View one flexible portfolio");
    outStream.println("8.Buy stock into a flexible portfolio");
    outStream.println("9.Sell stock from a flexible portfolio");
    outStream.println("10.Upload xml file of flexible portfolio");
    outStream.println("11.Get the total value of a flexible portfolio for a certain date");
    outStream.println("12.View the cost basis of a flexible portfolio");
    outStream.println("13.View how portfolio has performed over a period of time");
    outStream.println("14.Display all flexible portfolios");
    outStream.println("15.Exit");
    view.displayMenu();
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testSinglePortfolio() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    HashMap<String, Float> map = new HashMap<>();
    map.put("AAPL", 10f);

    outStream.println();
    outStream.println("Portfolio Name : edu");
    outStream.println("----------------------------------");
    outStream.println("Stock = AAPL, Quantity = 10.0");
    view.displaySinglePortfolio(map, "edu");
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testSingleFlexiblePortfolio() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    Map<String, Map<String, Float>> map = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap1.put("AAPL", 10f);
    //innerMap1.put("GOOGL", 5f);
    map.put("2001-09-18", innerMap1);
    outStream.println();
    outStream.println("Portfolio Name : edu");
    outStream.println("----------------------------------");
    outStream.println("Buying Date : 2001-09-18");
    outStream.println("Stock = AAPL, Quantity = 10.0");
    view.displaySingleFlexiblePortfolio(map, "edu");
    outStream.println();
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testShowOutput() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    outStream.println("Show output testing");
    view.showOutput("Show output testing");
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testDisplaySinglePortValuation() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    HashMap<String, Float> map = new HashMap<>();
    map.put("AAPL", 10f);
    map.put("GOOGL", 5f);
    List<Float> values = new ArrayList<>();
    values.add((float) 150.345);
    values.add((float) 74.896);
    outStream.println();
    outStream.println("Portfolio Name : edu");
    outStream.println("----------------------------------");
    outStream.println();
    outStream.println("Stock = GOOGL, Quantity = 5.0");
    outStream.println("Value of the stock: 150.345");
    outStream.println();
    outStream.println();
    outStream.println("Stock = AAPL, Quantity = 10.0");
    outStream.println("Value of the stock: 74.896");
    outStream.println();
    outStream.println();
    outStream.println("Total Valuation : 225.241");
    view.displaySinglePortValuation(map, values, "edu");
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testDisplayFlexiblePortValuation() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    Map<String, Map<String, Float>> map = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap1.put("AAPL", 10f);
    map.put("2001-09-18", innerMap1);
    List<Float> values = new ArrayList<>();
    values.add((float) 150.345);
    //values.add((float) 74.896);
    outStream.println();
    outStream.println("Portfolio Name : edu");
    outStream.println("Buying Date : 2001-09-18");
    //outStream.println();
    outStream.println("Stock = AAPL, Quantity = 10.0");
    outStream.println("Value of the stock: 150.345");
    outStream.println();
    outStream.println();
    outStream.println("Total Valuation : 150.345");
    view.displaySingleFlexiblePortValuation(map, values, "edu");
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testCostBasis() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    Map<String, Map<String, Float>> map = new HashMap<>();
    Map<String, Float> innerMap1 = new HashMap<>();
    innerMap1.put("AAPL", 10f);
    map.put("2001-09-18", innerMap1);
    List<Float> values = new ArrayList<>();
    values.add((float) 150.345);
    outStream.println();
    outStream.println("Portfolio Name : edu");
    outStream.println();
    outStream.println("Cost Basis of the portfolio : 150.345");
    view.displayCostBasis(map, values, "edu");
    assertEquals(byteStream.toString(), bstream.toString());
  }

  @Test
  public void testPerformance() {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(byteStream);
    LinkedHashMap<String, Integer> innerMap1 = new LinkedHashMap<>();
    innerMap1.put("2005-01-05", 3);
    innerMap1.put("2005-01-06", 3);
    innerMap1.put("2005-01-07", 3);
    innerMap1.put("2005-01-08", 3);
    innerMap1.put("2005-01-09", 3);
    innerMap1.put("2005-01-10", 3);
    outStream.println("Performance of portfolio " + "edu" + " from " + "2005-01-05" + " to " +
            "2005-01-10");
    outStream.println();
    outStream.println("2005-01-05 : ***");
    outStream.println("2005-01-06 : ***");
    outStream.println("2005-01-07 : ***");
    outStream.println("2005-01-08 : ***");
    outStream.println("2005-01-09 : ***");
    outStream.println("2005-01-10 : ***");
    outStream.println();
    outStream.println("Scale: * = $" + 23f);
    view.getPerformanceForMonth("edu", "2005-01-05", "2005-01-10",
            innerMap1, 23f);
    assertEquals(byteStream.toString(), bstream.toString());
  }
}