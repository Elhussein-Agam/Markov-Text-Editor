package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		// TODO: Implement this method
		LLNode<E>newNode = new LLNode<E>(element);
		if( element == null){
			throw new NullPointerException();
		}
		else if( size == 0){
			head = tail =  newNode;
			this.size++;
		}
		else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			this.size++;
		}
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if (index >= this.size || index < 0 || this.size == 0){
			throw new IndexOutOfBoundsException();
		}
		else{
			LLNode<E>temp = this.head;
			for( int i = 0; i < index ; i++){
				temp =temp.next;
			}
			return (E)temp.data;
		}
		
		//return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> temp = this.head;
		if((index > this.size-1 && this.size != 0) || index < 0){
			throw new IndexOutOfBoundsException();
		}
		else if(element == null){
			throw new NullPointerException();
		}
		else{
			if(this.size == 0 && index == 0){
				head = tail = newNode;
				this.size++;
			}
			else{
				for (int i = 0; i< index ; i++){
					temp = temp.next;
				}
				temp.prev.next = newNode;
				newNode.prev = temp.prev;
				newNode.next = temp;
				temp.prev = newNode;
				this.size++;
				if(index == this.size-1){
					tail = newNode;
				}
			}	
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		LLNode<E> temp = head;
		if(index > this.size-1 || index < 0 || this.size == 0){
			throw new IndexOutOfBoundsException();
		}
		else{
			if(this.size == 1){
				head = tail = null;
				this.size--;
			}
			else if(index == 0){
				head =head.next;
				temp.next.prev = null;
				temp.next = null;
				this.size--;
			}
			else if(index == this.size-1){
				temp = tail;
				tail = tail.prev;
				temp.prev.next = null;
				temp.prev = null;
				this.size--;
			}
			
			else{
				for(int i = 0; i< index; i++){
					temp = temp.next;
				}
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				this.size--;
			}		
		}
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		LLNode<E> temp = head;
		E tempo;
		if(index > this.size-1 || index < 0 || this.size == 0){
			throw new IndexOutOfBoundsException();
		}
		else if(element == null){
			throw new NullPointerException();
		}
		else{
			for(int i = 0; i < index ; i++){
				temp = temp.next;
			}
			tempo = temp.data;
			temp.data = element;
		}
		return tempo;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode(){
		this.data = null;
		this.prev = null;
		this.next = null;
	}

}
