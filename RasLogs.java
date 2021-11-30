package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class RasLogs<K, V> {

	ArrayList<Double> yesterdaysOrders = new ArrayList<Double>();
	ArrayList<Double> dailyOrder = new ArrayList<Double>();
	ArrayList<Double> weeklyOrder = new ArrayList<Double>();
	ArrayList<Double> monthlyOrder = new ArrayList<Double>();
	ArrayList<Double> yearlyOrder = new ArrayList<Double>();

	BSTDictionary<K, V> dayBeforeItemCount = new BSTDictionary<>();
	BSTDictionary<K, V> dailyItemCount = new BSTDictionary<>();
	BSTDictionary<K, V> weeklyItemCount = new BSTDictionary<>();
	BSTDictionary<K, V> monthlyItemCount = new BSTDictionary<>();
	BSTDictionary<K, V> yearlyItemCount = new BSTDictionary<>();

	int daysofWeek = 0;
	int daysofMonth = 0;
	int daysofYear = 0;

	BufferedReader in;
	PrintWriter out;

	File statsFile;
	File pricesFile;
	File yesterdayStatsFile;

	private String itemCounters[] = new String[] { "--Daily--", "--Weekly--", "--Monthly--", "--Yearly--", "--End--" };

	/**
	 * Constructor Creates Files to Read and Write to in Selected Directory
	 * 
	 * @throws Exception
	 */
	public RasLogs() throws Exception {

		statsFile = new File("StatsFile.txt");
		if (statsFile.createNewFile()) {
			System.out.println("Stats File Created In " + statsFile.getAbsolutePath());
		} else {
			System.out.println("Stats File Already Exists In " + statsFile.getAbsolutePath() + ", Loading File Info");

			loadFileItemCountStats(dayBeforeItemCount, itemCounters[0], itemCounters[1]);
			loadFileItemCountStats(weeklyItemCount, itemCounters[1], itemCounters[2], daysofWeek);
			loadFileItemCountStats(monthlyItemCount, itemCounters[2], itemCounters[3], daysofMonth);
			loadFileItemCountStats(yearlyItemCount, itemCounters[3], itemCounters[4], daysofYear);
		}

		pricesFile = new File("PricesFile.txt");
		if (pricesFile.createNewFile()) {
			System.out.println("Prices File Created In " + pricesFile.getAbsolutePath());
		} else {
			System.out.println("Stats File Already Exists In " + pricesFile.getAbsolutePath() + ", Loading File Info");

			loadFilePricingStats(yesterdaysOrders, itemCounters[0], itemCounters[1]);
			loadFilePricingStats(weeklyOrder, itemCounters[1], itemCounters[2], daysofWeek);
			loadFilePricingStats(monthlyOrder, itemCounters[2], itemCounters[3], daysofMonth);
			loadFilePricingStats(yearlyOrder, itemCounters[3], itemCounters[4], daysofYear);
		}

		yesterdayStatsFile = new File("YesterdayStatsFile.txt");
		if (yesterdayStatsFile.createNewFile()) {
			System.out.println("Yesterday's Stats File Created In " + yesterdayStatsFile.getAbsolutePath());
		} else {
			System.out.println("Yesterday's Stats File Already Exists In " + yesterdayStatsFile.getAbsolutePath());
		}

	}

	/**
	 * Loads The Previous Days Item Counts and Stores In Respective Structure
	 * @param menu
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public void loadFileItemCountStats(BSTDictionary<K, V> menu, String start, String end) throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;
		System.out.println("Load File Stats Method Ran");

		while (!(in.readLine().equals(start))) {
		}
		try {
			while (!(line = in.readLine()).equals(end)) {
				if (line.equals("No Items Purchased Yet Today")) {
					return;
				}
				String items[] = line.split("\\s+");
				K menuItem = (K) items[1];
				V itemCount = (V) items[3];
				menu.put(menuItem, itemCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Alt Method to Handle Track of Time
	 * 
	 * @param menu
	 * @param start
	 * @param end
	 * @param dayofWeek
	 * @throws Exception
	 */
	public void loadFileItemCountStats(BSTDictionary<K, V> menu, String start, String end, int dayofWeek)
			throws Exception {
		in = new BufferedReader(new FileReader(statsFile));
		String line = null;

		while (!(in.readLine().equals(start))) {
		}
		line = in.readLine();
		
		/**
		 * Handles Output From Empty Structures
		 */
		if (line.equals("No Items Purchased Yet This Week")) {
			return;
		} else if (line.equals("No Items Purchased Yet This Month")) {
			return;
		} else if (line.equals("No Items Purchased Yet This Past Year")) {
			return;
		}

		String[] day = line.split("\\s+");

		if (start.equals(itemCounters[1])) {

			if ((Integer.parseInt(day[1]) % 7) == 0) {
				System.out.println("Method Stopped");
				return;
			} else {
				daysofWeek = Integer.parseInt(day[1]);
			}

		} else if (start.equals(itemCounters[2])) {

			if ((Integer.parseInt(day[1]) % 30) == 0) {
				return;
			} else {
				daysofMonth = Integer.parseInt(day[1]);
			}

		} else if (start.equals(itemCounters[3])) {

			if ((Integer.parseInt(day[1]) % 365) == 0) {
				return;
			} else {
				daysofYear = Integer.parseInt(day[1]);
			}

		}

		try {
			while (!(line = in.readLine()).equals(end)) {
				String items[] = line.split("\\s+");
				K menuItem = (K) items[1];
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
	 * 
	 * @param list
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public void loadFilePricingStats(ArrayList<Double> list, String start, String end) throws Exception {
		in = new BufferedReader(new FileReader(pricesFile));
		String line = null;

		while (!(in.readLine().equals(start))) {
		}
		try {
			while (!(line = in.readLine()).equals(end)) {
				if (line.equals("No Orders Yet Today")) {
					return;
				}
				Double price = Double.parseDouble(line);
				list.add(price);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Alt Method to Handle Pricing and Track of Time
	 * 
	 * @param list
	 * @param start
	 * @param end
	 * @param dayofWeek
	 * @throws Exception
	 */
	public void loadFilePricingStats(ArrayList<Double> list, String start, String end, int dayofWeek) throws Exception {
		in = new BufferedReader(new FileReader(pricesFile));
		String line = null;

		while (!(in.readLine().equals(start))) {
		}
		line = in.readLine();

		/**
		 * Handle Output From Blank Structures
		 */
		if (line.equals("No Orders Yet This Week")) {
			return;
		} else if (line.equals("No Orders Yet This Month")) {
			return;
		} else if (line.equals("No Order Yet This Year")) {
			return;
		}

		String[] day = line.split("\\s+");

		if (start.equals(itemCounters[1])) {

			if ((Integer.parseInt(day[1]) % 7) == 0) {
				daysofWeek = 0;
				return;
			}
			if (daysofWeek != Integer.parseInt(day[1])) {
				System.out.printf("Day: Variable - %d : Array - %d", daysofWeek, Integer.parseInt(day[1]));
				System.out.println("Error: Days Between Files Do Not Match");
				System.exit(0);
			}

		} else if (start.equals(itemCounters[2])) {

			if ((Integer.parseInt(day[1]) % 30) == 0) {
				daysofMonth = 0;
				return;
			}
			if (daysofMonth != Integer.parseInt(day[1])) {
				System.out.printf("Month: Variable - %d : Array - %d", daysofMonth, Integer.parseInt(day[1]));
				System.out.println("Error: Days Between Files Do Not Match");
				System.exit(0);
			}

		} else if (start.equals(itemCounters[3])) {

			if ((Integer.parseInt(day[1]) % 365) == 0) {
				daysofYear = 0;
				return;
			}
			if (daysofYear != Integer.parseInt(day[1])) {
				System.out.printf("Year: Variable - %d : Array - %d", daysofYear, Integer.parseInt(day[1]));
				System.out.println("Error: Days Between Files Do Not Match");
				System.exit(0);
			}

		}

		try {
			while (!(line = in.readLine()).equals(end)) {
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
	 * 
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
	 * 
	 * @param item
	 */
	public void addItemCount(String item) {
		K conItem = (K) item;
		Integer count = 1;
		Integer counts = 0;

		if (dailyItemCount.get(conItem) == null) {
			dailyItemCount.put(conItem, (V) count);
		} else {
			V counter = dailyItemCount.get(conItem);
			counts = (Integer) counter;
			counts++;
			dailyItemCount.put(conItem, (V) counts);
		}

		if (weeklyItemCount.get(conItem) == null) {
			weeklyItemCount.put(conItem, (V) count);
		} else {
			V counter = weeklyItemCount.get(conItem);
			counts = Integer.parseInt(String.valueOf(counter));
			counts++;
			weeklyItemCount.put(conItem, (V) counts);
		}

		if (monthlyItemCount.get(conItem) == null) {
			monthlyItemCount.put(conItem, (V) count);
		} else {
			V counter = monthlyItemCount.get(conItem);
			counts = Integer.parseInt(String.valueOf(counter));
			counts++;
			monthlyItemCount.put(conItem, (V) counts);
		}

		if (yearlyItemCount.get(conItem) == null) {
			yearlyItemCount.put(conItem, (V) count);
		} else {
			V counter = yearlyItemCount.get(conItem);
			counts = Integer.parseInt(String.valueOf(counter));
			counts++;
			yearlyItemCount.put(conItem, (V) counts);
		}
	}

	/**
	 * Public Method - Returns Average Price Per Order From Today
	 * 
	 * @return
	 */
	public void getAvgDailyPrices() {
		out.printf("Averages Daily Prices - $%.2f\n", getAveragePrice(dailyOrder));
	}

	/**
	 * Public Method - Returns Average Price Per Order Over Past Week
	 * 
	 * @return
	 */
	public void getAvgWeeklyPrices() {
		out.printf("Averages Weekly Prices - $%.2f\n", getAveragePrice(weeklyOrder));
	}

	/**
	 * Public Method - Returns Average Price Per Order Over Past Month
	 * 
	 * @return
	 */
	public void getAvgMonthlyPrices() {
		out.printf("Averages Monthly Prices - $ %.2f\n", getAveragePrice(monthlyOrder));
	}

	/**
	 * Public Method - Returns Average Price Per Order Over Past Year
	 * 
	 * @return
	 */
	public void getAvgYearlyPrices() {
		out.printf("Averages Yearly Prices - $ %.2f\n", getAveragePrice(yearlyOrder));
	}

	/**
	 * Private Method - Returns Pricing Average Per Order of Dictionary
	 * 
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
	 * 
	 * @param timespan
	 * @param start
	 */
	public void outputItemCounts(BSTDictionary<K, V> timespan, String start) {

		if (start.equals(itemCounters[1])) {
			out.printf("%s\nDay: %d\n", start, ++daysofWeek);
		} else if (start.equals(itemCounters[2])) {
			out.printf("%s\nDay: %d\n", start, ++daysofMonth);
		} else if (start.equals(itemCounters[3])) {
			out.printf("%s\nDay: %d\n", start, ++daysofYear);
		} else if (start.equals(itemCounters[0])) {
			out.printf("%s\n", start);
		} else {
			System.out.println("Error in RasLogs.outputItemCounts()");
			System.exit(0);
		}

		for (Iterator<K> itemCounter = timespan.keys(); itemCounter.hasNext();) {
			K item = itemCounter.next();
			V count = timespan.get(item);
			String itemString = (String) item;
			Integer counter = Integer.parseInt(String.valueOf(count));
			out.printf("Item: %15s Count: %3d\n", itemString, counter);

		}

	}

	/**
	 * Outputs Statistics to Stats File
	 * 
	 * @throws FileNotFoundException
	 */
	public void outputStatistics() throws FileNotFoundException {
		out = new PrintWriter(statsFile);
		getAvgDailyPrices();
		getAvgWeeklyPrices();
		getAvgMonthlyPrices();
		getAvgYearlyPrices();

		if (dailyItemCount.isEmpty()) {
			out.println(itemCounters[0]);
			out.println("No Items Purchased Yet Today");
		} else {
			outputItemCounts(dailyItemCount, itemCounters[0]);
		}

		if (weeklyItemCount.isEmpty()) {
			out.println(itemCounters[1]);
			out.println("No Items Purchased Yet This Week");
		} else {
			outputItemCounts(weeklyItemCount, itemCounters[1]);
		}

		if (monthlyItemCount.isEmpty()) {
			out.println(itemCounters[2]);
			out.println("No Items Purchased Yet This Month");
		} else {
			outputItemCounts(monthlyItemCount, itemCounters[2]);
		}

		if (yearlyItemCount.isEmpty()) {
			out.println(itemCounters[3]);
			out.println("No Items Purchased Yet This Past Year");
		} else {
			outputItemCounts(yearlyItemCount, itemCounters[3]);
		}
		out.println(itemCounters[4]);
		out.flush();
		out.close();
		System.out.println("Statistics Saved To " + statsFile.getAbsolutePath());

		out = new PrintWriter(pricesFile);

		if (dailyOrder.isEmpty()) {
			out.println(itemCounters[0]);
			out.println("No Orders Yet Today");
		} else {
			outputOrderPrices(dailyOrder, itemCounters[0]);
		}

		if (weeklyOrder.isEmpty()) {
			out.println(itemCounters[1]);
			out.println("No Orders Yet This Week");
		} else {
			outputOrderPrices(weeklyOrder, itemCounters[1]);
		}

		if (monthlyOrder.isEmpty()) {
			out.println(itemCounters[2]);
			out.println("No Orders Yet This Month");
		} else {
			outputOrderPrices(monthlyOrder, itemCounters[2]);
		}

		if (yearlyOrder.isEmpty()) {
			out.println(itemCounters[3]);
			out.println("No Orders Yet This Year");
		} else {
			outputOrderPrices(yearlyOrder, itemCounters[3]);
		}

		out.println(itemCounters[4]);
		out.flush();
		out.close();

		System.out.println("Prices Saved To " + pricesFile.getAbsolutePath());
	}

	/**
	 * Outputs Order Prices to Pricing File In Order to Keep Averages Accurate
	 * 
	 * @param timespan
	 * @param start
	 */
	private void outputOrderPrices(ArrayList<Double> timespan, String start) {
		if (start.equals(itemCounters[1])) {
			out.printf("%s\nDay: %d\n", start, daysofWeek);
		} else if (start.equals(itemCounters[2])) {
			out.printf("%s\nDay: %d\n", start, daysofMonth);
		} else if (start.equals(itemCounters[3])) {
			out.printf("%s\nDay: %d\n", start, daysofYear);
		} else if (start.equals(itemCounters[0])) {
			out.printf("%s\n", start);
		} else {
			System.out.println("Error in RasLogs.outputOrderPrices()");
			System.exit(0);
		}

		for (Iterator<Double> orders = timespan.iterator(); orders.hasNext();) {
			double itemPrice = orders.next();
			out.println(itemPrice);
		}
	}

	/**
	 * Prints Yesterdays Statistics to its Own File
	 * @throws FileNotFoundException
	 */
	public void outputYesterdayStatistics() throws FileNotFoundException {
		out = new PrintWriter(yesterdayStatsFile);

		if (dayBeforeItemCount.isEmpty() || yesterdaysOrders.isEmpty()) {
			out.println("No Orders Were Placed Yesterday");
			out.flush();
			out.close();
			return;
		}

		out.println("Day Before Item Count");

		for (Iterator<K> itemCounter = dayBeforeItemCount.keys(); itemCounter.hasNext();) {
			K item = itemCounter.next();
			V count = dayBeforeItemCount.get(item);
			String itemString = (String) item;
			Integer counter = Integer.parseInt(String.valueOf(count));
			out.printf("Item: %15s Count: %3d\n", itemString, counter);
		}

		out.println("Day Before Order Prices");

		int i = 1;
		for (Iterator<Double> orders = yesterdaysOrders.iterator(); orders.hasNext();) {
			double itemPrice = orders.next();
			out.printf("Order #%d - Price: $%.2f\n", i++, itemPrice);
		}

		out.flush();
		out.close();

		System.out.println("Yesterday's Stats Saved To " + yesterdayStatsFile.getAbsolutePath());
	}

}
