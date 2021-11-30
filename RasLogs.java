package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class RasLogs <K,V> {
	
	ArrayList<Double> yesterdaysOrders = new ArrayList<Double>();
	ArrayList<Double> dailyOrder = new ArrayList<Double>();
	ArrayList<Double> weeklyOrder = new ArrayList<Double>();
	ArrayList<Double> monthlyOrder = new ArrayList<Double>();
	ArrayList<Double> yearlyOrder = new ArrayList<Double>();
	
	BSTDictionary<K, V>	dayBeforeItemCount = new BSTDictionary<>();
	BSTDictionary<K,V> dailyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> weeklyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> monthlyItemCount = new BSTDictionary<> ();
	BSTDictionary<K,V> yearlyItemCount = new BSTDictionary<> ();
	
	int daysofWeek = 0;
	int daysofMonth = 0;
	int daysofYear = 0;
	
	BufferedReader in;
	PrintWriter out;
	
	File statsFile;
	File pricesFile;
	
	private String itemCounters[] = new String[]{"--Daily--", "--Weekly--", "--Monthly--", "--Yearly--", "--End--"};
	
	
	/**
	 * Constructor Creates Files to Read and Write to in Selected Directory
	 * @throws Exception 
	 */
	public RasLogs () throws Exception {
		statsFile = new File("StatsFile.txt");
		if(statsFile.createNewFile()) {
			System.out.println("Stats File Created In " + statsFile.getAbsolutePath());
		} else {
			System.out.println("Stats File Already Exists In " + statsFile.getAbsolutePath() + ", Loading File Info");
			
			loadFileItemCountStats(dayBeforeItemCount, itemCounters[0], itemCounters[1]);
			loadFileItemCountStats(weeklyItemCount, itemCounters[1], itemCounters[2], daysofWeek);
			loadFileItemCountStats(monthlyItemCount, itemCounters[2], itemCounters[3], daysofMonth);
			loadFileItemCountStats(yearlyItemCount, itemCounters[3], itemCounters[4], daysofYear);
			
		}
		
		pricesFile = new File("PricesFile.txt");
		if(pricesFile.createNewFile()) {
			System.out.println("Prices File Created In " + pricesFile.getAbsolutePath());
		} else {
			System.out.println("Stats File Already Exists In " + pricesFile.getAbsolutePath() + ", Loading File Info");
			
			loadFilePricingStats(yesterdaysOrders, itemCounters[0], itemCounters[1]);
			loadFilePricingStats(weeklyOrder, itemCounters[1], itemCounters[2], daysofWeek);
			loadFilePricingStats(monthlyOrder, itemCounters[2], itemCounters[3], daysofMonth);
			loadFilePricingStats(yearlyOrder, itemCounters[3], itemCounters[4], daysofYear);
		}
		
	}
	
	public void loadFileItemCountStats(BSTDictionary<K,V> menu, String start, String end) throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;
		System.out.println("Load File Stats Method Ran");
		
		while(!(in.readLine().equals(start))) {}
		try {
			while(!(line = in.readLine()).equals(end)) {
				String items[] = line.split("\\s+");
				K menuItem = (K)items[1];
				V itemCount = (V) items[3];
				menu.put(menuItem, itemCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Alt Method to Handle Track of Time
	 * @param menu
	 * @param start
	 * @param end
	 * @param dayofWeek
	 * @throws Exception
	 */
	public void loadFileItemCountStats(BSTDictionary<K,V> menu, String start, String end, int dayofWeek) throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;

		while(!(in.readLine().equals(start))) {}
		line = in.readLine();
		String[] day = line.split("\\s+");
		dayofWeek = Integer.parseInt(day[1]);
		try {
			while(!(line = in.readLine()).equals(end)) {
				String items[] = line.split("\\s+");
				K menuItem = (K)items[1];
				V itemCount = (V) items[3];
				menu.put(menuItem, itemCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Item Counts Loaded");
		
	}
	
	/**
	 * Method Only Loads the Day Before Order Prices
	 * @param list
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public void loadFilePricingStats(ArrayList<Double> list, String start, String end) throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;
		
		while(!(in.readLine().equals(start))) {}
		try {
			while(!(line = in.readLine()).equals(end)) {
				Double price = Double.parseDouble(line);
				list.add(price);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Alt Method to Handle Pricing and Track of Time
	 * @param list
	 * @param start
	 * @param end
	 * @param dayofWeek
	 * @throws Exception
	 */
	public void loadFilePricingStats(ArrayList<Double> list, String start, String end, int dayofWeek) throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;
		
		while(!(in.readLine().equals(start))) {}
		line = in.readLine();
		String[] day = line.split("\\s+");
		if (dayofWeek != Integer.parseInt(day[1])) {
			System.out.println("Error in loadFilePricingStats");
			System.exit(0);
		}
		try {
			while(!(line = in.readLine()).equals(end)) {
				Double price = Double.parseDouble(line);
				list.add(price);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Item Pricing Loaded Loaded");
		
	}
	
	/**
	 * Adds Order Number and Order Total to the Logs
	 * @param order
	 * @param orderTotal
	 */
	public void addPriceStatistics(Double orderTotal) {
		dailyOrder.add(orderTotal);
		weeklyOrder.add(orderTotal);
		monthlyOrder.add(orderTotal);
		yearlyOrder.add(orderTotal);
	}
	
	/**
	 * Adds Items to Dictionary Based Off Number of Times Ordered
	 * @param item
	 */
	public void addItemCount(String item) {
		K conItem = (K) item;
		Integer count = 0;
		
		if (dailyItemCount.get(conItem) == null)
		{
			dailyItemCount.put(conItem, (V) count);
		} else {
			Integer counter = (Integer)dailyItemCount.get(conItem);
			counter++;
			dailyItemCount.put(conItem, (V) counter);
		}
		
		if (weeklyItemCount.get(conItem) == null)
		{
			weeklyItemCount.put(conItem, (V) count);
		} else {
			Integer counter = (Integer)weeklyItemCount.get(conItem);
			counter++;
			weeklyItemCount.put(conItem, (V) counter);
		}
		
		if (monthlyItemCount.get(conItem) == null) 
		{
			monthlyItemCount.put(conItem, (V) count);
		} else {
			Integer counter = (Integer)monthlyItemCount.get(conItem);
			counter++;
			monthlyItemCount.put(conItem, (V) counter);
		}
		
		if (yearlyItemCount.get(conItem) == null)
		{
			yearlyItemCount.put(conItem, (V) count);
		} else {
			Integer counter = (Integer)yearlyItemCount.get(conItem);
			counter++;
			yearlyItemCount.put(conItem, (V) counter);
		}	
	}
	
	/**
	 * Public Method - Returns Average Price Per Order From Today
	 * @return
	 */
	public void getAvgDailyPrices() {
		out.printf("Averages Daily Prices - $%.2f\n", getAveragePrice(dailyOrder));
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Week
	 * @return
	 */
	public void getAvgWeeklyPrices() {
		out.printf("Averages Weekly Prices - $%.2f\n", getAveragePrice(weeklyOrder));
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Month
	 * @return
	 */
	public void getAvgMonthlyPrices() {
		out.printf("Averages Monthly Prices - $ %.2f\n", getAveragePrice(monthlyOrder));
	}
	
	/**
	 * Public Method - Returns Average Price Per Order Over Past Year
	 * @return
	 */
	public void getAvgYearlyPrices() {
		out.printf("Averages Yearly Prices - $ %.2f\n", getAveragePrice(yearlyOrder));
	}
	
	
	/**
	 * Private Method - Returns Pricing Average Per Order of Dictionary
	 * @param order
	 * @return
	 */
	private double getAveragePrice(ArrayList<Double> order) {
		double priceTotal = 0.0;
		for (Iterator<Double> orders = order.iterator(); orders.hasNext();) {
			Double itemPrice = orders.next();
			priceTotal = priceTotal + itemPrice;
		}
		return priceTotal / order.size();
	}
	
	/**
	 * Outputs Item Count of Each Ordered Menu Item
	 * @param timespan
	 * @param start
	 */
	public void outputItemCounts(BSTDictionary<K, V> timespan, String start) {
		
		if (start.equals(itemCounters[1])) { out.printf("%s\nDay: %d\n", start, ++daysofWeek); }
		else if (start.equals(itemCounters[2])) { out.printf("%s\nDay: %d\n", start, ++daysofMonth); }
		else if (start.equals(itemCounters[3])) { out.printf("%s\nDay: %d\n", start, ++daysofYear); }
		else if (start.equals(itemCounters[0])) { out.printf("%s\n", start); }
		else { System.out.println("Error in RasLogs.outputItemCounts()"); System.exit(0); }
		
		for (Iterator<K> itemCounter = timespan.keys(); itemCounter.hasNext();) {
			K item = itemCounter.next();
			V count = timespan.get(item);
			String itemString = (String) item;
			Integer counter = (Integer) count;
			out.printf("Item: %15s Count: %3d\n", itemString, counter);
			
		}
		
		
	}
	
	/**
	 * Outputs Statistics to Stats File 
	 * @throws FileNotFoundException
	 */
	public void outputStatistics() throws FileNotFoundException {
		out = new PrintWriter(statsFile);
		getAvgDailyPrices();
		getAvgWeeklyPrices();
		getAvgMonthlyPrices();
		getAvgYearlyPrices();
		outputItemCounts(dailyItemCount, itemCounters[0]);
		outputItemCounts(weeklyItemCount, itemCounters[1]);
		outputItemCounts(monthlyItemCount, itemCounters[2]);
		outputItemCounts(yearlyItemCount, itemCounters[3]);
		out.println(itemCounters[4]);
		out.flush();
		out.close();
		System.out.println("Statistics Saved To " + statsFile.getAbsolutePath());
		
		out = new PrintWriter(pricesFile);
		outputOrderPrices(dailyOrder, itemCounters[0]);
		outputOrderPrices(weeklyOrder, itemCounters[1]);
		outputOrderPrices(monthlyOrder, itemCounters[2]);
		outputOrderPrices(yearlyOrder, itemCounters[3]);
		out.println(itemCounters[4]);
		out.flush();
		out.close();
		
		System.out.println("Prices Saved To " + pricesFile.getAbsolutePath());
	}
	
	/**
	 * Outputs Order Prices to Pricing File In Order to Keep Averages Accurate
	 * @param timespan
	 * @param start
	 */
	private void outputOrderPrices(ArrayList<Double> timespan, String start) {
		if (start.equals(itemCounters[1])) { out.printf("%s\nDay: %d\n", start, daysofWeek); }
		else if (start.equals(itemCounters[2])) { out.printf("%s\nDay: %d\n", start, daysofMonth); }
		else if (start.equals(itemCounters[3])) { out.printf("%s\nDay: %d\n", start, daysofYear); }
		else if (start.equals(itemCounters[0])) { out.printf("%s\n", start); }
		else { System.out.println("Error in RasLogs.outputOrderPrices()"); System.exit(0); }
		
		for (Iterator<Double> orders = timespan.iterator(); orders.hasNext();) {
			double itemPrice = orders.next();
			out.println(itemPrice);
		}
	}
	
	
	
}

