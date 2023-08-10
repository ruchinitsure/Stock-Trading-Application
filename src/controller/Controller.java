package controller;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface which acts as a controller.
 * Has a method which controls the flow of the program.
 */
public interface Controller {

  /**
   * Method which controls the flow of the program.
   *
   * @throws ParseException if the input cannot be parsed into an integer.
   * @throws IOException if input is invalid.
   */
  void goController() throws ParseException, IOException;
}
