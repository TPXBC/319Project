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
					i++;
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
					cashPayButton();
					qrPayButton();
					debitPayButton();
					backButton(payPanel);
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
	}
	
	/**
	 * Adds Button to Handle Payment Through QR Code
	 */
	private void qrPayButton() {
		JButton qrCodeButton = new JButton("Pay With QR Code");
		qrCodeButton.setSize(75, 75);
		payPanel.add(qrCodeButton);
	}
	
	/**
	 * Adds Button to Handle Payment Through Debit/Credit/Coinbase
	 */
	private void debitPayButton() {
		JButton debitButton = new JButton("Pay With Debit/Credit");
		debitButton.setSize(75, 75);
		payPanel.add(debitButton);
	}
	
	/**
	 * Adds Button to Handle PayPal
	 */
	private void paypalPayButton() {
		JButton paypalButton = new JButton("Pay With Paypal");
		paypalButton.setSize(75, 75);
		payPanel.add(paypalButton);
	}


	
}
