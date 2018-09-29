package unionfind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] openr;
	final private WeightedQuickUnionUF uf;
	final private int N;
	private int nos;
	final private int start = 0;
	final private int bottom;
	
		
	public Percolation(int n){
		if (n <= 0) throw new IllegalArgumentException("IllegalArgument Exception");
		this.openr = new int[n][n];
		this.N = n;
		this.nos = 0;
		this.uf = new WeightedQuickUnionUF(n*n+2);
		this.bottom = n*n+1;
	}
	
	public void open(int row, int col) {
		/*
		 when open a block, we should check the nearby open blocks and connect them with the use of WeightedUnionFindUF
		 */
		if (row <= 0 || row > N || col <= 0 || col > N ) throw new IllegalArgumentException("Illegal Argument Exception");
		if (isOpen(row,col)) return;
		this.openr[row-1][col-1] = 1;
		this.nos ++;
		
		if (this.N < 2) return;
		
		//down
		if (row == this.N) {
			this.uf.union(this.bottom,twotoo(row,col));
		} else if (row+1<= this.N && isOpen(row+1, col)) {
			this.uf.union(twotoo(row, col), twotoo(row+1,col));
		}
		
		//up
		if (row-1 == 0) {
			this.uf.union(this.start, twotoo(row,col));
		} else if (isOpen(row-1, col)) {
			this.uf.union(twotoo(row,col), twotoo(row-1,col));
		}
		//left
		
		if (col-1 >= 1 && isOpen(row, col-1)) {
			this.uf.union(twotoo(row,col), twotoo(row,col-1));
		}
		
		//right 
		if (col+1 <= this.N && isOpen(row, col+1)) {
			this.uf.union(twotoo(row,col), twotoo(row,col+1));
		}	
	}
	
	private int twotoo(int row, int col) {
		return (row-1)*this.N + col;
	}
	
	public boolean isOpen(int row, int col) {
		/*
		 * check is a block is open
		 */
		if (row <= 0 || row > this.N || col <= 0 || col >  this.N ) throw new IllegalArgumentException("Illegal Argument Exception");
		return (this.openr[row-1][col-1] == 1);
	}
	
	public boolean isFull(int row, int col) {
		/*
		 * check is a block is full
		 */
		if (row <= 0 || row > this.N || col <= 0 || col > this.N ) throw new IllegalArgumentException("Illegal Argument Exception");
		return this.uf.connected(twotoo(row,col), this.start);
	}
	
	public int numberOfOpenSites() {
		/*
		 * return number of open blocks
		 */
		return this.nos;
	}
	
	public boolean percolates() {
		/*
		 * check is a system percolates
		 */
		return this.uf.connected(this.start,this.bottom);
	}
	
	public static void main(String[] args) {	
		Percolation perc = new Percolation(6);
		perc.open(1, 6);
		perc.open(2, 6);
		perc.open(3, 6);
		perc.open(4, 6);
		perc.open(5, 6);
		perc.open(5, 5);
		perc.open(4, 4);
		perc.open(3, 4);
		perc.open(2, 4);
		perc.open(2, 3);
		perc.open(2, 2);
		perc.open(2, 1);
		perc.open(3, 1);
		perc.open(4, 1);
		perc.open(5, 1);
		perc.open(5, 2);
		perc.open(6, 2);
		perc.open(5, 4);
		System.out.println(perc.isFull(5, 2)+"111");
		System.out.println(perc.isFull(6, 2)+"222");
		System.out.println(perc.isOpen(6, 2));
	
		System.out.println(perc.percolates());
		
	}
}
