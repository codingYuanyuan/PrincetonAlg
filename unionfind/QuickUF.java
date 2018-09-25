package unionfind;

import java.util.Scanner;

public class QuickUF {
	private int[] array;
	private int[] indexlist;
	private int max;
	
	public QuickUF(int N) {
		int[] temp = new int[N];
		for (int i = 0; i<  N; i++) {
			temp[i] = i;
		}
		array = temp;
		indexlist = new int[N];
		max = 1;
	}
	
	public boolean connected(int p, int q)
	{  return array[p] == array[q];  }
	
	
	public void union(int p, int q) {
		for (int i = 0; i< indexlist.length; i++) {
			if( indexlist[i] == q) {
				indexlist[i] = p;
			}
		}
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
