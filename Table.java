package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * Node Like Class Handling Per Table Orders
 * 
 */
public class Table implements Order {
	private ArrayList<String> menuItem;
	private ArrayList<Double> menuPrice;

	private String indexItem;

	private double subTotal;
	private double tipAmnt;

	private int indexCounter;
	private int tableNum;
	private int orderCounter = 0;
	
	private PrintWriter out;
	private File file;

	/**
	 * No Param Constructor
	 */
	public Table() {
		super();
		menuItem = new ArrayList<String>();
		menuPrice = new ArrayList<Double>();
		subTotal = 0;
		tipAmnt = 0;
	}

	/**
	 * Constructor with TableNum
	 * 
	 * @param tableNum
	 */
	public Table(int tableNum) {
		super();
		this.tableNum = tableNum + 1;
		menuItem = new ArrayList<String>();
		menuPrice = new ArrayList<Double>();
		subTotal = 0;
		tipAmnt = 0;
	}

	/**
	 * Add Item to Order
	 */
	public String addItem(BSTDictionary menu, String Item) {
		// TODO Auto-generated method stub
		Double itemPrice = Double.parseDouble((String) menu.get(Item));
		this.menuItem.add(Item);
		this.menuPrice.add(itemPrice);
		subTotal = subTotal + itemPrice;
		return Item;
	}

	/**
	 * Removes Item From Order
	 */
	public double removeItem(String item) {
		// TODO Auto-generated method stub
		int index = getIndex(item);
		menuItem.remove(index);
		double price = menuPrice.get(index);
		menuPrice.remove(index);
		subTotal = subTotal - price;
		return subTotal;
	}

	/**
	 * Adds Tip to Subtotal Also Adds to Server Assigned to Table
	 */
	public void addTip(double tipAmnt) {
		// TODO Auto-generated method stub
		this.tipAmnt = tipAmnt;
		subTotal = subTotal + this.tipAmnt;
	}

	/**
	 * Prints Receipt in Console/ Leaves Receipt File in Directory
	 * @throws Exception 
	 */
	public void printReceipt() throws Exception {
		file = new File("receipts");
		file.mkdir();
		if (!menuItem.isEmpty()) {
			if (tableNum > 10) {
				String receiptTitle = String.format("receipts\\Table%dOrder%d%s.txt", tableNum, orderCounter, "ORDER_RECEIPT");
				file = new File(receiptTitle);
				out = new PrintWriter(file);
				out.printf(" ___Table:_%d_#:_%d_%8s_\n", tableNum, orderCounter, "ORDER_RECEIPT");
				
			} else {
				String receiptTitle = String.format("receipts\\Table%dOrder%d%s.txt", tableNum, orderCounter, "ORDER_RECEIPT");
				file = new File(receiptTitle);
				out = new PrintWriter(file);
				out.printf(" ___Table:_%d_#:_%d_%7s_\n", tableNum, orderCounter, "ORDER_RECEIPT");
			}
			for (int i = 0; i < menuItem.size(); i++) {
				String item = menuItem.get(i);
				double itemPrice = menuPrice.get(i);
				String output = String.format("|%-25s - %.2f|", item, itemPrice);
				out.println(output);
			}
			out.println("|--------------------------------|");
			if (tipAmnt >= 1000.00 && tipAmnt < 10000.00) {
				out.printf("|%-22s - %.2f|\n", "Tip", tipAmnt);
			} else if (tipAmnt >= 100.00) {
				out.printf("|%-23s - %.2f|\n", "Tip", tipAmnt);
			} else if (tipAmnt >= 10.00) {
				out.printf("|%-24s - %.2f|\n", "Tip", tipAmnt);
			} else if (tipAmnt < 10.00 && tipAmnt >= 0.00) {
				out.printf("|%-25s - %.2f|\n", "Tip", tipAmnt);
			} else {
				System.out.println("Invalid Tip Amount or Tip to High");
			}

			out.println("|--------------------------------|");
			if (subTotal >= 100.00 && subTotal < 1000.00) {
				out.printf("|%-22s - %.2f |\n", "Subtotal", subTotal);
			} else if (subTotal >= 1000.00) {
				out.printf("|%-21s - %.2f |\n", "Subtotal", subTotal);
			} else {
				out.printf("|%-23s - %.2f |\n", "Subtotal", subTotal);
			}
			out.println("|________________________________|");

			System.out.printf("Table: %d Order #: %d Printed to Directory\n", tableNum, orderCounter);
		} else {
			out.println("NOTHING ORDERED YET");
		}
		out.flush();
		out.close();
	}

	/**
	 * Return SubTotal
	 */
	public double printSubTotal() {
		return subTotal;
	}

	/**
	 * Resets Arrays
	 */
	public void clearTable() {
		menuItem = new ArrayList<String>();
		menuPrice = new ArrayList<Double>();
		subTotal = 0;
		tipAmnt = 0;
	}

	/**
	 * Returns Index From Item
	 * 
	 * @param item
	 * @return
	 */
	private int getIndex(String item) {
		indexItem = menuItem.get(indexCounter);
		if (indexItem.equals(item)) {
			int i = indexCounter;
			indexCounter = 0;
			return i;
		} else {
			indexCounter++;
			return getIndex(item);
		}
	}

	/**
	 * Returns Amount of Orders to Occur at This Table
	 * 
	 * @return
	 */
	public int getOrderCounter() {
		return orderCounter;
	}

	/**
	 * Used to Increase Number of Orders at This Table
	 */
	public void increaseOrderCounter() {
		orderCounter++;
	}

}