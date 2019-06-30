package com.marcomorais.datastructures;

public class TreeNode <T extends Comparable<T>> {
	T value;
	TreeNode<T> left;
	TreeNode<T> right;
	
	public TreeNode(T val) {
		value = val;
	}
}
