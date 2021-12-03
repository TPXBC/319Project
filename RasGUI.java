package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import data.BSTDictionary;
import data.RasLogs;
import data.Table;
import ras.RAS;

public class RasGUI<S, K, V> {

	RAS ras;
	OrderGUI order;

	int hBTNClicks = 0;
	int sBTNClicks = 0;
	int tBTNClicks = 0;
	int tableClicks = 0;
	int tableNum;

	JFrame tablesFrame = new JFrame("Table Window");
	JFrame singleTableFrame = new JFrame();
	JFrame RASframe = new JFrame("Restaurant Automation System");

	JPanel RASpanel = new JPanel();

	JButton tableButton;
	JButton kitchen;
	JButton manager;

	JFileChooser fileChooser = new JFileChooser();
	File file;

	Table noder = null;

	JPanel tablesPanel = new JPanel();
	JPanel singleTablePanel = new JPanel();
	JPanel serverPanel = new JPanel();
	JPanel hostPanel = new JPanel();

	JFrame manageFrame = new JFrame();
	JPanel managePanel = new JPanel();

	Button[] button;

	/**
	 * Constructor Creating Buttons for the Positions
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		RasGUI mainWindow = new RasGUI();

		mainWindow.fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int result = mainWindow.fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			mainWindow.file = mainWindow.fileChooser.getSelectedFile();
		} else {
			throw new Exception("Failed to Chose File");
		}

		mainWindow.ras = new RAS(mainWindow.file);

		mainWindow.button = new Button[mainWindow.ras.getTableCount()];

		mainWindow.RASframe.setDefaultCloseOperation(mainWindow.RASframe.DO_NOTHING_ON_CLOSE);
		mainWindow.RASframe.setSize(400, 550);
		mainWindow.RASpanel.setLayout(new GridLayout(3, 0, 5, 5));
		mainWindow.RASpanel.setBounds(100, 100, 100, 100);
		mainWindow.addTableButton();
		mainWindow.addKitchenButton();
		mainWindow.addManagerButton();
		mainWindow.RASframe.add(BorderLayout.CENTER, mainWindow.RASpanel);
		
		mainWindow.RASframe.setVisible(true);
		
		mainWindow.RASframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					mainWindow.order.rasStats.outputStatisticsAtEndOfDay();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainWindow.RASframe.dispose();
				System.exit(0);
				
			}
		});
		

	}

	/*
	 * TABLE HANDLER
	 */
	/**
	 * Adds Table Button Table Button Leads to Host and Server Option
	 * 
	 * @throws Exception
	 */
	public void addTableButton() throws Exception {
		order = new OrderGUI(ras.getTableCount(), ras);
		tableButton = new JButton("Tables");
		
		RASpanel.add(tableButton);
		tableButton.addActionListener(new ActionListener() {

			/**
			 * ActionListener to Open TableWindow on ButtonClick
			 */
			public void actionPerformed(ActionEvent e) {
				showTables();
			}

		});
	}

	/**
	 * Shows the GUI Window
	 */
	public void showTables() {
		if (tableClicks == 0) {
			tableClicks++;
			tablesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			tablesFrame.setSize(400, 550);
			tablesPanel.setLayout(new GridLayout(10, 3, 5, 5));
			
			addTables();
			
			tablesFrame.add(BorderLayout.CENTER, tablesPanel);
			tablesFrame.setVisible(true);
			
		} else {
			
			tablesFrame = new JFrame("Tables Window");
			tablesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			tablesFrame.setSize(400, 550);
			tablesPanel.setLayout(new GridLayout(10, 3, 5, 5));
			tablesFrame.add(BorderLayout.CENTER, tablesPanel);
			tablesFrame.setVisible(true);
		}

	}

	/**
	 * Method to Add Tables to GUI
	 */
	private void addTables() {
		System.out.println("Tables: " + ras.getTableCount());
		for (int i = 0; i < ras.getTableCount(); i++) {
			int k = i + 1;
			button[i] = new Button("Table: " + k);

			button[i].setBackground(Color.red);

			tablesPanel.add(button[i]);

			button[i].addActionListener((ActionListener) new ActionListener() {

				/**
				 * Opens Up Table Window for the Selected Table Host Option - The Window Will
				 * Allow a Host to set the Table as Active, Process Payment, Print Receipt
				 * Server Option - The Window Will Allow a Server to Add Orders, Process
				 * Payment, and Print Receipt
				 */
				public void actionPerformed(ActionEvent e) {

					tableNum = k;

					if (tBTNClicks == 0) {
						tBTNClicks += 1;
						singleTableFrame.setTitle("Table: " + (tableNum));
						singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						singleTableFrame.setSize(400, 550);
						singleTablePanel.setLayout(new GridLayout(2, 0, 5, 5));
						addGreeterButton();
						addWaitstaffButton();
						singleTableFrame.setContentPane(singleTablePanel);
						singleTableFrame.setVisible(true);
					} else {
						greeterWaitFrameSetup();
					}
				}

			});
		}
	}

