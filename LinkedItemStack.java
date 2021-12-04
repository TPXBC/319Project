
package data;

import java.util.NoSuchElementException;

/**
 * Stack Class For Data
 * @author Restaurant Automation Inc.
 *
 * @param <E>
 */
public class LinkedItemStack<E> {
	private Node daTop;
	private int noItems;
	private E item;
	
	/**
	 * Inner Node Class
	 * @author Restuarant Automation Inc.
	 *
	 */
	private class Node {
		E item;
		Node next;
		
		public Node(E item, LinkedItemStack<E>.Node next) {
			super();
			this.item = item;
			this.next = next;
		}
	}
	
	/**
	 * No Args Constructor
	 */
	public LinkedItemStack() {
		super();
		daTop = new Node(item, daTop);
	}
	
	/**
	 * Adds Item to the Top of Stack
	 * @param item
	 */
	public void pushItem(E item) {
		Node prevTop = daTop;
		daTop = new Node(item, prevTop);
		noItems++;
	}

	/**
	 * Removes the Top Item of the Stack
	 * @return
	 */
	public E popItem() {
		if (!isEmpty()) {
			E item = daTop.item;
			daTop = daTop.next;
			noItems--;
			return item;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Shows the Top Item Without Removing.
	 * @return
	 */
	public E peekItem() {
		if (!isEmpty()) {
			return daTop.item;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Checks if the Stack is Empty
	 * @return
	 */
	public boolean isEmpty() {
		return noItems == 0;
	}

	/**
	 * Clears the Stack
	 */
	public void clear() {
		if(!isEmpty()) {
			popItem();
			clear();
		}
	}
}
