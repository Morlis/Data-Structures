package com.marcomorais.datastructures;

public class Stack<T> {
	private LinkedList<T> items = new LinkedList<T>();
	
	public void push(T item) {
		items.addFirst(item);
	}
	
	public T pop() throws Exception {
		if (items.size() == 0) {
			throw new Exception("The stack is empty");
		}
		return items.removeFirst();
	}
	
	public int size() {
		return items.size();
	}
	
	public boolean isEmpty() {
		return items.size() == 0;
	}
	
	public void clear() {
		items.clear();
	}
}
