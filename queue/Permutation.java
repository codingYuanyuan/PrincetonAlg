//package queue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		RandomizedQueue rq = new RandomizedQueue();
		while(!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		while(n>0) {
			System.out.println(rq.dequeue());
			n--;
		}
	}
}
