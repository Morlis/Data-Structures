package com.marcomorais.datastructures;

public class HashMap<K,V> {
	// bucketArray is used to store array of chains 
    private ArrayList<HashNode<K, V>> bucketArray; 
  
    // Current capacity of array list 
    private int numBuckets; 
  
    // Current size of array list 
    private int size; 
    
    public HashMap() {
    	size = 0;
    	numBuckets = 10;
    	bucketArray = new ArrayList<>(numBuckets);
    	
    	for (int i = 0; i < numBuckets; i++) {
    		bucketArray.add(null);
    	}
    }
    
    int size() {
    	return size;
    }
    
    boolean isEmpty() {
    	return size == 0;
    }
    
    // Adds a key value pair to hash 
    public void add(K key, V value) {
    	int index = getBucketIndex(key);
    	
    	// Check if the key already exists
    	HashNode<K, V> head = bucketArray.get(index);
    	HashNode<K, V> node = head;
    	while (node != null) {
    		if (node.key.equals(key)) {
    			node.value = value;
    			return;
    		}
    		node = node.next;
    	}
    	
    	HashNode<K, V> newNode = new HashNode<K, V>(key, value);
    	newNode.next = head;
    	size++;
    	bucketArray.set(index, newNode);
    	
    	// If load factor goes beyond threshold, then 
        // double hash table size 
        if ((1.0 * size) / numBuckets >= 0.7) {
        	ArrayList<HashNode<K, V>> temp = bucketArray;
        	numBuckets *= 2;
        	
        	bucketArray = new ArrayList<>(numBuckets);
        	for (int i = 0; i < numBuckets; i++) {
        		bucketArray.add(null);
        	}
        	
        	for (HashNode<K, V> headNode: temp) {
        		while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
        	}
        }
    }
    
    // Returns value for a key 
    public V get(K key) {
    	int index = getBucketIndex(key);
    	
    	HashNode<K, V> node = bucketArray.get(index);
    	
    	while (node != null) {
    		if (node.key.equals(key)) {
    			return node.value;
    		}
    		node = node.next;
    	}
    	return null;
    }
    
    // Method to remove a given key 
    public V remove(K key) {
    	int index = getBucketIndex(key);
    	
    	// Get head of chain
        HashNode<K, V> head = bucketArray.get(index);
        HashNode<K, V> prev = null;
        
        while (head != null) {
    		if (head.key.equals(key)) {
    			break;
    		}
    		prev = head;
    		head = head.next;
    	}
        
        if (head == null) {
        	return null;
        }
        
        size--;
        if (prev == null) {
        	bucketArray.set(index, head.next);
        } else {
        	prev.next = head.next;
        }
        return head.value;
    }
    
    // This implements hash function to find index 
    // for a key 
    private int getBucketIndex(K key) { 
        int hashCode = key.hashCode(); 
        int index = hashCode % numBuckets; 
        return index; 
    }
}

