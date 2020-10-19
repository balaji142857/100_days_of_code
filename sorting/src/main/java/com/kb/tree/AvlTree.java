package com.kb.tree;

public class AvlTree implements Tree {
	
	private Node root;

	@Override
	public Node insert(int value) {
		if (null == root) {
			root = new Node(value);
			return root;
		}
		return insert(root, value);
	}
	
	private Node insert(Node node, int value) {
		if (null == node) {
			Node newNode = new Node(value);
			return newNode;
		}
		if(value < node.value) {
			node.left = insert(node.left, value);
		} else if (value > node.value) {
			node.right = insert(node.right, value);
		} 
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		int balance = getBalance(node);
		if (balance > 1) {
			if (value < node.left.value) {
				rightRotate(node);
			} else {
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			}
		} else if (balance < -1) {
			if (value > node.right.value) {
				node = leftRotate(node);
			} else {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
		}
		return node;
	}

	private Node leftRotate(Node pivot) {
		Node newRoot = pivot.right;
		pivot.right = newRoot.left;
		newRoot.left = pivot;
		return newRoot;
	}

	private Node rightRotate(Node pivot) {
		Node newRoot = pivot.left;
		pivot.left= newRoot.right;
		newRoot.right = pivot;
		return newRoot;
	}

	private int getHeight(Node node) {
		if (null == node) {
			return 0;
		} 
		int leftHeight = null != node.left ? node.left.height : 0;
		int rightHeight = null != node.right ? node.right.height : 0;
		return Math.max(leftHeight, rightHeight);
	}
	
	private int getBalance(Node node) {
		return getHeight(node.left) - getHeight(node.right);
	}

	@Override
	public Node delete(int value) {
		return delete(root, value);
	}
	
	private Node delete(Node node, int value) {
		if (null == node) {
			return node;
		}
		if (value < node.value) {
			node = delete(node.left, value);
		} else if (value > node.value) {
			node = delete(node.right, value);
		}
		
		if (null == node.left || null == node.right) {
			node = null == node.left ? node.right : node.left;
//			Node temp = null == node.left ? node.right : node.left;
//			if (null == temp) {
//				node = null;
//			} else {
//				
//			}
		} else {
			Node temp = findMinNode(node.right);
			node.right = delete(root, temp.value);
		}
		if (null == node) {
			return node;
		}
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		int balance = getBalance(node);
		if (balance > 1) {
			if (value < node.left.value) {
				rightRotate(node);
			} else {
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			}
		} else if (balance < -1) {
			if (value > node.right.value) {
				node = leftRotate(node);
			} else {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
		} 
		return node; 
	}

	private Node findMinNode(Node node) {
		if (node.left != null) {
			return findMinNode(node.left);
		}
		return node;
	}

	@Override
	public void inOrder() {
		System.out.println("+++++++ inOrder ++++++++");
		inOrder(root);
		System.out.println("+++++++ inOrder End ++++++++");
	}

	private void inOrder(Node node) {
		if (null == node) {
			return;
		}
		inOrder(node.left);
		System.out.println(node.value);
		inOrder(node.right);
	}

	@Override
	public void preOrder() {
		System.out.println("+++++++ preOrder End ++++++++");
		preOrder(root);
		System.out.println("+++++++ preOrder End ++++++++");
	}
	
	private void preOrder(Node node) {
		if (null == node) {
			return;
		}
		System.out.println(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}

	@Override
	public void postOrder() {
		System.out.println("+++++++ postOrder End ++++++++");
		postOrder(root);
		System.out.println("+++++++ postOrder End ++++++++");
	}
	
	private void postOrder(Node node) {
		if (null == node) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.value);
	}

}
