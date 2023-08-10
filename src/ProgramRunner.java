import java.io.IOException;
import java.text.ParseException;

import controller.Controller;
import controller.ControllerImpl;
import controller.ControllerImpl2;
import model.User;
import model.UserImpl;
import view.View;
import view.View2;
import view.ViewImpl;
import view.ViewImpl2;

/**
 * A class from where the program executes.
 */
public class ProgramRunner {

  /**
   * Starter of the application, entry point of the application.
   * Used to create the model, view and controller and giving control to the controller.
   *
   * @param args array of string type.
   * @throws IOException    if input/output invokes an exception.
   * @throws ParseException if error is reached while parsing a string.
   */
  public static void main(String[] args) throws IOException, ParseException {
    /*

    User user = new UserImpl();
    View view = new ViewImpl();
    View2 view1 = new ViewImpl2();
    ControllerImpl2 c1 = new ControllerImpl2(user);
    c1.setView(view1);

    Controller controller = new ControllerImpl(user, view, System.in, System.out);
    controller.goController();

     */


    String viewType = (args[0]);
    if (viewType.equals("text")) {

      User user = new UserImpl();
      View view = new ViewImpl();
      Controller controller = new ControllerImpl(user, view, System.in, System.out);
      controller.goController();
    } else if (viewType.equals("gui")) {
      User user = new UserImpl();
      View2 view1 = new ViewImpl2();
      ControllerImpl2 c1 = new ControllerImpl2(user);
      c1.setView(view1);

    } else {
      System.out.println("Enter valid input");
    }
  }

}

