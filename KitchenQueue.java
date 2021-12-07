package data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Queue Class For Kitchen Display
 * @author Restaurant Automation Inc
 *
 */
public class KitchenQueue {

	/**
	 * Inner Node Class Containing Table Order and Table Number
	 * @author Christian
	 *
	 */
	private class orderQueue {
		int tableNum;
		ArrayList<String> order = new ArrayList<String>();
		
		public int setTableNum(int tableNum) {
			this.tableNum = tableNum;
			return tableNum;
		}
		
		public int getTableNum() {
			return tableNum;
		}
		
	}
	
	orderQueue[] orders;
	orderQueue orderNode;
	
	final int queueHead = 0;
	int queueIndex;
	int numOrders;
	
	/**
	 * Kitchen Queue Constructor
	 * @param numTables
	 */
	public KitchenQueue(int numTables) {
		orders = new orderQueue[numTables];
		queueIndex = 0;
		numOrders = 0;
		orderNode = null;
		
		for (int i = 0; i < orders.length; i++) {
			orders[i] = null;
		}
	}
	
	/**
	 * Adds Order To Queue
	 * @param menuItems
	 * @param tableNum
	 */
	public void addOrderToQueue(ArrayList<String> menuItems, int tableNum) {
		if (TableIsInQueue(tableNum)) {
			orderNode = orders[getTableIndex(tableNum)];
			orderNode.order.clear();
			
			for (Iterator<String> iterator = menuItems.iterator(); iterator.hasNext();) {
				orderNode.order.add(iterator.next());
			}
			
			orders[getTableIndex(tableNum)] = orderNode;
			
		} else {
			
				orderNode = new orderQueue();
				
				orderNode.setTableNum(tableNum);
				
				for (Iterator<String> iterator = menuItems.iterator(); iterator.hasNext();) {
					orderNode.order.add(iterator.next());
				}
				
				orders[queueIndex] = orderNode;
				
				queueIndex++;
			}
			
		}
	
	/**
	 * Returns True if Table is in Queue
	 * @param tableNum
	 * @return
	 */
	private boolean TableIsInQueue(int tableNum) {
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] == null ) {
				continue;
			} else {
				orderNode = orders[i];
				
				if (orderNode.tableNum == tableNum) {
					return true;
				} else {
					continue;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets The Queue Index Based Off Table Number
	 * @param tableNum
	 * @return
	 */
	private int getTableIndex(int tableNum) {
		for ( int i = 0; i < orders.length; i++ ) {
			if (orders[i] == null) {
				continue;
			} else {
				orderNode = orders[i];
				
				if (orderNode.tableNum != tableNum) {
					continue;
				} else {
					return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Removes Front Order From Queue
	 */
	public void completeOrder() {
		if (!isEmpty()) {
			orderNode = orders[queueHead];
			orders[queueHead] = null;
			
			for (int i = 0; i < orders.length - 1; i++) {
				orders[i] = orders[i+1];
			}
			
			if (queueIndex == 0) {
				return;
			} else {
				--queueIndex;
			}
			
			
		} else {
			System.out.println("Queue Is Empty");
		}

	}
	
	/**
	 * Returns Table Number of Order in Front
	 * @return
	 */
	public int getTableNum() {
		if (isEmpty()) {
			throw new NullPointerException();
		}
		orderNode = orders[queueHead];
		return (orderNode.getTableNum() + 1);
	}
	
	/**
	 * Returns True If Queue Is Empty
	 * @return
	 */
	public boolean isEmpty() {
		return queueIndex == 0;
	}
	
	/**
	 * Returns Array For Given Order Index
	 * @param index
	 * @return
	 */
	public ArrayList<String> getOrderMenu(int index) {
		if (orders[index].order.isEmpty()) {
			return null;
		}
		return orders[index].order;
	}
	
	/**
	 * Returns Order At Given Index
	 * @param index
	 * @return
	 */
	public orderQueue getOrderAtIndex(int index) {
		return orders[index];
	}
	
	/**
	 * Moves Order In Front of Queue
	 * @param index
	 */
	public void placeOrderInFront(int index) {
		if (index == 0) {
			return;
		}
		
		if (orders[queueHead] == null) {
			
			orderQueue noder = orders[index];
			orders[queueHead] = noder;
			orders[index] = null;
			
		} else {
			
			orderNode = orders[queueHead];
			orderQueue noder = orders[index];
			
			orders[queueHead] = noder;
			orders[index] = orderNode;
			
		}
		
	}
	
	/**
	 * Removes Order From Queue
	 * @param tableNum
	 */
	public void cancelOrder(int tableNum) {
		if (TableIsInQueue(tableNum)) {
			orders[getTableIndex(tableNum)] = null;
			
			for (int i = getTableIndex(tableNum); i < orders.length - 1; i++) {
				orders[i] = orders[i+1];
			}
			--queueIndex;
		} else {
			return;
		}
	}
	
}
