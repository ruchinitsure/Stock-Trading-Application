package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements 'Portfolio' interface, creates stock lists and performs operations on them.
 */
public class PortfolioImpl implements Portfolio {
  private String portfolioName;
  private ArrayList<Stock> stockList;
  private ArrayList<Stock> sellStockList;

  /**
   * Constructor that initialises class variables.
   *
   * @param portfolioName the name of the portfolio.
   */
  public PortfolioImpl(String portfolioName) {
    stockList = new ArrayList<>();
    sellStockList = new ArrayList<>();
    this.portfolioName = portfolioName;
  }

  @Override
  public void addStock(Stock stock) throws IOException {
    if (stock == null) {
      throw new IOException("Stock can't be null!");
    }
    stockList.add(stock);
  }

  @Override
  public ArrayList<Stock> displayPortfolio() {
    return stockList;
  }

  @Override
  public ArrayList<Stock> displaySellStockList() {
    return sellStockList;
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public int getNumberOfStock() {
    return stockList.size();
  }

  @Override
  public void setSellStockList(List<Stock> input) throws IOException {
    if (input == null) {
      throw new IOException("Input cannot be null.");
    }
    for (int i = 0; i < input.size(); i++) {
      this.sellStockList.set(i, input.get(i));
    }
  }


}
