package com.kb.tree;

public interface Tree {

	public Node root();

	public Node insert(int value);

	public Node delete(int value);

	public void inOrder();

	public void preOrder();

	public void postOrder();

	public default void printTree(Node currPtr, String indent, boolean last) {
		if (currPtr != null) {
			System.out.print(indent);
			if (last) {
				System.out.print("R----");
				indent += "   ";
			} else {
				System.out.print("L----");
				indent += "|  ";
			}
			System.out.println(currPtr.value);
			printTree(currPtr.left, indent, false);
			printTree(currPtr.right, indent, true);
		}
	}

}
