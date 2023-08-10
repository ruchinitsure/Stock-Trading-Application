package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An interface which defines all the operations that can be performed on the portfolio.
 */
public interface Portfolio {

  /**
   * Method that adds a stock into the stocklist.
   *
   * @param stockName the stock symbol to be added.
   * @throws IOException if input is invalid.
   */
  void addStock(Stock stockName) throws IOException;


  /**
   * Method that returns size of the portfolio.
   *
   * @return the number of stocks present in portfolio.
   */
  int getNumberOfStock();

  /**
   * Method that returns the stocklist.
   *
   * @return list of stocks of that portfolio.
   */
  ArrayList<Stock> displayPortfolio();


  /**
   * Method that returns the sold stocklist.
   *
   * @return list of stocks that were sold of that portfolio.
   */
  ArrayList<Stock> displaySellStockList();

  /**
   * Method that returns the name of the portfolio.
   *
   * @return the portfolio name.
   */
  String getPortfolioName();

  /**
   * Method that sets the sold stock list.
   *
   * @param input the list which contains all the stock details that were sold.
   * @throws IOException if input is invalid.
   */
  void setSellStockList(List<Stock> input) throws IOException;
}
