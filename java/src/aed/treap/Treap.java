package aed.treap;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.tree.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Treap<E extends Comparable<E>> implements Iterable<E> {
	private LinkedBinaryTree<Pair<E,Integer>> treap;
	private Random rand;

	public Treap() {
		this.treap = new LinkedBinaryTree<Pair<E,Integer>>();
		this.rand = new Random();
	}

	public int size() {
		return treap.size();
	}

	public boolean isEmpty() {
		return treap.isEmpty();
	}

	public boolean add(E e) {
		if(e==null) {throw new IllegalArgumentException();}

		if(treap.isEmpty()) {
			treap.addRoot(new Pair<>(e,rand.nextInt()));
			return true;
		}
		//Buscarlo
		Position<Pair<E,Integer>> pos=search(e);
		//Ver si ya existe
		E posElem=pos.element().getLeft();
		if(posElem.equals(e)) {
			return false;
		}
		//Añadirlo como BST
		Pair<E,Integer> nuevo = new Pair<>(e,rand.nextInt());
		Position<Pair<E,Integer>> hijo=null;
		if(posElem.compareTo(e)>0) {
			hijo = treap.insertLeft(pos, nuevo);
		}else {
			hijo = treap.insertRight(pos, nuevo);
		}
		//Rotaciones
		while(treap.parent(hijo)!=null && treap.parent(hijo).element().getRight().compareTo(nuevo.getRight())>0) {
			treap.rotate(hijo);
		}

		return true;
	}

	public boolean remove(E e) {
		if(e==null) {throw new IllegalArgumentException();}  
		if(treap.isEmpty()) {
			return false;
		}
		//Buscarlo
		Position<Pair<E,Integer>> pos=search(e);
		//Ver si ya existe
		E posElem=pos.element().getLeft();
		if(!posElem.equals(e)) {
			return false;
		}
		//Rotarlo hasta que sea hoja
		while(treap.isInternal(pos)) {
			//Buscar hijo con mayor prioridad
			Position<Pair<E,Integer>> derecha = treap.right(pos);
			Position<Pair<E,Integer>> izquierda = treap.left(pos);

			if(derecha!=null && izquierda != null && derecha.element().getRight().compareTo(izquierda.element().getRight())<0) {
				treap.rotate(derecha);
			}else if(izquierda==null) {
				treap.rotate(derecha);
			}else {
				treap.rotate(izquierda);
			}

		}

		treap.remove(pos);

		return true;
	}

	public boolean contains(E e) {

		if(e==null) {
			throw new IllegalArgumentException();
		}

		if(treap.isEmpty()) {
			return false;
		}


		return search(e).element().getLeft().equals(e);
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Position<Pair<E,Integer>> cursor=minimo(treap.root());


			private Position<Pair<E,Integer>> minimo(Position<Pair<E,Integer>> p){
				if(p==null) {
					return null;
				}

				Position<Pair<E,Integer>> devolver = p;

				while(treap.hasLeft(devolver)) {
					devolver = treap.left(devolver);
				}

				return devolver;
			}

			@Override
			public boolean hasNext() {
				return cursor!=null;
			}

			@Override
			public E next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException() ;
				}
				E devolver=cursor.element().getLeft();

				if (treap.hasRight(cursor)) {

					cursor = minimo(treap.right(cursor));

				}else {

					Position<Pair<E,Integer>> padre = treap.parent(cursor);

					while (padre != null && treap.hasRight(padre) && treap.right(padre).equals(cursor)) {

						cursor = padre;
						padre = treap.parent(padre);
					}

					cursor=padre;

				}


				return devolver;
			}

		};
	}

	@Override
	public String toString() {
		return treap.toString();
	}

	private Position<Pair<E,Integer>> search(E e){

		return searchAux(e, treap.root());

	}

	private Position<Pair<E,Integer>> searchAux(E e, Position<Pair<E,Integer>> pos){

		if(pos.element().getLeft().equals(e)) {
			return pos;
		}

		Position<Pair<E,Integer>> izquierda=null;
		Position<Pair<E,Integer>> derecha=null;

		if(e.compareTo(pos.element().getLeft())<0) {

			if(treap.hasLeft(pos)) {
				izquierda=treap.left(pos);
				return searchAux(e,izquierda);
			}

			return pos;
		}

		if(treap.hasRight(pos)) {
			derecha=treap.right(pos);

			return searchAux(e,derecha);
		}

		return pos;
	}

}
