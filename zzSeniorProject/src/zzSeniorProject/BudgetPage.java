package zzSeniorProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

/**
 * @author Nicholas Souder
 * @class BudgetPage.java
 * 
 * This class is used to create the main page of the application.
 * This application shows the user their expense information in a table as well as
 * their highest expenses and options to add or delete expenses.
 *
 */

public class BudgetPage {

	private JFrame frame;		// Frame used to create the page window.
	private static Users user;	// User object to retrieve expense list.
	private JTable table;		// Table to show user's expenses in table format.
	
	// Set all highest expenses to 0, as their needs to be a check for highest.
	double highestHousing = 0;
	double highestTrans = 0;
	double highestFood = 0;
	double highestUtil = 0;
	double highestIns = 0;
	double highestMedical = 0;
	double highestPersonal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetPage window = new BudgetPage(user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BudgetPage(Users user) {
		this.user = user;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Formats the amount of expenses to be two decimal places.
		NumberFormat amountFormat = new DecimalFormat("#0.00");
		
		// create user expense list load into list
		File file = new File("user.txt");
		user.loadIntoList(file);
		
		// loop through expense list and find highest expenses.
		for (int i = 0; i < user.expenseList.size(); i++) {
			
			if (user.expenseList.get(i).getCategory().equals("Housing")) {
				if (highestHousing < user.expenseList.get(i).getAmount()) {
					highestHousing = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Transportation")) {
				if (highestTrans < user.expenseList.get(i).getAmount()) {
					highestTrans = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Food")) {
				if (highestFood < user.expenseList.get(i).getAmount()) {
					highestFood = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Utilities")) {
				if (highestUtil < user.expenseList.get(i).getAmount()) {
					highestUtil = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Insurance")) {
				if (highestIns < user.expenseList.get(i).getAmount()) {
					highestIns = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Medical")) {
				if (highestMedical < user.expenseList.get(i).getAmount()) {
					highestMedical = user.expenseList.get(i).getAmount();
				}
				
			} else if (user.expenseList.get(i).getCategory().equals("Personal")) {
				if (highestPersonal < user.expenseList.get(i).getAmount()) {
					highestPersonal = user.expenseList.get(i).getAmount();
				}
			}
		}
		
		// Create the window
		frame = new JFrame();
		frame.setBounds(100, 100, 815, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Set the initial panel which is in the north section of the layout.
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		// Inserted into the north panel
		JLabel expensePageLabel = new JLabel("Expenses Page");
		expensePageLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		panel_1.add(expensePageLabel);
		
		// Create the west panel that holds the highest expenses.
		JPanel westPanel = new JPanel();
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setPreferredSize(new Dimension(300,500));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		JLabel blankLabel2 = new JLabel(" ");
		blankLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(blankLabel2);
		
		JLabel blankLabel2_1 = new JLabel(" ");
		blankLabel2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(blankLabel2_1);
		
		// Display the user's username.
		JLabel usernameLabel = new JLabel("User: " + user.username);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(usernameLabel);
		
		JLabel blankLabel = new JLabel(" ");
		blankLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		westPanel.add(blankLabel);
		
		JLabel highestExpensesLabel = new JLabel("Highest Expenses By Category");
		highestExpensesLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		westPanel.add(highestExpensesLabel);
		
		////////////////////////////////////////////////////////
		// Highest expenses sections, use math round function to round up
		// on amounts with higher than 2 decimal places.
		JLabel housingLabel = new JLabel("Housing: $" + Math.round(highestHousing * 100.0) / 100.0);
		housingLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(housingLabel);
		
		JLabel transportationLabel = new JLabel("Transportation: $" + Math.round(highestTrans * 100.0) / 100.0);
		transportationLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(transportationLabel);
		
		JLabel foodLabel = new JLabel("Food: $" + Math.round(highestFood * 100.0) / 100.0);
		foodLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(foodLabel);
		
		JLabel utilitiesLabel = new JLabel("Utilities: $" + Math.round(highestUtil * 100.0) / 100.0);
		utilitiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(utilitiesLabel);
		
		JLabel insuranceLabel = new JLabel("Insurance: $" + Math.round(highestIns * 100.0) / 100.0);
		insuranceLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(insuranceLabel);
		
		JLabel medicalLabel = new JLabel("Medical: $" + Math.round(highestMedical * 100.0) / 100.0);
		medicalLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(medicalLabel);
		
		JLabel personalLabel = new JLabel("Personal: $" + Math.round(highestPersonal * 100.0) / 100.0);
		personalLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		westPanel.add(personalLabel);
		
		////////////////////////////////////////////////////////
		
		JLabel blankLabel_1 = new JLabel(" ");
		blankLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		westPanel.add(blankLabel_1);
		
		JLabel blankLabel_2 = new JLabel(" ");
		blankLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		westPanel.add(blankLabel_2);
		
		JLabel blankLabel_3 = new JLabel(" ");
		blankLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		westPanel.add(blankLabel_3);
		
		JLabel blankLabel_4 = new JLabel(" ");
		blankLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		westPanel.add(blankLabel_4);
		
		// Set up the east panel with the table.
		JPanel eastPanel = new JPanel();
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setPreferredSize(new Dimension(500,500));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		
		// Set up the date sort radio button to sort by date.
		JRadioButton dateButton = new JRadioButton("       Date Sort");
		dateButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that sorts the table by date column.
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				// Create a table row sorter to set the date column as the sort option.
				TableRowSorter<TableModel> sorter 
			    = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(sorter);
			
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		dateButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		dateButton.setVerticalAlignment(SwingConstants.TOP);
		dateButton.setHorizontalAlignment(SwingConstants.RIGHT);
		eastPanel.add(dateButton);
		
		// Set up the category sort radio button that sorts by category.
		JRadioButton categoryButton = new JRadioButton(" Category Sort");
		categoryButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that sorts the rows by the category column.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				
				// Create a table row sorter that sorts by category.
				TableRowSorter<TableModel> sorter 
			    = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(sorter);
			
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		categoryButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		eastPanel.add(categoryButton);
		
		// Create a button group to group category and date buttons together
		// so only one can be selected.
		ButtonGroup categoryGroup = new ButtonGroup();
		categoryGroup.add(dateButton);
		categoryGroup.add(categoryButton);

		// Set up the table model with the named columns.
		String [] columnNames = {"Expense Name", "Category", "Amount", "Date"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			/**
			 * Method that sets cells to not be editable.
			 */
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		// Load the file into the table.
		String line = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("user.txt"));
	        while((line = reader.readLine()) != null) {
	        	model.addRow(line.split(";")); 
	        }
	        reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		table = new JTable(model);
		
		// Lets you sort by any column as an added bonus.
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		eastPanel.add(scrollPane);
		
		// Set up add expense button to east panel.
		JButton addButton = new JButton(" Add Expense  ");
		addButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that creates a separate window for user to add an expense through inputs.
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				// Set up a separate window to pop up for user input.
				JPanel p = new JPanel(new BorderLayout(5,5));

		        JPanel labels = new JPanel(new GridLayout(0,1,4,4));
		        labels.add(new JLabel("Expense Name", SwingConstants.TRAILING));
		        labels.add(new JLabel("Category", SwingConstants.TRAILING));
		        labels.add(new JLabel("Amount", SwingConstants.TRAILING));
		        labels.add(new JLabel("Date", SwingConstants.TRAILING));
		        p.add(labels, BorderLayout.LINE_START);

		        // Set layout of the input fields.
		        JPanel controls = new JPanel(new GridLayout(0,1,4,4));
		        JTextField expense = new JTextField("");
		        controls.add(expense);
		        
		        // Create a separate combo box for the user to select a category
		        // without having to input one themselves.
		        String[] options = {"Housing", "Transportation", "Food", "Utilities", "Insurance", 
		        					"Medical", "Personal"};
		        
		        JComboBox<String> comboBox = new JComboBox<String>();
		        JTextField categoryText = new JTextField(15);
		        
		        // Add each category to the combo box.
		        for (int i = 0; i < options.length; i++) {
		        	comboBox.addItem(options[i]);
		        }
		        categoryText.setEditable(false);
		        
		        controls.add(comboBox);
		        
		        // Create the amount field input field and add it to the controls.
		        JFormattedTextField amountField = new JFormattedTextField(amountFormat);
		        amountField.setColumns(10);

		        controls.add(amountField);
		        
		        // Set up the date format so the user has to specifically input 
		        // mm/dd/yyyy.
		        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		        JFormattedTextField date = new JFormattedTextField(dateFormat);
		        
		        controls.add(date);
		        
		        p.add(controls, BorderLayout.CENTER);

		        // Initialize the window for user input.
		        int input = JOptionPane.showOptionDialog(frame, p, "Add Expense", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		        
		        // If cancel is selected, do nothing and close out the window.
		        if (input == JOptionPane.CANCEL_OPTION) {
		        	
		        } 
		        // If OK is selected, retrieve all of the user's input and write it both to the text
		        // file and send it to the user's expense list.
		        else if (input == JOptionPane.OK_OPTION) {
		        	
		        	// regex string for date format.
		        	String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
		        	
		        	Pattern pattern = Pattern.compile(regex);
		            
		        	//Matching the compiled pattern in the String
		            Matcher matcher = pattern.matcher(date.getText());
		            boolean dateMatches = matcher.matches();
		        	
		        	// If any of the inputs are blank, close the window and try again.
		        	if (expense.getText().equals("") || amountField.getText().equals("") || !dateMatches) {
		        		
		        		JOptionPane.showMessageDialog(p, "Invalid inputs, please try again.");
		        	} 
		        	// If the inputs are fine, create a new object of the user's input to add to the table,
		        	// then write the expense to the user's text file.
		        	else {
		        		
		        		// Create the expense object and add it to the table.
		        		Object[] expenseToAdd = new Object[] {expense.getText(), comboBox.getSelectedItem(), amountField.getText(), date.getText()};
				        model.addRow(expenseToAdd);
				        
				        // Write the expense to the text file.
				        try {
				        	FileWriter writer = new FileWriter("user.txt", true);
				        	BufferedWriter bw = new BufferedWriter(writer);
				        	
				        	bw.write(expense.getText() + ";");
				        	bw.write(comboBox.getSelectedItem() + ";");
				        	bw.write(amountField.getText() + ";");
				        	bw.write(date.getText() + ";");
				        	bw.newLine();
				        	bw.close();
				        } catch (IOException e) {
				        	e.printStackTrace();
				        }
				        
				        // Go through the selected category to check for a new highest expense
				        // in a given category, then set that highest expense.
				        if (comboBox.getSelectedItem().equals("Housing")) {
				        	if (Double.parseDouble(amountField.getText()) > highestHousing) {
				        		highestHousing = Double.parseDouble(amountField.getText());
				        		
				        		housingLabel.setText("Housing: $" + Math.round(highestHousing * 100.0) / 100.0);
				        	}
				        	
				        } else if (comboBox.getSelectedItem().equals("Transportation")) {
				        	if (Double.parseDouble(amountField.getText()) > highestTrans) {
				        		highestTrans = Double.parseDouble(amountField.getText());
				        		
				        		transportationLabel.setText("Transportation: $" + Math.round(highestTrans * 100.0) / 100.0);
				        	}
				        } else if (comboBox.getSelectedItem().equals("Food")) {
				        	
				        	if (Double.parseDouble(amountField.getText()) > highestFood) {
				        		highestFood = Double.parseDouble(amountField.getText());
					        	
					        	foodLabel.setText("Food: $" + Math.round(highestFood * 100.0) / 100.0);
				        	}
				        	
				        } else if (comboBox.getSelectedItem().equals("Utilities")) {
				        	if (Double.parseDouble(amountField.getText()) > highestUtil) {
				        		highestUtil = Double.parseDouble(amountField.getText());
					        	
					        	utilitiesLabel.setText("Utilities: $" + Math.round(highestUtil * 100.0) / 100.0);
				        	}
				        	
				        } else if (comboBox.getSelectedItem().equals("Insurance")) {
				        	
				        	if (Double.parseDouble(amountField.getText()) > highestIns) {
				        		highestIns = Double.parseDouble(amountField.getText());
					        	
					        	insuranceLabel.setText("Insurance: $" + Math.round(highestIns * 100.0) / 100.0);
				        	}
				        	
				        } else if (comboBox.getSelectedItem().equals("Medical")) {
				        	
				        	if (Double.parseDouble(amountField.getText()) > highestMedical) {
				        		highestMedical = Double.parseDouble(amountField.getText());
					        	
					        	medicalLabel.setText("Medical: $" + Math.round(highestMedical * 100.0) / 100.0);
				        	}
				        	
				        } else if (comboBox.getSelectedItem().equals("Personal")) {
				        	
				        	if (Double.parseDouble(amountField.getText()) > highestPersonal) {
				        		highestPersonal = Double.parseDouble(amountField.getText());
					        	
					        	personalLabel.setText("Personal: $" + Math.round(highestPersonal * 100.0) / 100.0);
				        	}
				        }
		        	} 
		        }
		    }
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		eastPanel.add(addButton);
		
		
		// Create the delete expense button.
		JButton deleteButton = new JButton("Delete Expense");
		deleteButton.addActionListener(new ActionListener() {
			
			/**
			 * Method that deletes the selected row in the table from the table and text file.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				
				// Check if the table row is not deselected.
				if (table.getSelectedRow() != -1) {
					
					// Convert the index to model to get the proper selected row.
					// Needed especially after sorting the rows.
					int modelIndex = table.convertRowIndexToModel(table.getSelectedRow());
					DefaultTableModel model = (DefaultTableModel)table.getModel();

					// Delete the row.
					model.removeRow(modelIndex);
					
					// After removing row, write to file
					try {
						FileWriter fwOb = new FileWriter("user.txt", false); 
				        PrintWriter pwOb = new PrintWriter(fwOb, false);
				        pwOb.flush();
				        pwOb.close();
				        fwOb.close();
						
						BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
						
						for (int i = 0; i < model.getRowCount(); i++) {
							for (int j = 0; j < model.getColumnCount(); j++) {
								writer.write(Objects.toString(model.getValueAt(i, j), "") + ";");
							}
							writer.write(System.lineSeparator());
						}
						writer.flush();
						writer.close();
						
						// Now that the row is removed, reload the text file
						// to the application with newly removed data to properly
						// display the correct highest expenses.
						user.loadIntoList(file);
						highestHousing = 0;
						highestTrans = 0;
						highestFood = 0;
						highestUtil = 0;
						highestIns = 0;
						highestMedical = 0;
						highestPersonal = 0;
						
						
						// loop through expense list and find highest expenses.
						for (int i = 0; i < user.expenseList.size(); i++) {
							
							if (user.expenseList.get(i).getCategory().equals("Housing")) {
								if (highestHousing < user.expenseList.get(i).getAmount()) {
									highestHousing = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Transportation")) {
								if (highestTrans < user.expenseList.get(i).getAmount()) {
									highestTrans = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Food")) {
								if (highestFood < user.expenseList.get(i).getAmount()) {
									highestFood = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Utilities")) {
								if (highestUtil < user.expenseList.get(i).getAmount()) {
									highestUtil = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Insurance")) {
								if (highestIns < user.expenseList.get(i).getAmount()) {
									highestIns = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Medical")) {
								if (highestMedical < user.expenseList.get(i).getAmount()) {
									highestMedical = user.expenseList.get(i).getAmount();
								}
								
							} else if (user.expenseList.get(i).getCategory().equals("Personal")) {
								if (highestPersonal < user.expenseList.get(i).getAmount()) {
									highestPersonal = user.expenseList.get(i).getAmount();
								}
							}
						}
						
						// Set labels after retrieving the highest expenses.
						housingLabel.setText("Housing: $" + Math.round(highestHousing * 100.0) / 100.0);
						transportationLabel.setText("Transportation: $" + Math.round(highestTrans * 100.0) / 100.0);
						foodLabel.setText("Food: $" + Math.round(highestFood * 100.0) / 100.0);
						utilitiesLabel.setText("Utilities: $" + Math.round(highestUtil * 100.0) / 100.0);
						insuranceLabel.setText("Insurance: $" + Math.round(highestIns * 100.0) / 100.0);
						medicalLabel.setText("Medical: $" + Math.round(highestMedical * 100.0) / 100.0);
						personalLabel.setText("Personal: $" + Math.round(highestPersonal * 100.0) / 100.0);
					} catch (IOException f) {
						f.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Row deleted successfully.");
				}
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		eastPanel.add(deleteButton);
	}
}
