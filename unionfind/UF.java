package unionfind;

import java.util.Arrays;
import java.util.Scanner;


public class UF{
	private int[] array;
	private int[][] arraycheck;
	private int n1id;
	private int n2id;
	
	public UF(int N) {
		int[] temp = new int[N];
		for (int i = 1; i<=  N; i++) {
			temp[i-1] = i;
		}
		array = temp;
		arraycheck = new int[N][];
	}
	
	public int find(int p) {
		int n1 = -1;
		for( int i = 0; i < arraycheck.length; i++) {
			if (arraycheck[i] != null) {
				for (int j = 0; j < arraycheck[i].length; j++) {
					if (arraycheck[i][j] == p) {
						n1 = i;
						break;
					}
				}
			}
		}
		return n1;
	}
	
	public boolean connected(int p, int q) {
		n1id = find(p);
		n2id = find(q);
		System.out.println(n1id + "this is n1");
		System.out.println(n2id + "this is n2");
		if (n1id == -1) {
			System.out.println("n1 not in");
			int id = 0;
			while ( id < arraycheck.length && arraycheck[id] != null) {
				id ++;
				}
			int[] temp = new int[]{p};
			arraycheck[id] = temp;
			n1id = id;
			System.out.println("id1 this" + id);
			} 
		if (n2id == -1) {
			System.out.println("n2 not in");
			int id = 0;
			while ( id < arraycheck.length && arraycheck[id] != null) {
				id ++;
			}
			int[] temp = new int[] {q};
			arraycheck[id] = temp;
			n2id = id;
			System.out.println("id2 this" + id);
		}
		return n2id == n1id;
	}
	
	public void union(int p, int q) {
		int a  = arraycheck[n1id].length;
		int b = arraycheck[n2id].length;
		int[] c = new int[a+b];
		int count = 0;
		for (int i = 0; i < a; i++) {
			c[i] = arraycheck[n1id][i];
			count ++;
		}
		for (int i = 0; i < b; i++) {
			c[count++] = arraycheck[n2id][i];
		}
		arraycheck[n1id] = c;

		for (int i = n2id; i < arraycheck.length-1; i++) {
			arraycheck[i] = arraycheck[i+1];
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
