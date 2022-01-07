public class BST {
	private BSTNode root;

	public BST() {
		root = null;
	}

	private int heightRecursive(BSTNode currentNode) {
		// https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree

		if (currentNode == null) {

			// Baum ist leer
			return -1;

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

	private boolean isValidBSTRecursive(BSTNode currentNode, int minRange, int maxRange) {
		// https://afteracademy.com/blog/check-if-a-binary-tree-is-BST-or-not
		// Stackoverflow bei zu kleinem Stack

		if (currentNode == null) {
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

	private BSTNode insertRecursive(BSTNode currentNode, BSTNode newNode) {
		// https://www.baeldung.com/java-binary-tree

		if (currentNode == null) {

			// Knoten ist leer
			return newNode;

		}

		if (newNode.key < currentNode.key) {

			// Weiter im linken Teilbaum
			newNode.parent = currentNode;
			currentNode.left = insertRecursive(currentNode.left, newNode);

		} else {

			// Weiter im rechten Teilbaum
			newNode.parent = currentNode;
			currentNode.right = insertRecursive(currentNode.right, newNode);

		}

		return currentNode;

	}

	private BSTNode searchRecursive(BSTNode currentNode, int key) {

		if (currentNode == null) {

			// Key nicht gefunden
			return null;

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

	private BSTNode removeRecursive(BSTNode currentNode, int key) {
		// https://www.baeldung.com/java-binary-tree

		if (currentNode == null) {

			// Knoten ist leer
			return null;

		}

		if (key == currentNode.key) {

			// Hat keine Kinder
			if (currentNode.left == null && currentNode.right == null) {
				currentNode.parent = null;
				return null;

			}

			// Hat ein Kind
			if (currentNode.right == null) {

				currentNode.left.parent = currentNode.parent;
				return currentNode.left;

			}

			if (currentNode.left == null) {

				currentNode.right.parent = currentNode.parent;
				return currentNode.right;

			}

			// Hat zwei Kinder
			// Kleinsten Key im rechten Teilbaum finden
			int smallestKey = findSmallestKey(currentNode.right);
			currentNode.key = smallestKey;
			currentNode.right = removeRecursive(currentNode.right, smallestKey);
			return currentNode;

		}

		if (key < currentNode.key) {

			// Weiter im linken Teilbaum
			currentNode.left = removeRecursive(currentNode.left, key);
			return currentNode;

		} else {

			// Weiter im rechten Teilbaum
			currentNode.right = removeRecursive(currentNode.right, key);
			return currentNode;

		}

	}

	private int findSmallestKey(BSTNode root) {
		// https://www.baeldung.com/java-binary-tree

		if (root.left == null) {

			return root.key;

		} else {

			return findSmallestKey(root.left);

		}

	}

	public void insert(int k, String s) {
		root = insertRecursive(root, new BSTNode(k, s));
	}

	public BSTNode searchNode(int k) {
		return searchRecursive(root, k);
	}

	public String search(int k) {
		return searchRecursive(root, k).val;
	}

	public int height() {
		return heightRecursive(root);
	}

	public boolean isValidBST() {
		return isValidBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public void remove(int k) {
		root = removeRecursive(root, k);
	}

}
