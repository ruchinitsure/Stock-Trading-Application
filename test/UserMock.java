import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Portfolio;
import model.User;

/**
 * Logs the inputs provided to it.
 * Used to test the controller in isolation.
 */

public class UserMock implements User {

  private PrintStream out;

  public UserMock(PrintStream outStream) {
    this.out = outStream;
  }

  @Override
  public void createPortfolio(Portfolio portfolio) {
    this.out.println("Created portfolio: " + portfolio.getPortfolioName());
  }

  @Override
  public ArrayList<Portfolio> displayPortfolios() {
    this.out.println("Displayed all portfolios.");
    return null;
  }

  @Override
  public ArrayList<Portfolio> displayFlexiblePortfolios() {
    this.out.println("Displayed all flexible portfolios.");
    return null;
  }

  @Override
  public void readXMLFile(String filePath) {
    this.out.println("XML File succesfully read from the " + filePath + "provided.");
  }

  @Override
  public void readFlexibleXMLFile(String filePath) {
    this.out.println("Flexible XML File succesfully read from the " + filePath + "provided.");
  }

  @Override
  public int getNumberOfPortfolio() {
    this.out.println("Number of Portfolios returned.");
    return 1;
  }

  @Override
  public int getNumberOfFlexiblePortfolio() {
    this.out.println("Number of Flexible Portfolios returned.");
    return 0;
  }

  @Override
  public Map<String, Float> displaySinglePort(int i) {
    this.out.println("Displayed single portfolio from the portfoliolist.");
    Map<String, Float> m = new HashMap<>();
    return m;
  }

  @Override
  public String getPortfolioName(int i) {
    this.out.println("Portfolio name returned at index " + i + " from the portfoliolist.");
    return null;
  }

  @Override
  public String getFlexiblePortfolioName(int i) {
    this.out.println("Portfolio name returned at index " + i + " from the Flexible portfoliolist.");
    return null;
  }

