package Converter;

import java.util.HashMap;

/**
 * Class contains user details such as name and wallet.
 * Methods to add funds to currencyTo and deduct funds from currencyFrom. 
 * 
 * 
 * @author Thet Zin
 * @version 1.0
 * Date 25/02/2023
 */
public class User {
	public String name;
	private HashMap<String, Double> wallet = new HashMap<String, Double>();
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Double> getWallet() {
		return wallet;
	}

	public void setWallet(HashMap<String, Double> wallet) {
		this.wallet = wallet;
	}
	
	/**
     * Adds funds to the currencyTo based on the amount passed
     * If currencyTo does not exist yet, it will be added to the wallet
     */
	public void depositWallet(String currency, double amount) {
        if (wallet.containsKey(currency)) {
            double currentAmount = wallet.get(currency);
            double newAmount = currentAmount + amount;
            wallet.put(currency, newAmount);
        } else {
            wallet.put(currency, amount);
        }
    }
	
	/**
     * Deducts funds from the currencyFrom based on the amount passed
     */
	public void withdrawWallet(String currency, double amount) {
		double currentAmount = wallet.get(currency);
        double newAmount = currentAmount - amount;
        wallet.put(currency, newAmount);
    }
	
}
