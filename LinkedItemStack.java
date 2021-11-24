package data;

import java.util.NoSuchElementException;

public class LinkedItemStack<E> {
	private Node daTop;
	private int noItems;
	private E item;
	
	private class Node {
		E item;
		Node next;
		
		public Node(E item, LinkedItemStack<E>.Node next) {
			super();
			this.item = item;
			this.next = next;
		}
	}
	
	public LinkedItemStack() {
		super();
		daTop = new Node(item, daTop);
	}
	
	public void pushItem(E item) {
		Node prevTop = daTop;
		daTop = new Node(item, prevTop);
		noItems++;
	}

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

	public E peekItem() {
		if (!isEmpty()) {
			return daTop.item;
		} else {
			throw new NoSuchElementException();
		}
	}

	public boolean isEmpty() {
		return noItems == 0;
	}

	public void clear() {
		if(!isEmpty()) {
			popItem();
			clear();
		}
	}
}
