package controller;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface which acts as a controller for GUI.
 */
public interface Controller2 {

  /**
   * Method that creates a flexible portfolio.
   * @throws IOException if input is invalid.
   */
  void createFlexible() throws IOException;


  /**
   * Method that buys stocks into a flexible portfolio.
   * @throws IOException if input is invalid.
   */
  void buyStockFlexiblePort() throws IOException;

  /**
   * Method that sells stock from a flexible portfolio.
   *
   * @throws ParseException if an error occurs while parsing the portfolio.
   * @throws IOException if input is invalid.
   */
  void sellStockFlexiblePort() throws ParseException, IOException;

  /**
   * Method that loads a flexible portfolio.
   */
  void uploadFlexiblePort();

  /**
   * Method that calculates the valuation of a flexible portfolio.
   */
  void valuationFlexiblePort();

  /**
   * Method that calculates the cost basis of a flexible portfolio.
   *
   * @throws ParseException if an error occurs while parsing the portfolio.
   */
  void costBasisFlexiblePort() throws ParseException;

  /**
   * Method to invest a fixed amount into an existing portfolio.
   * @throws IOException if input is invalid.
   */
  void investPortfolio() throws IOException;

  /**
   * Method that offers creating "start-to-finish" dollar-cost averaging as a single operation.
   * @throws IOException if input is invalid.
   */
  void dollarCost() throws IOException;

}
