package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	private DoubleNode<T> front;

	//	referencia al Último aux de la lista
	private DoubleNode<T> last;


	@SuppressWarnings("hiding")
	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
			this.node = aux;
		}

		@Override
		public boolean hasNext() {
			// TODO
			boolean has = false;
			
			if (this.node.next != null){
				has = true;
			}

			return has;
		}
	

		@Override
		public T next() {
		// TODO

			if (!hasNext()){
				throw new NoSuchElementException();
			}
			else{
				this.node = this.node.next;
				T elem = this.node.elem;
				return elem;
			}
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			// TODO	
			this.node = aux;
			}

		@Override
		public boolean hasNext() {
			// TODO	
			boolean has = false;

			if (this.node.prev != null){
				has = true;
			}

			return has;
			}

		@Override
		public T next() {
			// TODO
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			else{
				this.node = this.node.prev;
				T elem = this.node.elem;
				return elem;
			}
		}
	}
	
	
	// TODO: añadir clases para el resto de iteradores
	@SuppressWarnings("hiding")
	private class DoubleLinkedListProgress<T> implements Iterator<T> {
    	private DoubleNode<T> node;
    	private int step = 1;

    	public DoubleLinkedListProgress(DoubleNode<T> aux) {
        	this.node = aux;
    	}

    	@Override
    	public boolean hasNext() {
        	return node != null;
    	}

    	@Override
    	public T next() {
        	if (!hasNext()) {
            	throw new NoSuchElementException();
        	}

        	T elem = node.elem;
        	for (int i = 0; i < step && node != null; i++) {
            node = node.prev;
        }
        step++;
        return elem;
    	}
	}	

	@SuppressWarnings("hiding")
private class DoubleLinkedListProgressReverse<T> implements Iterator<T> {
    private DoubleNode<T> node;
    private int step = 1;

