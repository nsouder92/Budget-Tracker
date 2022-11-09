package zzSeniorProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;


import javax.swing.JOptionPane;

/**
 * @author Nicholas Souder
 * @class Users.java
 * 
 * This class is to create a user object that holds/ the user's expenses both in an array list
 * as well as storing them in a text file for the budget page.
 *
 */

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;	// Needed for serialization.
	
	
	String username;				// Stores the user's username.
	
	private Expense expense;		// Used to create an expense object to be inserted into the list.
	
	ArrayList<Expense> expenseList; // The user's expense list that holds expense objects.
	
	private File userFile;			// Text file that is used to both read and write expenses.
	FileWriter writer;
	
	/**
	 * Constructor method
	 * @param username
	 */
	public Users(String username) {
		
		this.username = username;
		
		// Instantiate the expense list upon creating the user.
		expenseList = new ArrayList<>();

		// Check for the user's text file, if there isn't one, create one.
		try {
			userFile = new File("user.txt");
			
			if (userFile.createNewFile()) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method that adds an expense to the user's expense list.
	 * @param expenseName: name of the user's expense
	 * @param category: category of the expense
	 * @param amount: amount of money the expense was
	 * @param date: date that the expense took place
	 */
	public void addExpense(String expenseName, String category, double amount, String date) {
		
		// Create the expense as a new expense, then add it to the expense list.
		expense = new Expense(expenseName, category, amount, date);
		expenseList.add(expense);
	}

	
	/**
	 * Method that deletes an expense from the user's expense list.
	 * @param expenseName: name of the user's expense
	 * @param date: date that the expense took place
	 */
	public void deleteExpense(String expenseName, String date) {
		
		// Loop through the expense list to find the matching expense and then remove it from the list.
		// Once found, display a message saying the expense was deleted and finish the loop.
		for (int i = 0; i < expenseList.size(); i++) {
			if (expenseList.get(i).getExpenseName().equals(expenseName) && expenseList.get(i).getDate().equals(date)) {
				expenseList.remove(i);
				JOptionPane.showMessageDialog(null, "Expense successfully deleted.");
				break;
			}
		}
	}
	
	/**
	 * Method that loads a file into the user's expense list.
	 * @param file: file that contains expense data to be loaded into the user's expense list.
	 */
	public void loadIntoList(File file) {
		
		// Create a reader to read the file.
		BufferedReader reader;
		
		// Load the file into the reader and read each line of text while splitting it up.
		// Add the split text as a new expense while also adding it to the expense list.
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String[] split;
			
			// Run lines through expense list
			while (line != null) {
				split = line.split(";");
				expense = new Expense(split[0], split[1], Double.parseDouble(split[2]), split[3]);
				expenseList.add(expense);
				
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}