package com.marcomorais.datastructures;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TreeTest {
    @Test()
    public void test() throws Exception {
    	Tree<String> tree = new Tree<>();
    	String[] words = {"some", "words", "to", "sort", "and", "test", "algorithm"};
    	
    	for (String word: words) {
    		tree.add(word);
    	}
    	
    	assertTrue(7 == tree.size());

    	for (String word: words) {
    		assertThat(tree.contains(word), is(true));
    	}
    	
    	for (String word: words) {    		
    		assertThat(tree.remove(word), is(true));
    	}
    	assertTrue(0 == tree.size());
    	
    	for (String word: words) {
    		assertThat(tree.contains(word), is(false));
    	}
    	assertTrue(0 == tree.size());

    	for (String word: words) {
    		tree.add(word);
    	}
    	assertTrue(7 == tree.size());

    	tree.inOrder((value) -> System.out.println(value));
    	System.out.println("--------------------");
    	tree.preOrder((value) -> System.out.println(value));
    	System.out.println("--------------------");
    	tree.postOrder((value) -> System.out.println(value));
    	System.out.println("--------------------");
    	
    	tree.clear();
    	assertTrue(0 == tree.size());
    }
}
