package data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Queue Class For Kitchen Display
 * @author Restaurant Automation Inc
 *
 */
public class KitchenQueue {

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
	
	public KitchenQueue(int numTables) {
		orders = new orderQueue[numTables];
		queueIndex = 0;
		numOrders = 0;
		orderNode = null;
		
		for (int i = 0; i < orders.length; i++) {
			orders[i] = null;
		}
	}
	
	public void addOrderToQueue(ArrayList<String> menuItems, int tableNum) {
		if (TableIsInQueue(tableNum)) {
			orderNode = orders[getTableIndex(tableNum)];
			
			for (Iterator<String> iterator = menuItems.iterator(); iterator.hasNext();) {
				orderNode.order.add(iterator.next());
			}
			
			orders[getTableIndex(tableNum)] = orderNode;
			
		} else {
			
			orderNode = orders[queueIndex];
			orderNode = new orderQueue();
			
			orderNode.setTableNum(tableNum);
			
			for (Iterator<String> iterator = menuItems.iterator(); iterator.hasNext();) {
				orderNode.order.add(iterator.next());
			}
			
			orders[queueIndex] = orderNode;
			
			queueIndex++;
			
		}
	}
	
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
	
	public void completeOrder() {
		if (!isEmpty()) {
			orderNode = orders[queueHead];
			orders[queueHead] = null;
			if (orders[1] != null) {
				orders[queueHead] = orders[1];
				reorderQueue();
			}
		} else {
			System.out.println("Queue Is Empty");
		}

	}
	
	public int getTableNum() {
		if (isEmpty()) {
			throw new NullPointerException();
		}
		orderNode = orders[queueHead];
		return (orderNode.getTableNum() + 1);
	}
	
	public boolean isEmpty() {
		return orders.length == 0;
	}
	
	public ArrayList<String> getOrders(int index) {
		if (orders[index].order.isEmpty()) {
			return null;
		}
		return orders[index].order;
	}
	
	public void placeOrderInFront(int index) {
		if (index == 0) {
			return;
		}
		
		if (orders[queueHead] == null) {
			orderQueue noder = orders[index];
			orders[queueHead] = noder;
		} else {
			orderNode = orders[queueHead];
			orderQueue noder = orders[index];
			
			orders[queueHead] = noder;
			orders[index] = orderNode;
		}
		
	}
	
	private void reorderQueue() {
		for (int i = 2; i < orders.length; i++) {
			for (int k = i+=1; k < orders.length; k++) {
				if (orders[k] == null) {
					continue;
				} else {
					orders[i] = orders[k];
					break;
				}
			}
		}
	}
	
	
}
