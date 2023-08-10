package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller2;

/**
 * An interface which displays the graphical user interface options.
 * Also displays the features visible to the user.
 */
public interface View2 {


  /**
   * Method which implements the features of controller.
   * Acts as an action listener.
   *
   * @param controllerImpl2 gui controller class.
   */
  void addFeatures(Controller2 controllerImpl2);

  /**
   * Shows message on the screen.
   *
   * @param message that has to be displayed on the screen.
   */
  void showOutput(String message);

  /**
   * Method for taking portfolio name and number of stocks as input for creation of portfolio.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> createPortfolioInput() throws IllegalArgumentException;

  /**
   * Method for taking portfolio name,amount to be invested,investment date, commission fee
   * and number of stocks as input for investing in a portfolio.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> investPortfolioInput() throws IllegalArgumentException;

  /**
   * Method for taking ticker and percentage as inputs while investing.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */
  ArrayList<String> investHelper() throws IllegalArgumentException;

  /**
   * Method for taking portfolio name,amount to be invested,investment start date, end date,
   * time span,commission fee and number of stocks as input for investing in a portfolio.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> dollarCostInput() throws IllegalArgumentException;

  /**
   * Method for taking portfolio name,ticker,quantity,date and commission fee as inputs
   * for buying and selling stocks.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> buyStocksHelper() throws IllegalArgumentException;

  /**
   * Method for taking portfolio name,ticker,quantity,date and commission fee as inputs
   * for creating a stock.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> createHelper() throws IllegalArgumentException;

  /**
   * Method for taking portfolio name, date as inputs.
   *
   * @return ArrayList containing all the inputs.
   * @throws IllegalArgumentException if any of the input is blank.
   */

  ArrayList<String> getInput() throws IllegalArgumentException;


  /**
   * Method for displaying portfolio valuation in the form of a table.
   *
   * @param hm            Map containing date, ticker symbol, quantity respectively.
   * @param values        list of valuations of all the stocks.
   * @param portfolioName name of the portfolio.
   */

  void displaySingleFlexiblePortValuation(Map<String, Map<String, Float>> hm,
                                          List<Float> values, String portfolioName);

  /**
   * Method for displaying cost basis of a portfolio.
   *
   * @param newMap        Map containing date, ticker symbol, quantity respectively.
   * @param valuation     list of valuations of all the stocks.
   * @param portfolioName name of the portfolio.
   */

  void displayCostBasis(Map<String, Map<String, Float>> newMap, List<Float> valuation,
                        String portfolioName);

}
