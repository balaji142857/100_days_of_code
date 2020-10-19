package com.kb.tree;

public class Test {
	public static void main(String[] args) {
		Tree tree = new AvlTree();
		tree.insert(5);
		tree.insert(10);
		tree.insert(15);
		tree.insert(20);
		tree.insert(25);
		print(tree);
		tree.delete(5);
		System.out.println("deletion complete");
		print(tree);
	}

	private static void print(Tree tree) {
		tree.preOrder();
		tree.inOrder();
		tree.postOrder();
	}

}
