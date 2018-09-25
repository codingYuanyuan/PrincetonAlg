package unionfind;

import java.util.Scanner;

public class QuickUnionUF {
	private int[] id;
	
	public QuickUnionUF(int N){
		id = new int[N];
		for (int i = 0; i< N; i++) id[i] = i;
	}
	
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	
	public static void main(String[] args) {
		System.out.println("Give me a number: ");
		Scanner numb = new Scanner(System.in);
		int N = numb.nextInt();
		UF uf = new UF(N);
		while (!numb.nextLine().isEmpty() == false) {
			System.out.println("Give me another number p: ");
			int p = numb.nextInt();
			System.out.println("Give me another number q: ");
			int q = numb.nextInt();
			if (!uf.connected(p,q)) {
				uf.union(p,q);
				System.out.println(p + " " + q);
			}
		}
		numb.close();
	}
}
