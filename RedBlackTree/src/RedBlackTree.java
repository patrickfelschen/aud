public class RedBlackTree {
	private final RBTNode nil = new RBTNode(-1, "nil");
	private RBTNode root;

	public RedBlackTree() {
		root = this.nil;
		root.left = this.nil;
		root.right = this.nil;
		root.parent = this.nil;
	}

	private boolean isValidBSTRecursive(RBTNode currentNode, int minRange, int maxRange) {
		// https://afteracademy.com/blog/check-if-a-binary-tree-is-BST-or-not
		// Stackoverflow bei zu kleinem Stack

		if (currentNode == nil) {
			return true;
		}

		if (currentNode.key < minRange || currentNode.key > maxRange) {
			return false;
		}

		if (isValidBSTRecursive(currentNode.left, minRange, currentNode.key - 1)) {

			return isValidBSTRecursive(currentNode.right, currentNode.key + 1, maxRange);

		}

		return false;

	}

	private int blackHeightRecursive(RBTNode currentNode) {
		// 5. Auf jedem Pfad von einem Knoten liegen gleich viele schwarze Knoten

		if (currentNode == this.nil) {
			return 0;
		}

		int lbh = blackHeightRecursive(currentNode.left);
		int rbh = blackHeightRecursive(currentNode.right);

		if (lbh != rbh || lbh == -1 || rbh == -1) {
			return -1;
		}

		if (currentNode.color == RBTNode.black) {
			return lbh + 1;
		} else {
			return lbh;
		}

	}

	private boolean checkRed(RBTNode currentNode) {
		// 4. Nachfolger von roten Knoten sind schwarz

		if (currentNode == this.nil) {
			return true;
		}

		if (!checkRed(currentNode.left)) {
			return false;
		}

		if (!checkRed(currentNode.right)) {
			return false;
		}

		return currentNode.color != RBTNode.red
				|| ((currentNode.left == nil || currentNode.left.color != RBTNode.red)
				&& (currentNode.right == nil || currentNode.right.color != RBTNode.red));
	}

	private RBTNode searchRecursive(RBTNode currentNode, int key) {

		if (currentNode == nil) {
			// Key nicht gefunden
			return nil;
		}

		if (key == currentNode.key) {
			// Key gefunden
			return currentNode;
		}

		if (key < currentNode.key) {
			// Weiter im linken Teilbaum
			return searchRecursive(currentNode.left, key);

		} else {
			// Weiter im rechten Teilbaum
			return searchRecursive(currentNode.right, key);
		}

	}

	private int heightRecursive(RBTNode currentNode) {
		// https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree

		if (currentNode == nil) {
			// Baum ist leer
			return 0;
		}

		// Linken und rechten Teilbaum zaehlen
		int leftHeight = heightRecursive(currentNode.left);
		int rightHeight = heightRecursive(currentNode.right);

		// Groessere Hoehe zurueckgeben
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}

	}

	private void rightRotate(RBTNode x) {
		// Folien_6_2_B�ume.pdf S. 37

		if (x.left == this.nil) {
			return;
		}

		RBTNode y = x.left;
		x.left = y.right;

		if (y.right != this.nil) {
			y.right.parent = x;
		}

		y.parent = x.parent;

		if (x.parent == this.nil) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}

		y.right = x;
		x.parent = y;

	}

	private void leftRotate(RBTNode x) {
		// Folien_6_2_B�ume.pdf S. 37

		if (x.right == this.nil) {
			return;
		}

		RBTNode y = x.right;
		x.right = y.left;

		if (y.left != this.nil) {
			y.left.parent = x;
		}

		y.parent = x.parent;

		if (x.parent == this.nil) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}

		y.left = x;
		x.parent = y;

	}

	private void insertFixup(RBTNode z) {
		// Folien_6_2_B�ume.pdf S. 43

		RBTNode y;

		while (z.parent.color == RBTNode.red) {
			if (z.parent == z.parent.parent.left) {
				// Fall 1
				y = z.parent.parent.right;
				if (y.color == RBTNode.red) {
					z.parent.color = RBTNode.black;
					y.color = RBTNode.black;
					z.parent.parent.color = RBTNode.red;
					z = z.parent.parent;

				} else {
					// Fall 2
					if (z == z.parent.right) {
						z = z.parent;
						leftRotate(z);
					}
					// Fall 3
					z.parent.color = RBTNode.black;
					z.parent.parent.color = RBTNode.red;
					rightRotate(z.parent.parent);
				}

			} else {
				// Fall 1
				y = z.parent.parent.left;
				if (y.color == RBTNode.red) {
					z.parent.color = RBTNode.black;
					y.color = RBTNode.black;
					z.parent.parent.color = RBTNode.red;
					z = z.parent.parent;
				} else {
					// Fall 2
					if (z == z.parent.left) {
						z = z.parent;
						rightRotate(z);
					}
					// Fall 3
					z.parent.color = RBTNode.black;
					z.parent.parent.color = RBTNode.red;
					leftRotate(z.parent.parent);
				}

			}

		}

		this.root.color = RBTNode.black;
	}

	public void insert(int k, String s) {
		// Folien_6_2_B�ume.pdf S.40

		RBTNode z = new RBTNode(k, s);
		RBTNode y = this.nil;
		RBTNode x = this.root;

		while (x != this.nil) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		z.parent = y;

		if (y == this.nil) {
			this.root = z;
		} else if (z.key < y.key) {
			y.left = z;
		} else {
			y.right = z;
		}

		z.left = this.nil;
		z.right = this.nil;
		z.color = RBTNode.red;
		insertFixup(z);
	}

	public RBTNode searchNode(int k) {
		return searchRecursive(root, k);
	}

	public String search(int k) {
		return searchRecursive(root, k).val;
	}

	public int height() {
		return heightRecursive(root);
	}

	public boolean CheckRB() {

		return isValidBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE)
				&& blackHeightRecursive(root) != -1
				&& checkRed(root)
				&& this.root.color == RBTNode.black;

	}
}
