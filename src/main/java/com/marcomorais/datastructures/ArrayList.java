package com.marcomorais.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements Iterable<E>{
	private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];
    
    public ArrayList(final int capacity) {
    	elements = new Object[capacity];
    }
    
    public ArrayList() {
    	this(DEFAULT_CAPACITY);
    }
    
    public boolean add(E e) {
    	if (size == elements.length) {
    		ensureCapacity();
    	}
    	elements[size++] = e;
		return true;
	}
    
    public void add(int index, E element) {
    	if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    	if (size == elements.length) {
    		ensureCapacity();
    	}
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    
    @SuppressWarnings("unchecked")
	public E set(int i, E e) {
    	if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
    	
    	E oldValue = (E)elements[i];
    	elements[i] = e;
        return oldValue;
	}
    
    public E remove(int index) {
		E oldValue = get(index);
		
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elements, index + 1, elements, index, numMoved);
		}
		elements[--size] = null;
		return oldValue;
	}
    
    public int size() {
		return size;
	}
    
    public boolean isEmpty() {
		return size == 0;
	}
  
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }
    
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }
    
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }
    
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i );
        }
        return (E) elements[i];
    }

    public void clear() {
    	// clear to let GC do its work
        for (int i = 0; i < size; i++)
        	elements[i] = null;

        size = 0;
		
	}
    
    private void ensureCapacity() {
        int newSize = elements.length << 1;
        elements = Arrays.copyOf(elements, newSize);
    }
	
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int cursor = 0; // index of next element to return
			
			public boolean hasNext() {
				return cursor != size;
			}

			@SuppressWarnings("unchecked")
			public E next() {
				if (cursor >= size) {
					throw new NoSuchElementException();
				}
				return (E) elements[cursor++];
			}
		};
	}
}