	/**
	 * READJUST FRAME Adjusts Frame to the Original Frame Without Re-adding Buttons
	 */
	private void greeterWaitFrameSetup() {
		singleTableFrame.setTitle("Table: " + (tableNum));
		singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		singleTableFrame.setSize(400, 550);
		singleTablePanel.setLayout(new GridLayout(2, 0, 5, 5));
		singleTableFrame.setContentPane(singleTablePanel);
		singleTableFrame.setVisible(true);
	}

	/**
	 * Used to Reload Original Frame When Respective Back Button is Pressed
	 */
	private void mainFrameSetup() {
		RASframe.setSize(400, 550);
		RASpanel.setLayout(new GridLayout(3, 0, 5, 5));
		RASpanel.setBounds(100, 100, 100, 100);
		RASframe.add(BorderLayout.CENTER, RASpanel);
		RASframe.setVisible(true);
	}

	/**
	 * Used to Reload Manager Frame When Respective Back Button is Pressed
	 */
	private void manageFrameSetup() {
		manageFrame.setTitle("Management Window");
		manageFrame.setSize(400, 550);
		manageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		managePanel.setLayout(new GridLayout(4, 1, 5, 5));
		manageFrame.setContentPane(managePanel);
		manageFrame.setVisible(true);
	}

	/**
	 * Sets Table to Unassigned Color Coding
	 * 
	 * @param tableNum
	 */
	public void setTableUnassigned(int tableNum) {
		this.button[tableNum - 1].setBackground(Color.red);
	}

	/**
	 * Sets Table to Assigned Color Coding
	 * 
	 * @param tableNum
	 */
	public void setTableAssigned(int tableNum) {
		this.button[tableNum - 1].setBackground(Color.blue);
	}

	/**
	 * Sets Table to Ordered Color Coding
	 * 
	 * @param tableNum
	 */
	public void setTableOrdered(int tableNum) {
		this.button[tableNum - 1].setBackground(Color.yellow);
	}

	/**
	 * Sets Table to Order-Ready Color Coding
	 * 
	 * @param tableNum
	 */
	public void setTableOrderReady(int tableNum) {
		this.button[tableNum - 1].setBackground(Color.cyan);
	}

	/**
	 * Sets Table to Order Completed Color Coding
	 * 
	 * @param tableNum
	 */
	public void setTableOrderCompleted(int tableNum) {
		this.button[tableNum - 1].setBackground(Color.green);
	}

	/*
	 * New Window Table Window
	 */

