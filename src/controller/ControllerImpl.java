package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import model.User;
import view.View;

/**
 * Class which implements 'Controller' class.
 * It controls the actual flow of the program.
 * Controls the data flow into the models and updates the view.
 */
public class ControllerImpl implements Controller {

  private final InputStream inStream;
  String portfolioName;
  String stockSymbol;
  String valuationDate = "";
  String filePath;
  Map<String, Float> map = new HashMap<>();
  Map<String, Map<String, Float>> newMap = new HashMap<>();
  String option;
  private User user;
  private View view;

  /**
   * Constructor which initialises objects needed in the class.
   *
   * @param user the object of 'User' class
   * @param view the object of 'View' class
   */
  public ControllerImpl(User user, View view, InputStream in, PrintStream out) {
    PrintStream outStream;
    this.user = user;
    this.view = view;
    this.inStream = in;
    outStream = out;
    this.view.setOutStream(outStream);
  }

  private static boolean isWeekend(final LocalDate ld) {
    DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }

  @Override
  public void goController() throws ParseException, IOException {

    String option;

    Scanner sc = new Scanner(inStream);
    boolean flag = true;
    while (flag) {
      view.displayMenu();
      option = sc.next();
      switch (option) {

        case "1":
          this.viewAllPortfolios();
          break;

        case "2":
          this.viewOnePortfolio(sc);
          break;

        case "3":
          this.addPortfolio(sc);
          break;

        case "4":

          this.valuationInflexible(sc);
          break;

        case "5":
          this.uploadInflexiblePort(sc);
          break;

        case "6":
          this.createFlexible(sc);
          break;

        case "7":
          this.viewOneFlexiblePortfolio(sc);
          break;

        case "8":
          this.buyStockFlexiblePort(sc);
          break;

        case "9":
          this.sellStockFlexiblePort(sc);
          break;

        case "10":
          this.uploadFlexiblePort(sc);
          break;

        case "11":
          this.valuationFlexiblePort(sc);
          break;

        case "12":
          this.costBasisFlexiblePort(sc);
          break;

        case "13":
          this.portfolioPerformance(sc);
          break;

        case "14":
          this.displayAllFlexiblePortfolios(sc);
          break;
        case "15":
          this.investPortfolio(sc);
          break;

        case "16":
          this.investExistingPortfolio(sc);
          break;

        case "17":
          this.dollarCost(sc);
          break;

        case "18":
          view.showOutput("Successfully exited.");
          flag = false;
          break;


        default:
          view.showOutput("Invalid input. Please try again.");

      }
    }

  }

  private void viewAllPortfolios() {
    int flag = 0;
    int noOfPortfolios = user.getNumberOfPortfolio();
    if (noOfPortfolios == 0) {
      view.showOutput("You do not have any portfolios.Please add portfolios first.");
      flag = 1;
    }
    if (flag == 0) {
      for (int i = 0; i < noOfPortfolios; i++) {
        map = user.displaySinglePort(i);
        portfolioName = user.getPortfolioName(i);
        view.displaySinglePortfolio(map, portfolioName);
      }
    }
  }

  private void viewOnePortfolio(Scanner sc) {
    int flag = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();
    map = user.getPortfolio(portfolioName);
    if (map == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      view.displaySinglePortfolio(map, portfolioName);
    }
  }

