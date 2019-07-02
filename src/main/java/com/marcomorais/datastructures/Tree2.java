package com.marcomorais.datastructures;

import java.util.function.Consumer;

/**
 * Binary Search Tree implementation
 * @author marco morais
 *
 * @param <T>
 */
public class Tree2<T extends Comparable<T>> {
	private TreeNode<T> root = null;
	private int size = 0;

	/**
	 * Add value to tree
	 *
	 * @param value Value to add
	 */
	public void add(T value) {
		if (root == null) {
			root = new TreeNode<T>(value);
		} else {
			addTo(root, value);
		}
		size++;
	}
	
	/**
	 * Add value to node
	 *
	 * @param node Node to add the value
	 * @param value Value to add
	 */
	private void addTo(TreeNode<T> node, T value) {
		if (node.value.compareTo(value) > 0) {
			if (node.left == null) {
				node.left = new TreeNode<T>(value);
			} else {
				addTo(node.left, value);
			}
		} else {
			if (node.right == null) {
				node.right = new TreeNode<T>(value);
			} else {
				addTo(node.right, value);
			}
		}
	}
	
	/**
	 * Return true if value is found in tree otherwise return false
	 *
	 * @param value Value to search for
	 * @return True if the value is found
	 */
	public boolean contains(T value) {
		return find(value) != null;
	}
	
	/**
	 * Remove value from tree
	 * @param value Value to remove
	 * @return return true if the value is found
	 */
	public boolean remove(T value) {
		int tmp = size;
		root = deleteFromTree(root, value);
		return size < tmp;
	}

	private TreeNode<T> deleteFromTree(TreeNode<T> node, T value) {
		if (node == null) {
			return node;
		}
		if (value.compareTo(node.value) < 0) {
			node.left = deleteFromTree(node.left, value);
		} else if (value.compareTo(node.value) > 0) {
			node.right = deleteFromTree(node.right, value);
		} else {
			// Case 1: no childs
			if (node.left == null && node.right == null) {
				size--;
				node = null;
			} else if (node.left == null) { // Case 2.1: no left child
				size--;
				node = node.right;
			} else if (node.right == null) { // Case 2.2: no right child
				size--;
				node = node.left;
			} else {
				// Case 3: When a node to be delete is having two childs then
				// the minimum from its right sub tree can be copied to the 
				// node and then the minimum value can be deleted from the node's right subtree
				// (Converted to Case 2)
				TreeNode<T> tmp = node.right;
				while (tmp.left != null) {
					tmp = tmp.left;
				}
				node.value = tmp.value;
				node.right = deleteFromTree(node.right, tmp.value);
			}
		}
		return node;
	}
	
	public TreeNode<T> find(T value) {
		TreeNode<T> node = root;
		
		while (node != null) {
			int result = node.value.compareTo(value);
			if (result > 0) {
				node = node.left;
			} else if (result < 0) {
				node = node.right;
			} else {
				// Found it
				break;
			}
		}
		return node;
	}
	
	public void preOrder(Consumer<T> consumer) {
		preOrderTraversal(root, consumer);
	}
	
	private void preOrderTraversal(TreeNode<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}
		
		consumer.accept(node.value);
		preOrderTraversal(node.left, consumer);
		preOrderTraversal(node.right, consumer);
	}
	
	public void inOrder(Consumer<T> consumer) {
		inOrderTraversal(root, consumer);
	}
	
	private void inOrderTraversal(TreeNode<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}

		inOrderTraversal(node.left, consumer);
		consumer.accept(node.value);
		inOrderTraversal(node.right, consumer);
	}
	
	public void postOrder(Consumer<T> consumer) {
		postOrderTraversal(root, consumer);
	}
	
	private void postOrderTraversal(TreeNode<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}

		postOrderTraversal(node.left, consumer);
		postOrderTraversal(node.right, consumer);
		consumer.accept(node.value);
	}
	
	public void clear() {
		size = 0;
		root = null;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
