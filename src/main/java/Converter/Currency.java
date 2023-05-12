package Converter;

/**
  * Class contains each currency's details such as Code, AlphaCode, Numerical Code,
  * currency name, exchange rate from USD, date and exchange rate to USD
  * Getters and setters for all the attributes included. 
  * 
  * 
  * @author Thet Zin
  * @version 1.0
 * Date 25/02/2023
*/

public class Currency{
    private String code;
    private String alphaCode;
    private String numericCode;
    private String name;
    private double rate;
    private String date;
    private double inverseRate;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAlphaCode() {
		return alphaCode;
	}
	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}
	
	public String getNumericCode() {
		return numericCode;
	}
	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getInverseRate() {
		return inverseRate;
	}
	public void setInverseRate(double inverseRate) {
		this.inverseRate = inverseRate;
	}
}


	