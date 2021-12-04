package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import data.BSTDictionary;
import data.RasLogs;
import data.Table;
import ras.RAS;

/**
 * GUI Window That Handles Taking Orders at Table
 *
 * @author Restaurant Automation Inc.
 * @param <K>
 * @param <V>
 */
public class OrderGUI<K, V> extends RasGUI {
	int number;

	Integer orderNum;

	Double subtotal;

	Table tableNode;
	Table[] table;

	RAS ras;
	RasLogs rasStats;

	JFrame orderFrame = new JFrame();
	JPanel orderPanel = new JPanel();

	JFrame payFrame = new JFrame();
	JPanel payPanelRight = new JPanel();
	JPanel payPanelLeft = new JPanel();
	JPanel payPanelCenter = new JPanel();

	JLabel customPayLabel;
	JLabel tipPayLabel;
	JLabel changeDuePayLabel;

	JTextField customPayField;
	JTextField tipPayField;
	JTextField changeDuePayField;

	JPanel drinksPanel = new JPanel();
	JPanel appetizePanel = new JPanel();
	JPanel entreePanel = new JPanel();
	JPanel dessertsPanel = new JPanel();
	JPanel sidesPanel = new JPanel();

	Button[] button = new Button[100];
	Button[] spare;

	/**
	 * Constructor Setting the Outline of the Window
	 *
	 * @throws Exception
	 */
	public OrderGUI(int tableCount, RAS ras, Button[] button) throws Exception {

		super();
		spare = button;
		this.ras = ras;
		rasStats = new RasLogs();

		orderFrame.setTitle("Order Menu");
		orderFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		orderFrame.setSize(400, 550);

		orderPanel.setLayout(new GridLayout(8, 8, 5, 5));
		orderFrame.add(BorderLayout.CENTER, orderPanel);
		tableNode = new Table();

		addDrinksButton();
		addAppetizersButton();
		addEntreesButton();
		addDessertsButton();
		addSidesButton();
		sendOrder();
		printReceipt();
		processPayment();

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
		tableNode = table[tableNum - 1];
		orderFrame.setVisible(true);
	}

