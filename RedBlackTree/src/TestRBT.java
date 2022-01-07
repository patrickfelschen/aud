import java.util.Random;

public class TestRBT {

	public static void main(String[] args) {
		TestRBT test = new TestRBT();

		// Test 1 mit zufaelligen Knoten
		System.out.println("TEST 1");
		RedBlackTree tree = new RedBlackTree();
		int[] testArray = test.randPermArray(10000);
		for (int i = 0; i < testArray.length; i++) {
			tree.insert(testArray[i], "Nr. " + testArray[i]);
		}
		System.out.println("Hoehe bei 10000 zufaelligen Knoten: " + tree.height());
		System.out.println(tree.CheckRB());

		// Farbe des Knotens aendern einer von beiden muss false ergeben
		tree.searchNode(42).color = RBTNode.black;
		System.out.println(tree.CheckRB());
		tree.searchNode(42).color = RBTNode.red;
		System.out.println(tree.CheckRB());

		// Test 2 mit aufsteigenden Knoten
		System.out.println("TEST 2");
		RedBlackTree tree1 = new RedBlackTree();
		for (int i = 0; i < 10000; i++) {
			tree1.insert(i, "Nr. " + i);
		}
		System.out.println("Hoehe bei 10000 aufsteigenden Knoten: " + tree1.height());
		System.out.println(tree1.CheckRB());

	}

	public int[] randPermArray(int n) {

		int[] p = new int[n];
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			p[i] = i;
		}

		// Fisher-Yates-Verfahren
		// https://de.wikipedia.org/wiki/Zuf�llige_Permutation
		for (int i = 1; i < n; i++) {
			int z = random.nextInt(i);

			int tmp = p[i];
			p[i] = p[z];
			p[z] = tmp;
		}

		return p;

	}

}
