public class TestBST {

	public static void main(String[] args) {
		BST tree = new BST();

		// Einfuegen
		for (int i = 0; i < 10000; i++) {
			tree.insert(i, "Knoten_" + i);
		}

		System.out.println("Height: " + tree.height() + " , isValid: " + tree.isValidBST());

		// Jeden zweiten loeschen
		for (int i = 0; i < 10000; i++) {

			if (i % 2 != 0) {
				tree.remove(i);
			}

		}

		System.out.println("Height: " + tree.height() + " , isValid: " + tree.isValidBST());

		// Manipulieren
		BSTNode manipulation = tree.searchNode(2);
		manipulation.right.key = 1;

		System.out.println("Height: " + tree.height() + " , isValid: " + tree.isValidBST());

	}

}
