import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import model.AlphaVantage;
import model.AlphaVantageImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'AlphaVantageImpl' class.
 */
public class AlphaVantageImplTest {
  String apiKey = "MTWVU7GBVGXVXNPB";
  AlphaVantage api = new AlphaVantageImpl();

  @Test
  public void testGetURL1() {
    String stockSymbol = "AAPL";
    assertEquals("https://www.alphavantage"
                    + ".co/query?function=TIME_SERIES_DAILY"
                    + "&outputsize=full"
                    + "&symbol"
                    + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv",
            api.getUrl(stockSymbol).toString());
  }

  @Test
  public void testGetURL2() {
    String stockSymbol = "aappl";
    assertEquals("https://www.alphavantage"
                    + ".co/query?function=TIME_SERIES_DAILY"
                    + "&outputsize=full"
                    + "&symbol"
                    + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv",
            api.getUrl(stockSymbol).toString());
  }

  @Test
  public void testGetClosingValue() throws MalformedURLException {
    URL url = new URL("https://www.alphavantage.co/query?function=" +
            "TIME_SERIES_DAILY&outputsize=full&symbol=GOOGL&apikey=WYE3KK5KVZK34RXFX&datatype=csv");
    assertEquals("100.3350", api.getClosingValue(url, "2004-08-19"));
  }

  @Test
  public void testGetClosingValue2() throws MalformedURLException {
    URL url = new URL("https://www.alphavantage.co/query?function=" +
            "TIME_SERIES_DAILY&outputsize=full&symbol=GOOGL&apikey=WYE3KK5KVZK34RXFX&datatype=csv");
    assertEquals("0", api.getClosingValue(url, "2000-05-03"));
  }


}