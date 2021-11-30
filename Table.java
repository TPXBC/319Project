package data;

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

	int indexCounter;
	int tableNum;
	int orderCounter = 0;

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
	 */
	public void printReceipt() {
		if (!menuItem.isEmpty()) {
			if (tableNum > 10) {
				System.out.printf(" ___Table: %d #: %d %8s_\n", tableNum, orderCounter, "ORDER RECEIPT");
			} else {
				System.out.printf(" ____Table: %d #: %d %7s_\n", tableNum, orderCounter, "ORDER RECEIPT");
			}
			for (int i = 0; i < menuItem.size(); i++) {
				String item = menuItem.get(i);
				double itemPrice = menuPrice.get(i);
				String output = String.format("|%-25s - %.2f|", item, itemPrice);
				System.out.println(output);
			}
			System.out.println("|--------------------------------|");
			System.out.printf("|%-25s - %.2f|\n", "Tip", tipAmnt);
			System.out.println("|--------------------------------|");
			if (subTotal >= 100.00 && subTotal < 1000.00) {
				System.out.printf("|%-22s - %.2f |\n", "Subtotal", subTotal);
			} else if (subTotal >= 1000.00) {
				System.out.printf("|%-21s - %.2f |\n", "Subtotal", subTotal);
			} else {
				System.out.printf("|%-23s - %.2f |\n", "Subtotal", subTotal);
			}
			System.out.println("|________________________________|");

		} else {
			System.out.println("NOTHING ORDERED YET");
		}
	}

	/**
	 * Return SubTotal
	 */
	public double printSubTotal() {
		// TODO Auto-generated method stub
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

	/*
	 * FOR DAVID AVERAGE METHOD
	 */
	public void GetAverageItemPrice() {
		double avgPriceTest = 0.0;
		for (int i = 0; i < menuPrice.size(); i++) {
			avgPriceTest = avgPriceTest + menuPrice.get(i);
		}
		System.out.printf("%.2f \n", avgPriceTest / menuPrice.size());
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