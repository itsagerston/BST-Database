import java.io.*;

/**
 * BST.java is a standard binary search tree.
 * Uses Node.java to represent a given node on the tree.
 */
public class BST implements Serializable {
	private Node root;
	
	/**
	 * Creates a new binary search tree.
	 * null root.  That is all.
	 */
	public BST() {
		root = null;
	}
	
	/**
	 * Creates a new node with key k and value o and inserts it into the BST at the proper location.
	 * @param int k - the key of the new node
	 * @param Comparable o - the value of the new node
	 */
	public void insert(Comparable o) {
		if (root == null) {
			root = new Node(o);
			return;
		}
		Node temp = root;
		Node node = new Node(o);
		Node parent;
		while (true) {
			parent = temp;
			if (o.compareTo(temp.getValue()) < 0) {
				temp = temp.getLeft();
				if (temp == null) {
					parent.setLeft(node);
					return;
				}
			}
			else {
				temp = temp.getRight();
				if (temp == null) {
					parent.setRight(node);
					return;
				}
			}
		}
	}
	
	/**
	 * Iterates through the tree to find a specific node, given its key
	 * @param int k - the key of the node to be found
	 * @return the node (with key k) being searched for
	 * @return null if no node with key k exists
	 */
	public Comparable find(Comparable k) {
		if (root == null) {
			return null;
		}
		Node node = root;
		while (node.getValue().compareTo(k) != 0) {
			if (node.getValue().compareTo(k) > 0) {
				node = node.getLeft();
			}
			else {
				node = node.getRight();
			}
			if (node == null) {
				return null;
			}
		}
		return node.getValue();
	}
	
	/**
	 * Iterates through the tree to find a specific node of key k;
	 * removes all pointers to this node;
	 * finds a replacement node if necessary; returns the node.
	 * @param int k - the key of the node to be deleted
	 * @return Node node - the node being deleted
	 * @return null if no node with key k exists
	 */
	public void delete(Comparable k) {
		Node node = root;
		Node parent = root;
		boolean isLeft = true;
		while (node.getValue().compareTo(k) != 0) { // until key is found
			parent = node;
			if (node.getValue().compareTo(k) > 0) {
				node = node.getLeft();
				isLeft = true;
			}
			else {
				node = node.getRight();
				isLeft = false;
			}
		}
		if (node.getLeft() == null && node.getRight() == null) {
			if (isLeft) {
				parent.setLeft(null);	
			}
			else {
				parent.setRight(null);
			}
		}
		else if (node.getRight() == null) {
			if (node == root) {
				root = node.getLeft();
			}
			else if (isLeft) {
				parent.setLeft(node.getLeft());
			}
			else {
				parent.setRight(node.getLeft());
			}
		}
		else if (node.getLeft() == null) {
			if (node == root) {
				root = node.getRight();
			}
			else if (isLeft) {
				parent.setLeft(node.getRight());
			}
			else {
				parent.setRight(node.getRight());
			}
		}
		else { // 2 kids
			Node temp = node;
			Node tempParent = parent;
			Node current = node.getRight();
			while (current != null) {
				tempParent = temp;
				temp = current;
				current = current.getLeft();
			}
			if (temp == node.getRight()) {
				temp.setLeft(node.getLeft());
				if (isLeft) {
					parent.setLeft(temp);
				}
				else {
					parent.setRight(temp);
				}
			}
			else {  // temp != node.getRight()
				tempParent.setLeft(temp.getRight());
				temp.setRight(node.getRight());
				temp.setLeft(node.getLeft());
				if (isLeft) {
					parent.setLeft(temp);
				}
				else {
					parent.setRight(temp);
				}
			}
		}
	}
	
	public void printTree(Node n) {
		if (n.getLeft() != null) {
			printTree(n.getLeft());
		}
		System.out.println(n);
		if (n.getRight() != null) {
			printTree(n.getRight());
		}
	}
		
	public void printTree() {
		printTree(root);
	}
}
