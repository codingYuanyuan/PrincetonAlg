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
		Node back;
		
		public void setback(Node input) {
			back = input;
		}
		
		public Node getback() {
			return back;
		}
		
		public void setnext(Node input) {
			next= input;
		}
		
		public Node getnext() {
			return next;
		}
	}
	
	public boolean isEmpty() {
		return this.numb == 0;
	}
	
	public int size() {
		return numb;
	}
	
	public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
		Node oldfirst= this.head;
		head = new Node();
		head.item = item;
		if (this.isEmpty()) {
			last = head;
			head.next = null;
		} else {
			head.setnext(oldfirst);
			oldfirst.setback(head);
		}
		this.numb ++;
		//System.out.println(head.item+"head"+last.item+"last");
		
	}

	public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
		Node oldlast = this.last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (this.isEmpty()) this.head = last;
		else {
			oldlast.setnext(last);
			last.setback(oldlast);
		}
		this.numb++;
		//System.out.println(head.item+"head"+last.item+"last");

	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Node oldhead = this.head;
		this.head = head.next;
		if (this.head == null) {
			this.last = null;
		}else {
			head.setback(null);
			if (head.getnext()==null) {
				last.setback(null);
			}
		}
		this.numb-- ;
		return oldhead.item;
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Node oldlast = this.last;
		this.last = this.last.getback();
		
		if (this.last == null) {
			this.head = null;
		} else {
			last.setnext(null);
			if (last.getback() == null) {
				this.head.setnext(null);
			}
		}
		this.numb-- ;
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
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (hasNext() == false) throw new NoSuchElementException();
			Item item = current.item;
			current = current.getnext();
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		Deque<Integer> a = new Deque<Integer>();
		a.addLast(3);
		System.out.println(a.removeLast()+"remove last");
		a.addLast(4);
		a.addLast(113);
		a.addLast(5);
		System.out.println(a.removeLast()+"remove last");
		System.out.println(a.removeLast()+"remove last");
		a.addLast(6);
		a.addLast(7);
		a.addLast(8);
		a.addLast(9);
		a.addLast(10);
		a.addLast(11);
		a.addLast(12);
		a.addLast(13);
		a.addLast(14);
		a.addLast(15);
		a.addLast(16);
		a.addLast(17);
		a.addLast(18);
		a.addLast(19);
		//a.addLast(5);
		//a.addLast(6);
		System.out.println(a.removeLast()+"remove last");
	}	
}
