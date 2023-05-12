package Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Class handles currency conversion. 
 * Method Convert included.
 * 
 * @author Thet Zin
 * @version 1.0
 * Date 25/02/2023
 */

public class Converter {
	/**
     * Constructs a new `Converter` object with the specified parameters.
     *
     * @param currencyFrom the currency to convert from
     * @param currencyTo the currency to convert to
     * @param amountToConvert the amount of currency to convert
     * @param currencyMap the map of currencies and their exchange rates
     */
    private String currencyFrom;
    private String currencyTo;
    private double amountToConvert;
    private  HashMap<String, Currency> currencyMap;
    
    private double convertedAmount;
    
    
    public Converter(String currencyFrom, String currencyTo, double amountToConvert, Map<String, Currency> currencyMap) {
    	this.currencyFrom = currencyFrom;
    	this.currencyTo = currencyTo;
    	this.amountToConvert = amountToConvert;
    	this.currencyMap = (HashMap<String, Currency>) currencyMap;
    }
	
    /**
     * Converts the currency based on the currencies and amount specified in the constructor.
     *
     * @return the converted amount
     */
    public double Convert() {
        if (currencyFrom.equals(currencyTo)) {
            convertedAmount = amountToConvert;
            } 
        
        else if (currencyFrom.equals("usd")) {
            convertedAmount = amountToConvert * currencyMap.get(currencyTo).getRate();
            }
        
        else if (currencyTo.equals("usd")) {
            convertedAmount = amountToConvert * currencyMap.get(currencyFrom).getInverseRate();
            } 
        
        else {
        	 convertedAmount = amountToConvert * currencyMap.get(currencyFrom).getInverseRate() * currencyMap.get(currencyTo).getRate();
        	 }
        return convertedAmount;
    }
    
}
