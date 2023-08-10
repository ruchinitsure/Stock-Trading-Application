package model;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An interface that represents the operations which an user can do.
 */
public interface User {
  /**
   * Method to add a inflexible portfolio.
   *
   * @param portfolio the inflexible portfolio that needs to be added.
   */
  void createPortfolio(Portfolio portfolio);

  /**
   * Method that returns a inflexible portfoliolist.
   *
   * @return inflexible portfoliolist.
   */
  ArrayList<Portfolio> displayPortfolios();

  /**
   * Method that returns a flexible portfoliolist.
   *
   * @return flexible portfoliolist.
   */
  ArrayList<Portfolio> displayFlexiblePortfolios();

  /**
   * Method that reads a inflexible XML file that the user provided.
   *
   * @param filePath the location of the inflexible XML file on the machine.
   */
  void readXMLFile(String filePath);

  /**
   * Method that reads a flexible XML file that the user provided.
   *
   * @param filePath the location of the flexible XML file on the machine.
   */
  void readFlexibleXMLFile(String filePath);

  /**
   * Method that returns the total inflexible portfolios of the user.
   *
   * @return the total inflexible portfolios created by the user.
   */

  int getNumberOfPortfolio();

  /**
   * Method that returns the total flexible portfolios of the user.
   *
   * @return the total flexible portfolios created by the user.
   */

  int getNumberOfFlexiblePortfolio();

  /**
   * Method that returns a particluar portfolio converted into a map.
   *
   * @param i the position of the portfolio in the portfoliolist.
   * @return a partfolio in the form of map.
   */
  Map<String, Float> displaySinglePort(int i);

  /**
   * Method that returns the  inflexible portfolio name from inflexible portfoliolist.
   *
   * @param i the index of the inflexible portfolio in inflexible portfoliolist.
   * @return the inflexible portfolio name.
   */
  String getPortfolioName(int i);

  /**
   * Method that returns the flexible portfolio name from flexible portfoliolist.
   *
   * @param i the index of the flexible portfolio in flexible portfoliolist.
   * @return the flexible portfolio name.
   */
  String getFlexiblePortfolioName(int i);

  /**
   * Method to displays a portfolio from the portfoliolist.
   *
   * @param portfolioName the name of the portfolio that needs to be displayed.
   * @return a portfolio.
   */
  Map<String, Float> getPortfolio(String portfolioName);

  /**
   * Method that returns a single sold flexible portfolio.
   *
   * @param i the index of the flexible portfolio which is sold.
   * @return a flexible portfolio which is sold.
   */
  Map<String, Map<String, Float>> displaySingleSoldFlexiblePort(int i);

  /**
   * Method to get the valuation of the inflexible portfolio.
   *
   * @param portfolioName the inflexible  portfolioName for which valuation needs to be done.
   * @param valuationdate the date at which portfolio value needs to be calculated.
   * @return the value of the inflexible portfolio.
   */
  List<Float> getValuationForPort(String portfolioName, String valuationdate);

  /**
   * Method to get the valuation of the flexible portfolio.
   *
   * @param portfolioName the flexible portfolioName for which valuation needs to be done.
   * @param valuationDate the date at which portfolio value needs to be calculated.
   * @return the value of the flexible portfolio.
   * @throws ParseException if parsing the portfolio causes an error.
   */
  List<Float> getValuationForFlexiblePort(String portfolioName, String valuationDate)
          throws ParseException;

  /**
   * Method to get the valuation for a single stock.
   *
   * @param stockSymbol   the stock symbol.
   * @param valuationDate the date at which valuation needs to be calculated.
   * @return the valuation of the stock.
   */
  Float getValuationForSingleSymbol(String stockSymbol, String valuationDate);

  /**
   * Method that allows the user to view the performance of the portfolio over a period of time.
   *
   * @param startDateString the start date of the period of time.
   * @param endDateString   the end date of the period of time.
   * @param portfolioName   the portfolio name.
   * @return a linkedhashmap which contains timestamps and corresponding number of asteriks.
   */
  LinkedHashMap<String, Integer> getPerformance(String startDateString, String endDateString,
                                                String portfolioName);

  /**
   * Method that returns the scale used for the portfolio performance.
   *
   * @return the scale.
   */
  float returnScale();

