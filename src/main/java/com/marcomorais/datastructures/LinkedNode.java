package com.marcomorais.datastructures;

public class LinkedNode<E> {
	E value;
	LinkedNode<E> prev;
	LinkedNode<E> next;
	
	public LinkedNode(E val) {
		value = val;
	}
}
