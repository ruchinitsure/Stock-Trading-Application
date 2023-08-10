The design of our application allows MVC architecture.

We have separated our program into three packages : Model,controller and view.
All the model , controller and view classes have their respective interfaces.  


We have a file called ProgramRunner which is outside the packages which acts as a main function. It has user and view objects which are passed inside the controller object.
It acts as a starter code for the application.
The main function calls the goController() method of the controller.


Package model contains : User, Portfolio, Stock and AlphaVantage interfaces and their classes.

The User class has its own constructor which initializes the portfolio list.
The Portfolio class has a constructor where the stocklist is initialized.
The Stock class has a constructor where stock symbol and quantity are initialized for a inflexible portfolio. It has another constructor which initializes stock symbol, date and quantity for a flexible portfolio.

The earlier design is intact and we enhanced the design by creating a separate portfolio list for flexible portfolio without disturbing the inflexible portfolio list adhering to SOLID principles.

Each class has a single purpose, interfaces are segregated and client is exposed to only User interface, all those functionalities that are useful for him.

UserImpl class implements functionalities such as creating a portfolio, displaying a portfolio, getting the portfolio name, checking if the portfolio exists, calculating the valuation, reading from the xml file when a file is uploaded, writing to a xml file while creating the portfolio, getting the total number of portfolios for a inflexible portfolio. It also offers features including to create an inflexible portfolio, view one portfolio, view all portfolios, to upload xml file of the portfolio, buy stock, sell stocks, find the cost basis of a portfolio at a specified date and to get portfolio performance over a time for an inflexible portfolio. A fixed commission fee per transaction is considered for calculating the cost basis of a flexible portfolio. 


PortfolioImpl class has functions which add stocks in the stock list, get the total number of stocks, method that returns the stocklist, method that returns the name of the portfolio.


StockImpl has methods which return the stock symbol of the stock, the stock date and the stock quantity. It also has a method which sets the stock quantity which is used while selling the stocks in a flexible portfolio.


AlphaVantageImpl has methods which get the url and the closing value of the stock. For calculating the total valuation, these methods are called from the user. 


The controller  acts as a facilitator between model and view. The ControllerImpl contains a function named goController(). For the option selected by the user, switch case is implemented. 
The controller takes input from the view and then passes that input to the specific model. In this program the controller takes input from the user and then passes it to the user model where all the functions are implemented. The result from the model is again passed to the controller and from there to view.The controller takes inputs from the user and tells the model what to do and the view what to show. 



View contains all the functions which produce an output for the user on the console.The view is the part of the program that shows results to the user.
View incorporates all the features the earlier assignment too.

In this way, the code is segregated into model, view, controller packages adhering to the MVC architecture allowing us to isolate the entire behavior of the program into categories: actual functionality, user display and user interaction and delegation.

This design supports the earlier flexible portfolio as well as the flexible portfolio by enhancing the earlier design.


Below are some of the changes : 


1. For implementing GUI, added a new controller interface called Controller2 along with its class called ControllerImpl2.

2. Added another view interface for GUI with View2 as it's interface and Viewmpl2 as it's class. Used JavaSwing for implementing the GUI. 

3. Added user input and validation for commission fees.

4. For the user,stock, portfolio and alphavantage model we modified the earlier design by refactoring the interfaces. We added new methods to the existing interfaces rather than changing the earlier methods. We didn't create another interface and extend it to the existing interface as both the interfaces would have needed to co-exist, plus that approach wasn't that scalable as it would have been resulted into redundant code. If we had followed that approach we would need to add new methods to the interface again and again. 

So, we enhanced the design by refactoring the earlier interfaces without disturbing the earlier functionalities. 

5. Added two new features which help the user to invest in an existing portfolio by specifying a fixed amount, date and the percentages.

6. For a holiday, chose the  next available day to invest. 

7. The tests for added new functionalities of flexible portfolio have been done thoroughly in the test files. 