package Converter;

/**
  * Custom exception class for TransactionProcessor
  * Throws an exception when the currency user trying to convert is not present in the wallet
  * 
  * @author Thet Zin
  * @version 1.0
  * Date 25/02/2023
*/


@SuppressWarnings("serial")
public class InvalidCurrencyException extends Exception {
	public InvalidCurrencyException() {
        super("Currency Not Present In The Wallet.");
    }

    public InvalidCurrencyException(String message) {
        super(message);
    }
}
