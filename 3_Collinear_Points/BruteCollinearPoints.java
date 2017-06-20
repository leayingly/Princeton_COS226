import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
	
	private List<LineSegment> list = new ArrayList<LineSegment>();
   
   public BruteCollinearPoints(Point[] points) {
	   int n = points.length;
	   double s1, s2, s3;
	   Point min, max;
	   
	   for (int i = 0; i < n; i++) {
		   for (int j = i + 1; j < n; j++) {
			   for (int k = j + 1; k < n; k++) {
				   for (int l = k + 1; l < n; l++) {
					   s1 = points[i].slopeTo(points[j]);
					   s2 = points[i].slopeTo(points[k]);
					   s3 = points[i].slopeTo(points[l]);
					   
					   if (s1 == Double.NEGATIVE_INFINITY || s2 == Double.NEGATIVE_INFINITY || s3 == Double.NEGATIVE_INFINITY)
						   throw new java.lang.IllegalArgumentException();
					   if (s1 == s2 && s2 == s3 && s3 == s1) {
						   min = points[i];
						   max = points[i];
						   

						   if (min.compareTo(points[j]) > 0) min = points[j];
						   if (max.compareTo(points[j]) < 0) max = points[j];
						   
						   if (min.compareTo(points[k]) > 0) min = points[k];
						   if (max.compareTo(points[k]) < 0) max = points[k];
						   
						   if (min.compareTo(points[l]) > 0) min = points[l];
						   if (max.compareTo(points[l]) < 0) max = points[l];
						   list.add(new LineSegment(min, max));
					   } 
				   }
			   }
		   }
	   }
   }
   
   public int numberOfSegments() {
	   // the number of line segments
	   return this.list.size();
   }
   
   public LineSegment[] segments() {
	   return list.toArray(new LineSegment[list.size()]);
   }
	   
}