    public DoubleLinkedListProgressReverse(DoubleNode<T> aux) {
        this.node = aux;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        T elem = node.elem;
        for (int i = 0; i < step && node != null; i++) {
            node = node.prev; // Retrocede hacia atrás
        }
        step++;
        return elem;
    }
}

	/////
	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		// permite añadir varios elementos a la lista en el constructor
		for (T elem:v) {
			this.addLast(elem);
			
		}
	}

	@Override
	public boolean isEmpty() {
		//TODO
		return (this.size() == 0);
		}


	@Override
	public void clear() {
		//TODO
		front = null;
		last = null;
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		DoubleNode<T> newNode = new DoubleNode<>(elem);

		if(elem == null){
			throw new NullPointerException();
		}
		else{
			if(front == null){
				front = newNode;
				last = newNode;
			}
			else{
				newNode.next = front;
				front.prev = newNode;
				front = newNode;
			}
		}
	}


	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		DoubleNode<T> newNode = new DoubleNode<>(elem); // Creamos un nuevo nodo

    	if (front == null) {
        	front = newNode;
        	last = newNode;
    	} 
		else {
        	last.next = newNode; 
        	newNode.prev = last; 
        	last = newNode; 
    }
	}


	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub

		if(position <= 0){
			throw new IllegalArgumentException();
		}
		if(elem == null){
			throw new NullPointerException();
		}
		else{
			if(position > this.size()){
				addLast(elem);
			}
			else{
				DoubleNode<T>newNode = new DoubleNode<>(elem);
				DoubleNode<T>current = front;
				for (int i=0; i<position; i++){
					current = current.next;
				}
				newNode.next = current;
				newNode.prev = current.prev;

				if(current.prev != null){
					current = newNode;
				}
				else{
					front = newNode;
				}
				current.prev = newNode;
			}
		}
		
	}



	@Override
	public T getElemPos(int position) {
		
		//TODO
		if(position < 1 || position > this.size()) {
			throw new IllegalArgumentException();
		}
		else {
			DoubleNode<T>current = front;
			int i=0;
			while(i != position-1) {
				i++;
				current = current.next;
			}
			
			return current.elem;
		}
	}


	@Override
	public int getPosFirst(T elem) {
		//TODO
		
		if(elem == null) {
			throw new NullPointerException();
		}
		else {
			DoubleNode<T>current = front;
			int i=0;
			while(current != null) {
				i++;
				if(current.elem.equals(elem)) {
					return i;
				}
				current = current.next;
			}
			throw new NoSuchElementException();
			
		}
	}


	@Override
	public int getPosLast(T elem) {
		//TODO

		if(elem == null) {
			throw new NullPointerException();
		}
		else {
			int i=this.size();
			DoubleNode<T>current = last;
			while(current != null) {
				if(current.elem.equals(elem)) {
					return i;
				}
				current= current.prev;
				i--;
			}
			
			throw new NoSuchElementException();
		}
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		//TODO
		if(isEmpty()) {
			throw new EmptyCollectionException("Vacia");
		}
		else {
			T removedElem;
			if(this.size() == 1) {
				removedElem = last.elem;
			}
			else{
				removedElem = last.elem;
				last = last.prev;
				last.next = null;
			}
			return removedElem;
		}
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		if(isEmpty()) {
			throw new EmptyCollectionException("Vacia");
		}
		
		if(pos<1 || pos>this.size()) {
			throw new IllegalArgumentException();
		}
		T removedElem;
		DoubleNode<T>current = front;
		if (pos == 1) { 
			removedElem = front.elem;
			front = front.next;
			if(front != null) {
				front.prev = null;
			}
			else {
		            last = null; 
		        }
		    } else {
		        for (int i = 1; i < pos; i++) {
		            current = current.next; 
		        }
		        removedElem = current.elem;
		        current.prev.next = current.next;
		        if (current.next != null) {
		            current.next.prev = current.prev;
		        } else {
		            last = current.prev; 
		        }
		    }
			return removedElem;
			
		
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public DoubleList<T> copy() {
		// TODO Auto-generated method stub
		DoubleLinkedListImpl<T> copyList = new DoubleLinkedListImpl<>();
    	DoubleNode<T> current = front;
    	while (current != null) {
        	copyList.addLast(current.elem);
        	current = current.next;
    	}
    	return copyList;
	}


	@Override
	public int size() {
		//TODO
		int size = 0;
		DoubleNode<T> current = front;

		while(current != null){
			size++;
			current = current.next;
		}
		return size;
	}


	
	@Override
	public int maxRepeated() {
	// TODO
		if(isEmpty()){
			return 0;
		}
		int max = 1;
		DoubleNode<T>current = front;
		while(current != null){
			int count = 0;
			DoubleNode<T> paso = front;
			while(paso != null){
				if(current.elem.equals(paso.elem)){
					count++;
				}
				paso = paso.next;
			}
			if(count>max){
				max = count;
			}
			current = current.next;
		}

		return max;
	}


	@Override
	public void addAfter(T elem, T target) {
		// TODO Auto-generated method stub
		
		if(elem == null || target == null){
			throw new NullPointerException();
		}

		DoubleNode<T>current = front;
		while (current != null && !current.elem.equals(target)){
			current = current.next;
		}
		if(current == null){
			addLast(elem);
		}
		else{
			DoubleNode<T>newNode = new DoubleNode<>(elem);
			newNode.next = current.next;
			newNode.prev = current;
			current.next = newNode;
		}

	}


	@Override
	public void addAfterAll(T elem, T target) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public T removePenul() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new EmptyCollectionException("Vacio");
		}
		else{
			if(front.next == null){
				throw new EmptyCollectionException("Insufientes elementos");
			}
			else{
				DoubleNode<T>current = front;
				while(current.next.next != null){
					current = current.next;
				}
				T removedElem = current.next.elem;
				current.next = last;
				last.prev = current;

				return removedElem;
			}
		}
	}


	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException();
		}

		DoubleNode<T>current = front;
		int count = 0;

		while(current != null){
			if(current.elem.equals(elem)){
				count++;
			}
			current = current.next;
		}

		return count;
	}


	@Override
	public boolean sameElems(DoubleList<T> other) {
		// TODO Auto-generated method stub
		if (this.size() != other.size()){
			return false;
		}

		Iterator<T> actual = this.iterator();
		Iterator<T> otro = other.iterator();

		while (actual.hasNext() && otro.hasNext()){
			T elem1 = actual.next();
			T elem2 = otro.next();

			if(!elem1.equals(elem2)){
				return false;
			}
		}
		return true;
	}



	@Override
	public String toString() {
		// TODO
		DoubleNode<T> current = front;

		StringBuilder result = new StringBuilder();

		result.append("(");

		while(current != null){
			result.append(current.elem + " ");
			current = current.next;
		}
		result.append(")");

		return result.toString();
	}
	
	@Override
	public String toStringReverse() {
		// TODO
		StringBuilder result = new StringBuilder();
		DoubleNode<T>current = last;
		
		result.append("(");
		while(current != null) {
			result.append(current.elem + " ");
			current = current.prev;
		}
		result.append(")");
		
		return result.toString();
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO
		StringBuilder result = new StringBuilder();
		DoubleNode<T>current = front;
		int i = 1;
		
		result.append("(");
		while(current != null) {
			if(i >= from && i<= until) {
				result.append(current.elem + " ");
			}
			i++;
			current = current.next;
		}
		result.append(")");
		
		return result.toString();
	}
	
	@Override
	public String toStringFromUntilReverse(int from, int until) {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		DoubleNode<T>current = last;
		int i = this.size();
		
		result.append("(");
		while(current != null) {
			if(i >= from && i <= until) {
				result.append(current.elem + " ");
			}
			i--;
			current = current.prev;
		}
		result.append(")");
		
		return result.toString();
	}



	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(last);
	}
	

	@Override
	public Iterator<T> progressIterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListProgress<>(front);
	}

	@Override
	public Iterator<T> progressReverseIterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListProgressReverse<>(last);
	}


}