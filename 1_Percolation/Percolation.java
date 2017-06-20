
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF uf, fullUf;
	private int[] val;
	private int dimension;
	
	public Percolation(int n) { 
		// create n-by-n grid, with all sites blocked  
		
		if (n <= 0)
			throw new java.lang.IllegalArgumentException();
		
		this.dimension = n;
		this.uf = new WeightedQuickUnionUF(n*n + 2);
		this.fullUf = new WeightedQuickUnionUF(n*n + 1);
		
		this.val = new int[n*n + 2];
        for (int i = 0; i < n*n+2; i++) {
            val[i] = 0;
        }
        val[0] = 1;
        val[n*n+1] = 1;
	}
	
	public void open(int row, int col) {
		if (!isOpen(row, col)) {
			this.val[transformIdx(row, col)] = 1;
			
			if (row == 1 && !uf.connected(transformIdx(row, col), 0)) 
				uf.union(transformIdx(row, col), 0);
			if (row == dimension && !uf.connected(transformIdx(row, col), dimension * dimension + 1)) 
				uf.union(transformIdx(row, col), dimension * dimension + 1);
			
			if (row > 1 && isOpen(row-1, col) && !uf.connected(transformIdx(row, col), transformIdx(row-1, col))) 
				uf.union(transformIdx(row, col), transformIdx(row-1, col));
			if (row < dimension && isOpen(row+1, col) && !uf.connected(transformIdx(row, col), transformIdx(row+1, col))) 
				uf.union(transformIdx(row, col), transformIdx(row+1, col));
			if (col > 1 && isOpen(row, col-1) && !uf.connected(transformIdx(row, col), transformIdx(row, col-1))) 
				uf.union(transformIdx(row, col), transformIdx(row, col-1));
			if (col < dimension && isOpen(row, col+1) && !uf.connected(transformIdx(row, col), transformIdx(row, col+1))) 
				uf.union(transformIdx(row, col), transformIdx(row, col+1));
			
			
			if (row == 1 && !fullUf.connected(transformIdx(row, col), 0)) 
				fullUf.union(transformIdx(row, col), 0);
			
			if (row > 1 && isOpen(row-1, col) && !fullUf.connected(transformIdx(row, col), transformIdx(row-1, col))) 
				fullUf.union(transformIdx(row, col), transformIdx(row-1, col));
			if (row < dimension && isOpen(row+1, col) && !fullUf.connected(transformIdx(row, col), transformIdx(row+1, col))) 
				fullUf.union(transformIdx(row, col), transformIdx(row+1, col));
			if (col > 1 && isOpen(row, col-1) && !fullUf.connected(transformIdx(row, col), transformIdx(row, col-1))) 
				fullUf.union(transformIdx(row, col), transformIdx(row, col-1));
			if (col < dimension && isOpen(row, col+1) && !fullUf.connected(transformIdx(row, col), transformIdx(row, col+1))) 
				fullUf.union(transformIdx(row, col), transformIdx(row, col+1));
			
		}
	}
	
	public boolean isOpen(int row, int col) {
		return val[transformIdx(row, col)] == 1;
	}
	
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		return fullUf.connected(transformIdx(row, col), 0);
	}

	public int numberOfOpenSites() {
	   // number of open sites
	   int cnt = 0;
       for (int i = 1; i <= dimension * dimension; i++) {
           cnt += val[i] == 1 ? 1 : 0;
       }
       return cnt;
	}

	public boolean percolates() {
		// does the system percolate?
		return uf.connected(0, dimension * dimension + 1);
	}

	private int transformIdx(int row, int col) {

		if (row < 1 || row > dimension || col < 1 || col > dimension)
			throw new java.lang.IndexOutOfBoundsException();
		int idx = (row - 1) * dimension + (col-1) + 1;
		if (idx >= 1 && idx <= dimension*dimension) return idx;
		else throw new IndexOutOfBoundsException("row index i out of bounds");
	}
	
	public static void main(String[] args) {
		
	}
}


