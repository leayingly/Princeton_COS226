import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
	private Item[] s;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
	   // construct an empty randomized queue
		
		s = (Item[]) new Object[1];
   }

    /**
     * Changes the queue size to the specified size.
     * @param newSize the new queue size.
     */
    private void resize(int newSize) {
        Item[] newArray = Arrays.copyOfRange(s, 0, newSize);
        s = newArray;
    }
    
   public boolean isEmpty() {
	   // is the queue empty?
	   return N == 0;
   }
   
   public int size() {
	   // return the number of items on the queue
	   return N;
   }
   
   public void enqueue(Item item) {
	   // add the item
	   if (item == null) {
		   throw new java.lang.NullPointerException();
	   } else {
	        if (N == s.length) 
	            resize(s.length*2);

	        s[N++] = item;
	   }
   }
   
   public Item dequeue() {
	   // remove and return a random item
	   if (isEmpty()) {
		   throw new java.util.NoSuchElementException();
	   } else {
		   if (N < s.length/4) {
			   resize(s.length/2);
		   }
	   }
	   
	   int idx = StdRandom.uniform(0, N);
	   
	   Item temp = s[idx];
	   s[idx] = s[--N];
	   s[N] = null;
	   
	   return temp;
	   
   }
   
   public Item sample() {
	   // return (but do not remove) a random item
	   if (isEmpty()) {
		   throw new java.util.NoSuchElementException();
	   } else {
		   int idx = StdRandom.uniform(0, N);
		   return s[idx];
	   }
   }
   
   public Iterator<Item> iterator() {
	   // return an independent iterator over items in random order
	   return new RandomQueueIterator();
   }
   
   public static void main(String[] args) {
	   // unit testing (optional)
   }
   
   private class RandomQueueIterator implements Iterator<Item> {

	    private int count = 0;
	    private Item[] ss;

        public RandomQueueIterator() {
            ss = Arrays.copyOfRange(s, 0, N);
            shuffle(ss);
        }
        
        private void shuffle(Item[] array) {
        	int idx;
        	for (int i = 0; i < N; i++) {
        		idx = StdRandom.uniform(0, N);
        		Item temp = ss[i];
        		s[i] = s[idx];
        		s[idx] = temp;
        	}
        }
        
		@Override
		public boolean hasNext() {
			return count < N;
		}
	
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			} else {
				return ss[count++];
			}
		}
		
		@Override
		public void remove() {
			throw new  java.lang.UnsupportedOperationException();
		}
		
	   
   }
   
}