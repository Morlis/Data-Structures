package com.marcomorais.datastructures;

public class Queue<T> {
	private LinkedList<T> items = new LinkedList<T>();
	
	public void enqueue(T item) {
		items.addLast(item);
	}
	
	public T dequeue() throws Exception {
		if (items.size() == 0) {
			throw new Exception("The queue is empty");
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
