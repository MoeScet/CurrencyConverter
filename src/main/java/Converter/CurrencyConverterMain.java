package Converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class runs the automatic currency converter by calling Converter methods and TransactionProcessor methods 
 * Include readFromJSONFile method which converts user.json to User object
 * 
 * @author Thet Zin
 * @version 1.0
 * Date 25/02/2023
 */
public class CurrencyConverterMain {
	
    /**
     * Converts user.json file to User object array
     *
     * @return User array
     */
	public static User[] readFromJSONFile(String filename) throws StreamReadException, DatabindException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		File jsonFile = new File(filename);	
		User[] users = mapper.readValue(jsonFile, User[].class);
		return users;
	} 
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, InsufficientFundsException, UserNotFoundException, InvalidCurrencyException {
		
		User[] users = readFromJSONFile("src\\main\\resources\\users.json");
	
		/**
		 * TypeReference object specifies to deserialize the JSON into a Map<String, Currency> object
		 */
		ObjectMapper mapper = new ObjectMapper();
		File jsonFile = new File("src\\main\\resources\\fx_rates.json");
		TypeReference<Map<String, Currency>> typeRef = new TypeReference<Map<String, Currency>>() {};
		Map<String, Currency> currencyMap = mapper.readValue(jsonFile, typeRef);	
		
	
		String transactionFile = "src\\main\\resources\\transactions.txt";
		BufferedReader readerUser = new BufferedReader(new FileReader(new File(transactionFile)));
	    
		String line; 
		TransactionProcessor tranProc = new TransactionProcessor();
		tranProc.setUser(users);
		tranProc.setCurrencyMap((HashMap<String, Currency>) currencyMap);
		
		while((line = readerUser.readLine()) != null) {
			tranProc.executeTransaction(line);
			System.out.print("\n");
			
		tranProc.updateUsersFile(users);
		} 
		
	}
	
}
	
	

