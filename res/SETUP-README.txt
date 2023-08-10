SETUP - 


How to run the jar file : 
1. Navigate to the folder that has the jar file.
2. Type java -jar Test4.jar gui for gui view in the terminal and press enter.
3. Type java -jar Test4.jar text for text view in the terminal and press enter.
3. The program should run now.


STEPS TO FOLLOW : 


1. After running the jar file a menu will be displayed as below , allowing the user to choose the option he wants to select.
2. For creating a flexible portfolio, click on Create flexible button.
3. A pop-up will appear asking the user to enter the portfolio name and number of stocks he wants to buy. Enter "test" and 1 respectively.
4. After inputting this, a new pop up will appear asking the user to enter the ticker of the stock, quantity, date and commission fees.
5. Enter the stock symbol for the first stock. 
6. Enter "AAPL" here.
7. It will then ask for the quantity of the stock - Enter 10.
8. It will then ask for the date. Enter "2001-09-18" as the buying date of AAPL stock.
9. Enter 100 as the commission fee. After inputting valid inputs, xml file will be created.
10. After that click on "Buy stock" button.
11. enter the portfolio name as : "test".
12. Enter stock symbol as "TSLA".
13. Enter stock quantity : "20".
14. Enter the stock date as "2010-06-30".
15. Enter 15 as the commission fee.
16. This stock will then be added to the portfolio test.
17. Click on "Sell stocks" button.
18. Enter stock symbol as "AAPL".
19. Enter stock quantity : "5".
20. Enter the stock date as "2011-06-30".
21. Enter 20 as the commission fee.
22. Stock quantity of AAPL then will be 5.
23. Click on the "Load portfolio" button.
24. A list of files in the current working directory will be visible. Select test.xml file and click on open. Portfolio successfully uploaded message will pop up.
25. Click on "invest portfolio" button.
26. Enter portfolio name as test.
27.Enter the amount to invest as 2000.
28.Enter number of stocks as 1.
29.Enter commission fee as 150
30.Enter the date as 2015-01-03
31.Enter stock symbol as GOOGL
32.Enter percentage 100
33.After inputting all the valid inputs, investment strategy will be applied on the portfolio.
34.Click on "Dollar Cost averaging" button.
35.Enter portfolio name as test.
36.Enter the amount to invest as 2000.
37.Enter number of stocks as 1.
38.Enter commission fee as 150
39.Enter start date as 2003-09-18
40.Enter end date as 2004-01-03
41.Enter ticker as AAL
42.Enter percentage 100
43.Dollar cost strategy will be applied to the portfolio.
44.Click on "Cost Basis" button.
45.Enter the portfolio name as test
46.Enter the date as 2003-10-18
47.Enter the time span as 50 
48.Cost basis of the portfolio will be displayed.
49.Click on "Valuation of the portfolio" button.
50.Enter portfolio name as test
51.Enter the date as 2013-01-04
52.Portfolio valuation will be displayed in the form of a table.



Limitations: 
1. We have considered only 105 NASDAQ stocks. Stocks are listed below:
           "AAL", "AAPL", "ADBE", "ADI", "ADP", "ADSK", "AKAM", "ALXN", "AMAT",
 "AMGN", "AMZN", "ATVI", "AVGO", "BBBY", "BIDU", "BIIB", "BMRN", "CA","CELG", "CERN", "CHKP", "CHTR", "CMCSA", "COST", "CSCO", "CSX", "CTRP", "CTSH", "CTXS", "DISCA", "DISCK", "DISH", "DLTR", "EA", "EBAY", "ESRX", "EXPE", "FAST", "FB", "FISV", "FOX", "FOXA", "GILD", "GOOGL" , "HSIC", "ILMN", "INCY", "INTC", "INTU", "ISRG", "JD", "KHC", "LBTYA" , "LBTYK", "LLTC", "LRCX", "LVNTA", "MAR", "MAT", "MCHP”, "MDLZ", "MNST", "MSFT", "MU", "MXIM", "MYL", "NCLH", "NFLX", "NTAP", "NTES" , "NVDA", "NXPI", "ORLY", "PAYX", "PCAR", "PCLN", "PYPL", "QCOM", "QVCA", "REGN", "ROST", "SBAC", "SBUX", "SIRI", "SRCL", "STX", "SWKS", "SYMC", "TMUS", "TRIP", "TSCO", "TSLA", "TXN", "ULTA", "VIAB", "VOD", "VRSK", "VRTX", "WBA", "WDC", "WFM", "XLNX", "YHOO", "XRAY", "NDX"

 Any other stock symbols entered by the user will be invalid for this program.
2. Only .xml files should be uploaded by the user. Any other file if uploaded will give an error.
3. User can create portfolios of unique names only.