  /**
   * Helper method for calculation of scale for portfolio performance.
   *
   * @param valuationList the list which contains the valuations for each timestamp.
   * @return average of the valuations.
   */
  float calculateAverage(List<Float> valuationList);

  /**
   * Method which acts as a helper method to get cost basis of a flexible cost basis.
   *
   * @param portfolioName the portfolio name.
   * @return the composition of the portfolio.
   */
  Map<Integer, Map<String, Map<String, Float>>> getFlexibleCostBasis(String portfolioName);

  /**
   * Method that displays cost basis of a flexible portfolio.
   *
   * @param i the index at which the portfolio is present in the portfolio list.
   * @return the composition of the portfolio.
   */
  Map<Integer, Map<String, Map<String, Float>>> displayFlexibleCostBasis(int i);

  /**
   * Method which acts as a helper method for selling stocks from a flexible portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return the composition of sell stock list.
   */
  Map<Integer, Map<String, Map<String, Float>>> getFlexibleSoldNew(String portfolioName);

  /**
   * Method that displays single flexible portfolio after selling stocks.
   *
   * @param i the index at which the portfolio is present in the portfolio list.
   * @return the portfolio.
   */
  Map<Integer, Map<String, Map<String, Float>>> displaySingleSoldFlexiblePortNew(int i);

  /**
   * Method that calculates the total amount of money invested in a portfolio till a specific date.
   *
   * @param portfolioName the portfolio name.
   * @param costBasisDate the date at which cost basis of the portfolio needs to calculated.
   * @return the cost basis of the portfolio.
   * @throws ParseException if an error occurs while parsing the portfolio.
   */
  List<Float> calculateCostBasis(String portfolioName, String costBasisDate) throws ParseException;

  /**
   * Method that checks if a inflexible portfolio exists in the inflexible portfolio list.
   *
   * @param portfolioName the name of the inflexible portfolio.
   * @return if the inflexible portfolio exists or not.
   */
  boolean checkPortfolioExists(String portfolioName);

  /**
   * Method that checks if a flexible portfolio exists in the flexible portfolio list.
   *
   * @param portfolioName the name of the flexible portfolio.
   * @return if the flexible portfolio exists or not.
   */
  boolean checkFlexiblePortfolioExists(String portfolioName);

  /**
   * Method that adds stocks into the inflexible portfolio.
   *
   * @param names         the stock symbols.
   * @param qty           the quantities of the stocks.
   * @param portfolioName the name of the inflexible portfolio.
   */
  void addPortfolio(ArrayList<String> names, ArrayList<Float> qty, String portfolioName)
          throws IOException;

  /**
   * Method that adds stocks into the flexible portfolio.
   *
   * @param stkSymbols    the stock symbols.
   * @param stkQty        the quantities of the stocks.
   * @param stkDates      the buying dates of the stocks.
   * @param stkCom        the commission fees of the stocks.
   * @param portfolioName the name of the flexible portfolio.
   * @throws IOException if any input is invalid.
   */
  void addFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQty,
                            ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException;

