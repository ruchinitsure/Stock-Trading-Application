package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Class that represents an user and defines all the operations that a user can accomplish.
 * Implements 'User' interface.
 */
public class UserImpl implements User {

  int transactionCount = 0;
  float scale = 0f;

  private ArrayList<Portfolio> portfolioList;

  private ArrayList<Portfolio> portfolioListFlexible;

  /**
   * Constructor that initialises a portfoliolist.
   */
  public UserImpl() {
    portfolioList = new ArrayList<Portfolio>();
    portfolioListFlexible = new ArrayList<Portfolio>();
  }

  @Override
  public void createPortfolio(Portfolio portfolio) {
    portfolioList.add(portfolio);

    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();

      // root element
      Element rootElement = doc.createElement("portfolio");
      rootElement.setAttribute("portfolioName", portfolio.getPortfolioName());

      doc.appendChild(rootElement);

      ArrayList<Stock> stockList = new ArrayList<Stock>();

      stockList = portfolio.displayPortfolio();
      for (int j = 0; j < stockList.size(); j++) {


        Element stockElement = doc.createElement("stock");
        rootElement.appendChild(stockElement);


        Element stockName = doc.createElement("stockName");
        stockName.setTextContent(stockList.get(j).getStockSymbol());
        stockElement.appendChild(stockName);

        Element stockQuantity = doc.createElement("stockQuantity");
        stockQuantity.setTextContent(Float.toString(stockList.get(j).getStockQuantity()));
        stockElement.appendChild(stockQuantity);
      }

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      String filePath = new File("").getAbsolutePath();
      StreamResult result = new StreamResult(new File(filePath + "/"
              + portfolio.getPortfolioName() + ".xml"));
      transformer.transform(source, result);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<Portfolio> displayPortfolios() {
    return portfolioList;
  }

  @Override
  public int getNumberOfPortfolio() {
    return portfolioList.size();
  }

  @Override
  public Map<String, Float> displaySinglePort(int i) {
    Map<String, Float> map = new HashMap<>();
    Portfolio port = portfolioList.get(i);
    ArrayList<Stock> stockList = port.displayPortfolio();
    int numberOfStock = port.getNumberOfStock();
    for (int j = 0; j < numberOfStock; j++) {
      Stock stock = stockList.get(j);
      map.put(stock.getStockSymbol(), stock.getStockQuantity());
    }
    return map;
  }

  @Override
  public String getPortfolioName(int i) {
    Portfolio tempPort = portfolioList.get(i);
    return tempPort.getPortfolioName();
  }

  @Override
  public Map<String, Float> getPortfolio(String portfolioName) {
    int portfolioLength = portfolioList.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioList.get(i).getPortfolioName().equals(portfolioName)) {
        return displaySinglePort(i);
      }
    }
    return null;
  }

  @Override
  public List<Float> getValuationForPort(String portfolioName, String valuationDate) {
    URL url;
    List<Float> values = new ArrayList<>();
    List<String> stockName = new ArrayList<>();
    List<Float> stockQty = new ArrayList<>();
    Map<String, Float> map = getPortfolio(portfolioName);
    AlphaVantage api = new AlphaVantageImpl();
    float temp = 0;
    for (Map.Entry<String, Float> entry : map.entrySet()) {
      stockName.add(entry.getKey());
      stockQty.add(entry.getValue());
    }
    for (int i = 0; i < stockName.size(); i++) {
      url = api.getUrl(stockName.get(i));
      temp = Float.parseFloat(api.getClosingValue(url, valuationDate));
      values.add(temp * (stockQty.get(i)));
    }
    return values;
  }

  @Override
  public boolean checkPortfolioExists(String portfolioName) {
    for (int i = 0; i < portfolioList.size(); i++) {
      if (portfolioList.get(i).getPortfolioName().equals(portfolioName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addPortfolio(ArrayList<String> names, ArrayList<Float> qty, String portfolioName)
          throws IOException {

    Portfolio temp = new PortfolioImpl(portfolioName);
    for (int i = 0; i < names.size(); i++) {
      temp.addStock(new StockImpl(names.get(i), qty.get(i)));
    }
    this.createPortfolio(temp);
  }

  @Override
  public ArrayList<Portfolio> displayFlexiblePortfolios() {
    return portfolioListFlexible;
  }

  @Override
  public void readXMLFile(String filePath) {

    // Instantiate the Factory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(filePath));
      doc.getDocumentElement().normalize();

      Element root = doc.getDocumentElement();

      String portName = root.getAttribute("portfolioName");
      Portfolio portTemp = new PortfolioImpl(portName);
      NodeList nodeList = doc.getElementsByTagName("stock");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String stockNameTemp = element.getElementsByTagName("stockName").item(0)
                  .getTextContent();
          String stockQuantityTemp = element.getElementsByTagName("stockQuantity").item(0)
                  .getTextContent();
          portTemp.addStock(new StockImpl(stockNameTemp, Float.parseFloat(stockQuantityTemp)));
        }
      }
      this.createPortfolio(portTemp);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void readFlexibleXMLFile(String filePath) {

    // Instantiate the Factory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(filePath));
      doc.getDocumentElement().normalize();

      Element root = doc.getDocumentElement();

      String portName = root.getAttribute("portfolioName");
      Portfolio portTemp = new PortfolioImpl(portName);
      NodeList nodeList = doc.getElementsByTagName("stock");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String stockNameTemp = element.getElementsByTagName("stockName").item(0)
                  .getTextContent();
          String stockQuantityTemp = element.getElementsByTagName("stockQuantity").item(0)
                  .getTextContent();
          String stockDateTemp = element.getElementsByTagName("stockDate").item(0)
                  .getTextContent();
          String stockComTemp = element.getElementsByTagName("stockCom").item(0)
                  .getTextContent();
          portTemp.addStock(new StockImpl(stockNameTemp, Float.parseFloat(stockQuantityTemp),
                  stockDateTemp, Integer.parseInt(stockComTemp)));
        }
      }
      this.createFlexiblePortfolio(portTemp);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public int getNumberOfFlexiblePortfolio() {
    return portfolioListFlexible.size();
  }


  @Override
  public String getFlexiblePortfolioName(int i) {
    Portfolio tempPort = portfolioListFlexible.get(i);
    return tempPort.getPortfolioName();
  }


  @Override
  public Map<String, Map<String, Float>> getComposition(String portfolioName, String inputDate)
          throws ParseException {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return displayComposition(i, inputDate, portfolioName);
      }
    }
    return null;


  }

  @Override
  public List<String> getSymbol(String portfolioName) {

    int portfolioLength = portfolioListFlexible.size();
    int numberOfStock = 0;
    ArrayList<String> symbols = new ArrayList<>();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);
        ArrayList<Stock> stockList = port.displayPortfolio();
        numberOfStock = port.getNumberOfStock();
        for (int j = 0; j < numberOfStock; j++) {
          symbols.add(stockList.get(j).getStockSymbol());


        }

      }
    }
    return symbols;
  }


  @Override
  public Map<String, Map<String, Float>> displayComposition(int i, String date1,
                                                            String portfolioName)
          throws ParseException {
    Map<String, Map<String, Float>> outerMap = new HashMap<>();
    LocalDate date2 = LocalDate.parse(date1);
    Portfolio port = portfolioListFlexible.get(i);
    ArrayList<Stock> stockList = port.displayPortfolio();
    int numberOfStock = port.getNumberOfStock();

    for (int j = 0; j < numberOfStock; j++) {
      LocalDate stockListDate = LocalDate.parse(stockList.get(j).getStockDate());
      Map<String, Float> innerMap = new HashMap<>();
      if (date2.isAfter(stockListDate)) {
        innerMap.put(stockList.get(j).getStockSymbol(), stockList.get(j).getStockQuantity());
        outerMap.put(stockList.get(j).getStockDate(), innerMap);
      }

    }
    boolean temp = false;
    List<String> stockName = null;
    List<Float> stockQty = null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Map<String, Map<String, Float>> soldOuterMap = getFlexibleSoldPortfolio(portfolioName);
    for (Map.Entry<String, Map<String, Float>> entry : soldOuterMap.entrySet()) {
      String dateInMap = entry.getKey();
      LocalDate date3 = LocalDate.parse(dateInMap);
      Map<String, Float> soldInnerMap = entry.getValue();
      if (date3.isAfter(date2)) {
        for (Map.Entry<String, Float> tempMap : soldInnerMap.entrySet()) {
          stockName = new ArrayList<>();
          stockQty = new ArrayList<>();
          stockName.add(tempMap.getKey());
          stockQty.add(tempMap.getValue());
        }
        for (int k = 0; k < stockName.size(); k++) {
          for (int j = 0; j < stockList.size(); j++) {
            if (stockName.get(k).equals(stockList.get(j).getStockSymbol())) {

              float originalQty = stockList.get(j).getStockQuantity();
              float sellQty = stockQty.get(k);
              stockList.get(j).setStockQuantity(originalQty + sellQty);
            }
          }

        }
        temp = true;
      }

    }

    if (temp) {
      Map<String, Map<String, Float>> resultMap = new HashMap<>();
      resultMap = getFlexiblePortfolio(portfolioName);
      return resultMap;
    } else {
      return outerMap;
    }

  }

  @Override
  public Map<String, Map<String, Float>> getFlexiblePortfolio(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return displaySingleFlexiblePort(i);
      }
    }
    return null;
  }

  @Override
  public Map<String, Map<String, Float>> getFlexibleSoldPortfolio(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return displaySingleSoldFlexiblePort(i);
      }
    }
    return null;
  }

  @Override
  public Portfolio getFlexiblePortfolioP(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return portfolioListFlexible.get(i);
      }
    }
    return null;
  }

  @Override
  public Map<String, Map<String, Float>> displaySingleFlexiblePort(int i) {
    Map<String, Map<String, Float>> outerMap = new HashMap<>();

    Portfolio port = portfolioListFlexible.get(i);
    ArrayList<Stock> stockList = port.displayPortfolio();
    int numberOfStock = port.getNumberOfStock();

    for (int j = 0; j < numberOfStock; j++) {
      Map<String, Float> innerMap = new HashMap<>();
      innerMap.put(stockList.get(j).getStockSymbol(), stockList.get(j).getStockQuantity());
      outerMap.put(stockList.get(j).getStockDate(), innerMap);
    }

    return outerMap;
  }

  @Override
  public Map<String, Map<String, Float>> displaySingleSoldFlexiblePort(int i) {
    Map<String, Map<String, Float>> outerMap = new HashMap<>();

    Portfolio port = portfolioListFlexible.get(i);
    ArrayList<Stock> stockList = port.displaySellStockList();
    int numberOfStock = stockList.size();
    for (int j = 0; j < numberOfStock; j++) {
      Map<String, Float> innerMap = new HashMap<>();
      innerMap.put(stockList.get(j).getStockSymbol(), stockList.get(j).getStockQuantity());
      outerMap.put(stockList.get(j).getStockDate(), innerMap);
    }

    return outerMap;
  }


  @Override
  public List<Float> getValuationForFlexiblePort(String portfolioName, String valuationDate)
          throws ParseException {
    List<Float> values = new ArrayList<>();
    List<String> stockName = null;
    List<Float> stockQty = null;
    List<Date> stockDate = null;
    Map<String, Map<String, Float>> outerMap = getFlexiblePortfolio(portfolioName);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
    Date valDate = formatter.parse(valuationDate);
    AlphaVantage api = new AlphaVantageImpl();
    float temp = 0;

    for (Map.Entry<String, Map<String, Float>> entry : outerMap.entrySet()) {
      String dateInMap = entry.getKey();
      Date date = formatter.parse(dateInMap);
      stockDate = new ArrayList<>();
      stockDate.add(date);
      Date minDate = Collections.min(stockDate);
      if (valDate.after(minDate)) {
        Map<String, Float> innerMap = entry.getValue();
        if (valDate.after(date)) {
          // Iterate InnerMap
          for (Map.Entry<String, Float> tempMap : innerMap.entrySet()) {
            stockName = new ArrayList<>();
            stockQty = new ArrayList<>();
            stockName.add(tempMap.getKey());
            stockQty.add(tempMap.getValue());
          }
          for (int i = 0; i < stockName.size(); i++) {
            URL url;
            url = api.getUrl(stockName.get(i));
            temp = Float.parseFloat(api.getClosingValue(url, valuationDate));
            values.add(temp * (stockQty.get(i)));
          }
        } else {
          values.add(0f);
        }
      } else {
        values.add(0f);
      }

    }

    return values;
  }

  @Override
  public Float getValuationForSingleSymbol(String stockSymbol, String valuationDate) {
    URL url;
    float temp = 0f;
    AlphaVantage api = new AlphaVantageImpl();
    url = api.getUrl(stockSymbol);
    temp = Float.parseFloat(api.getClosingValue(url, valuationDate));
    return temp;

  }


  @Override
  public LinkedHashMap<String, Integer> getPerformance(String startDateString, String endDateString,
                                                       String portfolioName) {
    LinkedHashMap<Float, String> map = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> starMap = new LinkedHashMap<>();
    List<LocalDate> totalDates = new ArrayList<>();
    List<LocalDate> startDates = new ArrayList<>();
    List<Float> values = new ArrayList<>();
    List<Float> valuationList = new ArrayList<>();

    try {
      // Convert `String` to `Date`
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
      Date startDate = sdf.parse(startDateString);
      Date endDate = sdf.parse(endDateString);

      // Calculate the number of days between dates
      long timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
      long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);


      if (daysDiff < 30) {

        LocalDate start = LocalDate.parse(startDateString);
        LocalDate end = LocalDate.parse(endDateString);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        totalDates = new ArrayList<>();
        boolean flag = false;
        while (!start.isAfter(end)) {
          totalDates.add(start);
          startDates.add(start);
          for (int m = 0; m < totalDates.size(); m++) {
            if ((totalDates.get(m)).compareTo(start) == 0) {
              values = getValuationForFlexiblePort(portfolioName, totalDates.get(m).toString());
              valuationList.add(values.get(0));
              flag = true;
            }
            if (flag) {
              map.put(values.get(0), start.toString());
              flag = false;
            }
          }
          start = start.plusDays(1);
        }
        scale = calculateAverage(valuationList);
        int noOfAsteriks;

        for (int n = 0; n < startDates.size(); n++) {
          noOfAsteriks = (int) (valuationList.get(n) / scale);
          starMap.put(startDates.get(n).toString(), noOfAsteriks);
        }


      } else if (daysDiff > 30 && daysDiff < 913) {
        List<String> listOfDates = new ArrayList<>();

        String[] startDateParts = startDateString.split("-");
        String startYear = startDateParts[0];
        String startMonth = startDateParts[1];
        String startDay = startDateParts[2];

        String[] endDateParts = endDateString.split("-");
        String endYear = endDateParts[0];
        String endMonth = endDateParts[1];
        String endDay = endDateParts[2];

        Date startDate1 = sdf.parse(startDateString);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate1);

        Date endDate1 = sdf.parse(endDateString);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate1);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                           "Oct", "Nov", "Dec"};
        int[] monthNo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] dates = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] tempMonthNum = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                                 "11", "12"};

        String month1 = null;
        String month2 = null;
        for (int i = 0; i < monthNo.length; i++) {
          if (monthNo[i] == startCalendar.get(Calendar.MONTH)) {
            month1 = months[i];
          }
          if (monthNo[i] == endCalendar.get(Calendar.MONTH)) {
            month2 = months[i];
          }
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yyyy",
                Locale.ENGLISH);
        YearMonth beginDate = YearMonth.parse(month1 + "-" + startYear, formatter);
        YearMonth finishDate = YearMonth.parse(month2 + "-" + endYear, formatter);


        List<String> displayDate = new ArrayList<>();
        while (beginDate.isBefore(finishDate)) {
          displayDate.add(beginDate.toString());
          listOfDates.add(beginDate.format(formatter));
          beginDate = beginDate.plusMonths(1);
        }
        displayDate.add(finishDate.toString());
        listOfDates.add(finishDate.format(formatter));

        for (int i = 0; i < displayDate.size(); i++) {
          String temp = displayDate.get(i);
          String[] temp2 = temp.split("-");
          String y = temp2[0];
          String m = temp2[1];

          int tempDate = 0;
          for (int j = 0; j < tempMonthNum.length; j++) {
            if (tempMonthNum[j].equals(m)) {
              tempDate = dates[j];
            }
          }
          String resultString = y + "-" + m + "-" + String.valueOf(tempDate);
          values = getValuationForFlexiblePort(portfolioName, resultString);
          valuationList.add(values.get(0));

        }

        scale = calculateAverage(valuationList);
        int noOfAsteriks;

        for (int n = 0; n < valuationList.size(); n++) {
          noOfAsteriks = (int) (valuationList.get(n) / scale);
          starMap.put(listOfDates.get(n), noOfAsteriks);
        }
      } else if (daysDiff > 913 && daysDiff < 10957) {
        List<String> listOfDates = new ArrayList<>();

        String[] startDateParts = startDateString.split("-");
        String startYear = startDateParts[0];
        String startMonth = startDateParts[1];
        String startDay = startDateParts[2];

        String[] endDateParts = endDateString.split("-");
        String endYear = endDateParts[0];
        String endMonth = endDateParts[1];
        String endDay = endDateParts[2];

        Date startDate1 = sdf.parse(startDateString);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate1);

        Date endDate1 = sdf.parse(endDateString);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate1);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
                           "Nov", "Dec"};
        int[] monthNo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] dates = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] tempMonthNum = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                                 "11", "12"};

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH);

        Year beginDate = Year.parse(startYear, formatter);
        Year finishDate = Year.parse(endYear, formatter);


        List<String> displayDate = new ArrayList<>();
        while (beginDate.isBefore(finishDate)) {
          displayDate.add(beginDate.toString());
          listOfDates.add(beginDate.format(formatter));
          beginDate = beginDate.plusYears(1);
        }
        displayDate.add(finishDate.toString());
        listOfDates.add(finishDate.format(formatter));

        for (int i = 0; i < displayDate.size(); i++) {
          String temp = displayDate.get(i);
          String resultString = temp + "-" + "12" + "-" + "31";

          values = getValuationForFlexiblePort(portfolioName, resultString);
          if (values.get(0) == 0) {
            resultString = temp + "-" + "12" + "-" + "30";
            values = getValuationForFlexiblePort(portfolioName, resultString);
            if (values.get(0) == 0) {
              resultString = temp + "-" + "12" + "-" + "29";
              values = getValuationForFlexiblePort(portfolioName, resultString);
            }
          }
          valuationList.add(values.get(0));

        }
        scale = calculateAverage(valuationList);
        int noOfAsteriks;

        for (int n = 0; n < valuationList.size(); n++) {
          noOfAsteriks = (int) (valuationList.get(n) / scale);
          starMap.put(listOfDates.get(n), noOfAsteriks);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return starMap;
  }


  @Override
  public float returnScale() {
    return scale;
  }

  @Override
  public float calculateAverage(List<Float> valuationList) {
    float diff = Collections.max(valuationList) - Collections.min(valuationList);
    return diff / 5;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> getFlexibleCostBasis(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return displayFlexibleCostBasis(i);
      }
    }
    return null;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> displayFlexibleCostBasis(int i) {
    Map<Integer, Map<String, Map<String, Float>>> outerMap = new HashMap<>();

    Portfolio port = portfolioListFlexible.get(i);
    ArrayList<Stock> stockList = port.displayPortfolio();
    int numberOfStock = port.getNumberOfStock();

    for (int j = 0; j < numberOfStock; j++) {
      Map<String, Map<String, Float>> innerMap = new HashMap<>();
      Map<String, Float> insideMap = new HashMap<>();
      insideMap.put(stockList.get(j).getStockSymbol(), stockList.get(j).getStockQuantity());
      innerMap.put(stockList.get(j).getStockDate(), insideMap);
      outerMap.put(stockList.get(j).getComFee(), innerMap);
    }

    return outerMap;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> getFlexibleSoldNew(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return displaySingleSoldFlexiblePortNew(i);
      }
    }
    return null;
  }

  @Override
  public Map<Integer, Map<String, Map<String, Float>>> displaySingleSoldFlexiblePortNew(int i) {
    Map<Integer, Map<String, Map<String, Float>>> outerMap = new HashMap<>();

    Portfolio port = portfolioListFlexible.get(i);
    ArrayList<Stock> stockList = port.displaySellStockList();
    int numberOfStock = stockList.size();
    for (int j = 0; j < numberOfStock; j++) {
      Map<String, Map<String, Float>> innerMap = new HashMap<>();
      Map<String, Float> insideMap = new HashMap<>();
      insideMap.put(stockList.get(j).getStockSymbol(), stockList.get(j).getStockQuantity());
      innerMap.put(stockList.get(j).getStockDate(), insideMap);
      outerMap.put(stockList.get(j).getComFee(), innerMap);
    }

    return outerMap;
  }

  @Override
  public List<Float> calculateCostBasis(String portfolioName, String costBasisDate)
          throws ParseException {

    List<Float> values = new ArrayList<>();
    List<String> stockName = null;
    List<Float> stockQty = null;
    Map<Integer, Map<String, Map<String, Float>>> outerMap = getFlexibleCostBasis(portfolioName);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date cbDate = formatter.parse(costBasisDate);
    AlphaVantage api = new AlphaVantageImpl();
    float temp = 0;
    float temp2 = 0;
    int comFee = 0;
    //float comFee = 100f;
    float totalFee = 0f;
    for (Map.Entry<Integer, Map<String, Map<String, Float>>> entry : outerMap.entrySet()) {
      comFee = entry.getKey();
      Map<String, Map<String, Float>> insideMap = entry.getValue();
      String dateInMap;
      for (Map.Entry<String, Map<String, Float>> inMap : insideMap.entrySet()) {
        dateInMap = inMap.getKey();
        //String dateInMap = entry.getValue();
        Date date = formatter.parse(dateInMap);
        Map<String, Float> innerMap = inMap.getValue();
        if (cbDate.after(date)) {
          // Iterate InnerMap
          for (Map.Entry<String, Float> tempMap : innerMap.entrySet()) {
            stockName = new ArrayList<>();
            stockQty = new ArrayList<>();
            stockName.add(tempMap.getKey());
            stockQty.add(tempMap.getValue());
          }

          for (int i = 0; i < stockName.size(); i++) {
            URL url;
            url = api.getUrl(stockName.get(i));
            temp = Float.parseFloat(api.getClosingValue(url, dateInMap));
            values.add(temp * (stockQty.get(i)));
          }

        } else {
          values.add(0f);
        }

      }
    }
    int count = transactionCount;
    String stockSymbolInList = "";
    Map<Integer, Map<String, Map<String, Float>>> soldOuterMap = getFlexibleSoldNew(portfolioName);
    for (Map.Entry<Integer, Map<String, Map<String, Float>>> entry : soldOuterMap.entrySet()) {
      comFee += entry.getKey();
      Map<String, Map<String, Float>> insideMap = entry.getValue();
      for (Map.Entry<String, Map<String, Float>> inMap : insideMap.entrySet()) {
        String dateInMap = inMap.getKey();
        Date date = formatter.parse(dateInMap);
        Map<String, Float> soldInnerMap = inMap.getValue();

        if (cbDate.after(date)) {
          count++;
        }
        // Iterate InnerMap
        for (Map.Entry<String, Float> tempMap : soldInnerMap.entrySet()) {
          stockName = new ArrayList<>();
          stockQty = new ArrayList<>();
          stockName.add(tempMap.getKey());
          stockQty.add(tempMap.getValue());
          stockSymbolInList = stockName.get(0);
        }
        String dateToBePassedString = "";
        int portfolioLength = portfolioListFlexible.size();
        for (int i = 0; i < portfolioLength; i++) {
          if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {

            Portfolio port = portfolioListFlexible.get(i);
            ArrayList<Stock> stockList = port.displayPortfolio();

            for (int j = 0; j < stockList.size(); j++) {

              if (stockList.get(j).getStockSymbol().equals(stockSymbolInList)) {
                dateToBePassedString = stockList.get(j).getStockDate();
              }
            }
          }
        }

        for (int i = 0; i < stockName.size(); i++) {
          URL url;
          url = api.getUrl(stockName.get(i));
          temp2 = Float.parseFloat(api.getClosingValue(url, dateToBePassedString));
          values.add(temp2 * (stockQty.get(i)));
        }
      }

    }

    totalFee = comFee * count;
    values.add(totalFee);
    return values;

  }


  @Override
  public boolean checkFlexiblePortfolioExists(String portfolioName) {
    for (int i = 0; i < portfolioListFlexible.size(); i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        return true;
      }
    }
    return false;
  }


  @Override
  public void addFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQty,
                                   ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException {
    Portfolio temp = new PortfolioImpl(portfolioName);
    for (int i = 0; i < stkSymbols.size(); i++) {
      temp.addStock(new StockImpl(stkSymbols.get(i), stkQty.get(i), stkDates.get(i)
              , stkCom.get(i)));
    }
    this.createFlexiblePortfolio(temp);
  }

  @Override
  public void writeFlexibleXML(String portfolioName) {
    Portfolio portfolio = this.getFlexiblePortfolioP(portfolioName);

    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();

      // root element
      Element rootElement = doc.createElement("portfolio");
      rootElement.setAttribute("portfolioName", portfolio.getPortfolioName());

      doc.appendChild(rootElement);

      ArrayList<Stock> stockList = new ArrayList<Stock>();

      stockList = portfolio.displayPortfolio();
      for (int j = 0; j < stockList.size(); j++) {


        Element stockElement = doc.createElement("stock");
        rootElement.appendChild(stockElement);


        Element stockName = doc.createElement("stockName");
        stockName.setTextContent(stockList.get(j).getStockSymbol());
        stockElement.appendChild(stockName);

        Element stockQuantity = doc.createElement("stockQuantity");
        stockQuantity.setTextContent(Float.toString(stockList.get(j).getStockQuantity()));
        stockElement.appendChild(stockQuantity);

        Element stockDate = doc.createElement("stockDate");
        stockDate.setTextContent(stockList.get(j).getStockDate());
        stockElement.appendChild(stockDate);

        Element stockCom = doc.createElement("stockCom");
        stockCom.setTextContent(Integer.toString(stockList.get(j).getComFee()));
        stockElement.appendChild(stockCom);
      }

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      String filePath = new File("").getAbsolutePath();
      StreamResult result = new StreamResult(new File(filePath + "/"
              + portfolio.getPortfolioName() + ".xml"));
      transformer.transform(source, result);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addToFlexiblePortfolio(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity,
                                     ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException {

    transactionCount++;
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        portfolioListFlexible.get(i).addStock(new StockImpl(stkSymbols.get(i), stkQuantity.get(i),
                stkDates.get(i), stkCom.get(i)));

      }
    }
    writeFlexibleXML(portfolioName);
  }

  @Override
  public void addToFlexiblePortfolio1(ArrayList<String> stkSymbols, ArrayList<Float> stkQuantity,
                                      ArrayList<String> stkDates, ArrayList<Integer> stkCom
          , String portfolioName) throws IOException {

    transactionCount++;
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        for (int j = 0; j < stkSymbols.size(); j++) {
          portfolioListFlexible.get(i).addStock(new StockImpl(stkSymbols.get(j), stkQuantity.get(j),
                  stkDates.get(j), stkCom.get(j)));
        }
      }
    }
    writeFlexibleXML(portfolioName);
  }

  @Override
  public boolean checkIfStockExistsInFlexiblePortfolio(String stockSymbol, String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);
        ArrayList<Stock> stockList = port.displayPortfolio();

        for (int j = 0; j < stockList.size(); j++) {

          if (stockList.get(j).getStockSymbol().equals(stockSymbol)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public boolean checkSellStockDate(String sellDateString, String portfolioName, String stockSymbol)
          throws ParseException {
    int portfolioLength = portfolioListFlexible.size();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
    Date sellDate = formatter.parse(sellDateString);
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);
        ArrayList<Stock> stockList = port.displayPortfolio();

        for (int j = 0; j < stockList.size(); j++) {

          if (stockList.get(j).getStockSymbol().equals(stockSymbol)) {
            String buyingDateString = stockList.get(j).getStockDate();
            Date buyingDate = formatter.parse(buyingDateString);
            return (sellDate.after(buyingDate));

          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkSellStockQty(Float qty, String portfolioName, String stockSymbol) {
    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);
        ArrayList<Stock> stockList = port.displayPortfolio();

        for (int j = 0; j < stockList.size(); j++) {

          if (stockList.get(j).getStockSymbol().equals(stockSymbol)) {
            return (stockList.get(j).getStockQuantity() > qty);

          }
        }
      }
    }
    return false;
  }

  @Override
  public void removeFromFlexiblePortfolio(String stkSymbol, Float stkQuantity, String stkDate
          , Integer comFees, String portfolioName) throws IOException {

    int portfolioLength = portfolioListFlexible.size();
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {

        Portfolio port = portfolioListFlexible.get(i);
        ArrayList<Stock> stockList = port.displayPortfolio();
        //get sold stocks
        ArrayList<Stock> sellStockList = port.displaySellStockList();
        for (int j = 0; j < stockList.size(); j++) {

          if (stockList.get(j).getStockSymbol().equals(stkSymbol)) {

            float oldStkQty = stockList.get(j).getStockQuantity();
            if (oldStkQty >= stkQuantity) {
              oldStkQty = oldStkQty - stkQuantity;
              stockList.get(j).setStockQuantity(oldStkQty);
              stockList.get(j).setComFee(comFees);
              //update sold stocks
              sellStockList.add(new StockImpl(stkSymbol, stkQuantity, stkDate, comFees));

            } else {
              System.out.println("You can't sell stocks greater than what you have bought.");
            }
          }

        }

        portfolioListFlexible.get(i).setSellStockList(sellStockList);
      }
    }
    writeFlexibleXML(portfolioName);
  }

  @Override
  public void createFlexiblePortfolio(Portfolio portfolio) {
    portfolioListFlexible.add(portfolio);
    transactionCount++;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();

      // root element
      Element rootElement = doc.createElement("portfolio");
      rootElement.setAttribute("portfolioName", portfolio.getPortfolioName());

      doc.appendChild(rootElement);

      ArrayList<Stock> stockList = new ArrayList<Stock>();

      stockList = portfolio.displayPortfolio();
      for (int j = 0; j < stockList.size(); j++) {


        Element stockElement = doc.createElement("stock");
        rootElement.appendChild(stockElement);


        Element stockName = doc.createElement("stockName");
        stockName.setTextContent(stockList.get(j).getStockSymbol());
        stockElement.appendChild(stockName);

        Element stockQuantity = doc.createElement("stockQuantity");
        stockQuantity.setTextContent(Float.toString(stockList.get(j).getStockQuantity()));
        stockElement.appendChild(stockQuantity);

        Element stockDate = doc.createElement("stockDate");
        stockDate.setTextContent(stockList.get(j).getStockDate());
        stockElement.appendChild(stockDate);

        Element stockCom = doc.createElement("stockCom");
        stockCom.setTextContent(Integer.toString(stockList.get(j).getComFee()));
        stockElement.appendChild(stockCom);


      }

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      String filePath = new File("").getAbsolutePath();
      StreamResult result = new StreamResult(new File(filePath + "/"
              + portfolio.getPortfolioName() + ".xml"));
      transformer.transform(source, result);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getStockSize(String portfolioName) {
    int portfolioLength = portfolioListFlexible.size();
    int numberOfStock = 0;
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);
        //ArrayList<Stock> stockList = port.displayPortfolio();
        numberOfStock = port.getNumberOfStock();
      }

    }
    return numberOfStock;
  }

  @Override
  public void investInPortfolio(String portfolioName, float newAmt, int comFees, String buyingDate,
                                ArrayList<Float> percentage) {
    int portfolioLength = portfolioListFlexible.size();
    int numberOfStock = 0;
    float stockQty = 0f;
    for (int i = 0; i < portfolioLength; i++) {
      if (portfolioListFlexible.get(i).getPortfolioName().equals(portfolioName)) {
        Portfolio port = portfolioListFlexible.get(i);

        ArrayList<Stock> stockList = port.displayPortfolio();
        numberOfStock = port.getNumberOfStock();
        for (int j = 0; j < numberOfStock; j++) {
          float trial = ((percentage.get(i) / 100) * newAmt);
          float temp1 = getValuationForSingleSymbol(stockList.get(j).getStockSymbol(), buyingDate);
          stockQty = temp1 / trial;
          stockList.get(j).setStockQuantity(stockQty);
          stockList.get(j).setComFee(comFees);


        }
      }

    }
    writeFlexibleXML(portfolioName);


  }

}
