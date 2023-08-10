package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.User;
import view.View2;

/**
 * Class which implements 'Controller' class.
 * It controls the actual flow of the program.
 * Controls the data flow into the models and updates the view.
 */
public class ControllerImpl2 implements Controller2 {

  Map<String, Map<String, Float>> newMap = new HashMap<>();
  String portfolioName;
  private User user;
  private View2 view;

  /**
   * Constructor which initialises the user model.
   *
   * @param m the object of 'user' class.
   */
  public ControllerImpl2(User m) {
    this.user = m;
  }

  private static boolean isWeekend(final LocalDate ld) {
    DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }

  /**
   * Method which sets view and adds features.
   *
   * @param v the object of 'view' class.
   */
  public void setView(View2 v) {
    view = v;
    view.addFeatures(this);
  }

  private boolean dateFormatHelper(String date) throws IllegalArgumentException {
    String temp_date = date.replaceAll("[\\s\\-()]", "");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    String curr_date = dtf.format(now).replaceAll("[\\s\\-()]", "");
    if (Integer.parseInt(temp_date) >= Integer.parseInt(curr_date)) {
      throw new IllegalArgumentException(
              "Date cannot be greater or equal to current date. Try a different date");
    }
    if (Integer.parseInt(temp_date) <= 20000101) {
      throw new IllegalArgumentException(
              "Date should be more than 1st January 2000. Try a different date");
    }
    return true;
  }

  @Override
  public void createFlexible() throws IOException {
    Integer noOfStocks = null;
    Float stockQty = null;
    Integer comFees = null;
    // view.showOutput("Enter Portfolio name:");
    ArrayList<String> output = view.createPortfolioInput();
    // portfolioName = sc.next();

    portfolioName = output.get(0);
    String stockSymbol = "";

    try {
      //view.showOutput("Enter number of stocks you want to buy:");
      String s = output.get(1);
      noOfStocks = Integer.parseInt(s);
      if (noOfStocks < 0) {
        view.showOutput("Stocks cannot be negative.");
      }
    } catch (NumberFormatException ex) {
      view.showOutput("You can enter stocks of integer value only. Please enter "
              + "correct quantity!");
    }

    ArrayList<String> stkSymbols = new ArrayList<>();
    ArrayList<Float> stkQuantity = new ArrayList<>();
    ArrayList<String> stkDates = new ArrayList<>();
    ArrayList<Integer> stkCom = new ArrayList<>();
    ArrayList<String> output1 = new ArrayList<>();

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

      output1 = view.createHelper();

      //view.showOutput("Enter Stock symbol:");
      stockSymbol = output1.get(0);
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

      try {

        //view.showOutput("Enter the stock quantity:");
        String s = output1.get(1);
        stockQty = Float.parseFloat(s);
        if (stockQty < 0) {
          view.showOutput("Quantity can't be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can only buy stocks of integer value. Please enter "
                + "correct quantity:");
      }
      try {
        //view.showOutput("Enter commission fees");
        String com = output1.get(2);
        comFees = Integer.parseInt(com);
        if (comFees < 0) {
          view.showOutput("Commission fees cannot be negative");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter commission of integer value only. Please enter "
                + "correct quantity!");
      }


      String buyingDate = output1.get(3);
      String newBuyingDate = dateValidation(buyingDate);

      stkSymbols.add(stockSymbol);
      stkQuantity.add(stockQty);
      stkDates.add(newBuyingDate);
      stkCom.add(comFees);

    }

    user.addFlexiblePortfolio(stkSymbols, stkQuantity, stkDates, stkCom, portfolioName);

  }

  @Override
  public void buyStockFlexiblePort() throws IOException {
    int flag = 0;
    ArrayList<String> output = view.buyStocksHelper();
    Float stkQty = null;
    Integer comFees = null;
    String stockSymbol = "";
    //view.showOutput("Enter Flexible Portfolio name :");
    portfolioName = output.get(0);
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

      //view.showOutput("Enter Stock symbol:");
      stockSymbol = output.get(1);
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


      try {

        //view.showOutput("Enter the stock quantity:");
        String s = output.get(2);
        stkQty = Float.parseFloat(s);
        if (stkQty < 0) {
          view.showOutput("Quantity can't be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can only buy stocks of integer value. Please enter "
                + "correct quantity:");
      }
      try {
        //view.showOutput("Enter commission fees");
        String com = output.get(3);
        comFees = Integer.parseInt(com);
        if (comFees < 0) {
          view.showOutput("Commission fees cannot be negative");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter commission of integer value only. Please enter "
                + "correct quantity!");
      }


      String buyingDate1 = output.get(4);
      String buyingDate = dateValidation(buyingDate1);
      stkSymbols1.add(stockSymbol);
      stkQuantity1.add(stkQty);
      stkDates1.add(buyingDate);
      stkCom1.add(comFees);

      user.addToFlexiblePortfolio(stkSymbols1, stkQuantity1, stkDates1, stkCom1, portfolioName);
    }
  }

