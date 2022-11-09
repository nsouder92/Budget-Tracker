package zzSeniorProject;

/**
 * @author Nicholas Souder
 * @class Expense.java
 * 
 * This class is used to create expense objects for the user to use for their expense list.
 *
 */

public class Expense {

	
	private String expenseName;	// Name of the user's expense
	private double amount;		// Amount of the expense
	private String category;	// Category of the expense
	private String date;		// Date the expense took place
	
	/**
	 * Constructor method
	 * @param expenseName: Name of the user's expense
	 * @param category: Category of the expense
	 * @param amount: Amount of the expense
	 * @param date: Date the expense took place
	 */
	public Expense(String expenseName, String category, double amount, String date) {
		this.expenseName = expenseName;
		this.amount = amount;
		this.category = category;
		this.date = date;
	}
	
	/**
	 * Default Constructor Method
	 */
	public Expense() {
	}

	/**
	 * Method to return the name of the expense.
	 * @return expense name
	 */
	public String getExpenseName() {
		return expenseName;
	}
	
	/**
	 * Method to set the name of the expense.
	 * @param name: Name of the expense
	 */
	public void setExpenseName(String name) {
		expenseName = name;
	}
	
	/**
	 * Method to return the amount of the expense.
	 * @return the amount of the expense
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Method that sets the amount of the expense.
	 * @param amount: amount of the expense
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Method that gets the category of the expense.
	 * @return category of the expense.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Method that sets the category of the expense.
	 * @param category: category of the expense
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Method that gets the date the expense took place.
	 * @return date the expense took place
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Method that sets the date the expense took place.
	 * @param date: date the expense took place
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
