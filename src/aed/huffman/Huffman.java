package aed.huffman;

import java.util.Map;
import java.util.PriorityQueue;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.map.*;
import es.upm.aedlib.tree.*;
import es.upm.aedlib.priorityqueue.*;


public class Huffman {

	public static Map<Character,Integer> frequencies(String texto) {
		Map<Character, Integer> res = new HashTableMap<>();
		for(int i=0;i<texto.length();i++) {
			Character actual=texto.charAt(i);
			Integer cuantos= res.get(actual);
			if(cuantos==null) {
				cuantos=0;
			}
			res.put(actual, cuantos+1);
		}
		return res;
	}

	public static BinaryTree<Character> constructHuffmanTree(Map<Character,Integer> charCounts) {

		PriorityQueue<Integer,BinaryTree<Character>> treeQ= new SortedListPriorityQueue<>();

		for(Character c: charCounts.keys()) {

			BinaryTree<Character> treeT= new LinkedBinaryTree<>();
			treeT.addRoot(c);
			treeQ.enqueue(charCounts.get(c), treeT);
		}

		while(treeQ.size()>1) {

			Entry<Integer,BinaryTree<Character>> left=treeQ.dequeue();
			Entry<Integer,BinaryTree<Character>> right=treeQ.dequeue();
			BinaryTree<Character> treeTT= joinTrees(' ',left.getValue(),right.getValue());
			treeQ.enqueue(left.getKey()+right.getKey(), treeTT);
		}

		return treeQ.dequeue().getValue();
	}
	public static <E> BinaryTree<E> joinTrees(E e,BinaryTree<E> leftTree,BinaryTree<E> rightTree) {

		BinaryTree<E> tree=new LinkedBinaryTree<>();
		Position<E> pos=tree.addRoot(e);
		
		joinTreesAux(tree,leftTree, pos, leftTree.root(),null);
		joinTreesAux(tree,rightTree, pos, null , rightTree.root());
		return tree;
	}

	
	private static<E> void joinTreesAux(BinaryTree<E> t, BinaryTree<E> treeAnadir,Position<E> cursor,Position<E> cursorL,Position<E> cursorR) {
		if(cursorL!=null) {
			Position<E> nuevo=t.insertLeft(cursor, cursorL.element());
			joinTreesAux(t, treeAnadir,nuevo , treeAnadir.left(cursorL), treeAnadir.right(cursorL));
		}
		if(cursorR!=null) {
			Position<E> nuevo=t.insertRight(cursor, cursorR.element());
			joinTreesAux(t, treeAnadir, nuevo , treeAnadir.left(cursorR), treeAnadir.right(cursorR));
		}
		
	}

	public static Map<Character,String> characterCodes(BinaryTree<Character> tree) {
		Map<Character, String> res = new HashTableMap<>();
		characterCodesAux(tree,res,"",tree.root());
		return res;
	}

	private static void characterCodesAux(BinaryTree<Character> tree, Map<Character, String> res, String camino,Position<Character> cursor) {
		if(tree.isExternal(cursor)) {
			res.put(cursor.element(), camino);
			return;
		}

		if(tree.hasLeft(cursor)) {
			characterCodesAux(tree, res, camino+'0', tree.left(cursor));
		}
		if(tree.hasRight(cursor)) {
			characterCodesAux(tree, res, camino+'1', tree.right(cursor));
		}

	}

	public static String encode(String text, Map<Character,String> map) {
		StringBuilder res = new StringBuilder();
		for(int i=0;i<text.length();i++) {
			res.append(map.get(text.charAt(i))); 
		}
		return res.toString();
	}

	public static String decode(String encodedText, BinaryTree<Character> huffmanTree) {
		if(encodedText==null || encodedText.isEmpty()) {
			return "";
		}

		return decodeAux(encodedText, huffmanTree,0, huffmanTree.root());
	}

	private static String decodeAux(String encodedText, BinaryTree<Character> huffmanTree, int pos, Position<Character> cursor) {
		if(encodedText.length()<=pos) {
			if(huffmanTree.isExternal(cursor)) {
				return cursor.element()+"";
			}else {
				return "";
			}
		}

		if(huffmanTree.isExternal(cursor)) {
			return cursor.element()+decodeAux(encodedText, huffmanTree, pos, huffmanTree.root());
		}

		if(encodedText.charAt(pos)=='0') {
			return decodeAux(encodedText, huffmanTree, pos+1, huffmanTree.left(cursor));
		}else if(encodedText.charAt(pos)=='1') {
			return decodeAux(encodedText, huffmanTree, pos+1, huffmanTree.right(cursor));
		}else {
			throw new IllegalArgumentException("En la posicion del String"+pos+"no hay un caracter que sea 0 o 1");
		}

	}


}