  @Override
  public void sellStockFlexiblePort() throws ParseException, IOException {
    int flag = 0;
    Float qty = null;
    Integer comFees = null;
    String stockSymbol = "";
    ArrayList<String> output = view.buyStocksHelper();
    //view.showOutput("Enter Flexible Portfolio name :");
    portfolioName = output.get(0);
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
      //view.showOutput("Enter Stock symbol:");
      stockSymbol = output.get(1);
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


      stockExists = user.checkIfStockExistsInFlexiblePortfolio(stockSymbol, portfolioName);
      if (stockExists) {
        try {

          //view.showOutput("Enter the stock quantity:");
          String s = output.get(2);
          qty = Float.parseFloat(s);
          if (qty < 0) {
            view.showOutput("Quantity can't be negative.");
          }

        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }

        try {
          //view.showOutput("Enter commission fees");
          String com = output.get(3);
          comFees = Integer.parseInt(com);
          if (comFees < 0) {
            view.showOutput("Commission fees cannot be negative");
          }
        } catch (NumberFormatException ex) {
          view.showOutput("You can enter commission of integer value only. Please enter "
                  + "correct quantity!");
        }

        boolean sellStockQtyValid = false;
        sellStockQtyValid = user.checkSellStockQty(qty, portfolioName, stockSymbol);

        boolean sellStockDateValid = false;
        String sellDate1 = output.get(4);
        String sellDate = dateValidation(sellDate1);
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

  @Override
  public void uploadFlexiblePort() {

    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "XML files only", "xml");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(null);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      try {
        user.readFlexibleXMLFile(String.valueOf(f));
        view.showOutput("Flexible Portfolio successfully uploaded!");

      } catch (IllegalArgumentException e) {
        view.showOutput("Cannot upload portfolio.");
      }
    }
  }

