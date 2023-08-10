package view;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that represents the view of the program and enlists all the features the user can view.
 * Implements 'View' interface.
 */
public class ViewImpl implements View {
  private PrintStream outStream;

  @Override
  public void displayMenu() {
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
    this.outStream.println("15.Invest in portfolio");
    this.outStream.println("16.Invest in existing portfolio");
    this.outStream.println("17.Dollar cost averaging.");
    this.outStream.println("18.Exit");

  }

  @Override
  public void displaySinglePortfolio(Map<String, Float> map, String name) {
    this.outStream.println();
    this.outStream.println("Portfolio Name : " + name);
    this.outStream.println("----------------------------------");
    for (Map.Entry<String, Float> entry : map.entrySet()) {
      this.outStream.println("Stock = " + entry.getKey() +
              ", Quantity = " + entry.getValue());
    }

  }

  @Override
  public void displaySingleFlexiblePortfolio(Map<String, Map<String, Float>> map, String name) {
    this.outStream.println();
    this.outStream.println("Portfolio Name : " + name);
    this.outStream.println("----------------------------------");
    for (Map.Entry<String, Map<String, Float>> entry : map.entrySet()) {
      this.outStream.println("Buying Date : " + entry.getKey());
      Map<String, Float> addMap = entry.getValue();
      // Iterate InnerMap
      for (Map.Entry<String, Float> innerMap : addMap.entrySet()) {
        this.outStream.println("Stock = " + innerMap.getKey() +
                ", Quantity = " + innerMap.getValue());
      }
      this.outStream.println();
    }
  }


  @Override
  public void displaySinglePortValuation(Map<String, Float> hm, List<Float> values,
                                         String portfolioName) {
    this.outStream.println();
    this.outStream.println("Portfolio Name : " + portfolioName);
    this.outStream.println("----------------------------------");
    int i = 0;
    Float temp = Float.valueOf(0);
    for (Map.Entry<String, Float> entry : hm.entrySet()) {
      this.outStream.println();
      this.outStream.println("Stock = " + entry.getKey() +
              ", Quantity = " + entry.getValue());
      if (values.get(i) == 0.0) {
        this.outStream.println(entry.getKey() + " doesn't have a valuation on the entered date." +
                "Hence it's valuation is 0.0");
      }
      this.outStream.println("Value of the stock: " + values.get(i));
      this.outStream.println();
      temp += values.get(i);
      i++;
    }
    this.outStream.println();
    this.outStream.println("Total Valuation : " + temp);
  }

  @Override
  public void displayCostBasis(Map<String, Map<String, Float>> newMap, List<Float> valuation,
                               String portfolioName) {

    this.outStream.println();
    this.outStream.println("Portfolio Name : " + portfolioName);
    int i = 0;
    Float temp = Float.valueOf(0);

    for (int k = 0; k < valuation.size(); k++) {
      temp += valuation.get(k);
    }
    this.outStream.println();
    this.outStream.println("Cost Basis of the portfolio : " + temp);
  }


  @Override
  public void displaySingleFlexiblePortValuation(Map<String, Map<String, Float>> hm,
                                                 List<Float> values, String portfolioName) {
    this.outStream.println();
    this.outStream.println("Portfolio Name : " + portfolioName);
    int i = 0;
    Float temp = Float.valueOf(0);
    for (Map.Entry<String, Map<String, Float>> entry : hm.entrySet()) {
      this.outStream.println("Buying Date : " + entry.getKey());
      Map<String, Float> addMap = entry.getValue();
      for (Map.Entry<String, Float> innerMap : addMap.entrySet()) {
        this.outStream.println("Stock = " + innerMap.getKey() +
                ", Quantity = " + innerMap.getValue());
        if (values.get(i) == 0.0) {
          this.outStream.println(innerMap.getKey() + " doesn't have a valuation on the " +
                  "entered date." + "Hence it's valuation is 0.0");
        }
        this.outStream.println("Value of the stock: " + values.get(i));
        this.outStream.println();
        temp += values.get(i);
        i++;
      }
    }
    this.outStream.println();
    this.outStream.println("Total Valuation : " + temp);
  }

  @Override
  public void showOutput(String output) {
    this.outStream.println(output);
  }

  @Override
  public void setOutStream(PrintStream outStream) {
    this.outStream = outStream;
  }

  @Override
  public void getPerformanceForMonth(String portfolioName, String startDate, String endDate,
                                     LinkedHashMap<String, Integer> map, float scale) {
    this.outStream.println("Performance of portfolio " + portfolioName + " from " + startDate
            + " to " + endDate);
    this.outStream.println();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      this.outStream.print(entry.getKey() + " : ");
      int star = entry.getValue();
      for (int i = 0; i < star; i++) {
        this.outStream.print("*");

      }
      this.outStream.println();

    }
    this.outStream.println();
    this.outStream.println("Scale: * = $" + scale);
  }


}

