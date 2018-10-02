//package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private Node head,last;
	private int numb;
	
	public RandomizedQueue() {
		this.numb = 0;
		this.head = null;
		this.last = null;
	}
	
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return this.numb == 0;
	}
	
	public int size() {
		return this.numb;
	}
	
	public void enqueue(Item item) {
		Node oldhead = this.head;
		Node n = new Node();
		n.item = item;
		this.head = n;
		if (isEmpty()) this.last = this.head;
		else this.head.next = oldhead;
		this.numb ++;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		
		Node templast= this.head;
		Node templastnext = this.head;
		Iterator<Item> i = iterator();
		Item ite = sample();
		while( i.hasNext() && templastnext.item != ite) {
			templast = ((RandomizedQueue<Item>.ListIterator) i).getcurrent(); 
			i.next();
			templastnext = ((RandomizedQueue<Item>.ListIterator) i).getcurrent();
		}
		if (templastnext == this.head) {
			this.head = this.head.next;
		} else if (templastnext == this.last) {
			this.last = templast;
			this.last.next = null;
		} else {
			templast.next = templast.next.next;
		}
		this.numb --;
		return templastnext.item;
	}
	
	public Item sample() {
		int nr = StdRandom.uniform(this.numb);
		Iterator<Item> i = iterator();
		Node tempa = ((RandomizedQueue<Item>.ListIterator) i).getcurrent();
		int count = 0;
		//System.out.println(nr+"nr"+tempa.item+"current item");
		while( i.hasNext() && count < nr) {
			i.next();
			tempa = ((RandomizedQueue<Item>.ListIterator) i).getcurrent(); 	
			//System.out.println(tempa.item+"item to go"+count + "count");
			count ++;
		}
		return tempa.item;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = head;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (current != null) {
				return current.next != null;
			}
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (hasNext() == false) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public Node getcurrent() {
			return current;
		}	
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> n = new RandomizedQueue<Integer>();
		n.enqueue(3);
		n.enqueue(4);
		n.enqueue(7);
		n.enqueue(9);
		n.enqueue(8);
		n.enqueue(17);
		n.enqueue(91);
		n.enqueue(82);
		n.enqueue(37);
		n.enqueue(49);
		n.enqueue(58);
		System.out.println(n.dequeue());
		System.out.println(n.dequeue());
		
	}
	
}
