package data;

public interface Order {
	
	public String addItem(BSTDictionary menu, String menuItem);
	public double removeItem(String item);
	public void addTip(double tipAmnt);
	public void printReceipt();
	public double printSubTotal();
}
