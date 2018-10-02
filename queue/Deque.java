//package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int numb;
	private Node head, last;
	
	public Deque() {
		numb = 0;
		head = null;
		last = null;
	}
	
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty() {
		return this.numb == 0;
	}
	
	public int size() {
		return numb;
	}
	
	public void addFirst(Item item) {
		Node oldfirst= this.head;
		head = new Node();
		head.item = item;
		if (this.isEmpty()) {
			last = head;
			head.next = null;
		} else head.next = oldfirst;
		this.numb ++;
	}

	public void addLast(Item item) {
		Node oldlast = this.last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (this.isEmpty()) this.head = last;
		else oldlast.next = last;
		this.numb ++;

	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Node oldhead = this.head;
		this.head = head.next;
		this.numb --;
		return oldhead.item;
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Node oldlast = this.last;
		Node tempNode = this.head;
		int count = 0;
		
		Iterator<Item> i =  iterator();
		while(i.hasNext()) {	
			this.last = ((Deque<Item>.ListIterator) i).getcurrent();
			i.next();
		}
		this.numb --;
		return oldlast.item;
	}
	

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = head;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next != null;
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
		Deque<Integer> a = new Deque<Integer>();
		a.addFirst(3);
		a.addFirst(4);
		a.addLast(5);
		a.addLast(6);
		System.out.println(a.removeFirst()+ "remove first");
		System.out.println(a.removeLast()+"remove last");
	}	
}
