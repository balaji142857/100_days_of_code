package com.kb.tree;

public interface Tree {
	
	public String inOrder();
	
	public String preOrder();
	
	public String postOrder();
	
	public Tree create(int value);
	
	public Node insert(int value);
	
	public Node delete(int value);

}