  private void addPortfolio(Scanner sc) throws IOException {
    Integer totalStocks = null;
    Float quantity = 0f;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();
    do {
      try {
        view.showOutput("Enter number of stocks you want to buy");
        String s = sc.next();
        totalStocks = Integer.parseInt(s);
        if (totalStocks < 0) {
          view.showOutput("Stocks cannot be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter stocks of integer value only. Please enter "
                + "correct quantity!");
      }
    }
    while (totalStocks == null || totalStocks < 0);

    ArrayList<String> stockSymbols = new ArrayList<>();
    ArrayList<Float> stockQuantity = new ArrayList<>();

    String[] tickerSymbol = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                             "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                             "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                             "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH",
                             "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                             "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                             "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                             "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                             "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                             "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                             "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                             "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                             "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                             "NDX"};
    boolean flag1 = false;
    for (int i = 0; i < totalStocks; i++) {
      do {
        view.showOutput("Enter Stock symbol:");
        stockSymbol = sc.next();
        flag1 = false;
        for (int k = 0; k < tickerSymbol.length; k++) {
          if (stockSymbol.contains(tickerSymbol[k])) {
            flag1 = true;
            break;
          }
        }
        if (!flag1) {
          view.showOutput("Invalid ticker.");
        }
      }
      while (!flag1);

      do {
        try {

          view.showOutput("Enter the stock quantity:");
          String s = sc.next();
          quantity = Float.parseFloat(s);
          if (quantity < 0) {
            view.showOutput("Quantity can't be negative.");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }
      }
      while (quantity == null || quantity < 0);

      if (stockSymbols.contains(stockSymbol)) {
        quantity += stockQuantity.get(stockSymbols.indexOf(stockSymbol));
      }
      stockSymbols.add(stockSymbol);
      stockQuantity.add(quantity);
    }
    user.addPortfolio(stockSymbols, stockQuantity, portfolioName);
  }

  private void valuationInflexible(Scanner sc) {
    int flag = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    boolean ifExists = user.checkPortfolioExists(portfolioName);
    if (!ifExists) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      String valuationDate = dateValidation(sc);

      try {
        map = user.getPortfolio(portfolioName);

        if (map == null) {
          view.showOutput("You do not have any portfolios.Please add portfolios first.");
          //break;
        } else {
          List<Float> values = user.getValuationForPort(portfolioName, valuationDate);
          view.displaySinglePortValuation(map, values, portfolioName);
        }
      } catch (IllegalArgumentException e) {
        view.showOutput("Date does not exist.");
      }

    }
  }

  private void uploadInflexiblePort(Scanner sc) {
    view.showOutput("Enter the file path of the xml file");
    filePath = sc.next();
    user.readXMLFile(filePath);
    view.showOutput("Portfolio successfully uploaded!");
  }

  private void createFlexible(Scanner sc) throws IOException {
    Integer noOfStocks = null;
    Float stockQty = null;
    Integer comFees = null;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();


    do {
      try {
        view.showOutput("Enter number of stocks you want to buy:");
        String s = sc.next();
        noOfStocks = Integer.parseInt(s);
        if (noOfStocks < 0) {
          view.showOutput("Stocks cannot be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter stocks of integer value only. Please enter "
                + "correct quantity!");
      }
    }
    while (noOfStocks == null || noOfStocks < 0);

    ArrayList<String> stkSymbols = new ArrayList<>();
    ArrayList<Float> stkQuantity = new ArrayList<>();
    ArrayList<String> stkDates = new ArrayList<>();
    ArrayList<Integer> stkCom = new ArrayList<>();

    boolean flag3 = false;
    String[] tickers = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                        "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                        "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                        "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH",
                        "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                        "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                        "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                        "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                        "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                        "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                        "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                        "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                        "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                        "NDX"};

    for (int i = 0; i < noOfStocks; i++) {

      do {
        view.showOutput("Enter Stock symbol:");
        stockSymbol = sc.next();
        flag3 = false;
        for (int k = 0; k < tickers.length; k++) {
          if (stockSymbol.contains(tickers[k])) {
            flag3 = true;
            break;
          }
        }
        if (!flag3) {
          view.showOutput("Invalid ticker.");
        }
      }
      while (!flag3);

      do {
        try {

          view.showOutput("Enter the stock quantity:");
          String s = sc.next();
          stockQty = Float.parseFloat(s);
          if (stockQty < 0) {
            view.showOutput("Quantity can't be negative.");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }
      }
      while (stockQty == null || stockQty < 0);


      String buyingDate = dateValidation(sc);

      do {
        try {
          view.showOutput("Enter commission fees");
          String com = sc.next();
          comFees = Integer.parseInt(com);
          if (comFees < 0) {
            view.showOutput("Commission fees cannot be negative");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter commission of integer value only. Please enter "
                  + "correct quantity!");
        }
      }
      while (comFees == null || comFees < 0);

      stkSymbols.add(stockSymbol);
      stkQuantity.add(stockQty);
      stkDates.add(buyingDate);
      stkCom.add(comFees);

    }

    user.addFlexiblePortfolio(stkSymbols, stkQuantity, stkDates, stkCom, portfolioName);

  }

  private void viewOneFlexiblePortfolio(Scanner sc) throws ParseException {
    int flag = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();
    String inputDate = dateValidation(sc);
    newMap = user.getComposition(portfolioName, inputDate);
    if (newMap == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      view.displaySingleFlexiblePortfolio(newMap, portfolioName);
    }

  }

  private void buyStockFlexiblePort(Scanner sc) throws IOException {
    int flag = 0;
    Float stkQty = null;
    Integer comFees = null;
    view.showOutput("Enter Flexible Portfolio name :");
    portfolioName = sc.next();
    newMap = user.getFlexiblePortfolio(portfolioName);
    if (newMap == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      ArrayList<String> stkSymbols1 = new ArrayList<>();
      ArrayList<Float> stkQuantity1 = new ArrayList<>();
      ArrayList<String> stkDates1 = new ArrayList<>();
      ArrayList<Integer> stkCom1 = new ArrayList<>();

      boolean flag4 = false;
      String[] validTicker = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                              "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                              "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                              "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH",
                              "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                              "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                              "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                              "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                              "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                              "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                              "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                              "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                              "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                              "NDX"};


      do {
        view.showOutput("Enter Stock symbol:");
        stockSymbol = sc.next();
        flag4 = false;
        for (int k = 0; k < validTicker.length; k++) {
          if (stockSymbol.contains(validTicker[k])) {
            flag4 = true;
            break;
          }
        }
        if (!flag4) {
          view.showOutput("Invalid ticker.");
        }
      }
      while (!flag4);

      do {
        try {

          view.showOutput("Enter the stock quantity:");
          String s = sc.next();
          stkQty = Float.parseFloat(s);
          if (stkQty < 0) {
            view.showOutput("Quantity can't be negative.");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }
      }
      while (stkQty == null || stkQty < 0);
      do {
        try {
          view.showOutput("Enter commission fees");
          String com = sc.next();
          comFees = Integer.parseInt(com);
          if (comFees < 0) {
            view.showOutput("Commission fees cannot be negative");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter commission of integer value only. Please enter "
                  + "correct quantity!");
        }
      }
      while (comFees == null || comFees < 0);


      String buyingDate = dateValidation(sc);
      stkSymbols1.add(stockSymbol);
      stkQuantity1.add(stkQty);
      stkDates1.add(buyingDate);
      stkCom1.add(comFees);

      user.addToFlexiblePortfolio(stkSymbols1, stkQuantity1, stkDates1, stkCom1, portfolioName);
    }
  }

  private void investExistingPortfolio(Scanner sc) {
    Map<Integer, Map<String, Map<String, Float>>> newMap1 = new HashMap<>();
    Integer noOfStocks = null;
    Float stockQty = null;
    Float qty1 = 0f;
    Integer comFees = null;
    int flag = 0;
    float amt = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    view.showOutput("Enter the amount you want to invest:");
    String amt1 = sc.next();
    amt = Float.parseFloat(amt1);

    newMap1 = user.getFlexibleCostBasis(portfolioName);
    if (newMap1 == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    do {
      try {
        view.showOutput("Enter commission fees");
        String com = sc.next();
        comFees = Integer.parseInt(com);
        if (comFees < 0) {
          view.showOutput("Commission fees cannot be negative");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter commission of integer value only. Please enter "
                + "correct quantity!");
      }
    }
    while (comFees == null || comFees < 0);
    String buyingDate = dateValidation(sc);
    float newAmt = amt - comFees;
    ArrayList<Float> stkQuantity = new ArrayList<>();
    int numberOfStocks = user.getStockSize(portfolioName);
    List<String> symbols = user.getSymbol(portfolioName);
    float qty2 = 0f;
    int flag2 = 0;
    for (int i = 0; i < numberOfStocks; i++) {
      do {
        try {

          view.showOutput("Enter the percentage for " + symbols.get(i) + ":");
          String s = sc.next();
          qty1 = Float.parseFloat(s);
          qty2 += qty1;
          if (qty1 < 0) {
            view.showOutput("Quantity can't be negative.");
          }
          if (qty2 > 100) {
            flag2 = 1;
          }
          stkQuantity.add(qty1);
        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }
      }
      while (qty1 == null || qty1 < 0);
    }

    user.investInPortfolio(portfolioName, newAmt, comFees, buyingDate, stkQuantity);

  }

  private void investPortfolio(Scanner sc) throws IOException {

    Map<Integer, Map<String, Map<String, Float>>> newMap1 = new HashMap<>();
    Integer noOfStocks = null;
    Float stockQty = null;
    float newAmt = 0;
    Float qty1 = 0f;
    Integer comFees = null;
    int flag = 0;
    float amt = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    view.showOutput("Enter the amount you want to invest:");
    String amt1 = sc.next();
    amt = Float.parseFloat(amt1);

    newMap1 = user.getFlexibleCostBasis(portfolioName);
    if (newMap1 == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      do {
        try {
          view.showOutput("Enter number of stocks you want to buy:");
          String s = sc.next();
          noOfStocks = Integer.parseInt(s);
          if (noOfStocks < 0) {
            view.showOutput("Stocks cannot be negative.");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter stocks of integer value only. Please enter "
                  + "correct quantity!");
        }
      }
      while (noOfStocks == null || noOfStocks < 0);
      do {
        try {
          view.showOutput("Enter commission fees");
          String com = sc.next();
          comFees = (Integer.parseInt(com));
          if (comFees < 0) {
            view.showOutput("Commission fees cannot be negative");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter commission of integer value only. Please enter "
                  + "correct quantity!");
        }
      }

      while (comFees == null || comFees < 0);
      String investmentDate = "";
      String investmentDate1 = dateValidation(sc);
      LocalDate start = LocalDate.parse(investmentDate1);
      if (!isWeekend(start)) {
        investmentDate = (investmentDate1);
      } else {
        start = start.plusDays(2);
        investmentDate = (start.toString());

      }


      newAmt = amt - comFees;


      ArrayList<String> stkSymbols = new ArrayList<>();
      ArrayList<Float> stkQuantity = new ArrayList<>();
      ArrayList<String> stkDates = new ArrayList<>();
      ArrayList<Integer> stkCom = new ArrayList<>();
      ArrayList<Float> stockPercentage = new ArrayList<>();

      boolean flag3 = false;
      String[] tickers = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                          "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                          "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                          "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH",
                          "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                          "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                          "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                          "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                          "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                          "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                          "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                          "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                          "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                          "NDX"};

      for (int i = 0; i < noOfStocks; i++) {


        do {
          view.showOutput("Enter Stock symbol:");
          stockSymbol = sc.next();
          flag3 = false;
          for (int k = 0; k < tickers.length; k++) {
            if (stockSymbol.contains(tickers[k])) {
              flag3 = true;
              break;
            }
          }
          if (!flag3) {
            view.showOutput("Invalid ticker.");
          }
        }
        while (!flag3);


        do {
          try {

            view.showOutput("Enter the percentage:");
            String s = sc.next();
            qty1 = Float.parseFloat(s);
            if (qty1 < 0) {
              view.showOutput("Quantity can't be negative.");
            }
            stockPercentage.add(qty1);
          } catch (NumberFormatException ex) {
            view.showOutput("You can only buy stocks of integer value. Please enter "
                    + "correct quantity:");
          }
        }
        while (qty1 == null || qty1 < 0);


        stkSymbols.add(stockSymbol);
        stkCom.add(comFees / noOfStocks);


      }
      float sum = 0;
      for (int i = 0; i < stockPercentage.size(); i++) {
        sum += stockPercentage.get(i);
      }
      if (sum != 100.0f) {
        view.showOutput("Sum of percentages is greater than 100. Please try again");
      }


      this.percentageAddToPortfolio(stkSymbols, stockPercentage, newAmt, investmentDate, stkCom);
    }
  }

  private void dollarCost(Scanner sc) throws IOException {

    Map<Integer, Map<String, Map<String, Float>>> newMap1 = new HashMap<>();
    Integer noOfStocks = null;
    Float stockQty = null;
    float newAmt = 0;
    Float qty1 = 0f;
    Integer comFees = null;
    int flag = 0;
    float amt = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    view.showOutput("Enter the amount you want to invest:");
    String amt1 = sc.next();
    amt = Float.parseFloat(amt1);

    newMap1 = user.getFlexibleCostBasis(portfolioName);
    if (newMap1 == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      do {
        try {
          view.showOutput("Enter number of stocks you want to buy:");
          String s = sc.next();
          noOfStocks = Integer.parseInt(s);
          if (noOfStocks < 0) {
            view.showOutput("Stocks cannot be negative.");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter stocks of integer value only. Please enter "
                  + "correct quantity!");
        }
      }
      while (noOfStocks == null || noOfStocks < 0);
      do {
        try {
          view.showOutput("Enter commission fees");
          String com = sc.next();
          comFees = (Integer.parseInt(com));
          if (comFees < 0) {
            view.showOutput("Commission fees cannot be negative");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter commission of integer value only. Please enter "
                  + "correct quantity!");
        }
      }
      while (comFees == null || comFees < 0);

      newAmt = amt - comFees;


      String startDate = "";
      String startDate1 = dateValidation(sc);
      LocalDate start1 = LocalDate.parse(startDate1);
      if (!isWeekend(start1)) {
        startDate = (startDate1);
      } else {
        start1 = start1.plusDays(2);
        startDate = (start1.toString());

      }

      String endDate = sc.next();

      if (endDate.equals("null")) {
        endDate = ("2024-01-09");
      }

      LocalDate start = LocalDate.parse(startDate);
      LocalDate end = LocalDate.parse(endDate);

      view.showOutput("Enter the time span:");
      String time = sc.next();
      int time1 = Integer.parseInt(time);


      ArrayList<String> stkSymbols = new ArrayList<>();
      ArrayList<Float> stkQuantity = new ArrayList<>();
      ArrayList<String> stkDates = new ArrayList<>();
      ArrayList<Integer> stkCom = new ArrayList<>();
      ArrayList<Float> stockPercentage = new ArrayList<>();

      boolean flag3 = false;
      String[] tickers = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                          "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                          "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                          "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH",
                          "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                          "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                          "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                          "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                          "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                          "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                          "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                          "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                          "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                          "NDX"};

      for (int i = 0; i < noOfStocks; i++) {


        do {
          view.showOutput("Enter Stock symbol:");
          stockSymbol = sc.next();
          flag3 = false;
          for (int k = 0; k < tickers.length; k++) {
            if (stockSymbol.contains(tickers[k])) {
              flag3 = true;
              break;
            }
          }
          if (!flag3) {
            view.showOutput("Invalid ticker.");
          }
        }
        while (!flag3);


        do {
          try {

            view.showOutput("Enter the percentage:");
            String s = sc.next();
            qty1 = Float.parseFloat(s);
            if (qty1 < 0) {
              view.showOutput("Quantity can't be negative.");
            }
            stockPercentage.add(qty1);
          } catch (NumberFormatException ex) {
            view.showOutput("You can only buy stocks of integer value. Please enter "
                    + "correct quantity:");
          }
        }
        while (qty1 == null || qty1 < 0);


        stkSymbols.add(stockSymbol);
        stkCom.add(comFees / noOfStocks);


      }
      float sum = 0;
      for (int i = 0; i < stockPercentage.size(); i++) {
        sum += stockPercentage.get(i);
      }
      if (sum != 100.0f) {
        view.showOutput("Sum of percentages is greater than 100. Please try again");
      }


      for (LocalDate loopDate = start; loopDate.isBefore(end);
           loopDate = loopDate.plusDays(time1)) {

        this.percentageAddToPortfolio(stkSymbols, stockPercentage, newAmt, loopDate.toString()
                , stkCom);
      }
    }
  }

  private void percentageAddToPortfolio(ArrayList<String> stockNames
          , ArrayList<Float> stckPercentage, float newAmt, String stckDate
          , ArrayList<Integer> commFees) throws IOException {
    ArrayList<Float> stkqty = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    float stockQty = 0;
    for (int i = 0; i < stckPercentage.size(); i++) {
      float trial = ((stckPercentage.get(i) / 100) * newAmt);
      float temp1 = user.getValuationForSingleSymbol(stockNames.get(i), stckDate);
      stockQty = temp1 / trial;
      stkqty.add(stockQty);
      dates.add(stckDate);
    }
    user.addToFlexiblePortfolio1(stockNames, stkqty, dates, commFees, portfolioName);
  }

  private void sellStockFlexiblePort(Scanner sc) throws ParseException, IOException {
    int flag = 0;
    Float qty = null;
    Integer comFees = null;
    view.showOutput("Enter Flexible Portfolio name :");
    portfolioName = sc.next();
    newMap = user.getFlexiblePortfolio(portfolioName);
    if (newMap == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      ArrayList<String> stkSymbols2 = new ArrayList<>();
      ArrayList<Float> stkQuantity2 = new ArrayList<>();
      ArrayList<String> stkDates2 = new ArrayList<>();

      boolean flag5 = false;
      String[] validTickerSymbols = {"AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN",
                                     "AMAT", "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB",
                                     "BMRN", "CA", "CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST",
                                     "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK","DISH",
                                     "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV",
                                     "FOX", "FOXA", "GILD", "GOOGL", "HSIC", "ILMN", "INCY", "INTC",
                                     "INTU", "ISRG", "JD", "KHC", "LBTYA", "LBTYK", "LLTC", "LRCX",
                                     "LVNTA", "MAR", "MAT", "MCHP", "MDLZ", "MNST", "MSFT", "MU",
                                     "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES", "NVDA", "NXPI",
                                     "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN",
                                     "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC",
                                     "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD",
                                     "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY",
                                     "NDX"};

      boolean stockExists = false;
      do {
        view.showOutput("Enter Stock symbol:");
        stockSymbol = sc.next();
        flag5 = false;
        for (int k = 0; k < validTickerSymbols.length; k++) {
          if (stockSymbol.contains(validTickerSymbols[k])) {
            flag5 = true;
            break;
          }
        }
        if (!flag5) {
          view.showOutput("Invalid ticker.");
        }
      }
      while (!flag5);

      stockExists = user.checkIfStockExistsInFlexiblePortfolio(stockSymbol, portfolioName);
      if (stockExists) {
        do {
          try {

            view.showOutput("Enter the stock quantity:");
            String s = sc.next();
            qty = Float.parseFloat(s);
            if (qty < 0) {
              view.showOutput("Quantity can't be negative.");
            }

          } catch (NumberFormatException ex) {
            view.showOutput("You can only buy stocks of integer value. Please enter "
                    + "correct quantity:");
          }
        }
        while (qty == null || qty < 0);
        do {
          try {
            view.showOutput("Enter commission fees");
            String com = sc.next();
            comFees = Integer.parseInt(com);
            if (comFees < 0) {
              view.showOutput("Commission fees cannot be negative");
            }
          } catch (NumberFormatException ex) {
            view.showOutput("You can enter commission of integer value only. Please enter "
                    + "correct quantity!");
          }
        }
        while (comFees == null || comFees < 0);

        boolean sellStockQtyValid = false;
        sellStockQtyValid = user.checkSellStockQty(qty, portfolioName, stockSymbol);

        boolean sellStockDateValid = false;
        String sellDate = dateValidation(sc);
        sellStockDateValid = user.checkSellStockDate(sellDate, portfolioName, stockSymbol);

        if (sellStockDateValid && sellStockQtyValid) {
          user.removeFromFlexiblePortfolio(stockSymbol, qty, sellDate, comFees, portfolioName);
        } else {

          if (!sellStockQtyValid) {
            view.showOutput("You can't sell stocks greater than what you bought.");
          }
          if (!sellStockDateValid) {
            view.showOutput("You can't sell a stock before it is purchased.");
          }

        }

      } else {
        view.showOutput("Stock you're trying to sell doesn't exist in your portfolio.");
      }

    }
  }

  private void uploadFlexiblePort(Scanner sc) {
    view.showOutput("Enter the file path of the flexible xml file");
    filePath = sc.next();
    user.readFlexibleXMLFile(filePath);
    view.showOutput("Flexible Portfolio successfully uploaded!");
  }

  private void valuationFlexiblePort(Scanner sc) {
    int flag = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    boolean ifPortExists = user.checkFlexiblePortfolioExists(portfolioName);
    if (!ifPortExists) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      String valDate;
      valDate = dateValidation(sc);

      try {
        newMap = user.getFlexiblePortfolio(portfolioName);

        if (newMap == null) {
          view.showOutput("You do not have any portfolios.Please add portfolios first.");
          //break;
        } else {
          List<Float> values = user.getValuationForFlexiblePort(portfolioName, valDate);
          view.displaySingleFlexiblePortValuation(newMap, values, portfolioName);
        }
      } catch (IllegalArgumentException | ParseException e) {
        view.showOutput("Date does not exist.");
      }
    }
  }

  private void costBasisFlexiblePort(Scanner sc) throws ParseException {
    int flag = 0;
    view.showOutput("Enter Portfolio name:");
    portfolioName = sc.next();

    boolean ifPortfolioExists = user.checkFlexiblePortfolioExists(portfolioName);
    if (!ifPortfolioExists) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      newMap = user.getFlexiblePortfolio(portfolioName);
      String costBasisDate;
      costBasisDate = dateValidation(sc);
      List<Float> valuation = user.calculateCostBasis(portfolioName, costBasisDate);
      view.displayCostBasis(newMap, valuation, portfolioName);
    }
  }

  private void portfolioPerformance(Scanner sc) throws ParseException {
    int flag = 0;
    view.showOutput("Enter the portfolio which you want to visualize:");
    portfolioName = sc.next();
    boolean ifPortfolioExists1 = user.checkFlexiblePortfolioExists(portfolioName);
    if (!ifPortfolioExists1) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      view.showOutput("Enter the start date:");
      String startDate = dateValidation(sc);
      view.showOutput("Enter the end date:");
      String endDate = dateValidation(sc);
      boolean validDate = endAfterStartDate(startDate, endDate);
      if (validDate) {
        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>();
        map2 = user.getPerformance(startDate, endDate, portfolioName);
        float scale = user.returnScale();
        view.getPerformanceForMonth(portfolioName, startDate, endDate, map2, scale);
      } else {
        view.showOutput("End date cannot be before start date!");
      }
    }
  }

  private void displayAllFlexiblePortfolios(Scanner sc) {
    int flag = 0;
    int portfolioNumber = user.getNumberOfFlexiblePortfolio();
    if (portfolioNumber == 0) {
      view.showOutput("You do not have any portfolios.Please add portfolios first.");
      flag = 1;
    }
    if (flag == 0) {
      for (int i = 0; i < portfolioNumber; i++) {
        newMap = user.displaySingleFlexiblePort(i);
        portfolioName = user.getFlexiblePortfolioName(i);
        view.displaySingleFlexiblePortfolio(newMap, portfolioName);
      }
    }
  }

  private boolean endAfterStartDate(String startDate, String endDate) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date date1 = sdf.parse(startDate);
    Date date2 = sdf.parse(endDate);
    return (date2.after(date1));
  }

  private String dateValidation(Scanner sc) {
    boolean flag2 = false;
    String valuationDate = null;
    do {
      try {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String currDate = formatter.format(date);
        Date currentDate = new SimpleDateFormat("yyyy-mm-dd").parse(currDate);
        view.showOutput("Enter the date " + "in yyyy-mm-dd format:");
        valuationDate = sc.next();
        if (valuationDate.matches("[a-zA-Z]+")) {
          view.showOutput("Entered date is invalid and contains letter. Enter a valid date!");
          flag2 = true;
        } else {
          Date date2 = null;
          try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            date2 = sdf.parse(valuationDate);
            if (!valuationDate.equals(sdf.format(date2))) {
              date2 = null;
              flag2 = true;
              view.showOutput("Format of date is invalid. Please enter a valid date! ");
            } else {
              String strDate = sdf.format(date2);
              if (Integer.parseInt(strDate.substring(5, 7)) > 12) {
                flag2 = true;
                view.showOutput("Invalid month");
              } else if (Integer.parseInt(strDate.substring(8, 10)) > 31) {
                flag2 = true;
                view.showOutput("Invalid date");
              }
              flag2 = false;

            }

          } catch (ParseException ex) {
            view.showOutput("Invalid date");
          }
          Date certainDate = new SimpleDateFormat("yyyy-mm-dd").parse(valuationDate);

          if (currentDate.compareTo(certainDate) < 0) {
            view.showOutput("The date entered is invalid. Please enter a valid date: ");
            flag2 = true;
          } else if (certainDate == null) {
            view.showOutput("The date entered is invalid. Please enter a valid date: ");
            flag2 = true;
          }
        }
      } catch (ParseException e) {
        view.showOutput("The date entered is invalid. Please enter a valid date: ");
      }
    }
    while (flag2);
    return valuationDate;
  }

}
