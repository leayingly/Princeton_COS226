import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
   private Node first, last;
   private int count = 0;
	
   public Deque() {
	   // construct an empty deque
	   this.first = null;
	   this.last = null;
	   
   }
   
   public boolean isEmpty() {
	   // is the deque empty?
	   return this.first == null;
   }
   
   public int size() {
	   // return the number of items on the deque
	   return count;
   }
   
   public void addFirst(Item item) {
	   // add the item to the front
	   if (item == null) {
		   throw new java.lang.NullPointerException();
	   } else {
		   if (this.first == null) {
			   Node temp = new Node();
			   temp.item = item;
			   temp.next = null;
			   temp.previous = null;
			   
			   this.first = temp;
			   this.last = temp;
		   } else {
			   Node temp = new Node();
			   temp.item = item;
			   temp.next = first;
			   temp.previous = null;
			   
			   this.first.previous = temp;
			   this.first = temp;
		   }
		   
		   count++;
	   }
   }
   
   public void addLast(Item item) {
	   // add the item to the end
	   if (item == null) {
		   throw new java.lang.NullPointerException();
	   } else {
		   if (this.first == null) {
			   Node temp = new Node();
			   temp.item = item;
			   temp.next = null;
			   temp.previous = null;
			   
			   this.first = temp;
			   this.last = temp;
		   } else {
			   Node temp = new Node();
			   temp.item = item;
			   temp.next = null;
			   temp.previous = last;
			   
			   this.last.next = temp;
			   this.last = temp;
		   }   

		   count++;
	   }
   }
   
   public Item removeFirst() {
	   // remove and return the item from the front
	   if (isEmpty()) {
		   throw new java.util.NoSuchElementException();
	   } else {
		   Node temp = first;
		   
		   if (first.next == null) {
			   first = null;
			   last = null;
		   } else {
			   first.next.previous = null;
			   first = first.next;
		   }
		   
		   count--;
		   return temp.item;
	   }
   }
   
   public Item removeLast() {
	   // remove and return the item from the end
	   if (isEmpty()) {
		   throw new java.util.NoSuchElementException();
	   } else {
		   Node temp = last;
		   
		   if (last.previous == null) {
			   first = null;
			   last = null;
		   } else {
			   last.previous.next = null;
			   last = last.previous;
		   }
		   
		   count--;
		   return temp.item;
	   }
   }
   
   public Iterator<Item> iterator() {
	   // return an iterator over items in order from front to end
	   return new DequeIterator();
   }
   
   public static void main(String[] args) {
	   // unit testing (optional)
   }
   
   
   private class Node {
		Item item;
		Node next;
		Node previous;
   }
   
   private class DequeIterator implements Iterator<Item> {
	   private Node current = first;
	   
	   public boolean hasNext() {
		   return current != null;
	   }
	   
	   public void remove() {};
	   
	   public Item next() {
		   if (hasNext()) {
			   Item item = current.item;
			   current = current.next;
			   return item;
		   } else {
			   throw new java.util.NoSuchElementException();
		   }

	   }
   }
}

