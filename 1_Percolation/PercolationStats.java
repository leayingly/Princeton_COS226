import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int dimension, trials;
	private double[] counts;
	
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if (n <=0 || trials <= 0)
			throw new java.lang.IllegalArgumentException();
		this.dimension = n;
		this.trials = trials;
		this.counts = new double[trials];
		int row, col;
		
		for (int c = 0; c < trials; c++) {
			Percolation percolation = new Percolation(n);
			while (!percolation.percolates()) {
				row = StdRandom.uniform(1, n + 1);
				col = StdRandom.uniform(1, n + 1);
				percolation.open(row, col);
			}
			counts[c] = ((double)percolation.numberOfOpenSites()) / (n * n);
		}
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(counts);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
        return StdStats.stddev(counts);
	}
	
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - stddev() * 1.96 / Math.sqrt(trials); 
	}
	
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + stddev() * 1.96 / Math.sqrt(trials); 
	}
	
	public static void main(String[] args) {
		
	}
}