	/**
	 * Method Handling Adding the Button to Access Drinks Menu
	 */
	public void addDrinksButton() {
		JButton drinks = new JButton("Drinks");
		orderPanel.add(drinks);

		drinks.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					orderFrame.setTitle("Drinks Menu");
					orderFrame.setSize(400, 550);
					addItems(ras.getDrinksMenu(), drinksPanel);
					backButton(drinksPanel);
					orderFrame.setContentPane(drinksPanel);
					orderFrame.setVisible(true);
				} else {
					drinksOrderMenu();
				}
			}

			/**
			 * Method Called After Drinks Window Has Been Created with Buttons Added to
			 * Avoid Duplication
			 */
			public void drinksOrderMenu() {
				orderFrame.setTitle("Drinks Menu");
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(drinksPanel);
				orderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Method Handling Adding the Button to Access Appetizers Menu
	 */
	public void addAppetizersButton() {
		JButton appetizerButton = new JButton("Appetizers");

		orderPanel.add(appetizerButton);
		appetizerButton.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					orderFrame.setTitle("Appetizers Menu");
					orderFrame.setSize(400, 550);
					addItems(ras.getAppetizersMenu(), appetizePanel);
					backButton(appetizePanel);
					orderFrame.setContentPane(appetizePanel);
					orderFrame.setVisible(true);
				} else {
					appetizeOrderMenu();
				}
			}

			/**
			 * Method Called After Appetizer Window Has Been Created with Buttons Added to
			 * Avoid Duplication
			 */
			public void appetizeOrderMenu() {
				orderFrame.setTitle("Appetizer Menu");
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(appetizePanel);
				orderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Method Handling Adding the Button to Access Entrees Menu
	 */
	public void addEntreesButton() {
		JButton entrees = new JButton("Entrees");

		orderPanel.add(entrees);
		entrees.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					orderFrame.setTitle("Entrees Menu");
					orderFrame.setSize(400, 550);
					addItems(ras.getEntreeMenu(), entreePanel);
					backButton(entreePanel);
					orderFrame.setContentPane(entreePanel);
					orderFrame.setVisible(true);
				} else {
					entreesOrderMenu();
				}
			}

			/**
			 * Method Called After Entree Window Has Been Created with Buttons Added to
			 * Avoid Duplication
			 */
			public void entreesOrderMenu() {
				orderFrame.setTitle("Entrees Menu");
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(entreePanel);
				orderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Method Handling Adding the Button to Access Desserts Menu
	 */
	public void addDessertsButton() {
		JButton desserts = new JButton("Desserts");

		orderPanel.add(desserts);
		desserts.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					orderFrame.setTitle("Dessert Menu");
					orderFrame.setSize(400, 550);
					addItems(ras.getDessertsMenu(), dessertsPanel);
					backButton(dessertsPanel);
					orderFrame.setContentPane(dessertsPanel);
					orderFrame.setVisible(true);
				} else {
					dessertsOrderMenu();
				}
			}

			/**
			 * Method Called After Dessert Window Has Been Created with Buttons Added to
			 * Avoid Duplication
			 */
			public void dessertsOrderMenu() {
				orderFrame.setTitle("Dessert Menu");
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(dessertsPanel);
				orderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Method Handling Adding the Button to Access Sides Menu
	 */
	public void addSidesButton() {
		JButton sides = new JButton("Sides");

		orderPanel.add(sides);
		sides.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;
					orderFrame.setTitle("Sides Menu");
					orderFrame.setSize(400, 550);
					addItems(ras.getSidesMenu(), sidesPanel);
					backButton(sidesPanel);
					orderFrame.setContentPane(sidesPanel);
					orderFrame.setVisible(true);
				} else {
					sidesOrderMenu();
				}
			}

			/**
			 * Method Called After Sides Window Has Been Created with Buttons Added to Avoid
			 * Duplication
			 */
			public void sidesOrderMenu() {
				orderFrame.setTitle("Sides Menu");
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(sidesPanel);
				orderFrame.setVisible(true);
			}
		});
	}

	/**
	 * Iterates Through the BSTDictionary Menu and Adds the Keys as Clickable Items
	 *
	 * @param <K>
	 */
	public <K> void addItems(BSTDictionary<K, V> menu, JPanel panel) {
		int i = 0;
		for (Iterator<K> keys = ras.menuItems(menu); keys.hasNext();) {
			String menuItem = (String) keys.next();
			button[i] = new Button(menuItem);
			panel.add(button[i]);
			button[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tableNode = table[number - 1];
					tableNode.addItem(menu, menuItem);

					rasStats.addItemCount(menuItem);

					table[number - 1] = tableNode;
				}
			});
		}
	}

	private void sendOrder() {
		JButton sendOrder = new JButton("Submit Order");
		orderPanel.add(sendOrder);
		
		sendOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTableOrdered(number);
				
			}
			
		});
	}
	
	
	/**
	 * Creates Button to Print Receipt Prints Receipt in Console and Outputs to New
	 * File
	 */
	private void printReceipt() {
		JButton printReceiptButton = new JButton("Print Receipt");

		orderPanel.add(printReceiptButton);
		printReceiptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableNode = table[number - 1];

				try {
					tableNode.printReceipt();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	/**
	 * Returns to Main Order Menu Consisting of Categories "Drinks, Appetizers,
	 * Entrees, Desserts, Sides" Upon Click
	 *
	 * @param panel
	 */
	public void backButton(JPanel panel) {
		JButton backToMenu = new JButton("Return");

		panel.add(backToMenu);

		backToMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainOrderMenu();
			}

			/**
			 * Method Called When Returning to Menu Categories
			 */
			public void mainOrderMenu() {
				orderFrame.setTitle("Order Menu");
				orderFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
				orderFrame.setSize(400, 550);
				orderFrame.setContentPane(orderPanel);
				showOrderWindow(number);
			}
		});
	}

	/**
	 * Adds Button to Handle Making Payment
	 */
	private void processPayment() {
		JButton payButton = new JButton("Process Payment");

		orderPanel.add(payButton);
		payButton.addActionListener(new ActionListener() {

			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					i++;

					
					payFrame.setTitle("Payment Menu");
					payFrame.setSize(600, 750);
					payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					/// Set Up Left Side of Frame With Cash Buttons
					payPanelLeft.setLayout(new GridLayout(6, 1, 5, 5));
					oneDollarButton();
					fiveDollarButton();
					tenDollarButton();
					twentyDollarButton();
					fiftyDollarButton();
					hundredDollarButton();
					payFrame.add(BorderLayout.WEST, payPanelLeft);

					/// Set Up Center Of Frame With Value TextFields
					payPanelCenter.setLayout(new GridLayout(7, 1, 5, 5));
					customPayLabel();
					customPayField();
					tipPayLabel();
					tipPayField();
					addTipButton();
					changeDuePayLabel();
					changeDuePayField();
					payFrame.add(BorderLayout.CENTER, payPanelCenter);

					/// Set Up Right Side of Frame With Payment Buttons
					payPanelRight.setLayout(new GridLayout(5, 1, 5, 5));
					cashPayButton();
					validQRScan();
					invalidQRScan();
					validDebitScan();
					invalidDebitScan();
					payFrame.add(BorderLayout.EAST, payPanelRight);

					tableNode = table[number - 1];
					subtotal = tableNode.printSubTotal();
					
					changeDuePayField.setText(String.format("%.2f", subtotal));

					payFrame.setVisible(true);

				} else {
					paymentMenu();
				}
			}

			/**
			 * Method Called After payGUI has been opened once
			 */
			private void paymentMenu() {
				payFrame.setTitle("Payment Menu");
				payFrame.setSize(600, 750);

				payFrame.add(BorderLayout.WEST, payPanelLeft);
				payFrame.add(BorderLayout.CENTER, payPanelCenter);
				payFrame.add(BorderLayout.EAST, payPanelRight);
				
				tableNode = table[number - 1];
				subtotal = tableNode.printSubTotal();
				
				customPayField.setText("0.00");
				tipPayField.setText("0.00");
				changeDuePayField.setText(String.format("%.2f", subtotal));

				payFrame.setVisible(true);
			}

			private void oneDollarButton() {
				JButton dollarButton = new JButton("$1.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("1.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 1.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			private void fiveDollarButton() {
				JButton dollarButton = new JButton("$5.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("5.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 5.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			private void tenDollarButton() {
				JButton dollarButton = new JButton("$10.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("10.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 10.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			private void twentyDollarButton() {
				JButton dollarButton = new JButton("$20.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("20.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 20.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			private void fiftyDollarButton() {
				JButton dollarButton = new JButton("$50.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("50.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 50.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			private void hundredDollarButton() {
				JButton dollarButton = new JButton("$100.00");
				payPanelLeft.add(dollarButton);

				dollarButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (customPayField.getText().isBlank()) {
							customPayField.setText("100.00");
						} else {
							Double currentAmount = Double.parseDouble(customPayField.getText());
							currentAmount = currentAmount + 100.00;
							customPayField.setText(String.format("%.2f", currentAmount));
						}
					}

				});
			}

			/**
			 * Creates Label For Amount Given By Customer
			 */
			private void customPayLabel() {
				customPayLabel = new JLabel("Enter Amount or Use Cash Buttons");
				payPanelCenter.add(customPayLabel);

			}

			/**
			 * Creates Pay TextField
			 */
			private void customPayField() {
				customPayField = new JTextField("0.00");
				payPanelCenter.add(customPayField);
			}

			/**
			 * Creates Tip Pay Label on GUI Window
			 */
			private void tipPayLabel() {
				tipPayLabel = new JLabel("Leave a Tip? ");
				payPanelCenter.add(tipPayLabel);
			}

			/**
			 * Creates the Tips TextField
			 */
			private void tipPayField() {
				tipPayField = new JTextField();
				payPanelCenter.add(tipPayField);
			}

			/**
			 * Button For Adding Tips
			 */
			private void addTipButton() {
				JButton addTip = new JButton("Confirm Tip");
				payPanelCenter.add(addTip);

				addTip.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (tipPayField.getText().isBlank()) {
							tipPayField.setText(String.format("%.2f", 0.00));
						} else if (Double.parseDouble(tipPayField.getText()) >= 0.00) {

							Double tipAmount = Double.parseDouble(tipPayField.getText());
							subtotal = tipAmount + subtotal;

							customPayField.setText(String.format("%.2f", tipAmount));
							changeDuePayField.setText(String.format("%.2f", subtotal));
						} else {
							tipPayField.setText(String.format("%.2f", 0.00));
						}

					}

				});
			}

			/**
			 * Creates Change Due Pay Label
			 */
			private void changeDuePayLabel() {
				changeDuePayLabel = new JLabel("Change Due: ");
				payPanelCenter.add(changeDuePayLabel);
			}

			/**
			 * Creates the Change Due TextField
			 */
			private void changeDuePayField() {
				changeDuePayField = new JTextField();
				payPanelCenter.add(changeDuePayField);
			}

			private void cashPayButton() {
				JButton cashButton = new JButton("Pay With Cash");
				payPanelRight.add(cashButton);

				cashButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (subtotal == 0.00) {
							return;
						}

						if (Double.parseDouble(customPayField.getText()) > 0.00) {
							subtotal = subtotal - Double.parseDouble(customPayField.getText());

							if (subtotal > 0.00) {
								customPayField.setText(String.format("%.2f", 0.00));
								changeDuePayField.setText(String.format("Still Owe: $%.2f", subtotal));
							} else if (subtotal <= 0.00) {
								changeDuePayField.setText(String.format("Change Owed:  $%.2f", subtotal));

								tableNode.increaseOrderCounter();

								try {
									tableNode.printReceipt();
								} catch (Exception e1) {
									e1.printStackTrace();
								}

								rasStats.addPriceStatistics(tableNode.printSubTotal());
								tableNode.clearTable();

								table[number - 1] = tableNode;
								
								try {
									rasStats.outputStatistics();
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
								
								setTableUnassigned(number);
								
							} else {
								changeDuePayField.setText("Invalid Payment Entry");
							}
						}

					}

				});
			}

			private void validQRScan() {
				JButton QRScan = new JButton("Valid QR Scan");
				payPanelRight.add(QRScan);

				QRScan.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (subtotal == 0.00) {
							return;
						}

						tableNode.increaseOrderCounter();

						try {
							tableNode.printReceipt();
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						rasStats.addPriceStatistics(tableNode.printSubTotal());
						tableNode.clearTable();
						
						table[number - 1] = tableNode;

						try {
							rasStats.outputStatistics();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						setTableUnassigned(number);
						
						customPayField.setText(String.format("Paid $%.2f", subtotal));
						changeDuePayField.setText(String.format("Total Due: $%.2f", 0.00));
						
						
					}

				});
			}

			private void invalidQRScan() {
				JButton QRScan = new JButton("Invalid QR Scan");
				payPanelRight.add(QRScan);

				QRScan.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (subtotal == 0.00) {
							return;
						}

						changeDuePayField.setText("QR Scan Failed!");
					}

				});
			}

			private void validDebitScan() {
				JButton DebitScan = new JButton("Valid Debit Scan");
				payPanelRight.add(DebitScan);

				DebitScan.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (subtotal == 0.00) {
							return;
						}

						tableNode.increaseOrderCounter();

						try {
							tableNode.printReceipt();
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						rasStats.addPriceStatistics(tableNode.printSubTotal());
						tableNode.clearTable();
						
						
						table[number - 1] = tableNode;

						try {
							rasStats.outputStatistics();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						setTableUnassigned(number);
						
						customPayField.setText(String.format("Paid $%.2f", subtotal));
						changeDuePayField.setText(String.format("Total Due: $%.2f", 0.00));
					}

				});
			}

			private void invalidDebitScan() {
				JButton DebitScan = new JButton("Invalid Debit Scan");
				payPanelRight.add(DebitScan);

				DebitScan.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (subtotal == 0.00) {
							return;
						}

						changeDuePayField.setText("Debit Scan Failed!");
					}

				});
			}

		});
	}
	
	/**
	 * Sets Table to Unassigned Color Coding
	 *
	 * @param tableNum
	 */
	public void setTableUnassigned(int tableNum) {
		spare[tableNum - 1].setBackground(Color.red);
	}

	/**
	 * Sets Table to Assigned Color Coding
	 *
	 * @param tableNum
	 */
	public void setTableAssigned(int tableNum) {
		spare[tableNum - 1].setBackground(Color.yellow);
	}

	/**
	 * Sets Table to Ordered Color Coding
	 *
	 * @param tableNum
	 */
	public void setTableOrdered(int tableNum) {
		spare[tableNum - 1].setBackground(Color.cyan);
	}

	/**
	 * Sets Table to Order-Ready Color Coding
	 *
	 * @param tableNum
	 */
	public void setTableOrderReady(int tableNum) {
		spare[tableNum - 1].setBackground(Color.green);
	}

}
