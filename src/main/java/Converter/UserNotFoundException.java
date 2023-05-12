package Converter;

/**
  *Custom exception class for TransactionProcessor
  * Throws an exception when the user name does not exist in the array of User objects.
  * 
  * @author Thet Zin
  * @version 1.0
  * Date 25/02/2023
*/

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
