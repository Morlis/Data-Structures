package com.marcomorais.datastructures;

public class LinkedList<E> {
	private int size = 0;
	private LinkedNode<E> head;
	private LinkedNode<E> tail;
	
	public LinkedList() {
		head = tail = null;
		size = 0;
	}
	
	// Appends the specified element to the end of this list.
	public boolean add(E value) {
		LinkedNode<E> node = new LinkedNode<E>(value);
		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
		return true;
	}

	// Inserts the specified element at the beginning of this list.
	public void addFirst(E value) {
		LinkedNode<E> node = new LinkedNode<E>(value);		
		if (size == 0) {
			head = tail = node;
		} else {
			node.next = head;
			head = node;
		}
		size++;
	}
	
	// Appends the specified element to the end of this list.
	public void addLast(E value) {
		add(value);
	}
	
	// Removes all of the elements from this list
	void clear() {
		head = tail = null;
		size = 0;
	}
	
	// Returns the element at the specified position in this list.
	public E get(int index) {
		int i = 0;
		
		LinkedNode<E> node = head;
		while (node != null) {
			if (i == index) {
				return node.value;
			}
			node = node.next;
			i++;
		}
		return null;
	}
	
	// Returns the first element in this list.
	public E getFirst() {
		return head.value;
	}
	
	// Returns the last element in this list.
	public E getLast() {
		return tail.value;
	}
	
	// Returns the index of the first occurrence of the
	// specified element in this list, or -1 if this list does not contain the element.
	public int indexOf(Object element) {
		int i = 0;
		
		LinkedNode<E> node = head;	
		while (node != null) {
			if (node.value.equals(element)) {
				return i;
			}
			node = node.next;
			i++;
		}
		return -1;
	}
	
	// Returns the index of the last occurrence of the specified element
	// in this list, or -1 if this list does not contain the element.
	public int lastIndexOf(Object element) {
		int i = 0;
		int found = -1;
		
		LinkedNode<E> node = head;	
		while (node != null) {
			if (node.value.equals(element)) {
				found = i;
			}
			node = node.next;
			i++;
		}
		return found;
	}
	
	// Retrieves and removes the head (first element) of this list.
	public E remove() {
		E value = null;
		if (head != null) {
			value = head.value;
			size--;
			if (size == 0) {
				head = tail = null;
			} else {
				head = head.next;
				if (size == 1) {
					tail = head;
				}
			}
		}
		return value;
	}
	
	// Removes the element at the specified position in this list.
	public E remove(int index) {
		// Same as remove from head
		if (index == 0) {
			return remove();
		}
		
		int i = 0;
		E value = null;
		
		LinkedNode<E> node = head;
		LinkedNode<E> prev = null;
		while (node != null) {
			if (i == index) {
				value = node.value;
				size--;
				
				// Is the last one?
				if (size == index) {
					tail = prev;
					prev.next = null;
				} else {
					prev.next = node.next;
				}
				break;
			}
			prev = node;
			node = node.next;
			i++;
		}
		return value;
	}
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	public boolean remove(Object obj) {
		int i = 0;
		E value = null;
		
		LinkedNode<E> node = head;
		LinkedNode<E> prev = null;
		while (node != null) {
			if (node.value.equals(obj)) {
				value = node.value;
				size--;

				if (size == 0) {
					head = tail = null;
				} else if (i == 0) {
					head = head.next;
					if (size == 1) {
						tail = head;
					}
				} else if (size == i) {	
					tail = prev;
					prev.next = null;
				} else {
					prev.next = node.next;
				}
			}
			prev = node;
			node = node.next;
			i++;
		}
		return value != null;
	}
	
	// Removes and returns the first element from this list.
	public E removeFirst() {
		return remove();
	}

	// Removes and returns the last element from this list.
	public E removeLast() {
		// TODO: OPTIMIZE
		return remove(size - 1);
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	public E set(int index, E element) {
		int i = 0;
		
		LinkedNode<E> node = head;
		while (node != null) {
			if (i == index) {
				E value = node.value;
				node.value = element;
				return value;
			}
			node = node.next;
			i++;
		}
		return null;
	}

	// Returns the number of elements in this list.
	public int	size()  {
		return size;
	}

	// Returns an array containing all of the elements in this list
	// in proper sequence (from first to last element).
	public Object[] toArray() {
		int i = 0;
		Object newArr[] = new Object[size];
		
		LinkedNode<E> node = head;
		while (node != null) {
			newArr[i++] = node.value;
			node = node.next;
		}
		return newArr;
	}
}
