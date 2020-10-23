import java.util.*;

public class ArrayList<T>{
	private T[] arr;
	public ArrayList(){
		arr= (T[]) new Object[10];
	}
	public int size(){
		return size;
	}
	public T get(int pos){
		if(pos<0||pos>=size){
			throw new Exception("Invalid position");
		}
		return arr[pos];
	}
	private void grow_array(){
		T [] new_arr=(T[]) new Object[arr.length*2];
		for(int i=0; i< arr.length; i++){
			new_arr[i]= arr[i];
		}
		arr=new_arr;
	}
	public boolean add (T item){
		if(size== arr.length){
			grow_array();
		}
		arr[size++]=item;
		return true;
	}
	
}