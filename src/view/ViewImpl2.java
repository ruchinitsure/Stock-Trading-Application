package view;



//import java.awt.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import controller.Controller2;

/**
 * Class used to implement GUI which uses Java Swing.
 */

public class ViewImpl2 extends JFrame implements View2 {

  private final JButton cpBtn;
  private final JButton bsBtn;
  private final JButton ssBtn;
  private final JButton cbBtn;
  private final JButton vBtn;
  private final JButton lpBtn;

  private final JButton ipBtn;
  private final JButton dcBtn;

  /**
   * Constructor for view which initializes all the buttons required for
   * different methods.
   */

  public ViewImpl2() {

    setTitle("STOCKS");
    setSize(400, 500);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //mainScrollPane = new JScrollPane(mainPanel);
    add(mainPanel);

    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Choose any option from below:"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    JPanel createPortfolioPanel = new JPanel();
    createPortfolioPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(createPortfolioPanel);

    cpBtn = new JButton("Create a flexible portfolio");
    cpBtn.setActionCommand("Create portfolio");
    createPortfolioPanel.add(cpBtn);

    JPanel buyStocksPanel = new JPanel();
    buyStocksPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(buyStocksPanel);

    bsBtn = new JButton("Buy stocks");
    bsBtn.setActionCommand("Buy Stocks");
    buyStocksPanel.add(bsBtn);

    JPanel sellStocksPanel = new JPanel();
    sellStocksPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sellStocksPanel);

    ssBtn = new JButton("Sell stocks");
    ssBtn.setActionCommand("Sell Stocks");
    sellStocksPanel.add(ssBtn);

    JPanel findCostBasisPanel = new JPanel();
    findCostBasisPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(findCostBasisPanel);

    cbBtn = new JButton("Find cost basis of a portfolio");
    cbBtn.setActionCommand("Cost Basis");
    findCostBasisPanel.add(cbBtn);

    JPanel findValuePanel = new JPanel();
    findValuePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(findValuePanel);

    vBtn = new JButton("Find value of a portfolio");
    vBtn.setActionCommand("Value");
    findValuePanel.add(vBtn);

