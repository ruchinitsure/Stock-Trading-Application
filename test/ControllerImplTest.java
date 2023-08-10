import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;

import controller.Controller;
import controller.ControllerImpl;
import model.User;
import model.UserImpl;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for 'ControllerImpl' class.
 */

public class ControllerImplTest {

  private ByteArrayOutputStream byteStream;
  private PrintStream outStream;

  @Before
  public void setUp() {
    byteStream = new ByteArrayOutputStream();
    outStream = new PrintStream(byteStream);
  }

  @Test
  public void testCreatePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added portfolio of name: ruchi");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testCreateFlexiblePortfolio() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDisplaySinglePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added portfolio of name: ruchi");
      this.outStream.println("Number of Portfolios returned.");
      this.outStream.println("Displayed single portfolio from the portfoliolist.");
      this.outStream.println("Portfolio name returned at index 0 from the portfoliolist.");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDisplaySingleFlexiblePortfolio() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n7\nruchi\n2002-09-18\n15")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Get composition of the flexible portfolioruchi");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGetValuation() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\n2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added portfolio of name: ruchi");
      this.outStream.println("Checked if portfolio exists with name: ruchi");
      this.outStream.println("Portfolio returned with name: ruchi");
      this.outStream.println("Portfolio name: ruchiFor valuation date: 2003-03-12");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGetFlexibleValuation() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n11\nruchi\n2003-03-12\n15")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Checked if flexible portfolio exists with name: ruchi");
      this.outStream.println("Function that gets the flexible portfolio:ruchi");
      this.outStream.println("Flexible Portfolio name: ruchiFor valuation date: 2003-03-12");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUploadFile() {
    InputStream inputStream =
            new ByteArrayInputStream(("5\n/Users/akankshakulkarni/IdeaProjects/Stocks_Attempt3/" +
                    "ruchi.xml\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("XML File succesfully read from the /Users/akankshakulkarni/" +
              "IdeaProjects/Stocks_Attempt3/ruchi.xmlprovided.");
      this.outStream.println("Number of Portfolios returned.");
      this.outStream.println("Displayed single portfolio from the portfoliolist.");
      this.outStream.println("Portfolio name returned at index 0 from the portfoliolist.");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlexibleUploadFile() {
    InputStream inputStream =
            new ByteArrayInputStream(("10\n//Users/akankshakulkarni/IdeaProjects/stocks_part2" +
                    "/p2.xml" + "\n14\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Flexible XML File succesfully read from the " +
              "//Users/akankshakulkarni/IdeaProjects/stocks_part2/p2.xmlprovided.");
      this.outStream.println("Number of Flexible Portfolios returned.");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlexibleBuyStocks() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n8\nruchi\nGOOGL" +
                    "\n10\n2005-01-04\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Function that gets the flexible portfolio:ruchi");
      this.outStream.println("Stocks successfully added for symbol[GOOGL]having quantity[10.0]for" +
              " dates[2005-01-04]for the portfolio:ruchi");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlexibleSellStocks() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n9\nruchi\nAAPL\n5\n" +
                    "2005-01-04\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Function that gets the flexible portfolio:ruchi");
      this.outStream.println("Checked if AAPLexists in flexible portfolio with name: ruchi");
      this.outStream.println("Checked if5.0 is valid for Portfolio:ruchi");
      this.outStream.println("Checked if2005-01-04 is valid for Portfolio:ruchi");
      this.outStream.println("Removed from portfolio: ruchiquantity:5.0on2005-01-04for " +
              "stock symbol:AAPL");

      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  //fail
  @Test
  public void testFlexibleCostBasis1() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n12\nruchi\n" +
                    "2005-05-10\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Checked if flexible portfolio exists with name: ruchi");
      this.outStream.println("Function that gets the flexible portfolio:ruchi");
      this.outStream.println("Flexible Portfolio name: ruchi For valuation date: 2003-03-12");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGetPerformance() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n13\nruchi\n" +
                    "2003-03-12\n2003-03-15\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Checked if flexible portfolio exists with name: ruchi");
      this.outStream.println("Displayed graph for the portfolioruchi");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDisplayAllFlexible() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n6\nruchi1\n1\n" +
                    "GOOGL\n10\n2005-01-04\n14\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.outStream.println("Added flexible portfolio of name: ruchi");
      this.outStream.println("Added flexible portfolio of name: ruchi1");
      this.outStream.println("Number of Flexible Portfolios returned.");
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testExitMock() {
    InputStream inputStream =
            new ByteArrayInputStream(("15\n").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    ByteArrayOutputStream byteMock = new ByteArrayOutputStream();
    PrintStream outMock = new PrintStream(byteMock);

    User usermock = new UserMock(outMock);

    Controller controller = new ControllerImpl(usermock, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      assertEquals(byteStream.toString(), byteMock.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


  private void printHelper() {
    this.outStream.println();
    this.outStream.println("Welcome!");
    this.outStream.println("Please select one of the options below:");
    this.outStream.println("1.View all portfolios");
    this.outStream.println("2.View one portfolio");
    this.outStream.println("3.Add a portfolio");
    this.outStream.println("4.Get the total value of a portfolio for a certain date");
    this.outStream.println("5.Upload xml file of portfolio");
    this.outStream.println("6.Create a flexible portfolio");
    this.outStream.println("7.View one flexible portfolio");
    this.outStream.println("8.Buy stock into a flexible portfolio");
    this.outStream.println("9.Sell stock from a flexible portfolio");
    this.outStream.println("10.Upload xml file of flexible portfolio");
    this.outStream.println("11.Get the total value of a flexible portfolio for a certain date");
    this.outStream.println("12.View the cost basis of a flexible portfolio");
    this.outStream.println("13.View how portfolio has performed over a period of time");
    this.outStream.println("14.Display all flexible portfolios");
    this.outStream.println("15.Exit");
    /*this.outStream.println("15.Invest in portfolio");
    this.outStream.println("16.Invest in existing portfolio:");
    this.outStream.println("17.Dollar cost averaging.");
    this.outStream.println("18.Exit");

     */


  }

  @Test
  public void testViewAllEmptyPortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("2\nruchi\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Portfolio doesn't exist.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testViewOneEmptyFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("7\nruchi\n2001-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Portfolio doesn't exist.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testViewAllFlexibleEmptyPortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("14\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("You do not have any portfolios.Please add portfolios first.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testViewEmptyOnePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("You do not have any portfolios.Please add portfolios first.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testUserCreateCorrectPortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    User user = new UserImpl();
    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateCorrectFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);
    User user = new UserImpl();
    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testUserAllDisplayPortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n3\nakanksha" +
            "\n1\nGOOGL\n2\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.outStream.println("Portfolio Name : akanksha");
      this.outStream.println("----------------------------------");
      this.outStream.println("Stock = GOOGL, Quantity = 2.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserAllDisplayFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18" +
            "\n6\nakanksha" + "\n1\nGOOGL\n10\n2005-01-04\n14\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Portfolio Name : akanksha");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2005-01-04");
      this.outStream.println("Stock = GOOGL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserDisplay1Portfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }


  }

  @Test
  public void testUserDisplay1AfterCreationFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n2\nAAPL\n5\n2001-09-18 " +
            "            \nGOOGL\n10\n2005-01-04\n7\nruchi\n" +
            "2007-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2005-01-04");
      this.outStream.println("Stock = GOOGL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }


  }

  @Test
  public void testUserDisplay1BeforeCreationFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n2\nAAPL\n5\n2001-09-18 " +
            "            \nGOOGL\n10\n2005-01-04\n7\nruchi\n" +
            "2003-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }


  }

  @Test
  public void testUserDisplay1BeforeCreationDateFlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n2\nAAPL\n5\n2001-09-18 " +
            "            \nGOOGL\n10\n2005-01-04\n7\nruchi\n" +
            "2000-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }


  }


  @Test
  public void testUserOneDisplayPortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n3\nakanksha" +
            "\n1\nGOOGL\n2\n2\nruchi\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidtickerPortfolio1() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\naapl\nAAPL\n5\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Invalid ticker.");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");

      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidtickerFlexiblePortfolio1() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\naapl\nAAPL\n5\n2001-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Invalid ticker.");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");

      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidtickerPortfolio2() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nRUCHI\nGOOGL\n5\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Invalid ticker.");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidtickerFlexiblePortfolio2() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nRUCHI\nAAPL\n5\n2001-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Invalid ticker.");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");

      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidQuantity1Portfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n-1\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Quantity can't be negative.");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidQuantity1FlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n-1\n1\n2001-09-18\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Quantity can't be negative.");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidQuantity2Portfolio() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\nak\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("You can only buy stocks of integer value. Please enter correct " +
              "quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidQuantity2FlexiblePortfolio() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\nak\n1\n2001-09-18\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("You can only buy stocks of integer value. Please enter correct" +
              " quantity:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidQuantity3Portfolio() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n4.0\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidStockQuantity1() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n-1\n1\nAAPL\n4\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Stocks cannot be negative.");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidStockFlexibleQuantity1() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n-1\n1\nAAPL\n4\n2001-09-18\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Stocks cannot be negative.");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidStockQuantity2() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\naa\n1\nAAPL\n4\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("You can enter stocks of integer value only." +
              " Please enter correct quantity!");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidFlexibleStockQuantity2() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\naa\n1\nAAPL\n4\n2001-09-18\n15")
            .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("You can enter stocks of integer value only." +
              " Please enter correct quantity!");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidStockQuantity3() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n2.0\n1\nAAPL\n4\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("You can enter stocks of integer value only. " +
              "Please enter correct quantity!");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserCreateInvalidFlexibleStockQuantity3() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n2.0\n1\nAAPL\n4\n2001-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("You can enter stocks of integer value only. " +
              "Please enter correct quantity!");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidValuation() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\n2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidFlexibleValuation1() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n11\nruchi\n" +
                    "2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBeforeCreationDateFlexibleValuation1() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n11\nruchi\n2000-03-12\n15")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("AAPL doesn't have a valuation on the entered date.Hence "
              + "it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 0.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidCostBasisAfterSelling() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n9\nruchi" +
                    "\nAAPL\n5\n2003-01-04\n12\nruchi\n2005-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 362.8");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidCostBasisBeforeSelling() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n9\nruchi" +
                    "\nAAPL\n5\n2003-01-04\n12\nruchi\n2002-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 262.8");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidCostBasisForOneStock() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n2\nAAPL\n10\n2001-09-18\nGOOGL\n10" +
                    "\n2005-01-04\n12\nruchi\n2003-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 262.8");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGraphDDays() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n13\nruchi" +
                    "\n2003-01-04\n2003-01-08\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter the portfolio which you want to visualize:");
      this.outStream.println("Enter the start date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the end date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Performance of portfolio ruchi from 2003-01-04 to 2003-01-08");
      this.outStream.println();
      this.outStream.println("2003-01-04 : ");
      this.outStream.println("2003-01-05 : ");
      this.outStream.println("2003-01-06 : *****");
      this.outStream.println("2003-01-07 : ****");
      this.outStream.println("2003-01-08 : ****");
      this.outStream.println();
      this.outStream.println("Scale: * = $29.8");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGraphMonths() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n13\nruchi" +
                    "\n2003-01-04\n2003-05-08\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter the portfolio which you want to visualize:");
      this.outStream.println("Enter the start date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the end date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Performance of portfolio ruchi from 2003-01-04 to 2003-05-08");
      this.outStream.println();
      this.outStream.println("Jan-2003 : *****");
      this.outStream.println("Feb-2003 : ");
      this.outStream.println("Mar-2003 : ****");
      this.outStream.println("Apr-2003 : ****");
      this.outStream.println("May-2003 : ");
      this.outStream.println();
      this.outStream.println("Scale: * = $28.719997");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGraphYears() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n13\nruchi" +
                    "\n2003-01-04\n2008-05-08\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter the portfolio which you want to visualize:");
      this.outStream.println("Enter the start date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the end date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Performance of portfolio ruchi from 2003-01-04 to 2008-05-08");
      this.outStream.println();
      this.outStream.println("2003 : ");
      this.outStream.println("2004 : *");
      this.outStream.println("2005 : **");
      this.outStream.println("2006 : **");
      this.outStream.println("2007 : *****");
      this.outStream.println("2008 : **");
      this.outStream.println();
      this.outStream.println("Scale: * = $353.42");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void EndDateSmallerStartDateGraph() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n13\nruchi" +
                    "\n2008-05-08\n2003-01-04\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter the portfolio which you want to visualize:");
      this.outStream.println("Enter the start date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the end date:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("End date cannot be before start date!");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testBuyValidStocksForFlexible() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18 " +
            "            8\nruchi\nGOOGL\n10\n2005-01-04\n7\nruchi\n" +
            "2009-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2005-01-04");
      this.outStream.println("Stock = GOOGL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSellValidStocksOnValidDateFlexible() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18 " +
            "            9\nruchi\nAAPL\n5\n2007-01-04\n7\nruchi\n" +
            "2009-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSellValidStocksInvalidQtyFlexible() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18 " +
            "            9\nruchi\nAAPL\n15\n2007-01-04\n7\nruchi\n" +
            "2009-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("You can't sell stocks greater than what you bought.");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSellValidStocksDateBeforeCreationFlexible() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18 " +
            "            9\nruchi\nAAPL\n5\n2000-01-04\n7\nruchi\n" +
            "2009-09-18\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("You can't sell a stock before it is purchased.");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSellInvalidStocksFlexible() {
    InputStream inputStream = new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n " +
            "9\nruchi\nGOOGL\nAAPL\n5\n2009-01-04\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Flexible Portfolio name :");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Stock you're trying to sell doesn't exist in your portfolio.");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 10.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidNameValuation1() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nrandom\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Portfolio does not exist!");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidNameFlexibleValuation1() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-09-18\n11\nrandom\n15")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Portfolio does not exist!");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidDateValuation() {
    InputStream inputStream =
            new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\naa\n" +
                    "2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Entered date is invalid and contains letter. Enter a valid date!");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");

      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidDateFlexibleValuation() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n5\n2001-01-18\n11\nruchi\naa\n" +
                    "2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Entered date is invalid and contains letter. Enter a valid date!");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("Buying Date : 2001-01-18");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");

      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void InvalidMonthValuation() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\n" +
            "ruchi\n2003-16-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Invalid month");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("AAPL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 0.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void InvalidFormatValuation1() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\n" +
            "03-03-2000\n2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Format of date is invalid. Please enter a valid date! ");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void InvalidFormatValuation2() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\n" +
            "12-21-2003\n2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);


    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Format of date is invalid. Please enter a valid date! ");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidFutureDateValuation() {
    InputStream inputStream = new ByteArrayInputStream(("3\nruchi\n1\nAAPL\n5\n4\nruchi\n" +
            "2025-10-12\n2003-03-12\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.printHelper();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("The date entered is invalid. Please enter a valid date: ");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println();
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.outStream.println("Value of the stock: 71.1");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 71.1");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserUploadFile() {
    InputStream inputStream = new ByteArrayInputStream(("5\n/Users/akankshakulkarni/" +
            "IdeaProjects/Stocks_Attempt3/ruchi.xml\n1\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter the file path of the xml file");
      this.outStream.println("Portfolio successfully uploaded!");
      this.printHelper();
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("----------------------------------");
      this.outStream.println("Stock = AAPL, Quantity = 5.0");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUserFlexibleUploadFile() {
    InputStream inputStream = new ByteArrayInputStream(("10\n/Users/akankshakulkarni/" +
            "IdeaProjects/stocks_part2/p2.xml\n14\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Enter the file path of the flexible xml file");
      this.outStream.println("Flexible Portfolio successfully uploaded!");
      this.printHelper();
      this.outStream.println();
      this.outStream.println("Portfolio Name : p2");
      this.outStream.println("----------------------------------");
      this.outStream.println("Buying Date : 2005-09-18");
      this.outStream.println("Stock = TSLA, Quantity = 50.0");
      this.outStream.println();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testExit() {
    InputStream inputStream = new ByteArrayInputStream(("15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDefault() {
    InputStream inputStream = new ByteArrayInputStream(("17\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Invalid input. Please try again.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDefault2() {
    InputStream inputStream = new ByteArrayInputStream(("aaa\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Invalid input. Please try again.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDefault0() {
    InputStream inputStream = new ByteArrayInputStream(("0\n15").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);

    try {
      controller.goController();
      this.printHelper();
      this.outStream.println("Invalid input. Please try again.");
      this.printHelper();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


  private void printHelper1() {
    this.outStream.println();
    this.outStream.println("Welcome!");
    this.outStream.println("Please select one of the options below:");
    this.outStream.println("1.View all portfolios");
    this.outStream.println("2.View one portfolio");
    this.outStream.println("3.Add a portfolio");
    this.outStream.println("4.Get the total value of a portfolio for a certain date");
    this.outStream.println("5.Upload xml file of portfolio");
    this.outStream.println("6.Create a flexible portfolio");
    this.outStream.println("7.View one flexible portfolio");
    this.outStream.println("8.Buy stock into a flexible portfolio");
    this.outStream.println("9.Sell stock from a flexible portfolio");
    this.outStream.println("10.Upload xml file of flexible portfolio");
    this.outStream.println("11.Get the total value of a flexible portfolio for a certain date");
    this.outStream.println("12.View the cost basis of a flexible portfolio");
    this.outStream.println("13.View how portfolio has performed over a period of time");
    this.outStream.println("14.Display all flexible portfolios");
    //this.outStream.println("15.Exit");
    this.outStream.println("15.Invest in portfolio");
    this.outStream.println("16.Invest in existing portfolio");
    this.outStream.println("17.Dollar cost averaging.");
    this.outStream.println("18.Exit");


  }

  @Test
  public void testValidCostBasisForDollarCost() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n100\n17\nruchi\n2000" +
                    "\n1\n100\n2003-09-18\n2004-01-03\n50\nGOOGL\n100\n12\nruchi\n2005-01-04\n18")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter commission fees");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the amount you want to invest:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter commission fees");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the time span:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the percentage:");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 400.0");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValidCostBasisForDollarCost2Dates() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n100\n17\nruchi\n2000" +
                    "\n1\n100\n2003-09-18\n2004-01-03\n50\nGOOGL\n100\n12\nruchi\n2005-01-04\n12\n"
                    + "ruchi\n2003-09-28\n18").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter commission fees");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the amount you want to invest:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter commission fees");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the time span:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the percentage:");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 400.0");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println();
      this.outStream.println("Cost Basis of the portfolio : 400.0");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testValuationDollarCost() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n100\n17\nruchi\n2000" +
                    "\n1\n100\n2009-09-18\n2012-01-03\n50\nGOOGL\n100\n11\nruchi\n2013-01-04\n11\n"
                    + "ruchi\n2010-01-04\n18").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter commission fees");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the amount you want to invest:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter commission fees");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the time span:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the percentage:");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 10.0");
      this.outStream.println("Value of the stock: 5270.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-07-15");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2600105");
      this.outStream.println("Value of the stock: 191.87994");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-05-26");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25024736");
      this.outStream.println("Value of the stock: 184.67503");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-03-22");
      this.outStream.println("Stock = GOOGL, Quantity = 0.30385265");
      this.outStream.println("Value of the stock: 224.23413");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-01-31");
      this.outStream.println("Stock = GOOGL, Quantity = 0.31597894");
      this.outStream.println("Value of the stock: 233.18297");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-05-11");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2818158");
      this.outStream.println("Value of the stock: 207.97159");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-04-06");
      this.outStream.println("Stock = GOOGL, Quantity = 0.29906315");
      this.outStream.println("Value of the stock: 220.69962");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-02-15");
      this.outStream.println("Stock = GOOGL, Quantity = 0.28489473");
      this.outStream.println("Value of the stock: 210.24376");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-11-27");
      this.outStream.println("Stock = GOOGL, Quantity = 0.30957368");
      this.outStream.println("Value of the stock: 228.45609");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-06-30");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2665158");
      this.outStream.println("Value of the stock: 196.68065");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-09-03");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2475263");
      this.outStream.println("Value of the stock: 182.66698");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-10-08");
      this.outStream.println("Stock = GOOGL, Quantity = 0.28272104");
      this.outStream.println("Value of the stock: 208.63963");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-11-07");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2960579");
      this.outStream.println("Value of the stock: 218.48184");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-09-18");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25866315");
      this.outStream.println("Value of the stock: 190.88564");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-12-27");
      this.outStream.println("Stock = GOOGL, Quantity = 0.32782632");
      this.outStream.println("Value of the stock: 241.92598");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-12-12");
      this.outStream.println("Stock = GOOGL, Quantity = 0.31295788");
      this.outStream.println("Value of the stock: 230.95352");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-10-23");
      this.outStream.println("Stock = GOOGL, Quantity = 0.32447368");
      this.outStream.println("Value of the stock: 239.45183");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-08-19");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25837895");
      this.outStream.println("Value of the stock: 190.6759");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 8871.705");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println();
      this.outStream.println("Portfolio Name : ruchi");
      this.outStream.println("Buying Date : 2001-09-18");
      this.outStream.println("Stock = AAPL, Quantity = 10.0");
      this.outStream.println("Value of the stock: 2140.0999");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-07-15");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2600105");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-05-26");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25024736");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-03-22");
      this.outStream.println("Stock = GOOGL, Quantity = 0.30385265");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-01-31");
      this.outStream.println("Stock = GOOGL, Quantity = 0.31597894");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-05-11");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2818158");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-04-06");
      this.outStream.println("Stock = GOOGL, Quantity = 0.29906315");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-02-15");
      this.outStream.println("Stock = GOOGL, Quantity = 0.28489473");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-11-27");
      this.outStream.println("Stock = GOOGL, Quantity = 0.30957368");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-06-30");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2665158");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-09-03");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2475263");
      this.outStream.println("Value of the stock: 155.13712");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-10-08");
      this.outStream.println("Stock = GOOGL, Quantity = 0.28272104");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-11-07");
      this.outStream.println("Stock = GOOGL, Quantity = 0.2960579");
      this.outStream.println("Value of the stock: 185.55429");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-09-18");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25866315");
      this.outStream.println("Value of the stock: 162.11713");
      this.outStream.println();
      this.outStream.println("Buying Date : 2009-12-27");
      this.outStream.println("Stock = GOOGL, Quantity = 0.32782632");
      this.outStream.println("Value of the stock: 205.46515");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-12-12");
      this.outStream.println("Stock = GOOGL, Quantity = 0.31295788");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2010-10-23");
      this.outStream.println("Stock = GOOGL, Quantity = 0.32447368");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println("Buying Date : 2011-08-19");
      this.outStream.println("Stock = GOOGL, Quantity = 0.25837895");
      this.outStream.println("GOOGL doesn't have a valuation on the entered date." +
              "Hence it's valuation is 0.0");
      this.outStream.println("Value of the stock: 0.0");
      this.outStream.println();
      this.outStream.println();
      this.outStream.println("Total Valuation : 2848.3735");
      this.printHelper1();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void negativeCommission() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n-100\n1001\n18")
                    .getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter commission fees");
      this.outStream.println("Commission fees cannot be negative");
      this.outStream.println("Enter commission fees");
      this.printHelper1();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void percentageInvestment() {
    InputStream inputStream =
            new ByteArrayInputStream(("6\nruchi\n1\nAAPL\n10\n2001-09-18\n100\n17\nruchi\n2000" +
                    "\n1\n50\n2003-09-18\n2004-01-03\n50\nGOOGL\n200\n18").getBytes());
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(byteOut);

    User user = new UserImpl();

    Controller controller = new ControllerImpl(user, new ViewImpl(), inputStream, out);
    try {
      controller.goController();
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the stock quantity:");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter commission fees");
      this.printHelper1();
      this.outStream.println("Enter Portfolio name:");
      this.outStream.println("Enter the amount you want to invest:");
      this.outStream.println("Enter number of stocks you want to buy:");
      this.outStream.println("Enter commission fees");
      this.outStream.println("Enter the date in yyyy-mm-dd format:");
      this.outStream.println("Enter the time span:");
      this.outStream.println("Enter Stock symbol:");
      this.outStream.println("Enter the percentage:");
      this.outStream.println("Sum of percentages is greater than 100. Please try again");
      //this.outStream.println();
      this.printHelper1();
      this.outStream.println("Successfully exited.");
      assertEquals(byteStream.toString(), byteOut.toString());
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
  }


}








