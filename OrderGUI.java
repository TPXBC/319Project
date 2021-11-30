package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.*;

import data.BSTDictionary;
import data.Table;
import ras.RAS;
import data.RasLogs;
/**
 * GUI Window That Handles Taking Orders at Table
 * @author Christian
 * @param <K>
 * @param <V>
 */
public class OrderGUI<K, V> extends RasGUI {
	int number;
	
	Integer orderNum;
	
	Double subtotal;
	
	Table tableNode;
	Table [] table;
	
	RAS ras;
	RasLogs rasStats;
	
	
	JFrame qrPayFrame = new JFrame();
	JPanel qrPayPanel = new JPanel();
	JPanel qrPayPanelJr = new JPanel();
	
	JFrame cashPayFrame = new JFrame();
	JPanel cashPayPanel = new JPanel();
	JPanel cashPayPanelJr = new JPanel();
	
	JFrame debitPayFrame = new JFrame();
	JPanel debitPayPanel = new JPanel();
	JPanel debitPayPanelJr = new JPanel();
	
	
	
	
	JTextField customPayField;
	JTextField tipPayField;
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
	Button[] spare;
	
	/**
	 * Constructor Setting the Outline of the Window
	 * @throws Exception 
	 */
	public OrderGUI(int tableCount, RAS ras) throws Exception {
		
		super();
		this.ras = ras;
		rasStats = new RasLogs();
		
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
		spare = button;
		number = tableNum;
		tableNode = table[tableNum -1];
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
					
					rasStats.addItemCount(menuItem);
					
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
					subtotal = tableNode.printSubTotal();
					
					cashPayPanel.setLayout(new GridLayout(6,1,5,5));
					cashPayPanelJr.setLayout(new GridLayout(8,1,5,5));
					
					
					cashPayFrame.add(BorderLayout.WEST, cashPayPanel);
					cashPayFrame.add(BorderLayout.CENTER,cashPayPanelJr);
					
					fiveDollarButton();
					tenDollarButton();
					twentyDollarButton();
					fiftyDollarButton();
					hundredDollarButton();
					backButton(cashPayPanel);
					
					customPayLabel();
					customPayField();
					customPayButton();
					tipPayLabel();
					tipPayField();
					tipPayButton();
					changeDuePayLabel();
					changeDuePayField();
					
					customPayField.setText(String.format("%.2f", 0.00));
					changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
					
					cashPayFrame.setVisible(true);
					
				} else {
					
					mainCashFrame();
					
				}
				
			}
			
			/**
			 * Used After Cash Window Has Been Opened
			 */
			private void mainCashFrame() {
				cashPayFrame.setTitle("Payment Menu");
				cashPayFrame.setSize(400, 550);
				cashPayFrame.add(BorderLayout.WEST, cashPayPanel);
				cashPayFrame.add(BorderLayout.CENTER,cashPayPanelJr);
				
				tableNode = table[number - 1];
				subtotal = tableNode.printSubTotal();
				customPayField.setText(String.format("%.2f", 0.00));
				changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
				
				cashPayFrame.setVisible(true);
			}
			
			/**
			 * Adds Five Dollar Button
			 */
			private void fiveDollarButton() {
				JButton fiveDollarButton = new JButton("$5.00");
				cashPayPanel.add(fiveDollarButton);
				
				fiveDollarButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						if (customPayField.getText().isEmpty()) {
							customPayField.setText("5.00");
						} else {
							Double currentAmnt = Double.parseDouble(customPayField.getText());
							currentAmnt = currentAmnt + 5.00;
							customPayField.setText(String.format("%.2f", currentAmnt));
							
						};
					}
					
				});
				
			}
			
			/**
			 * Adds Ten Dollar Button
			 */
			private void tenDollarButton() {
				JButton tenDollarButton = new JButton("$10.00");
				cashPayPanel.add(tenDollarButton);
				
				tenDollarButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						if (customPayField.getText().isEmpty()) {
							customPayField.setText("10.00");
						} else {
							Double currentAmnt = Double.parseDouble(customPayField.getText());
							currentAmnt = currentAmnt + 10.00;
							customPayField.setText(String.format("%.2f", currentAmnt));
							
						}
					}
					
					
				});
			}
			
			/**
			 * Adds Twenty Dollar Button
			 */
			private void twentyDollarButton() {
				JButton twentyDollarButton = new JButton("$20.00");
				cashPayPanel.add(twentyDollarButton);
				
				twentyDollarButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isEmpty()) {
							customPayField.setText("20.00");
						} else {
							Double currentAmnt = Double.parseDouble(customPayField.getText());
							currentAmnt = currentAmnt + 20.00;
							customPayField.setText(String.format("%.2f", currentAmnt));
							
						}
					}
					
					
				});
				
			}
			
			/**
			 * Adds Fifty Dollar Button
			 */
			private void fiftyDollarButton() {
				JButton fiftyDollarButton = new JButton("$50.00");
				cashPayPanel.add(fiftyDollarButton);
				
				fiftyDollarButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isEmpty()) {
							customPayField.setText("50.00");
						} else {
							Double currentAmnt = Double.parseDouble(customPayField.getText());
							currentAmnt = currentAmnt + 50.00;
							customPayField.setText(String.format("%.2f", currentAmnt));
							
						}
					}
					
					
				});
			}
			
			/**
			 * Adds Hundred Dollar Button
			 */
			private void hundredDollarButton() {
				JButton hundredDollarButton = new JButton("$100.00");
				cashPayPanel.add(hundredDollarButton);
				
				hundredDollarButton.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent e) {
						
						if (customPayField.getText().isEmpty()) {
							customPayField.setText("100.00");
						} else {
							Double currentAmnt = Double.parseDouble(customPayField.getText());
							currentAmnt = currentAmnt + 100.00;
							customPayField.setText(String.format("%.2f", currentAmnt));
							
						}
					}
					
					
				});
				
			}
			
			/**
			 * Adds Button to Confirm Cash Amount Paid
			 */
			private void customPayButton() {
				JButton selfPayButton = new JButton("Enter Amount");
				cashPayPanelJr.add(selfPayButton);
				
				selfPayButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Double customAmnt = Double.parseDouble(customPayField.getText());
						Double changeDue = subtotal - customAmnt;
						if (changeDue <= 0.0) {
							
							changeDuePayField.setText(String.format("Change Owed:  $%.2f", changeDue));
							
							tableNode.increaseOrderCounter();
							tableNode.printReceipt();
							rasStats.addPriceStatistics(subtotal);
							tableNode.clearTable();
							
						} else if (changeDue > 0){
							changeDuePayField.setText(String.format("Still Owed: $%.2f", changeDue));
						} else {
							changeDuePayField.setText("Invalid Data Somewhere");
						}

					}
					
				});
			}
			
			/**
			 * Creates Paid Amount TextField
			 */
			private void customPayField() {
				customPayField = new JTextField();
				cashPayPanelJr.add(customPayField);
				
			}
			
			/**
			 * Creates Paid Amount Label
			 */
			private void customPayLabel() {
				JLabel customPayLabel = new JLabel("Enter Amount or Press Enter When Equal");
				cashPayPanelJr.add(customPayLabel);
			}
			
			/**
			 * Adds Button For Adding Tips
			 */
			private void tipPayButton() {
				JButton tipPayButton = new JButton("Enter Tip Amount");
				cashPayPanelJr.add(tipPayButton);
				
				tipPayButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (tipPayField.getText().isBlank()) {
							tipPayField.setText(String.format("$%.2f", 0.00));
						} else {
							Double tipAmnt = Double.parseDouble(tipPayField.getText());
							tableNode.addTip(tipAmnt);
							if (customPayField.getText().isEmpty()) {
								customPayField.setText(String.format("%.2f", tipAmnt));
							} else {
								Double currentAmnt = Double.parseDouble(customPayField.getText());
								currentAmnt = currentAmnt + tipAmnt;
								customPayField.setText(String.format("%.2f", currentAmnt));
								
							}
						}
					}
					
				});
			}
			
			/*
			 * Creates Tip TextField
			 */
			private void tipPayField() {
				tipPayField = new JTextField();
				cashPayPanelJr.add(tipPayField);
				
			}
			
			/**
			 * Creates Tip Pay Label on GUI Window
			 */
			private void tipPayLabel() {
				JLabel tipPayLabel = new JLabel("Leave a Tip? ");
				cashPayPanelJr.add(tipPayLabel);
			}
			
			/**
			 * Creates Change Due TextField
			 */
			private void changeDuePayField() {
				changeDuePayField = new JTextField();
				cashPayPanelJr.add(changeDuePayField);
				
			}
			
			/**
			 * Creates Change Due Pay Label
			 */
			private void changeDuePayLabel() {
				JLabel changeDuePayLabel = new JLabel("Change Due: ");
				cashPayPanelJr.add(changeDuePayLabel);
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
			
			
			int i = 0;
			
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					qrPayFrame.setTitle("QR Code Payment Payment");
					qrPayFrame.setSize(400, 550);
				
					tableNode = table[number - 1];
					subtotal = tableNode.printSubTotal();
				
					qrPayPanel.setLayout(new GridLayout(2,1,5,5));
					qrPayPanelJr.setLayout(new GridLayout(5,5,5,5));
				
					qrPayFrame.add(BorderLayout.CENTER, qrPayPanelJr);
					qrPayFrame.add(BorderLayout.WEST, qrPayPanel);
				
					validQRScanButton();
					invalidQRScanButton();
				
					customPayField();
					tipPayField();
					tipPayButton();
					changeDuePayField();
					backButton(qrPayPanelJr);
				
					customPayField.setText(String.format("Paying: %.2f", subtotal));
					tipPayField.setText(" ");
					changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
				
					qrPayFrame.setVisible(true);
				} else {
					qrMainSetUp();
				}
			}
			
			/**
			 * Used After QR Payment Window Has Been Created
			 */
			private void qrMainSetUp() {
				qrPayFrame.setTitle("Payment Menu");
				qrPayFrame.setSize(400, 550);
				qrPayFrame.add(BorderLayout.WEST, qrPayPanel);
				qrPayFrame.add(BorderLayout.CENTER,qrPayPanelJr);
				
				tableNode = table[number - 1];
				subtotal = tableNode.printSubTotal();
				
				customPayField.setText(String.format("Paying: %.2f", subtotal));
				tipPayField.setText(" ");
				changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
				
				qrPayFrame.setVisible(true);
			}
			
			/**
			 * Method to Simulate valid QR Scan
			 */
			private void validQRScanButton() {
				JButton validScanButton = new JButton("Valid QR Scan");
				qrPayPanel.add(validScanButton);
				
				validScanButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String payAmnt = customPayField.getText();
						String temp[] = payAmnt.split("\\s+");
						Double paidAmnt = Double.parseDouble(temp[1]);
						Double restDue = subtotal - paidAmnt;
						
						customPayField.setText(String.format("Paid: %.2f", paidAmnt));
						changeDuePayField.setText(String.format("Total Due: $%.2f", restDue));
						
						tableNode.increaseOrderCounter();
						tableNode.printReceipt();
						rasStats.addPriceStatistics(subtotal);
						tableNode.clearTable();
					}
					
					
				});
			}
			
			/**
			 * Method to Simulate Invalid QR Scan
			 */
			private void invalidQRScanButton() {
				JButton invalidScanButton = new JButton("Invalid QR Scan");
				qrPayPanel.add(invalidScanButton);
				
				invalidScanButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						customPayField.setText(String.format("Paying: %.2f", subtotal));
						changeDuePayField.setText("Invalid QR Scan Please Scan Again");
					}
					
					
				});
			}
			
			/**
			 * Adds Pay TextField
			 */
			private void customPayField() {
				customPayField = new JTextField();
				qrPayPanelJr.add(customPayField);
				
			}
			
			/**
			 * Adds Tip Button
			 */
			private void tipPayButton() {
				JButton tipPayButton = new JButton("Enter Tip Amount");
				qrPayPanelJr.add(tipPayButton);
				
				tipPayButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						if (tipPayField.getText().isBlank()) {
							tipPayField.setText(String.format("%.2f", 0.00));
						} else {
							Double tipAmnt = Double.parseDouble(tipPayField.getText());
							tableNode.addTip(tipAmnt);
							if (customPayField.getText().isEmpty()) {
								customPayField.setText(String.format("Paying: %.2f", tipAmnt));
							} else {
								String payingAmnt = customPayField.getText();
								String temp[] = payingAmnt.split("\\s+");
								Double currentAmnt = Double.parseDouble(temp[1]);
								currentAmnt = currentAmnt + tipAmnt;
								customPayField.setText(String.format("Paying %.2f", currentAmnt));
								subtotal = currentAmnt;
								changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
								
							}
						}
					}
					
				});
			}
			
			/**
			 * Creates the Tip Amount TextField
			 */
			private void tipPayField() {
				tipPayField = new JTextField();
				qrPayPanelJr.add(tipPayField);
				
			}
			
			/**
			 * Creates the Change Due TextField
			 */
			private void changeDuePayField() {
				changeDuePayField = new JTextField();
				qrPayPanelJr.add(changeDuePayField);
				
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
		
		debitButton.addActionListener(new ActionListener() {
			
			int i = 0;
			
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					debitPayFrame.setTitle("QR Code Payment Payment");
					debitPayFrame.setSize(400, 550);
				
					tableNode = table[number - 1];
					subtotal = tableNode.printSubTotal();
				
					debitPayPanel.setLayout(new GridLayout(2,1,5,5));
					debitPayPanelJr.setLayout(new GridLayout(5,5,5,5));
				
					debitPayFrame.add(BorderLayout.CENTER, debitPayPanelJr);
					debitPayFrame.add(BorderLayout.WEST, debitPayPanel);
				
					validDebitScanButton();
					invalidDebitScanButton();
				
					customPayField();
					tipPayField();
					tipPayButton();
					changeDuePayField();
					backButton(debitPayPanelJr);
				
					customPayField.setText(String.format("Paying: %.2f", subtotal));
					tipPayField.setText(" ");
					changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
				
					debitPayFrame.setVisible(true);
				} else {
					debitMainSetUp();
				}
			}
			
			/**
			 * Used After Debit Payment Window Has Already Been Created
			 */
			private void debitMainSetUp() {
				debitPayFrame.setTitle("Payment Menu");
				debitPayFrame.setSize(400, 550);
				debitPayFrame.add(BorderLayout.WEST, debitPayPanel);
				debitPayFrame.add(BorderLayout.CENTER, debitPayPanelJr);
				
				tableNode = table[number - 1];
				subtotal = tableNode.printSubTotal();
				
				customPayField.setText(String.format("Paying: %.2f", subtotal));
				tipPayField.setText(" ");
				changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
				
				debitPayFrame.setVisible(true);
			}
			
			/**
			 * Simulates Valid Debit Card Scan
			 */
			private void validDebitScanButton() {
				JButton validScanButton = new JButton("Valid Debit Scan");
				debitPayPanel.add(validScanButton);
				
				validScanButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String payAmnt = customPayField.getText();
						String temp[] = payAmnt.split("\\s+");
						Double paidAmnt = Double.parseDouble(temp[1]);
						Double restDue = subtotal - paidAmnt;
						
						customPayField.setText(String.format("Paid: %.2f", paidAmnt));
						changeDuePayField.setText(String.format("Total Due: $%.2f", restDue));
						
						tableNode.increaseOrderCounter();
						tableNode.printReceipt();
						rasStats.addPriceStatistics(subtotal);
						tableNode.clearTable();
						
					}
					
					
				});
			}
			
			/**
			 * Simulates Invalid Debit Card Scan
			 */
			private void invalidDebitScanButton() {
				JButton invalidScanButton = new JButton("Invalid Debit Scan");
				debitPayPanel.add(invalidScanButton);
				
				invalidScanButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						customPayField.setText(String.format("Paying: %.2f", subtotal));
						changeDuePayField.setText("Invalid Debit Scan Please Scan Again");
					}
					
					
				});
			}
			
			/**
			 * Creates Pay TextField
			 */
			private void customPayField() {
				customPayField = new JTextField();
				debitPayPanelJr.add(customPayField);
				
			}
			
			/**
			 * Adds Tip Button to Add Tip Amount to Subtotal
			 */
			private void tipPayButton() {
				JButton tipPayButton = new JButton("Enter Tip Amount");
				debitPayPanelJr.add(tipPayButton);
				
				tipPayButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (tipPayField.getText().isBlank()) {
							tipPayField.setText(String.format("$%.2f", 0.00));
						} else {
							Double tipAmnt = Double.parseDouble(tipPayField.getText());
							tableNode.addTip(tipAmnt);
							if (customPayField.getText().isEmpty()) {
								customPayField.setText(String.format("Paying: %.2f", tipAmnt));
							} else {
								String payingAmnt = customPayField.getText();
								String temp[] = payingAmnt.split("\\s+");
								Double currentAmnt = Double.parseDouble(temp[1]);
								currentAmnt = currentAmnt + tipAmnt;
								customPayField.setText(String.format("Paying %.2f", currentAmnt));
								subtotal = currentAmnt;
								changeDuePayField.setText(String.format("Total Due: $%.2f", subtotal));
								
							}
						}
					}
					
				});
			}
			
			/**
			 * Creates the Tips TextField
			 */
			private void tipPayField() {
				tipPayField = new JTextField();
				debitPayPanelJr.add(tipPayField);
				
			}
			
			/**
			 * Creates the Change Due TextField
			 */
			private void changeDuePayField() {
				changeDuePayField = new JTextField();
				debitPayPanelJr.add(changeDuePayField);
				
			}
		});
	}
	
}
