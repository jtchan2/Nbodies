import java.util.*;

public class LinkedList1<E>{
	private class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data = value;
			next=null;
		}
	}
	Node<E> head;
	int size;
	public LinkedList1(){
		head= null;
		size=0;
	}
	public void add(E item){
		if(head==null){
			head= new Node<E>(item);
			++size;
		}else{
			Node<E>prev=head;
			while(prev.next!= null){
				prev=prev.next;
			}
			Node<E> node= new Node <E>(item);
			prev.next=node;
			++size;
		}
	}
	public E get(int pos){
		if(pos<0||pos>=size){
			return null;
		}
		Node<E> curr=head;
		for(int i=0; i<pos; i++){
			curr=curr.next;
		}
		return curr.data;
	}
	public void linkToString(){
		Node<E> node= head;
		while(node !=null){
			System.out.println(node.data+"==> ");
			node=node.next;
		}
		System.out.println();
	}
}