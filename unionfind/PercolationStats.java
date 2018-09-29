package unionfind;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private int numb;
	private int trailsn;
	private double[] noss;
	private double tot;
	
	public PercolationStats(int n, int trials) {
		this.numb = n;
		this.trailsn = trials;
		WeightedQuickUnionUF tempUF = new WeightedQuickUnionUF(n);
		this.noss = new double[trials];
		this.tot = n*n;
		
		for (int i = 0; i < this.trailsn; i++) {
			Percolation perc = new Percolation(this.numb);
			while( !perc.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				perc.open(row, col);
			}
			this.noss[i] = perc.numberOfOpenSites()/(double) this.tot;
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
