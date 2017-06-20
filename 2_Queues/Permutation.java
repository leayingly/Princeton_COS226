import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
	   int count = Integer.parseInt(args[0]);

	   RandomizedQueue<String> queue = new RandomizedQueue<String>();
	   
       String[] str = StdIn.readAllStrings();

	   for (String s : str) {
		   queue.enqueue(s);
	   }
	   
	   for (int i = 0; i < count; i++) {
		   System.out.print(queue.dequeue() + "\r\n");
	   }

	   
   }
}
