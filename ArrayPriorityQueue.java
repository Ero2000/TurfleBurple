/*************************
  * Adris Jautakas
  * APCS2 pd1
  * HW #32: Getting Past the Velvet Rope
  * 2017-04-20 BLAZE IT (I'm so sorry I had to)
  **************************/

import java.util.ArrayList;

public class ArrayPriorityQueue implements PriorityQueue {
    
    private ArrayList<Object> list;

    public ArrayPriorityQueue() {
        list = new ArrayList<Object>();
    }

    // Adds element in ordered priority queue starting from bottom
    public void add(Object n) {
        int count;
        for(count = 0; count < list.size(); count++) {
            // This is why we use generics
            if (( (Comparable) list.get(count) ).compareTo( (Comparable) n)
                    > 0) {
                break;
            }
        }
        insert(n, count);
    }

    // Returns whether the queue is empty....
    public boolean isEmpty() {
        return (list.size() == 0);
    }

    // Peeks the minimum (front) element of the queue without deleting it
    public Object peekMin() {
        return list.get(0);
    }

    // Removes the minimum (front) element of the queue
    public Object removeMin() {
        Object min = peekMin();
        // Shift everything to the left
        for(int i = 0; i < list.size() - 1; i++) {
            list.set(i, list.get(i+1));
        }
        list.remove(list.size() - 1);
        return min;
    }

    /// Utility functions:

    // inserts "n" on the index "index"
    private void insert(Object n, int index) {
        list.add(0); // Free up an extra slot
        for(int i = list.size() - 1; i > index; i--) {
            list.set(i, list.get(i - 1));
        }
        list.set(index, n);
    }

    public static void main(String[] args) {
        // TESTS
        PriorityQueue q = new ArrayPriorityQueue();
        q.add(new Integer(1));
        q.add(new Integer(1));
        q.add(new Integer(5));
        q.add(new Integer(3));
        q.add(new Integer(100));
        q.add(new Integer(-2));
        q.add(new Integer(4));
        q.add(new Integer(7));
        
        while( !q.isEmpty() ) {
            System.out.print(q.removeMin() + ",");
        }
    }
}
