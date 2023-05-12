package Converter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class Handles execution of conversion operation and updating user wallet.
 * Methods excecuTransaction and updateUserFile included.
 * 
 * @author Thet Zin
 * @version 1.0
 * Date 25/02/2023
 */
public class TransactionProcessor {
	
	/**
     * Constructs a new `TransactionProcessor` object with the specified parameters.
     *
     * @param transactionLine the String Line from transaction.txt
     * @param users User object array
     * @param currencyMap the map of currencies and their exchange rates
     */
	@SuppressWarnings("unused")
	private String transactionLine;
	private User[] users;
	private HashMap<String, Currency> currencyMap;
	
	private static Logger logger = LogManager.getLogger(TransactionProcessor.class);
	
	public TransactionProcessor() {
	}
	
	/**
	 * Setter for user
	 * @param users
	 */
	public void setUser(User[] users) {
		this.users = users;
	}
	
	/**
	 * Setter for currencyMap 
	 * @param currencyMap
	 */
	public void setCurrencyMap(HashMap<String, Currency> currencyMap) {
		this.currencyMap = currencyMap;
	}
	
	/**
     * Execute transaction by calling Converter Object and doing checks such as
     *Username check, currency type in the wallet check  and balance check whether it is lower than amountToConvert
     */
	public void executeTransaction(String transactionLine) throws InvalidCurrencyException, UserNotFoundException {
	    try {
	        String[] transactionDetails = transactionLine.split(" ");

	        String userName = transactionDetails[0];
	        String currencyFrom = transactionDetails[1];

	        boolean userExists = false;
	        for (User user : users) {
	        	
	        	//Check if user exists
	            if (user.getName().equals(userName)) {
	                userExists = true;
	                
	                //Check currency type exists in the wallet
	                if (user.getWallet().get(currencyFrom) != null) {
	                	logger.info(userName + " has " + currencyFrom + " in the wallet");
	                    String currencyTo = transactionDetails[2];
	                    double amountToConvert = Double.parseDouble(transactionDetails[3]);

	                    // Check if user has enough currency to convert
	                    double userCurrency = user.getWallet().get(currencyFrom);
	                    if (amountToConvert > userCurrency) {
	                        throw new InsufficientFundsException("User " + userName + " does not have enough " + currencyFrom + " to perform transaction");
	                    }

	                    Converter converter = new Converter(currencyFrom, currencyTo, amountToConvert, currencyMap);
	                    double amountConverted = converter.Convert();
	                    logger.info(currencyFrom + " : " + amountToConvert + " converted to " + currencyTo + " : " + amountConverted);

	                    // Update user's wallet	
	                    //Deduct funds from currencyFrom amount
	                    user.withdrawWallet(currencyFrom, amountToConvert);     
	                    logger.info(currencyFrom + " : " + amountToConvert + " deducted from " +
	    	                    userName + "'s wallet");
	                    
	                    //Add funds to currencyTo amount
	                    user.depositWallet(currencyTo, amountConverted);
	                    logger.info(currencyTo + " : " + amountConverted + " deposited to " +
	    	                    userName + "'s wallet");    
	                } else {
	                    throw new InvalidCurrencyException(userName + " doesn't have " + currencyFrom + " in the wallet");
	                }
	                break;
	            }
	        }

	        if (!userExists) {
	        	throw new UserNotFoundException("User " + userName + " does not exist");
	        }
	    } catch (InsufficientFundsException e) {	      
	        logger.fatal(e.getMessage());
	        
	        
	    } catch (InvalidCurrencyException e) {
	    	logger.fatal(e.getMessage());
	    	
	    } catch (UserNotFoundException e) {
	    	logger.fatal(e.getMessage());
	    }
	    
	}
	
	/**
     * Update user data by converting User Objects to json file
     */
	public void updateUsersFile(User[] users) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File outputFile = new File("src\\main\\resources\\updatedUsers.json");
		objectMapper.writeValue(outputFile, users);
	}
}
