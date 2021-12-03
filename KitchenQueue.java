package data;

import java.util.ArrayList;
import java.util.Iterator;

public class KitchenQueue {

	private class orderQueue {
		int tableNum;
		ArrayList<String> order;
		
		public orderQueue() {
			order = new ArrayList<String>();
		}
		
		public int setTableNum(int tableNum) {
			this.tableNum = tableNum;
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
		if (isEmpty()) {
			orderNode = orders[queueHead];
			orders[queueHead] = null;
		} else {
			System.out.println("Queue Is Empty");
		}

	}
	
	public boolean isEmpty() {
		return orders.length == 0;
	}
	
}