	/**
	 * Adds Server Button to New Frame
	 */
	private void addWaitstaffButton() {
		JButton waitstaffButton = new JButton("Waitstaff");
		
		singleTablePanel.add(waitstaffButton);
		waitstaffButton.addActionListener(new ActionListener() {

			/**
			 * Handles New Frame On Click If Frame Has Already Been Opened. Separate Method
			 * Handling Frame Opening Will Be Called
			 */
			public void actionPerformed(ActionEvent e) {
				if (sBTNClicks == 0) {
					sBTNClicks += 1;
					singleTableFrame.setTitle("Table: " + (tableNum));
					singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					singleTableFrame.setSize(400, 550);
					serverPanel.setLayout(new GridLayout(5, 0, 5, 5));
					try {
						openOrder(serverPanel);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					clearTable(serverPanel);
					backButton(serverPanel, 1);
					singleTableFrame.setContentPane(serverPanel);
					singleTableFrame.setVisible(true);
				} else {
					serverFrameSetup();
				}
			}

			/**
			 * READJUST FRAME Adjusts Frame to the Server Without Re-adding Buttons
			 */
			private void serverFrameSetup() {
				singleTableFrame.setTitle("Table: " + (tableNum));
				singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				singleTableFrame.setSize(400, 550);
				serverPanel.setLayout(new GridLayout(5, 0, 5, 5));
				singleTableFrame.setContentPane(serverPanel);
				singleTableFrame.setVisible(true);
			}
		});
	}

	/**
	 * Adds Host Button to New Frame
	 */
	private void addGreeterButton() {
		JButton greeter = new JButton("Greeter");
		
		singleTablePanel.add(greeter);
		greeter.addActionListener(new ActionListener() {

			/**
			 * Handles New Frame On Click If Frame Has Already Been Opened. Separate Method
			 * Handling Frame Opening Will Be Called
			 */
			public void actionPerformed(ActionEvent e) {
				if (hBTNClicks == 0) {
					hBTNClicks += 1;
					singleTableFrame.setTitle("Table: " + (tableNum));
					singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					singleTableFrame.setSize(400, 550);
					hostPanel.setLayout(new GridLayout(5, 0, 5, 5));
					assignTable();
					clearTable(hostPanel);
					try {
						openOrder(hostPanel);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					backButton(hostPanel, 1);
					singleTableFrame.setContentPane(hostPanel);
					singleTableFrame.setVisible(true);
				} else {
					hostFrameSetup();
				}
			}

			/**
			 * READJUST FRAME Adjusts Frame to the Host Without Re-adding Buttons
			 */
			private void hostFrameSetup() {
				singleTableFrame.setTitle("Table: " + (tableNum));
				singleTableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				singleTableFrame.setSize(400, 550);
				hostPanel.setLayout(new GridLayout(5, 0, 5, 5));
				singleTableFrame.setContentPane(hostPanel);
				singleTableFrame.setVisible(true);
			}
		});
	}

	/**
	 * HOST BUTTON Button to Assign a Group to Table and Signify the Table is Ready
	 * to be Served
	 */
	private void assignTable() {
		JButton assignTableButton = new JButton("Assign Table");
		
		hostPanel.add(assignTableButton);
		assignTableButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setTableAssigned(tableNum);
			}

		});
	}

	/**
	 * HOST AND SERVER BUTTON Button to Take Orders of That Table
	 * 
	 * @throws Exception
	 */
	private void openOrder(JPanel panel) throws Exception {
		JButton orderButton = new JButton("Order");
		
		panel.add(orderButton);
		orderButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				order.showOrderWindow(tableNum);
			}
		});
	}

	/**
	 * HOST AND SERVER BUTTON Button to Clear Group From Table, Unassign It, and Get
	 * it Ready for Group
	 */
	private void clearTable(JPanel panel) {
		JButton clearTableButton = new JButton("Clear Table");
		
		panel.add(clearTableButton);
		clearTableButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setTableUnassigned(tableNum);

			}
		});
	}

	/**
	 * HOST AND SERVER PANEL BUTTON Back Button That Reloads the Main Frame
	 */
	public void backButton(JPanel panel, int num) {
		JButton backButton = new JButton("Back");
		
		panel.add(backButton);
		backButton.addActionListener(new ActionListener() {

			/**
			 * Handles Changing of the Frame on Click Sends User Back to Main Frame
			 */
			public void actionPerformed(ActionEvent e) {
				if (num == 0) {
					mainFrameSetup();
				} else if (num == 1) {
					greeterWaitFrameSetup();
				}
			}
		});
	}

	/*
	 * KITCHEN HANDLER
	 */
	/**
	 * Add Kitchen Button
	 */
	private void addKitchenButton() {
		kitchen = new JButton("Kitchen");
		
		RASpanel.add(kitchen);
	}

	/*
	 * MANAGER HANDLER
	 */
	/**
	 * Add Manager Button
	 */
	private void addManagerButton() {
		manager = new JButton("Manager");
		
		RASpanel.add(manager);

		manager.addActionListener(new ActionListener() {

			int i = 0;

			public void actionPerformed(ActionEvent arg0) {
				if (i == 0) {
					i++;
					manageFrame.setTitle("Management Window");
					manageFrame.setSize(400, 550);
					manageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					managePanel.setLayout(new GridLayout(4, 1, 5, 5));
					statsButton();
					endOfDayStatsButton();
					dayBeforeStatsButton();
					manageFrame.setContentPane(managePanel);
					manageFrame.setVisible(true);
				} else {
					manageFrameSetup();
				}

			}

			/**
			 * Prints the Statistics of the Restaurant
			 */
			public void statsButton() {
				JButton statsButton = new JButton("View Statistics");
				managePanel.add(statsButton);

				statsButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							order.rasStats.outputStatistics();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			
			/**
			 * Prints the Statistics of the Restaurant
			 */
			public void endOfDayStatsButton() {
				JButton statsButton = new JButton("View End of Day Statistics");
				managePanel.add(statsButton);

				statsButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							order.rasStats.outputStatisticsAtEndOfDay();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				});
			}

			/**
			 * Prints Day Before Statistics
			 */
			public void dayBeforeStatsButton() {
				JButton dayBeforeStats = new JButton("View Yesterdays Stats");
				managePanel.add(dayBeforeStats);

				dayBeforeStats.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							order.rasStats.outputYesterdayStatistics();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}

				});
			}

		});
	}

}
