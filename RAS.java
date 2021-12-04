package ras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GUI.RasGUI;
import data.BSTDictionary;

/**
 * Restaurant Automation System Main Data Class
 * Loads Menu Items and Table Count
 * @author Restaurant Automation Inc.
 *
 * @param <S>
 * @param <K>
 * @param <V>
 */
public class RAS<S, K, V> {

	/**
	 * Path for Configuration File Insert Path HERE
	 */
	private File file; // File Path Comes From rasGUI
	private String menuTypes[] = new String[] { "--Entrees--", "--Drinks--", "--Appetizers--", "--Desserts--",
			"--Sides--", "--End-Menu--" };
	private BufferedReader reader;

	/**
	 * The Menu Types
	 */
	private BSTDictionary<K, V> entrees = new BSTDictionary<>();
	private BSTDictionary<K, V> drinks = new BSTDictionary<>();
	private BSTDictionary<K, V> appetizers = new BSTDictionary<>();
	private BSTDictionary<K, V> desserts = new BSTDictionary<>();
	private BSTDictionary<K, V> sides = new BSTDictionary<>();


	/**
	 * Ints
	 */
	private int tableCount;

	private String line;

	public RAS(File file) {
		this.file = file;
		/**
		 * Get Table Count and Add Menu Items
		 */

		try {

			/*
			 * Add Number of Tables and Servers
			 */

			countTables(); // Get Table Count

			/*
			 * Add Items to The Menus
			 */

			addMenuItems(menuTypes[0], menuTypes[1], entrees); // Add Items to the Entree Menu
			addMenuItems(menuTypes[1], menuTypes[2], drinks); // Add Items to the Drink Menu
			addMenuItems(menuTypes[2], menuTypes[3], appetizers); // Add Items to the Appetizers Menu
			addMenuItems(menuTypes[3], menuTypes[4], desserts); // Add Items to the Desserts Menu
			addMenuItems(menuTypes[4], menuTypes[5], sides); // Add Items to the Sides Menu

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Function to Handle Reading Menu Items From ConfigFile and Sending Them
	 * Through the Dictionary Function to Put Into Their Respective Trees
	 * 
	 * @param menuType
	 * @param nextMenu
	 * @param menu
	 * @throws IOException
	 */
	private void addMenuItems(String menuType, String nextMenu, BSTDictionary<K, V> menu) throws IOException {
		reader = new BufferedReader(new FileReader(file));
		line = null;
		while (!(reader.readLine().equals(menuType))) {
		}
		try {
			while (!(line = reader.readLine()).equals(nextMenu)) {
				String items[] = line.split("\\s+");
				K menuItem = (K) items[0];
				V itemPrice = (V) items[1];
				put(menu, menuItem, itemPrice);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the Number of Tables Used in the Restaurant
	 * 
	 * @param tableCount
	 * @throws IOException
	 */
	private void countTables() throws IOException {
		reader = new BufferedReader(new FileReader(file));
		line = null;

		// Count Tables
		line = reader.readLine();
		String tableArray[] = line.split("\\s+");
		int count = Integer.parseInt(tableArray[1]);
		tableCount = count;
	}

	/**
	 * Returns the Appetizers BSTDictionary
	 * 
	 * @return
	 */
	public BSTDictionary<K, V> getAppetizersMenu() {
		return appetizers;
	}

	/**
	 * Returns the Desserts BSTDictionary
	 * 
	 * @return
	 */
	public BSTDictionary<K, V> getDessertsMenu() {
		return desserts;
	}

	/**
	 * Returns the Drinks BSTDictionary
	 * 
	 * @return
	 */
	public BSTDictionary<K, V> getDrinksMenu() {
		return drinks;
	}

	/**
	 * Returns the Entree BSTDictionary
	 * 
	 * @return
	 */
	public BSTDictionary<K, V> getEntreeMenu() {
		return entrees;
	}

	/**
	 * Returns the Sides BSTDictionary
	 * 
	 * @return
	 */
	public BSTDictionary<K, V> getSidesMenu() {
		return sides;
	}

	/**
	 * Get the Number of Tables in Current Restaurant System
	 * 
	 * @return
	 */
	public int getTableCount() {
		return tableCount;
	}

	/**
	 * Iterator to Add Items to GUI Menu
	 * 
	 * @param menu
	 * @return
	 */
	public Iterator<K> menuItems(BSTDictionary<K, V> menu) {
		return menu.keys();
	}

	/**
	 * Adds the Menu Item and Price Into the Respective Dictionary
	 * 
	 * @param menu
	 * @param key   (Menu Item)
	 * @param value (Item Price)
	 */
	private void put(BSTDictionary<K, V> menu, K key, V value) {
		menu.put(key, value);
	}
}
