package com.kb.tree;

public class Node {

	int height;
	int value;
	Node left, right;
	
	public Node(int value) {
		this.value = value;
		this.height = 1;
	}

}