  @Override
  public Map<String, Float> getPortfolio(String portfolioName) {
    this.out.println("Portfolio returned with name: " + portfolioName);
    Map<String, Float> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<String, Map<String, Float>> displaySingleSoldFlexiblePort(int i) {
    this.out.println("Displayed single sold portfolio from the flexible portfoliolist.");
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public List<Float> getValuationForPort(String portfolioName, String valuationdate) {
    this.out.println("Portfolio name: " + portfolioName + "For valuation date: " + valuationdate);
    return null;
  }

  @Override
  public List<Float> getValuationForFlexiblePort(String portfolioName, String valuationDate)
          throws ParseException {
    this.out.println("Flexible Portfolio name: " + portfolioName + "For valuation date: "
            + valuationDate);
    return null;
  }

  @Override
  public Float getValuationForSingleSymbol(String stockSymbol, String valuationDate) {
    return null;
  }

  @Override
  public LinkedHashMap<String, Integer> getPerformance(String startDateString, String endDateString,
                                                       String portfolioName) {
    this.out.println("Displayed graph for the portfolio" + portfolioName);
    LinkedHashMap<String, Integer> m = new LinkedHashMap<>();
    return m;
  }

  @Override
  public float returnScale() {
    return 1;
  }

  @Override
  public float calculateAverage(List<Float> valuationList) {
    return 1;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> getFlexibleCostBasis(String portfolioName) {
    this.out.println("Got single portfolio from the portfoliolist.");
    Map<Integer, Map<String, Map<String, Float>>> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> displayFlexibleCostBasis(int i) {
    this.out.println("Displayed single portfolio from the portfoliolist.");
    Map<Integer, Map<String, Map<String, Float>>> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> getFlexibleSoldNew(String portfolioName) {
    this.out.println("got single sold portfolio from the portfoliolist.");
    Map<Integer, Map<String, Map<String, Float>>> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> displaySingleSoldFlexiblePortNew(int i) {
    this.out.println("Displayed single sold portfolio from the portfoliolist.");
    Map<Integer, Map<String, Map<String, Float>>> m = new HashMap<>();
    return m;
  }

  @Override
  public List<Float> calculateCostBasis(String portfolioName, String costBasisDate)
          throws ParseException {
    this.out.println("Portfolio name: " + portfolioName + "costBasis " + costBasisDate);
    return null;
  }

  @Override
  public boolean checkPortfolioExists(String portfolioName) {
    this.out.println("Checked if portfolio exists with name: " + portfolioName);
    return true;
  }

  @Override
  public boolean checkFlexiblePortfolioExists(String portfolioName) {
    this.out.println("Checked if flexible portfolio exists with name: " + portfolioName);
    return true;
  }

  @Override
  public void addPortfolio(ArrayList<String> names, ArrayList<Float> qty, String portfolioName) {
    this.out.println("Added portfolio of name: " + portfolioName);
  }

  @Override
  public void addFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQty,
                                   ArrayList<String> stkDates, ArrayList<Integer> comFees,
                                   String portfolioName) {
    this.out.println("Added flexible portfolio of name: " + portfolioName);
  }

  @Override
  public void addToFlexiblePortfolio1(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity
          , ArrayList<String> stkDates, ArrayList<Integer> stkCom, String portfolioName) {
    this.out.println("Added flexible portfolio for investment of name: " + portfolioName);
  }

  @Override
  public boolean checkIfStockExistsInFlexiblePortfolio(String stockSymbol, String portfolioName) {
    this.out.println("Checked if " + stockSymbol + "exists in flexible portfolio with name: "
            + portfolioName);
    return true;
  }

  @Override
  public boolean checkSellStockDate(String sellDateString, String portfolioName, String stockSymbol)
          throws ParseException {
    this.out.println("Checked if" + sellDateString + " is valid for Portfolio:" + portfolioName);
    return true;
  }

  @Override
  public boolean checkSellStockQty(Float qty, String portfolioName, String stockSymbol) {
    this.out.println("Checked if" + qty + " is valid for Portfolio:" + portfolioName);
    return true;
  }

  @Override
  public void removeFromFlexiblePortfolio(String stkSymbol, Float stkQuantity, String stkDate
          , Integer comFees, String portfolioName) {
    this.out.println("Removed from portfolio: " + portfolioName + "quantity:" + stkQuantity + "on"
            + stkDate + "for stock symbol:" + stkSymbol);
  }

  @Override
  public void createFlexiblePortfolio(Portfolio portfolio) {
    this.out.println("Created portfolio: " + portfolio.getPortfolioName());
  }

  @Override
  public Map<String, Map<String, Float>> getComposition(String portfolioName, String inputDate)
          throws ParseException {
    this.out.println("Get composition of the flexible portfolio" + portfolioName);
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public List<String> getSymbol(String portfolioName) {
    this.out.println("Get symbol");
    return null;
  }

  @Override
  public Map<String, Map<String, Float>> displayComposition(int i, String date1,
                                                            String portfolioName)
          throws ParseException {
    this.out.println("Display the composition of the flexible portfolio:" + portfolioName);
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<String, Map<String, Float>> getFlexiblePortfolio(String portfolioName) {
    this.out.println("Function that gets the flexible portfolio:" + portfolioName);
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public Map<String, Map<String, Float>> getFlexibleSoldPortfolio(String portfolioName) {
    this.out.println("Displayed single sold portfolio from the flexible portfoliolist" +
            " for portfolio" + portfolioName);
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public Portfolio getFlexiblePortfolioP(String portfolioName) {
    this.out.println("Get portfolio" + portfolioName);
    return null;
  }

  @Override
  public Map<String, Map<String, Float>> displaySingleFlexiblePort(int i) {
    this.out.println("Displayed single flexible portfolio from the flexible portfoliolist.");
    Map<String, Map<String, Float>> m = new HashMap<>();
    return m;
  }

  @Override
  public void writeFlexibleXML(String portfolioName) {
    this.out.println("Flexible XML File succesfully written for the portfolio" + portfolioName);

  }

  @Override
  public void addToFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity,
                                     ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) {
    this.out.println("Stocks successfully added for symbol" + stkSymbols + "having quantity"
            + stkQuantity + "for dates" + stkDates + "for the portfolio:" + portfolioName);

  }

  @Override
  public int getStockSize(String portfolioName) {
    return 0;
  }

  @Override
  public void investInPortfolio(String portfolioName, float newAmt, int comFees, String buyingDate
          , ArrayList<Float> percentage) {
    this.out.println("Invest in " + portfolioName + "amount" + newAmt);

  }
}
