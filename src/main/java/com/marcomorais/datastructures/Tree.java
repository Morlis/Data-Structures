package com.marcomorais.datastructures;

import java.util.function.Consumer;

/**
 * Binary Search Tree implementation
 * @author marco morais
 *
 * @param <T>
 */
public class Tree<T extends Comparable<T>> {
	private TreeNode<T> root = null;
	private TreeNode<T> parent;
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
		return findWithParent(value) != null;
	}

	/**
	 * Remove value from tree
	 * @param value Value to remove
	 * @return return true if thevalue is found
	 */
	public boolean remove(T value) {
		TreeNode<T> current =  findWithParent(value);
		
		if (current == null) {
			return false;
		}
		size--;

		// Case 1: If the current has no right child, then the current's left replaces current
		if (current.right == null) {
			if (parent == null) {
				root = current.left;
			} else {
				int result = parent.value.compareTo(current.value);
				if (result > 0) {
					// If parent value is greater than the current value
					// make the current left child a left child of parent
					parent.left = current.left;
				} else if (result < 0) {
					// if parent value is less than the current value
					// make the current left child a right child of parent
					parent.right = current.left;
				}
			}
		}
		// Case 2: If current's right child has no left child, then current's right child
		// replaces current
		else if (current.right.left == null) {
			current.right.left = current.left;

			if (parent == null) {
				root = current.right;
			} else {
				int result = parent.value.compareTo(current.value);
				if (result > 0) {
					// If parent value is greater than the current value
					// make the current right child a left child of parent
					parent.left = current.right;
				} else if (result < 0) {
					// if parent value is less than the current value
					// make the current right child a right child of parent
					parent.right = current.right;
				}
			}
		}
		// Case 3: If current's right child has a left child, replace current with current's
		// right child's left most child
		else {
			// find the right's left-most child
			TreeNode<T> leftmost = current.right.left;
			TreeNode<T> leftmostParent = current.right;
			
			while (leftmost.left != null) {
				leftmostParent = leftmost;
				leftmost = leftmost.left;
			}
			
			// the parent's left subtree becomes the leftmost's right subtree
			leftmostParent.left = leftmost.right;
			
			// assign's leftmost left and right to current's left and right children
			leftmost.left = current.left;
			leftmost.right = current.right;
			
			if (parent == null) {
				root = leftmost;
			} else {
				int result = parent.value.compareTo(current.value);
				if (result > 0) {
					// If parent value is greater than the current value
					// make leftmost the parent's left child
					parent.left = leftmost;
				} else if (result < 0) {
					// if parent value is less than the current value
					// make leftmost the parent's right child
					parent.right = leftmost;
				}
			}
		}		
		return true;
	}
	
	private TreeNode<T> findWithParent(T value) {
		TreeNode<T> node = root;
		parent = null;
		
		while (node != null) {
			int result = node.value.compareTo(value);
			if (result > 0) {
				parent = node;
				node = node.left;
			} else if (result < 0) {
				parent = node;
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
