package model;

/**
 * An interface that defines all the operations that can be performed on stocks.
 */
public interface Stock {
  /**
   * Method that returns the stock symbol of the stock.
   *
   * @return the stock symbol of stock.
   */
  String getStockSymbol();

  /**
   * Method that returns the stock quantity of stock that user wants to buy.
   *
   * @return the stock quantity.
   */
  float getStockQuantity();

  /**
   * Method that sets the stock quantity of the stock.
   *
   * @param oldStkQty the earlier stock quantity.
   */
  void setStockQuantity(float oldStkQty);

  /**
   * Method that returns the date at which the user bought the stock.
   *
   * @return the buying date of stock.
   */
  String getStockDate();

  /**
   * Method that sets the date of the stock.
   *
   * @param newDate date to set instead of the earlier one.
   */

  void setStockDate(String newDate);

  /**
   * Method that returns the commission fee of stock that user wants to buy.
   *
   * @return the commission fee.
   */

  int getComFee();

  /**
   * Method that sets the commission fee of the stock.
   *
   * @param newFee new fee to set instead of the earlier one.
   */

  void setComFee(int newFee);
}
