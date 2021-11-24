package data;

import java.util.Iterator;

public class RasLogs <K,V> {
	
	BSTDictionary<K,V> dailyOrder = new BSTDictionary<> ();
	BSTDictionary<K,V> weeklyOrder = new BSTDictionary<> ();
	BSTDictionary<K,V> monthlyOrder = new BSTDictionary<> ();
	BSTDictionary<K,V> yearlyOrder = new BSTDictionary<> ();
	
	
	BSTDictionary<K,V> dailyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> weeklyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> monthlyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> yearlyItemCount = new BSTDictionary<> ();
	
	/**
	 * Constructor Creates Files to Read and Write to in Selected Directory
	 */
	public RasLogs () {
		
	}
	
	/**
	 * Adds Order Number and Order Total to the Logs
	 * @param order
	 * @param orderTotal
	 */
	public void addPriceStatistics(K order, V orderTotal) {
		dailyOrder.put(order, orderTotal);
		weeklyOrder.put(order, orderTotal);
		monthlyOrder.put(order, orderTotal);
		yearlyOrder.put(order, orderTotal);
	}
	
	/**
	 * Adds Items to Dictionary Based Off Number of Times Ordered
	 * @param item
	 */
	public void addItemCount(K item) {
		Integer count = 0;
		
		if (dailyOrder.get(item) == null)
		{
			dailyOrder.put(item, (V) count);
		} else {
			Integer counter = (Integer)dailyOrder.get(item);
			counter++;
			dailyOrder.put(item, (V) counter);
		}
		
		if (weeklyOrder.get(item) == null)
		{
			weeklyOrder.put(item, (V) count);
		} else {
			Integer counter = (Integer)weeklyOrder.get(item);
			counter++;
			weeklyOrder.put(item, (V) counter);
		}
		
		if (monthlyOrder.get(item) == null) 
		{
			monthlyOrder.put(item, (V) count);
		} else {
			Integer counter = (Integer)monthlyOrder.get(item);
			counter++;
			monthlyOrder.put(item, (V) counter);
		}
		
		if (yearlyOrder.get(item) == null)
		{
			yearlyOrder.put(item, (V) count);
		} else {
			Integer counter = (Integer)yearlyOrder.get(item);
			counter++;
			yearlyOrder.put(item, (V) counter);
		}	
	}
	
	/**
	 * Public Method - Returns Average Price Per Order From Today
	 * @return
	 */
	public double getAvgDailyPrices() {
		return getAverage(dailyOrder);
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Week
	 * @return
	 */
	public double getAvgWeeklyPrices() {
		return getAverage(weeklyOrder);
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Month
	 * @return
	 */
	public double getAvgMonthlyPrices() {
		return getAverage(monthlyOrder);
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Year
	 * @return
	 */
	public double getAvgYearlyPrices() {
		return getAverage(yearlyOrder);
	}
	
	
	/**
	 * Private Method - Returns Pricing Average Per Order of Dictionary
	 * @param order
	 * @return
	 */
	private double getAverage(BSTDictionary<K,V> order) {
		double priceTotal = 0.0;
		for (Iterator<K>orders=order.keys(); orders.hasNext();) {
			K menuItem = orders.next();
			double menuItemPrice = (double)order.get(menuItem);
			priceTotal = priceTotal + menuItemPrice;
		}
		return priceTotal/order.noKeys();
	}
	
	
	
	
}

