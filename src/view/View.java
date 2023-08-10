package view;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * An interface which displays the text-based user interface options.
 * Also displays the features visible to the user.
 */
public interface View {
  /**
   * Method that displays the menu and all the operations a user can do.
   */
  void displayMenu();

  /**
   * Method that displays a single inflexible portfolio contents to the user.
   *
   * @param hm   map which contains the stock symbols and quantities.
   * @param name the name of the portfolio name.
   */
  void displaySinglePortfolio(Map<String, Float> hm, String name);

  /**
   * Method that displays a single flexible portfolio contents to the user.
   *
   * @param map  map which contains the stock symbols, buying dates and quantities.
   * @param name the name of the portfolio name.
   */

  void displaySingleFlexiblePortfolio(Map<String, Map<String, Float>> map, String name);

  /**
   * Method that displays the total valuation of a inflexible portfolio.
   *
   * @param hm     the map which contains the stock symbols and quantities.
   * @param values the list of values of each stock in the portfolio.
   * @param name   the name of the portfolio.
   */
  void displaySinglePortValuation(Map<String, Float> hm, List<Float> values, String name);

  /**
   * Method that displays the total valuation of a flexible portfolio.
   *
   * @param hm            the map which contains the stock symbols and quantities.
   * @param values        the list of values of each stock in the portfolio.
   * @param portfolioName the name of the portfolio.
   */
  void displaySingleFlexiblePortValuation(Map<String, Map<String, Float>> hm, List<Float> values,
                                          String portfolioName);

  /**
   * Method that displays a message to the user on the console.
   *
   * @param output the message that has to be displayed.
   */
  void showOutput(String output);

  /**
   * Method that initialises the PrintStream object.
   *
   * @param outStream the PrintStream object.
   */
  void setOutStream(PrintStream outStream);

  /**
   * Method that displays the cost basis of a flexible portfolio at a specific date.
   *
   * @param newMap        the map which contains the composition of the portfolio.
   * @param valuation     the list of values of each stock in the portfolio.
   * @param portfolioName the name of the portfolio.
   */
  void displayCostBasis(Map<String, Map<String, Float>> newMap, List<Float> valuation,
                        String portfolioName);

  /**
   * Method that displays the portfolio performance over a period of time.
   *
   * @param portfolioName the portfolio name.
   * @param startDate     the start date of the period of time.
   * @param endDate       the end date of the period of time.
   * @param map           the map which contains the composition of the portfolio.
   * @param scale         the scale of the bar chart of the performance.
   */
  void getPerformanceForMonth(String portfolioName, String startDate, String endDate,
                              LinkedHashMap<String, Integer> map, float scale);
}
