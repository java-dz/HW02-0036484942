package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.*;

/**
 * Demonstrates the usage of {@linkplain Collection} class and its subclasses.
 *
 * @author Mario Bobic
 */
public class CollectionDemo {

    /** Collection that is for some reason a field variable. */
    private static ArrayIndexedCollection col;

    /**
     * Program entry point.
     *
     * @param args not used in this demo
     */
    /* Ne obazirite se na neurednost ovog programa.
     * (Nije ga bilo potrebno implementirati) */
    public static void main(String[] args) {
        col = new ArrayIndexedCollection(2);

        System.out.println("Adding Mario");
        String o1 = "Mario";
        col.add(o1);

        System.out.println("Adding 2");
        Integer o2 = 2;
        col.add(o2);

        System.out.println("Collection size: " + col.size());
        System.out.println("Contains Mario: " + col.contains("Mario"));
        System.out.println("Contains Karly: " + col.contains("Karly"));

        System.out.println("Adding Martin");
        String o3 = "Martin";
        col.insert(o3, 0);

        System.out.println("Collection size: " + col.size());

        System.out.println("Removing element " + o2);
        col.remove(Integer.valueOf(2));

        System.out.println("Collection size: " + col.size());

        printArray(col);

        System.out.println("Clearing collection.");
        col.clear();

        System.out.println("Collection size: " + col.size());

        System.out.print("Adding ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            col.add(Integer.valueOf(i));
        }
        System.out.println();
        System.out.println("Collection size: " + col.size());

        System.out.println("Inserting 150 at index 3");
        Integer o4 = 150;
        col.insert(o4, 3);

        System.out.println("Element at index 3: " + col.get(3));

        System.out.println("Collection size: " + col.size());

        System.out.println("Contains 2: " + col.contains(o2));

        try {
            System.out.println("Trying to add null:");
            col.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Adding failed: " + e.getMessage());
        }

        printArray(col);

        System.out.println("Removing object at index 3");
        col.remove(3);

        printArray(col);

        System.out.println("Creating a new collection col2 with the elements of col:");
        ArrayIndexedCollection col2 = new ArrayIndexedCollection(col, 2);
        printArray(col2);

        System.out.println("Collection col2 size: " + col2.size());

        col.clear();
//        col2.clear();


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        LinkedListIndexedCollection col3 = new LinkedListIndexedCollection(col);
        col3.add("Bobasto");
        col3.add("Mihalićo");
        col3.insert("Domy", 0);
        col3.insert("Mićo", 3);

        printArray(col3);

        System.out.println("Contains Bobasto: " + col3.contains("Bobasto"));
        System.out.println("Collection col3 size: " + col3.size());

        System.out.println("Adding not-recursively.");
        col3.addAll(col2);

        System.out.println("Collection col3 size: " + col3.size());
        printArray(col3);

        try {
            System.out.println("Trying to copy \"null\" to a collection.");
            Collection c = new LinkedListIndexedCollection(null);
            c.add("");
        } catch (NullPointerException e) {
            System.out.println("Other collection must not be null.");
        }

        System.out.println("Getting value at index 10: " + col3.get(10));
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
