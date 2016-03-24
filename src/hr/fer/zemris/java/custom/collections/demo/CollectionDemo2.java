package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.*;

/**
 * 
 *
 * @author Mario Bobic
 */
public class CollectionDemo2 {

	/**
	 * 
	 * @param args ae starci
	 */
	public static void main(String[] args) {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		
		System.out.println("Adding A & B...");
		col.add("A");
		col.add("B");
		printArray(col);
		
		System.out.println("Inserting C to the end...");
		col.insert("C", 2);
		printArray(col);
		
		System.out.println("Inserting D to the beginning...");
		col.insert("D", 0);
		printArray(col);
		
		System.out.println("Adding E & F...");
		col.add("E");
		col.add("F");
		printArray(col);
		
		System.out.println("Removing the last index...");
		col.remove(5);
		printArray(col);
		
		System.out.println("Removing the zeroth index...");
		col.remove(0);
		printArray(col);
		
		System.out.println("Removing the first element (A)...");
		col.remove("A");
		printArray(col);
		
		System.out.println("Removing the last element (E)...");
		col.remove("E");
		printArray(col);
	}
	
	/**
	 * Converts the given collection to an array and prints it out.
	 * 
	 * @param col collection to be printed out
	 */
	private static void printArray(Collection col) {
		System.out.println("Converting to array:");
		Object[] arr = col.toArray();
		for (Object o : arr) {
			System.out.println("   " + o);
		}
	}

}
