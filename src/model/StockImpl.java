package model;

/**
 * Class that implements 'Stock' interface and stores it's stock symbol(ticker) and quantity.
 */
public class StockImpl implements Stock {
  private String stockSymbol;
  private float quantity;
  private String stockDate;

  private int comFee;

  /**
   * Constructor that initialises StockImpl variables for Inflexible Portfolio.
   *
   * @param stockSymbol the stock symbol for a stock.
   * @param quantity    the quantity of stock the user wants to buy.
   */
  public StockImpl(String stockSymbol, float quantity) {
    this.stockSymbol = stockSymbol;
    this.quantity = quantity;
  }

  /**
   * Constructor that initialises StockImpl variables for flexible Portfolio.
   *
   * @param stockSymbol the stock symbol for a stock.
   * @param quantity    the quantity of stock the user wants to buy.
   * @param stockDate   the date at which the user buys the stock.
   * @param comFee      commission fee for buying/selling of stocks.
   */
  public StockImpl(String stockSymbol, float quantity, String stockDate, int comFee) {
    this.stockSymbol = stockSymbol;
    this.quantity = quantity;
    this.stockDate = stockDate;
    this.comFee = comFee;
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public float getStockQuantity() {
    return quantity;
  }

  @Override
  public void setStockQuantity(float oldStkQty) {
    this.quantity = oldStkQty;
  }

  @Override
  public String getStockDate() {
    return stockDate;
  }

  @Override
  public void setStockDate(String newDate) {
    this.stockDate = newDate;
  }

  @Override
  public int getComFee() {
    return comFee;
  }

  @Override
  public void setComFee(int newFee) {
    this.comFee = newFee;
  }

}
