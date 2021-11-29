package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.*;

import data.BSTDictionary;
import data.Table;
import ras.RAS;

/**
 * GUI Window That Handles Taking Orders at Table
 * @author Christian
 * @param <V>
 */
public class OrderGUI<V> extends RasGUI {
	int number;
	
	Table tableNode;
	Table [] table;
	RAS ras;
	
	
	JFrame qrPayFrame = new JFrame();
	JPanel qrPayPanel = new JPanel();
	
	JFrame cashPayFrame = new JFrame();
	JPanel cashPayPanelWest = new JPanel();
	JPanel cashPayPanelElse = new JPanel();
	
	JTextField customCashPayField;
	JTextField tipCashPayField;
	JTextField changeDuePayField;
	
	
	JFrame OrderFrame = new JFrame();
	JPanel MainOrderPanel = new JPanel();
	JPanel payPanel = new JPanel();
	JPanel drinksPanel = new JPanel();
	JPanel appetizePanel = new JPanel();
	JPanel entreePanel = new JPanel();
	JPanel dessertsPanel = new JPanel();
	JPanel sidesPanel = new JPanel();
	
	Button[] button = new Button[100];	
	
	/**
	 * Constructor Setting the Outline of the Window
	 */
	public OrderGUI(int tableCount, RAS ras) {
		this.ras = ras;
		OrderFrame.setTitle("Order Menu");
		OrderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		OrderFrame.setSize(400, 550);
		///OrderFrame.setLocationRelativeTo(null);
		MainOrderPanel.setLayout(new GridLayout(8, 8, 5, 5));
		OrderFrame.add(BorderLayout.CENTER, MainOrderPanel);
		tableNode = new Table();
		addDrinksButton();
		addAppetizersButton();
		addEntreesButton();
		addDessertsButton();
		addSidesButton();
		printReceipt();
		processPayment();
		//printAvgPrice(); ##Test Button Not in Final Product
		setTables(tableCount);
	}
	
	/**
	 * Initializes All Nodes in table
	 */
	private void setTables(int tableCount) {
		table = new Table[tableCount];
		for (int i = 0; i < table.length; i++) {
			table[i] = new Table(i);
		}
	}
	
	/**
	 * Method to Call to Show Window
	 */
	public void showOrderWindow(int tableNum) {
		number = tableNum;
		OrderFrame.setVisible(true);
	}
	
