


Following are the features of our program : 


A) INFLEXIBLE PORTFOLIO

1. This program works for a single user. Multiple users are not supported by this program.


1. Create Portfolio : A portfolio can be created by the user. The user should provide a portfolio name for creation. The user must add stocks by inputting a valid ticker (stock symbol) and should indicate the quantity of the stock. If any invalid ticker or quantity is inputted, an error message will be displayed and the user will have to re-enter the valid ticker or quantity. The user has the option to create numerous portfolios, each with a distinctive name. The portfolio cannot be changed once it has been created. After creation of the portfolio, all the portfolio details will be saved in a xml file having the portfolio name. For one portfolio, one xml file will be created.


2. View all portfolios : This feature allows the user to view all the details of the portfolio such as name of the portfolio, stock name and quantity of each stock. If the user has created multiple portfolios, he/she can view details of each portfolio at a glance. If the user tries to view the portfolios before creating them, a message will be displayed telling the user to create portfolios first.


3. View one portfolio : By entering the portfolio name, the user can access the details of the portfolio. The user will be able to view the portfolio if it is available. If the portfolio does not already exist, a message informing the user that the portfolio with that name does not exist will be displayed. If the user attempts to access the portfolio before creating it, a message informing the user that a portfolio must first be created will be displayed.


4. Get the valuation of a portfolio at a certain date : This functionality helps the user to get the total value of his/her portfolio at a certain date. For getting the stock data our program is using the Alpha Vantage API which returns data in csv format. This program considers  the closing value of the stock for calculating the total valuation. The user has to enter the date in yyyy-mm-dd format, if the date is not in proper format i.e if the user enters date in mm-dd-yyyy or dd-mm-yyyy format or if the month exceeds 12 then an error message will be displayed and the user will have to re-enter the date. If for a certain date if a particular stock value is not present, then the value of that stock will be 0. 


5. Upload portfolio : This functionality allows the user to upload his/her portfolio. The user can only upload a xml file. This file should contain the name of the portfolio and stock details. After uploading, the user can view portfolios and can get the valuation of the portfolio. 


6. Exit: This functionality lets the user end the program.



B) FLEXIBLE PORTFOLIO

1. Create Flexible Portfolio : A user can create a flexible portfolio by providing a portfolio name. The user can add stocks by inputting a valid ticker (stock symbol), number of stocks (quantity) and the buying date of each stock. If any invalid ticker or quantity or date is given by the user, an error message will be displayed and the user will have to re-enter. The user can create multiple portfolios each with an unique name. Since it is a flexible portfolio, it can be modified later. The user can add or remove stocks from the portfolio later by providing a date. After the portfolio has been created, the portfolio will be saved in a xml file having the portfolio name. Each portfolio will be saved into one xml file. 

2. View one flexible portfolio : This feature allows the user to view all the details of all portfolios created by the user at any specific date. If the user enters the portfolio name which doesn't exist, an error message will be displayed that the portfolio with that name doesn't exist. If the user enters an invalid date, he will be asked to re-enter the date. If the user attempts to access the portfolio before creating it, a message will be displayed that a portfolio must first be created to display it. If the user enters valid portfolio name and date, all the details of the portfolio including the portfolio name, each stock symbol, it's buying date and quantity will be displayed.


3. View all flexible portfolios: The user gets to view all the details of all the portfolios he/she created till date. The details will include all the portfolios, and for each portfolio : portfolio name, stock symbol, buying date and quantity. If the user has created multiple portfolios, he/she can view details of each portfolio at a glance. If the user tries to view all portfolios before creating any portfolio, he/she will be notified to create a portfolio first.


3. Buy stock into a flexible portfolio : The user can purchase specific number of shares of a specific stock on a specified date. The bought stocks will then be added to the portfolio. For buying a stock, the user will be asked to enter the portfolio name, stock symbol, the number of shares and the date. All these 3 inputs should be valid, otherwise the user will be asked to enter them again. If the user enters a portfolio name which doesn't exist, the user will be displayed an error message. After buying the stock, the xml file of that portfolio will be automatically updated. 


4. Sell stock from a flexible portfolio : This feature allows the user to sell a specific number of shares of a specific stock on a specified date from a given portfolio. The user is allowed to sell the stock if he has bought it earlier and it is present in the given portfolio name , and if that stock has quantity greater than the quantity the user wants to sell. If the user tries to sell stocks before he buys them, an error message will be displayed. After selling stocks, the xml file of that portfolio will be automatically updated.


5. Upload XML File: The user can upload a xml file representing one portfolio. This functionality allows the user to upload his/her portfolio. The application only supports uploading a xml file. This file should contain the name of the portfolio and stock details (buying date, stock symbol, stock quantity) . After uploading, the user can view portfolios , can get the valuation of the portfolio, buy or sell stocks from a portfolio, calculate cost basis and get performance of a portfolio over time.


