package zzSeniorProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Nicholas Souder
 * @class Login.java
 * 
 * This class is used to create a login page GUI to receive the user's input for logging in or signing up.
 *
 */

public class Login {

	private JFrame frmBudgetTrackerLogin;	// Frame used to create the GUI.
	private JTextField usernameTxtField;	// Input text field for the username input.
	private JTextField passwordTxtField;	// Input password text field for the password input.
	
	// Preset username and password for logging in.
	static String username = "nsouder";
	String password = "testpassword1";
	
	// Variables to store user's textfield inputs for username and password.
	String userInputUsername;
	String userInputPassword;
	
	// User object to create user upon 
	Users user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmBudgetTrackerLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Login page creation.
		frmBudgetTrackerLogin = new JFrame();
		frmBudgetTrackerLogin.setTitle("Expense Tracker Login");
		frmBudgetTrackerLogin.setBounds(100, 100, 596, 404);
		frmBudgetTrackerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBudgetTrackerLogin.getContentPane().setLayout(null);
		
		// Username label creation.
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(199, 227, 74, 25);
		frmBudgetTrackerLogin.getContentPane().add(usernameLabel);
		
		// Username text field input creation.
		usernameTxtField = new JTextField();
		usernameTxtField.setToolTipText("Input username here.");
		usernameLabel.setLabelFor(usernameTxtField);
		usernameTxtField.setBounds(283, 232, 96, 19);
		frmBudgetTrackerLogin.getContentPane().add(usernameTxtField);
		usernameTxtField.setColumns(10);
		
		// Password label creation.
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(199, 263, 74, 25);
		frmBudgetTrackerLogin.getContentPane().add(passwordLabel);
		
		// Password text field input creation.
		passwordTxtField = new JPasswordField();
		passwordTxtField.setToolTipText("Insert password here.");
		passwordLabel.setLabelFor(passwordTxtField);
		passwordTxtField.setColumns(10);
		passwordTxtField.setBounds(283, 268, 96, 19);
		frmBudgetTrackerLogin.getContentPane().add(passwordTxtField);
		
		// Title for the page creation.
		JLabel titleLabel = new JLabel("Budget Tracker Login Page");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		titleLabel.setBounds(172, 10, 322, 57);
		frmBudgetTrackerLogin.getContentPane().add(titleLabel);
		
		// Login button creation.
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that logs in the user if the correct username and password are inputted.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				
				// Store both the username and password inputs from the text fields.
				userInputPassword = passwordTxtField.getText();
				userInputUsername = usernameTxtField.getText();
				
				// Check to see if the input matches the preset username and password.
				// Create a user object and move to the main page after.
				if (password.equals(userInputPassword) && username.equals(userInputUsername)) {
					passwordTxtField.setText(null);
					usernameTxtField.setText(null);
					
					
					user = new Users(username);
					
					
					BudgetPage mainPage = new BudgetPage(user);
					BudgetPage.main(null);
					
					frmBudgetTrackerLogin.dispose();
				} 
				// If the information inputed is incorrect, display an error message.
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
					passwordTxtField.setText(null);
					usernameTxtField.setText(null);
				}
			}
		});
		
		loginButton.setToolTipText("Make sure your information is correctly typed.");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginButton.setBounds(294, 307, 85, 21);
		frmBudgetTrackerLogin.getContentPane().add(loginButton);
		
		// Sign up button creation.
		JButton signupButton = new JButton("Sign Up");

		signupButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that changes the username and password.
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				// Check for no input and display a message stating there can't be blank inputs.
				if (passwordTxtField.getText().equals("") || usernameTxtField.getText().equals("") || 
						usernameTxtField.getText().equals(" ")) {
					
					JOptionPane.showMessageDialog(signupButton, "Username and password cannot be blank.");
				} 
				// If there is input, change the username and password for the user and log in to the main page.
				else {
					password = passwordTxtField.getText();
					username = usernameTxtField.getText();
					
					
					user = new Users(username);
					
					
					BudgetPage mainPage = new BudgetPage(user);
					BudgetPage.main(null);
					
					frmBudgetTrackerLogin.dispose();
				}
			}
		});
		signupButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		signupButton.setBounds(188, 308, 85, 21);
		frmBudgetTrackerLogin.getContentPane().add(signupButton);
		
		// Exit button creation that exits the application.
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that exits the application.
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setBounds(409, 308, 85, 21);
		frmBudgetTrackerLogin.getContentPane().add(exitButton);
	}
}