	/**
	 * Method Handling Adding the Button to Access Drinks Menu
	 */
	public void addDrinksButton() {
		JButton drinks = new JButton("Drinks");
		drinks.setSize(75, 75);
		MainOrderPanel.add(drinks);
		drinks.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i ==0) {
					i++;
					OrderFrame.setTitle("Drinks Menu");
					OrderFrame.setSize(400, 550);
					addItems(ras.getDrinksMenu(), drinksPanel);
					backButton(drinksPanel);
					OrderFrame.setContentPane(drinksPanel);
					OrderFrame.setVisible(true);
				} else {
					drinksOrderMenu();
				}
			}
			
			/**
			 * Method Called After Drinks Window Has Been Created with Buttons Added to Avoid Duplication
			 */
			public void drinksOrderMenu() {
				OrderFrame.setTitle("Drinks Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(drinksPanel);
				OrderFrame.setVisible(true);
			}
		});
	}
	
	/**
	 * Method Handling Adding the Button to Access Appetizers Menu
	 */
	public void addAppetizersButton() {
		JButton appetizerButton = new JButton("Appetizers");
		appetizerButton.setSize(75, 75);
		MainOrderPanel.add(appetizerButton);
		appetizerButton.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i ==0) {
					i++;
					OrderFrame.setTitle("Appetizers Menu");
					OrderFrame.setSize(400, 550);
					addItems(ras.getAppetizersMenu(), appetizePanel);
					backButton(appetizePanel);
					OrderFrame.setContentPane(appetizePanel);
					OrderFrame.setVisible(true);
				} else {
					appetizeOrderMenu();
				}
			}
			
			/**
			 * Method Called After Appetizer Window Has Been Created with Buttons Added to Avoid Duplication
			 */
			public void appetizeOrderMenu() {
				OrderFrame.setTitle("Appetizer Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(appetizePanel);
				OrderFrame.setVisible(true);
			}
		});
	}
	
	/**
	 * Method Handling Adding the Button to Access Entrees Menu
	 */
	public void addEntreesButton() {
		JButton entrees = new JButton("Entrees");
		entrees.setSize(75, 75);
		MainOrderPanel.add(entrees);
		entrees.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i ==0) {
					i++;
					OrderFrame.setTitle("Entrees Menu");
					OrderFrame.setSize(400, 550);
					addItems(ras.getEntreeMenu(), entreePanel);
					backButton(entreePanel);
					OrderFrame.setContentPane(entreePanel);
					OrderFrame.setVisible(true);
				} else {
					entreesOrderMenu();
				}
			}
			
			/**
			 * Method Called After Entree Window Has Been Created with Buttons Added to Avoid Duplication
			 */
			public void entreesOrderMenu() {
				OrderFrame.setTitle("Entrees Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(entreePanel);
				OrderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Method Handling Adding the Button to Access Desserts Menu
	 */
	public void addDessertsButton() {
		JButton desserts = new JButton("Desserts");
		desserts.setSize(75, 75);
		MainOrderPanel.add(desserts);
		desserts.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i ==0) {
					i++;
					OrderFrame.setTitle("Dessert Menu");
					OrderFrame.setSize(400, 550);
					addItems(ras.getDessertsMenu(), dessertsPanel);
					backButton(dessertsPanel);
					OrderFrame.setContentPane(dessertsPanel);
					OrderFrame.setVisible(true);
				} else {
					dessertsOrderMenu();
				}
			}
			
			/**
			 * Method Called After Dessert Window Has Been Created with Buttons Added to Avoid Duplication
			 */
			public void dessertsOrderMenu() {
				OrderFrame.setTitle("Dessert Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(dessertsPanel);
				OrderFrame.setVisible(true);
			}
		});
	}
	
	/**
	 * Method Handling Adding the Button to Access Sides Menu
	 */
	public void addSidesButton() {
		JButton sides = new JButton("Sides");
		sides.setSize(75, 75);
		MainOrderPanel.add(sides);
		sides.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i ==0) {
					i++;																																								i++;
					OrderFrame.setTitle("Sides Menu");
					OrderFrame.setSize(400, 550);
					addItems(ras.getSidesMenu(), sidesPanel);
					backButton(sidesPanel);
					OrderFrame.setContentPane(sidesPanel);
					OrderFrame.setVisible(true);
				} else {
					sidesOrderMenu();
				}
			}
			
			/**
			 * Method Called After Sides Window Has Been Created with Buttons Added to Avoid Duplication
			 */
			public void sidesOrderMenu() {
				OrderFrame.setTitle("Sides Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(sidesPanel);
				OrderFrame.setVisible(true);
			}
		});
	}
	
	/**
	 * Iterates Through the BSTDictionary Menu and Adds the Keys as Clickable Items
	 * @param <K>
	 */
	public <K> void addItems(BSTDictionary<K, V> menu, JPanel panel) {
		int i = 0;
		for (Iterator<K> keys = ras.menuItems(menu); keys.hasNext(); ) {
			String menuItem = (String) keys.next();
			button[i] = new Button(menuItem);
			panel.add(button[i]);
			button[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					tableNode = table[number-1];
					System.out.println(tableNode.addItem(menu, menuItem));
					table[number-1] = tableNode;
				}
			});
		}
	}
	
	/**
	 * Creates Button to Print Receipt
	 * Prints Receipt in Console and Outputs to New File
	 */
	private void printReceipt() {
		JButton printReceiptButton = new JButton("Print Receipt");
		printReceiptButton.setSize(75, 75);
		MainOrderPanel.add(printReceiptButton);
		printReceiptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				tableNode = table[number-1];
				tableNode.printReceipt();
			}
		});
	}
	
	/**
	 * Returns to Main Order Menu Consisting of Categories "Drinks, Appetizers, Entrees, Desserts, Sides" Upon Click
	 * @param panel
	 */
	public void backButton(JPanel panel) {
		JButton backToMenu = new JButton("Return");
		backToMenu.setSize(75, 75);
		panel.add(backToMenu);
		
		backToMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mainOrderMenu();
			}
			
			/**
			 * Method Called When Returning to Menu Categories
			 */
			public void mainOrderMenu() {
				OrderFrame.setTitle("Order Menu");
				OrderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(MainOrderPanel);
				showOrderWindow(number);
			}
		});
	}
	
	/**
	 * Adds Button to Handle Making Payment
	 */
	public void processPayment() {
		JButton payButton = new JButton("Process Payment");
		payButton.setSize(75, 75);
		MainOrderPanel.add(payButton);
		payButton.addActionListener(new ActionListener() {
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					OrderFrame.setTitle("Payment Menu");
					OrderFrame.setSize(400, 550);
					payPanel.setLayout(new GridLayout(5,5,5,5));
					OrderFrame.setContentPane(payPanel);
					cashPayButton();
					qrPayButton();
					debitPayButton();
					backButton(payPanel);
					OrderFrame.setVisible(true);
				} else {
					paymentMenu();
				}
			}
			
			/**
			 * Method Called After payGUI has been opened once
			 */
			public void paymentMenu() {
				OrderFrame.setTitle("Payment Menu");
				OrderFrame.setSize(400, 550);
				OrderFrame.setContentPane(payPanel);
				OrderFrame.setVisible(true);
			}
		});
	}
	
	/*
	 * AVERAGE PRICE BUTTON 
	 */
	private void printAvgPrice() {
		JButton avgPrice = new JButton("Print Average Price");
		MainOrderPanel.add(avgPrice);
		avgPrice.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				tableNode = table[number -1 ];
				tableNode.GetAverageItemPrice();
			}
		});
	}
	
	/**
	 * Adds Button to Handle Cash Payment
	 */
	private void cashPayButton() {
		JButton cashButton = new JButton("Pay With Cash");
		cashButton.setSize(75, 75);
		payPanel.add(cashButton);
		
		cashButton.addActionListener(new ActionListener() {
			/**
			 * Open New GUI Window
			 * Send Current Total
			 * Add $5, $10, $20, $50, $100 Payment JButton
			 * Add Custom Textbox for custom payment I.E $35.45
			 * Add JButton to confirm custom Payment
			 * Adding Tip textbox and JButton
			 * Insert Tip ammount and confirm
			 * Click Confirm cash payment JButton
			 * Get change total
			 */
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					cashPayFrame.setTitle("Cash Payment");
					cashPayFrame.setSize(400, 550);
					
					tableNode = table[number - 1];
					Double subtotal = tableNode.printSubTotal();
					
					cashPayPanelWest.setLayout(new GridLayout(6,1,5,5));
					cashPayPanelElse.setLayout(new GridLayout(5,5,5,5));
					
					
					cashPayFrame.add(BorderLayout.WEST, cashPayPanelWest);
					cashPayFrame.add(BorderLayout.CENTER,cashPayPanelElse);
					
					fiveDollarButton();
					tenDollarButton();
					twentyDollarButton();
					fiftyDollarButton();
					hundredDollarButton();
					backButton(cashPayPanelWest);
					
					customPayField();
					customPayButton();
					tipPayField();
					tipPayButton();
					changeDuePayField();
					

					cashPayFrame.setVisible(true);
				} else {
					mainCashFrame();
				}
				
			}
			
			private void mainCashFrame() {
				cashPayFrame.setTitle("Payment Menu");
				cashPayFrame.setSize(400, 550);
				cashPayFrame.setContentPane(cashPayPanelWest);
				cashPayFrame.setVisible(true);
			}
			
			private void fiveDollarButton() {
				JButton fiveDollarButton = new JButton("$5.00");
				cashPayPanelWest.add(fiveDollarButton);
				
				fiveDollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						customCashPayField.setText("5.00");
					}
					
				});
				
			}
			
			private void tenDollarButton() {
				JButton tenDollarButton = new JButton("$10.00");
				cashPayPanelWest.add(tenDollarButton);
				
				tenDollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						customCashPayField.setText("10.00");
					}
					
				});
			}
			
			private void twentyDollarButton() {
				JButton twentyDollarButton = new JButton("$20.00");
				cashPayPanelWest.add(twentyDollarButton);
				
			}
			
			private void fiftyDollarButton() {
				JButton fiftyDollarButton = new JButton("$50.00");
				cashPayPanelWest.add(fiftyDollarButton);
				
			}
			
			private void hundredDollarButton() {
				JButton hundredDollarButton = new JButton("$100.00");
				cashPayPanelWest.add(hundredDollarButton);
				
			}
			
			private void customPayButton() {
				JButton selfPayButton = new JButton("Enter Amount");
				cashPayPanelElse.add(selfPayButton);
				
				selfPayButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Double customAmnt = Double.parseDouble(customCashPayField.getText());
						changeDuePayField.setText(String.format("%$.2f", customAmnt));
					}
					
				});
			}
			
			
			private void customPayField() {
				customCashPayField = new JTextField();
				cashPayPanelElse.add(customCashPayField);
				
			}
			
			private void tipPayButton() {
				JButton tipPayButton = new JButton("Enter Tip Amount");
				cashPayPanelElse.add(tipPayButton);
				
				tipPayButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
			
			private void tipPayField() {
				tipCashPayField = new JTextField();
				cashPayPanelElse.add(tipCashPayField);
				
			}
			
			private void changeDuePayField() {
				changeDuePayField = new JTextField();
				cashPayPanelElse.add(changeDuePayField);
				
			}
		});
	}
	
	/**
	 * Adds Button to Handle Payment Through QR Code
	 */
	private void qrPayButton() {
		JButton qrCodeButton = new JButton("Pay With QR Code");
		qrCodeButton.setSize(75, 75);
		payPanel.add(qrCodeButton);
		
		qrCodeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/**
				 * Open New GUI Window
				 * Send Current Total of Table (tableNode = table[number - 1]
				 * Double subtotal = tableNode.printSubtotal
				 * Add a JTextBox and JButton for TIP **Ask user if they would like to add a tip**
				 * Click JButton to Confirm Tip Amount
				 * If Tip Textbox is blank when Confirmed. Set tip to 0
				 * Give two new JButtons && Restructure JFrame
				 * JButton1 = Valid QR Scan
				 * JButton2 = Invalid QR Scan
				 * If JButton1 is Clicked -- Subtract Total from Total so dues = 0
				 * Print Payment Confirmed AND print receipt tableNode.printReceipt()
				 * Clear Table -> setTableUnassigned();
				 * 
				 * If Jbutton2 is Clicked -- Print Payment Invalid
				 * System.out.println("Payment Invalid Please Scan Again")
				 * Then retry payment
				 */
			}
		});
	}
	
	/**
	 * Adds Button to Handle Payment Through Debit/Credit/Coinbase
	 */
	private void debitPayButton() {
		JButton debitButton = new JButton("Pay With Debit/Credit");
		debitButton.setSize(75, 75);
		payPanel.add(debitButton);
	}
	
}
