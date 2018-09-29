package unionfind;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	final private int numb;
	final private int trailsn;
	final private double[] noss;
	final private double tot;
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException("IllegalArgument Exception");

		this.numb = n;
		this.trailsn = trials;
		this.noss = new double[trials];
		this.tot = n*n;
		
		for (int i = 0; i < this.trailsn; i++) {
			Percolation perc = new Percolation(this.numb);
			int count = 0;
			while( count < n) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				perc.open(row, col);
				count ++; 
			}
			while( !perc.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				perc.open(row, col);
			}
			this.noss[i] = perc.numberOfOpenSites()/ this.tot;
		}
	}
	
	public double mean() {
		return  StdStats.mean(this.noss);
	}
	
	public double stddev() {
		return StdStats.stddev(this.noss);
	}
	
	public double confidenceLo() {
		return this.mean() - 1.96* this.stddev()/Math.sqrt(this.trailsn);
	}
	
	public double confidenceHi() {
		return this.mean() + 1.96* this.stddev()/Math.sqrt(this.trailsn);
	}
	
	public static void main(String[] args) {
		PercolationStats perc = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean: " + perc.mean());
		System.out.println("sd: " + perc.stddev());
		System.out.println("96% low: " + perc.confidenceLo());
		System.out.println("96% high: " + perc.confidenceHi());
	}
}