    JPanel loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadPortfolioPanel);

    lpBtn = new JButton("Load portfolio");
    lpBtn.setActionCommand("Load Portfolio");
    loadPortfolioPanel.add(lpBtn);


    JPanel investPanel = new JPanel();
    investPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(investPanel);

    ipBtn = new JButton("Invest in a portfolio");
    ipBtn.setActionCommand("Invest in a portfolio");
    investPanel.add(ipBtn);

    JPanel dollarCostPanel = new JPanel();
    dollarCostPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(dollarCostPanel);

    dcBtn = new JButton("Create portfolio using dollar-cost averaging");
    dcBtn.setActionCommand("Create portfolio using dollar-cost averaging");
    dollarCostPanel.add(dcBtn);

    setVisible(true);
  }

  @Override
  public void addFeatures(Controller2 features) {
    cpBtn.addActionListener(evt -> {
      try {
        features.createFlexible();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    bsBtn.addActionListener(evt -> {
      try {
        features.buyStockFlexiblePort();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    lpBtn.addActionListener(evt -> features.uploadFlexiblePort());
    ipBtn.addActionListener(evt -> {
      try {
        features.investPortfolio();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    dcBtn.addActionListener(evt -> {
      try {
        features.dollarCost();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    ssBtn.addActionListener(evt -> {
      try {
        features.sellStockFlexiblePort();
      } catch (ParseException | IOException e) {
        throw new RuntimeException(e);
      }
    });
    vBtn.addActionListener(evt -> features.valuationFlexiblePort());
    cbBtn.addActionListener(evt -> {
      try {
        features.costBasisFlexiblePort();
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    });

  }

  @Override
  public void showOutput(String message) {
    JOptionPane.showMessageDialog(null
            , message);
  }

  @Override
  public ArrayList<String> createPortfolioInput() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    Object[] message = {"Please enter your portfolio name", field1,
                        "Please enter the number of stocks you want", field2};

    int option = JOptionPane.showConfirmDialog(null, message
            , "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    String ticker = "";
    String stockQty = "";
    String qty = "";
    String date = "";
    String commissionFee = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      stockQty = field2.getText();
      /*ticker = field3.getText();
      qty = field4.getText();
      commissionFee = field5.getText();
      date = field6.getText();

       */

    }

    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(stockQty);
    /*output.add(ticker);
    output.add(qty);
    output.add(commissionFee);
    output.add(date);

     */

    return output;
  }


  @Override
  public ArrayList<String> investPortfolioInput() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();
    Object[] message = {"Please enter your portfolio name", field1,
                        "Please enter the amount you want to invest", field2,
                        "Please enter the number of stocks you want", field3,
                        "Please enter the commission fee", field4,
                        "Please enter date at which you want to buy (yyyy-MM-dd)" , field5};

    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    // String ticker = "";
    String stockQty = "";
    //String percentage = "";
    String date = "";
    String commissionFee = "";
    String amount = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      amount = field2.getText();
      stockQty = field3.getText();
      commissionFee = field4.getText();
      date = field5.getText();
      //ticker = field6.getText();
      //percentage = field7.getText();


    }
    if (pName.equals("") || stockQty.equals("") || commissionFee.equals("") || date.equals("")
            || amount.equals("")) {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(amount);
    output.add(stockQty);
    output.add(commissionFee);
    output.add(date);
    //output.add(ticker);
    //output.add(percentage);


    return output;
  }

  @Override
  public ArrayList<String> investHelper() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();
    Object[] message = {"Please enter ticker of stock you want to add to portfolio", field1,
                        "Please enter percentage of stocks you would like to invest", field2};

    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    String percentage = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      percentage = field2.getText();


    }
    if (pName.equals("") || percentage.equals("")) {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(percentage);


    return output;
  }

  @Override
  public ArrayList<String> dollarCostInput() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();
    JTextField field8 = new JTextField();
    JTextField field9 = new JTextField();
    Object[] message = {"Please enter your portfolio name", field1,
                        "Please enter the amount you want to invest", field2,
                        "Please enter the number of stocks you want", field3,
                        "Please enter the commission fee", field4,
                        "Please enter the start date at which you want to buy (yyyy-MM-dd)", field5,
                        "Please enter the end date at which you want to buy (yyyy-MM-dd)", field6,
                        "Please enter the time span", field7};

    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    //String ticker = "";
    String stockQty = "";
    //String percentage = "";
    String startDate = "";
    String endDate = "";
    String commissionFee = "";
    String amount = "";
    String time = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      amount = field2.getText();
      stockQty = field3.getText();
      commissionFee = field4.getText();
      startDate = field5.getText();
      endDate = field6.getText();
      time = field7.getText();
      //ticker = field8.getText();
      //percentage = field9.getText();


    }
    if (pName.equals("") || stockQty.equals("") || commissionFee.equals("") || startDate.equals("")
            || amount.equals("")) {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(amount);
    output.add(stockQty);
    output.add(commissionFee);
    output.add(startDate);
    output.add(endDate);
    output.add(time);
    //output.add(ticker);
    //output.add(percentage);


    return output;
  }

  @Override
  public ArrayList<String> buyStocksHelper() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    Object[] message = {"Please enter your portfolio name", field1,
                        "Please enter ticker of stock you want to add to portfolio", field2,
                        "Please enter quantity of stocks", field3,
                        "Please enter the commission fee", field4,
                        "Please enter date at which you want to buy (yyyy-MM-dd)", field5};

    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    String ticker = "";
    String qty = "";
    String date = "";
    String commissionFee = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      ticker = field2.getText();
      qty = field3.getText();
      commissionFee = field4.getText();
      date = field5.getText();

    }
    if (pName.equals("") || ticker.equals("") || qty.equals("") || commissionFee.equals("")
            || date.equals("")) {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(ticker);
    output.add(qty);
    output.add(commissionFee);
    output.add(date);

    return output;
  }

  @Override
  public ArrayList<String> createHelper() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    //JTextField field5 = new JTextField();
    //JTextField field6 = new JTextField();
    Object[] message = {"Please enter ticker of stock you want to add to portfolio", field1,
                        "Please enter quantity of stocks", field2,
                        "Please enter the commission fee", field3,
                        "Please enter date at which you want to buy (yyyy-MM-dd)", field4};

    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    String ticker = "";
    String qty = "";
    String date = "";
    String commissionFee = "";
    if (option == JOptionPane.OK_OPTION) {
      //pName = field1.getText();
      ticker = field1.getText();
      qty = field2.getText();
      commissionFee = field3.getText();
      date = field4.getText();

    }

    ArrayList<String> output = new ArrayList<>();
    //output.add(pName);
    output.add(ticker);
    output.add(qty);
    output.add(commissionFee);
    output.add(date);

    return output;
  }


  @Override
  public ArrayList<String> getInput() throws IllegalArgumentException {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    Object[] message = {"Please enter your portfolio name", field1,
                        "Please enter date in format (yyyy-MM-dd)", field2,};
    int option = JOptionPane.showConfirmDialog(null, message, "Enter all values",
            JOptionPane.OK_CANCEL_OPTION);
    String pName = "";
    String date = "";
    if (option == JOptionPane.OK_OPTION) {
      pName = field1.getText();
      date = field2.getText();
    }
    if (pName.equals("") || date.equals("")) {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    ArrayList<String> output = new ArrayList<>();
    output.add(pName);
    output.add(date);
    return output;
  }


  @Override
  public void displaySingleFlexiblePortValuation(Map<String, Map<String, Float>> hm,
                                                 List<Float> values, String portfolioName) {


    JTableExamples table = new JTableExamples(portfolioName, hm, values);
  }

  @Override
  public void displayCostBasis(Map<String, Map<String, Float>> newMap, List<Float> valuation,
                               String portfolioName) {

    //this.outStream.println();
    //showOutput("Portfolio Name : " + portfolioName);
    int i = 0;
    Float temp = Float.valueOf(0);

    for (int k = 0; k < valuation.size(); k++) {
      temp += valuation.get(k);
    }
    //this.outStream.println();
    showOutput("Cost Basis of the portfolio : " + temp);
  }

  /**
   * New class for adding table for displaying valuation.
   */

  public class JTableExamples {
    // frame
    JFrame frame1;
    // Table
    JTable table1;

    float sum;

    /**
     * Constructor for creation of table.
     *
     * @param portfolioName name of the portfolio.
     * @param hm            map containing date, symbol and quantity to be displayed.
     * @param values        list of valuations of all stocks in a portfolio.
     */
    JTableExamples(String portfolioName, Map<String, Map<String, Float>> hm, List<Float> values) {
      // Frame initialization
      frame1 = new JFrame();

      // Frame Title
      frame1.setTitle("Portfolio Valuation for " + portfolioName);

      // Data to be displayed in the JTable


      // Initializing the JTable
      table1 = new JTable(hm.size(), 4);
      table1.setBounds(30, 40, 200, 300);

      // adding it to JScrollPane
      JScrollPane sp = new JScrollPane(table1);
      frame1.add(sp);
      // Frame Size
      frame1.setSize(500, 200);
      // Frame Visible = true
      frame1.setVisible(true);
      String quantity = "";
      String stockSymbol = "";
      table1.getColumnModel().getColumn(0).setHeaderValue("Date");
      table1.getColumnModel().getColumn(1).setHeaderValue("Stock Symbol");
      table1.getColumnModel().getColumn(2).setHeaderValue("Quantity");
      table1.getColumnModel().getColumn(3).setHeaderValue("Value");

      //DecimalFormat df = new DecimalFormat("0.00");

      int row = 0;
      Float temp = Float.valueOf(0);
      int i = 0;
      sum = 0;
      for (Map.Entry<String, Map<String, Float>> entry : hm.entrySet()) {
        table1.setValueAt(entry.getKey(), row, 0);
        for (Map.Entry<String, Float> innerMapEntry : entry.getValue().entrySet()) {

          stockSymbol = innerMapEntry.getKey();
          quantity = innerMapEntry.getValue().toString();
          temp += values.get(i);
          table1.setValueAt(values.get(i).toString(), row, 3);
          i++;

        }
        table1.setValueAt(stockSymbol, row, 1);
        table1.setValueAt(quantity, row, 2);

        row++;
      }


      JLabel label = new JLabel(String.valueOf(temp));
      label.setText("The total valuation of portfolio is :" + String.valueOf(temp));
      frame1.add(label, BorderLayout.AFTER_LAST_LINE);
    }
  }
}