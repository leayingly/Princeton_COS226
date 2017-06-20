import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FastCollinearPoints {
	private List<LineSegment> list = new ArrayList<LineSegment>();
	
	public FastCollinearPoints(Point[] points) {
	   int n = points.length;
	   Point min, max;
	   
	   for (int i = 0; i < n - 3; i++) { 
		   Point[] copyOfRange = Arrays.copyOfRange(points, i + 1, n);
		   Arrays.sort(copyOfRange, points[i].slopeOrder());
		   
		   double current, init = Integer.MIN_VALUE;
		   int count = 0, start_idx = 0;
		   
		   for (int j = i + 1; j < n; j++) {
			   current = points[i].slopeTo(copyOfRange[j - i - 1]);
			   
			   if (current == Double.NEGATIVE_INFINITY) 
				   throw new java.lang.IllegalArgumentException();
			   if (current == init) {
				   count ++;
			   } else {				   
				   if (count >= 3) {
					   min = points[i];
					   max = points[i];
						   
					   for (int c = 0; c < count; c++) {
						   if (min.compareTo(copyOfRange[start_idx + c]) > 0) min = copyOfRange[start_idx + c];
						   if (max.compareTo(copyOfRange[start_idx + c]) < 0) max = copyOfRange[start_idx + c];
					   }
					   
					   list.add(new LineSegment(min, max));
				   }
				   
				   count = 1;
				   start_idx = j - i - 1;
				   init = current;
			   }
		   }
		   if (count >= 3) {
			   min = points[i];
			   max = points[i];
				   
			   for (int c = 0; c < count; c++) {
				   if (min.compareTo(copyOfRange[start_idx + c]) > 0) min = copyOfRange[start_idx + c];
				   if (max.compareTo(copyOfRange[start_idx + c]) < 0) max = copyOfRange[start_idx + c];
			   }
			   
			   list.add(new LineSegment(min, max));
		   }
	   }
	}

	public int numberOfSegments() {
		// the number of line segments
		return this.list.size();
	}

	public LineSegment[] segments() {
		// the line segments
		PriorityQueue pq = new PriorityQueue();
		
		return list.toArray(new LineSegment[list.size()]);
		
		
		
		
	}
	   

}
