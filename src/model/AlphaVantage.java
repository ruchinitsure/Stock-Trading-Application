package model;

import java.net.URL;

/**
 * An interface which acts as an API to fetch stock prices.
 */
public interface AlphaVantage {

  /**
   * Method which creates an URL for a particular ticker(stocksymbol).
   *
   * @param stockSymbol the stock symbol for which url needs to be generated.
   * @return the url
   */
  URL getUrl(String stockSymbol);

  /**
   * Method that fetches the closing price of the stock for a certain date.
   *
   * @param url  the url for a particular stock.
   * @param date the date at which valuation needs to be done.
   * @return the closing value of the stock.
   */
  String getClosingValue(URL url, String date);

}