  @Override
  public void valuationFlexiblePort() {
    int flag = 0;
    ArrayList<String> output = view.getInput();
    //view.showOutput("Enter Portfolio name:");
    portfolioName = output.get(0);

    boolean ifPortExists = user.checkFlexiblePortfolioExists(portfolioName);
    if (!ifPortExists) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      String valDate1;
      valDate1 = output.get(1);
      String valDate = dateValidation(valDate1);

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

  @Override
  public void costBasisFlexiblePort() throws ParseException {
    int flag = 0;
    ArrayList<String> output = view.getInput();
    //view.showOutput("Enter Portfolio name:");
    portfolioName = output.get(0);

    boolean ifPortfolioExists = user.checkFlexiblePortfolioExists(portfolioName);
    if (!ifPortfolioExists) {
      view.showOutput("Portfolio does not exist!");
      flag = 1;
    }
    if (flag == 0) {
      newMap = user.getFlexiblePortfolio(portfolioName);
      String costBasisDate1;
      costBasisDate1 = output.get(1);
      String costBasisDate = dateValidation(costBasisDate1);
      List<Float> valuation = user.calculateCostBasis(portfolioName, costBasisDate);
      view.displayCostBasis(newMap, valuation, portfolioName);
    }
  }

  @Override
  public void investPortfolio() throws IOException {

    Map<Integer, Map<String, Map<String, Float>>> newMap1 = new HashMap<>();
    ArrayList<String> output = view.investPortfolioInput();
    Integer noOfStocks = null;
    Float stockQty = null;
    float newAmt = 0;
    Float qty1 = 0f;
    Integer comFees = null;
    String stockSymbol = "";
    int flag = 0;
    float amt = 0;
    //view.showOutput("Enter Portfolio name:");
    portfolioName = output.get(0);

    //view.showOutput("Enter the amount you want to invest:");
    String amt1 = output.get(1);
    amt = Float.parseFloat(amt1);

    newMap1 = user.getFlexibleCostBasis(portfolioName);
    if (newMap1 == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      try {
        // view.showOutput("Enter number of stocks you want to buy:");
        String s = output.get(2);
        noOfStocks = Integer.parseInt(s);
        if (noOfStocks < 0) {
          view.showOutput("Stocks cannot be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter stocks of integer value only. Please enter "
                + "correct quantity!");
      }
    }

    try {
      //view.showOutput("Enter commission fees");
      String com = output.get(3);
      comFees = (Integer.parseInt(com));
      if (comFees < 0) {
        view.showOutput("Commission fees cannot be negative");
      }
    } catch (NumberFormatException ex) {
      view.showOutput("You can enter commission of integer value only. Please enter "
              + "correct quantity!");
    }
    //String investmentDate1 = output.get(4);
    //String investmentDate = dateValidation(investmentDate1);
    String investmentDate = "";
    String investmentDate2 = output.get(4);
    String investmentDate1 = dateValidation(investmentDate2);
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

      ArrayList<String> output1 = view.investHelper();

      stockSymbol = output1.get(0);
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


      try {


        String s = output1.get(1);
        qty1 = Float.parseFloat(s);
        if (qty1 < 0) {
          view.showOutput("Quantity can't be negative.");
        }
        stockPercentage.add(qty1);
      } catch (NumberFormatException ex) {
        view.showOutput("You can only buy stocks of integer value. Please enter "
                + "correct quantity:");
      }


      stkSymbols.add(stockSymbol);
      stkCom.add(comFees / noOfStocks);


    }
    float sum = 0;
    for (int i = 0; i < stockPercentage.size(); i++) {
      sum += stockPercentage.get(i);
    }
    if (sum != 100.0f) {
      view.showOutput("Sum of percentages cannot be greater than 100");
    }


    this.percentageAddToPortfolio(stkSymbols, stockPercentage, newAmt, investmentDate, stkCom);
  }

  @Override
  public void dollarCost() throws IOException {

    Map<Integer, Map<String, Map<String, Float>>> newMap1 = new HashMap<>();
    ArrayList<String> output = view.dollarCostInput();
    Integer noOfStocks = null;
    Float stockQty = null;
    String stockSymbol = "";
    float newAmt = 0;
    Float qty1 = 0f;
    Integer comFees = null;
    int flag = 0;
    float amt = 0;
    portfolioName = output.get(0);

    String amt1 = output.get(1);
    amt = Float.parseFloat(amt1);

    newMap1 = user.getFlexibleCostBasis(portfolioName);
    if (newMap1 == null) {
      view.showOutput("Portfolio doesn't exist.");
      flag = 1;
    }
    if (flag == 0) {
      try {
        String s = output.get(2);
        noOfStocks = Integer.parseInt(s);
        if (noOfStocks < 0) {
          view.showOutput("Stocks cannot be negative.");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter stocks of integer value only. Please enter "
                + "correct quantity!");
      }
      try {
        //view.showOutput("Enter commission fees");
        String com = output.get(3);
        comFees = (Integer.parseInt(com));
        if (comFees < 0) {
          view.showOutput("Commission fees cannot be negative");
        }
      } catch (NumberFormatException ex) {
        view.showOutput("You can enter commission of integer value only. Please enter "
                + "correct quantity!");
      }
      //String investmentDate = dateValidation(sc);
      newAmt = amt - comFees;


      String startDate = "";
      String startDate2 = output.get(4);
      String startDate1 = dateValidation(startDate2);
      LocalDate start1 = LocalDate.parse(startDate1);
      if (!isWeekend(start1)) {
        startDate = (startDate1);
      } else {
        start1 = start1.plusDays(2);
        startDate = (start1.toString());

      }
      //view.showOutput("Input empty string:");
      String endDate1 = output.get(5);
      String endDate = dateValidation(endDate1);

      if (endDate.equals("null")) {
        endDate = ("2024-01-09");
      }


      LocalDate start = LocalDate.parse(startDate);
      LocalDate end = LocalDate.parse(endDate);

      //view.showOutput("Enter the time span:");
      String time = output.get(6);
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

        ArrayList<String> output1 = view.investHelper();

        stockSymbol = output1.get(0);
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


        try {

          //view.showOutput("Enter the percentage:");
          String s = output1.get(1);
          qty1 = Float.parseFloat(s);
          if (qty1 < 0) {
            view.showOutput("Quantity can't be negative.");
          }
          stockPercentage.add(qty1);
        } catch (NumberFormatException ex) {
          view.showOutput("You can only buy stocks of integer value. Please enter "
                  + "correct quantity:");
        }


        stkSymbols.add(stockSymbol);
        stkCom.add(comFees / noOfStocks);


        //user.addToFlexiblePortfolio(stkSymbols, stkQuantity, stkDates, stkCom, portfolioName);
      }
      float sum = 0;
      for (int i = 0; i < stockPercentage.size(); i++) {
        sum += stockPercentage.get(i);
      }
      if (sum != 100.0f) {
        //show error
      }


      for (LocalDate loopDate = start; loopDate.isBefore(end)
              ; loopDate = loopDate.plusDays(time1)) {

        this.percentageAddToPortfolio(stkSymbols, stockPercentage, newAmt, loopDate.toString()
                , stkCom);
      }
    }
  }

  private void percentageAddToPortfolio(ArrayList<String> stockNames,
                                        ArrayList<Float> stckPercentage,
                                        float newAmt, String stckDate
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

  private boolean endAfterStartDate(String startDate, String endDate) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date date1 = sdf.parse(startDate);
    Date date2 = sdf.parse(endDate);
    return (date2.after(date1));
  }

  private String dateValidation(String valuationDate) {
    boolean flag2 = false;
    //String valuationDate = null;
    try {
      Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
      String currDate = formatter.format(date);
      Date currentDate = new SimpleDateFormat("yyyy-mm-dd").parse(currDate);
      //view.showOutput("Enter the date " + "in yyyy-mm-dd format:");
      //valuationDate = sc.next();
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
    return valuationDate;
  }


}
