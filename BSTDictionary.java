
package data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * BSTDictionary Class For Storage
 * @author Restaurant Automation Inc
 *
 * @param <K>
 * @param <V>
 */
public class BSTDictionary<K, V> {
	private BSTDictionary<K, V> left;
	private BSTDictionary<K, V> right;
	private K key;
	private V value;
	private int noKeys;

	/**
	 * Iterator to Organize is Ascending Order
	 */
	private class KeyIterator implements Iterator<K> {
		LinkedItemStack<BSTDictionary<K, V>> pos = new LinkedItemStack<BSTDictionary<K, V>>();

		public KeyIterator() {
			pushLeft(pos);
		}

		/**
		 * Check if still has values
		 */
		public boolean hasNext() {
			return !pos.isEmpty();
		}

		/**
		 * Next Key In Order
		 */
		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				BSTDictionary<K, V> nextTree = pos.popItem();
				nextTree.right.pushLeft(pos);
				return (K) nextTree.key;
			}
		}
	}

	/**
	 * Insert Key and Value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key, V value) {
		if (isEmpty()) {
			this.key = key;
			this.value = value;
			this.left = new BSTDictionary<K, V>();
			this.right = new BSTDictionary<K, V>();
			this.noKeys += 1;
			return null;
		} else {
			int comp = ((Comparable) key).compareTo(this.key);
			if (comp < 0) {
				return left.put(key, value);
			} else if (comp == 0) {
				V old = this.value;
				this.value = value;
				return old;
			} else {
				return right.put(key, value);
			}
		}
	}

	/**
	 * Get Value for Associated Key
	 */
	public V get(K key) {
		if (isEmpty()) {
			return null;
		} else {
			int comp = ((Comparable) key).compareTo(this.key);
			if (comp < 0) {
				return this.left.get(key);
			} else if (comp == 0) {
				return value;
			} else {
				return right.get(key);
			}
		}
	}

	/**
	 * Copy Up
	 */
	private void copyUp(BSTDictionary<K, V> otherDict) {
		this.left = otherDict.left;
		this.right = otherDict.right;
		this.key = otherDict.key;
		this.value = otherDict.value;
		this.noKeys = otherDict.noKeys;
	}

	/**
	 * Get Left Most key
	 */
	private BSTDictionary<K, V> getLeftMost() {
		if (isEmpty()) {
			return this;
		} else {
			return left.getLeftMost();
		}
	}

	/**
	 * Removes Key From Dict -- Uses Copy Up Method
	 */

	public V remove(K key) {
		if (isEmpty()) {
			return null;
		}
		int ret = ((Comparable) key).compareTo(this.key);

		if (ret < 0) {
			V value = (V) left.remove(key);
			return value;
		} else if (ret > 0) {
			V value = (V) right.remove(key);
			return value;
		}

		V value2 = this.value;
		if (left.isEmpty()) {
			copyUp(right);
		} else if (right.isEmpty()) {
			copyUp(left);
		} else {
			BSTDictionary<K, V> leftMost = right.getLeftMost();
			this.key = leftMost.key;
			this.value = leftMost.value;
			noKeys--;
			right.remove(this.key);
		}
		return value2;
	}

	/**
	 * For Iterator
	 */
	public Iterator<K> keys() {
		return new KeyIterator();

	}

	/**
	 * Stack Helper For Key Iterator
	 */
	private void pushLeft(LinkedItemStack<BSTDictionary<K, V>> pos) {
		if (!isEmpty()) {
			pos.pushItem(this);
			left.pushLeft(pos);
		}
	}

	/**
	 * Check If Dict Is Empty
	 */
	public boolean isEmpty() {
		return noKeys == 0;
	}

	/**
	 * Get NoKeys
	 */
	public int noKeys() {
		if (isEmpty()) {
			return 0;
		}
		return noKeys;
	}

}
