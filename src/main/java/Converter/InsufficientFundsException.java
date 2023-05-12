package Converter;

/**
  * Custom exception class for TransactionProcessor
  * Throws an exception when the amount user trying to convert is larger than the amount in the wallet
  * 
  * @author Thet Zin
  * @version 1.0
  * Date 25/02/2023
*/

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
