package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

/**
 * A class that implements 'AlphaVantage' interface and finds stock prices for a certain date.
 */
public class AlphaVantageImpl implements AlphaVantage {

  InputStream in = null;
  private String apiKey = "MTWVU7GBVGXVXNPB";
  private String stockSymbol;

  @Override
  public URL getUrl(String stockSymbol) {
    URL url;
    try {

      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
      return url;
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
  }

  @Override
  public String getClosingValue(URL url, String date) {
    StringBuilder output = new StringBuilder();
    LocalDate start = LocalDate.parse(date);
    LocalDate end;
    start = start.plusDays(1);
    end = start.plusDays(1);
    String closingValue;
    int index = -1;
    boolean flag = false;
    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
      index = output.indexOf(date);
      if (index == -1) {
        index = output.indexOf(start.toString());
        if (index == -1) {
          index = output.indexOf(end.toString());
        }
        if (index == -1) {
          index = output.indexOf(end.toString());
        }
        if (index == -1) {
          return "0";
        }
      }

      int endIndex = output.indexOf("\n", index);
      String tempString = output.substring(index, endIndex);
      String[] result = tempString.split(",", 0);
      closingValue = result[4];
      return closingValue;

    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

  }

}