6. Total Valuation  : This feature allows the user to determine the value of a portfolio on a specific date. The value for a portfolio before the date of its first purchase would be 0, since each stock in the portfolio now was purchased at a specific point in time. The user can calculate the total valuation for any portfolio at any point of time. The user should enter portfolio name which exists, valid date ; otherwise he will be displayed an error message. If the user creates a portfolio on day 1 and sells some stocks on day 5 ; and tries to get valuation for day 3 he will be displayed the valuation before selling stocks since on day 3 he wouldn't have sold the stocks.For getting the stock data our program is using the Alpha Vantage API which returns data in csv format. This program considers  the closing value of the stock for calculating the total valuation. The user has to enter the date in yyyy-mm-dd format, if the date is not in proper format i.e if the user enters date in mm-dd-yyyy or dd-mm-yyyy format or if the month exceeds 12 then an error message will be displayed and the user will have to re-enter the date. If for a certain date if a particular stock value is not present, then the value of that stock will be 0. 

7. Cost basis : It determines the total amount of money invested in a portfolio by a specific date. This would include all the purchases made in that portfolio till that date. The user can find the cost basis of any portfolio he created / uploaded. This function also takes care of all the date possibilities. If the user enters invalid portfolio name, invalid date , he will be notified using an error message.

8. Portfolio Performance Over Time : This feature allows the user to visualize the performance of the portfolio over time at a glance in the form of a horizontal bar chart. The user can get performance for a timespan of days, months or years. If the timestamps are dates then the value is computed at the end of that day. If the timestamps are months then the value is computed at the last working day of that month. If the timestamp are years then the value is computed on the last working day of that year. The plot displays an absolute scale. The number of lines is derived by the timespan the user provides. It has no more than 30 lines at any time and no more than 50 asterisks on each line. This feature creates a bar chart for a specific portfolio and a specified time range. 



C) GUI
1. Create flexible Portfolio : A user can create a flexible portfolio by clicking on the options of create portfolio on the screen. The user has to enter the portfolio name and number of stocks to begin with. In another pop-up 
the user will be asked to enter a valid ticker symbol, quantity, date and commission fee. Once all the inputs are valid a portfolio will be created and will be saved as a XML file having the file name as the portfolio name.

2. Buy Stocks : This option lets the user buy stocks into an existing portfolio. Once the user clicks on "Buy Stocks" option, the user will be asked to specify the portfolio name, ticker of the stock, quantity, date and commission fee. If any field is invalid, the user will have to re-enter the inputs till a valid input is entered. Once all valid inputs are entered the xml file of the portfolio will be automatically updated.

3. Sell Stocks : This option lets the user sell stocks from an existing portfolio. After clicking on "Sell Stocks" option, the user will be asked to enter the portfolio name, the ticker of the stock he wants to sell, quantity, date and commission fee.The user is allowed to sell the stock if he has bought it earlier and it is present in the given portfolio name , and if that stock has quantity greater than the quantity the user wants to sell. If the user tries to sell stocks before he buys them, an error message will be displayed. After inputting all the valid inputs, the XML file will be updated with the updated quantity. 

4. Upload XML File : After clicking on "Load Portfolio" the user can choose any xml file in the current directory from the drop down menu. Once the user selects the desired xml file and clicks on open, the xml file
Will be successfully loaded. If the user tries to enter any file other than xml file, a message saying "Couldn't upload the file" will be displayed.

5. 6. Total Valuation  : This feature allows the user to determine the value of a portfolio on a specific date. The value for a portfolio before the date of its first purchase would be 0, since each stock in the portfolio now was purchased at a specific point in time. The user can calculate the total valuation for any portfolio at any point of time. The user should enter portfolio name which exists, valid date ; otherwise he will be displayed an error message. If the user creates a portfolio on day 1 and sells some stocks on day 5 ; and tries to get valuation for day 3 he will be displayed the valuation before selling stocks since on day 3 he wouldn't have sold the stocks.For getting the stock data our program is using the Alpha Vantage API which returns data in csv format. This program considers  the closing value of the stock for calculating the total valuation. The user has to enter the date in yyyy-mm-dd format, if the date is not in proper format i.e if the user enters date in mm-dd-yyyy or dd-mm-yyyy format or if the month exceeds 12 then an error message will be displayed and the user will have to re-enter the date. If for a certain date if a particular stock value is not present, then the next available date is chosen as the input date.

6. Cost basis : It determines the total amount of money invested in a portfolio by a specific date. This would include all the purchases made in that portfolio till that date. The user can find the cost basis of any portfolio he created / uploaded. This function also takes care of all the date possibilities. If the user enters invalid portfolio name, invalid date , he will be notified using an error message.

7. Investment Strategy: After clicking on "Investment strategy" the user can specify the strategy he wants to implement on an existing portfolio.The user will be asked to enter the portfolio name, the ticker of the Stock he wants to buy, date on which he wants to apply this strategy, commission fee, amount the user wishes to enter and finally the percentage of the amount he wants to invest in. If the percentages don't add up to 100, an error message will be displayed and the user will have to re-enter the details. After inputting all the valid inputs, the user can successfully invest.

8. Dollar Cost Averaging: After clicking on "Investment strategy" the user can specify the strategy he wants to implement on an existing portfolio.The user will be asked to enter the portfolio name, the ticker of the Stock he wants to buy, start date on which he wants to apply this strategy and an end date till which he wants to keep on investing. The user should also specify the time span , commission fee, amount the user wishes to enter and finally the percentage of the amount he wants to invest in. If the user inputs null as the end date, the end date will be set to a future date automatically. If the percentages don't add up to 100, an error message will be displayed and the user will have to re-enter the details. After inputting all the valid inputs, the user can successfully invest. After the successful application of the strategy, the xml will be updated and now the user can calculate cost basis and valuation for that portfolio.
