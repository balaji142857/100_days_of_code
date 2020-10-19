package com.kb.tree;

public interface Tree {
	
	public Node insert(int value);
	
	public Node delete(int value);

	public void inOrder();
	
	public void preOrder();
	
	public void postOrder();

}