  /**
   * Method that adds multiple stocks to the flexible portfolio.
   *
   * @param stkSymbols    the stock symbols.
   * @param stkQuantity   the quantities of the stocks.
   * @param stkDates      the buying dates of the stocks.
   * @param stkCom        the commission fees of the stocks.
   * @param portfolioName the name of the flexible portfolio.
   * @throws IOException if any input is invalid.
   */
  void addToFlexiblePortfolio1(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity,
                               ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException;

  /**
   * Method that checks if a particular stock exists in the flexible portfolio.
   *
   * @param stockSymbol   the stock symbol of that stock.
   * @param portfolioName the portfolio name in which the stock needs to be searched.
   * @return if the stock exists in the flexible portfolio or not.
   */
  boolean checkIfStockExistsInFlexiblePortfolio(String stockSymbol, String portfolioName);

  /**
   * Method that checks that the inputted selling date is after the buying date of the stock.
   *
   * @param sellDateString the date at which the user wants to sell the stock.
   * @param portfolioName  the portfolio name in which stock exists.
   * @param stockSymbol    the stock symbol.
   * @return if the selling date is after the buying date of the stock or not.
   * @throws ParseException if an error occurs while parsing the portfolio.
   */

  boolean checkSellStockDate(String sellDateString, String portfolioName, String stockSymbol)
          throws ParseException;

  /**
   * Method that checks if the selling stock quantity is greater than the stock quantity bought.
   *
   * @param qty           the stock quantity which user wants to sell.
   * @param portfolioName the portfolio name.
   * @param stockSymbol   the stock symbol of the stock which the user wants to sell.
   * @return if the selling stock quantity the user entered is valid.
   */
  boolean checkSellStockQty(Float qty, String portfolioName, String stockSymbol);

  /**
   * Method that incorporates the functionality of selling a stock from a flexible portfolio.
   * It also updates the xml file of the portfolio.
   *
   * @param stkSymbol     the stock symbol of the stock which needs to be sold.
   * @param stkQuantity   the stock quantity to be sold.
   * @param stkDate       the date at which the stock is sold.
   * @param portfolioName the flexible portfolio name.
   * @throws IOException if input is invalid.
   */
  void removeFromFlexiblePortfolio(String stkSymbol, Float stkQuantity, String stkDate
          , Integer comFees, String portfolioName) throws IOException;

  /**
   * Method to add a flexible portfolio.
   *
   * @param portfolio the flexible portfolio that needs to be added.
   */
  void createFlexiblePortfolio(Portfolio portfolio);

  /**
   * Method that displays the composition of a flexible portfolio.
   *
   * @param portfolioName the portfolio name.
   * @param inputDate     the date at which composition needs to be displayed.
   * @return composition of the portfolio with all the details.
   * @throws ParseException if an error occurs while parsing the portfolio.
   */
  Map<String, Map<String, Float>> getComposition(String portfolioName, String inputDate)
          throws ParseException;

  /**
   * Method to get stock symbol from the portfolio list.
   *
   * @param portfolioName the portfolio name.
   * @return the list of stock symbols in the portfolio.
   */
  List<String> getSymbol(String portfolioName);

  /**
   * Method that displays the composition of a single portfolio.
   *
   * @param i             the index at which the portfolio exists in the portfolio list.
   * @param date1         the date at which the composition of portfolio needs to be displayed.
   * @param portfolioName the portfolio name.
   * @return the composition of portfolio.
   * @throws ParseException if an error occurs while parsing the portfolio.
   */
  Map<String, Map<String, Float>> displayComposition(int i, String date1, String portfolioName)
          throws ParseException;

  /**
   * Method that displays a particular flexible portfolio from the flexible portfolio list.
   *
   * @param portfolioName the portfolio name.
   * @return a flexible portfolio.
   */
  Map<String, Map<String, Float>> getFlexiblePortfolio(String portfolioName);

  /**
   * Method that gets the composition of a sold flexible portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return the composition of the sold flexible portfolio.
   */
  Map<String, Map<String, Float>> getFlexibleSoldPortfolio(String portfolioName);

  /**
   * Method that returns a flexible portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return a flexible portfolio.
   */
  Portfolio getFlexiblePortfolioP(String portfolioName);

  /**
   * Method that displays the composition of a single flexible portfolio.
   *
   * @param i the index at which the portfolio exists in the portfolio list.
   * @return a single flexible portfolio.
   */
  Map<String, Map<String, Float>> displaySingleFlexiblePort(int i);

  /**
   * Method that writes the flexible portfolio into a xml file.
   *
   * @param portfolioName the flexible portfolio name which needs to be written.
   */
  void writeFlexibleXML(String portfolioName);

  /**
   * Method that adds the stock details when a stock is bought into the flexible portfolio.
   *
   * @param stkSymbols    the lis of stock symbols.
   * @param stkQuantity   the list of stock quantities.
   * @param stkDates      the list of buying dates of stocks.
   * @param stkCom        the commission fees of the stocks.
   * @param portfolioName the portfolio name.
   * @throws IOException if input is invalid.
   */
  void addToFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity,
                              ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException;

  /**
   * Method to find total stocks present in the portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return number of stocks in the portfolio.
   */
  int getStockSize(String portfolioName);

  /**
   * Method to invest a fixed amount in the portfolio.
   *
   * @param portfolioName the portfolio name.
   * @param newAmt        the amount to be invested.
   * @param comFees       the commission fee.
   * @param buyingDate    the investment date.
   * @param percentage    the list of percentages of stocks.
   */
  void investInPortfolio(String portfolioName, float newAmt, int comFees, String buyingDate,
                         ArrayList<Float> percentage);


}
