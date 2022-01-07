import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Quelle:
// https://www.geeksforgeeks.org/printing-items-01-knapsack/

class Rucksack {

	static int N;
	static int[] A;
	static int[] W;
	static int[] P;

	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static ArrayList<Integer> rucksack(int n, int[] a, int[] w, int G) {
		int i, g;
		int[][] K = new int[n + 1][G + 1];

		// Build table
		for (i = 0; i <= n; i++) {
			for (g = 0; g <= G; g++) {
				if (i == 0 || g == 0)
					K[i][g] = 0;
				else if (w[i - 1] <= g)
					K[i][g] = max(a[i - 1] + K[i - 1][g - w[i - 1]], K[i - 1][g]);
				else
					K[i][g] = K[i - 1][g];
			}
		}

		// stores the result of Knapsack
		int res = K[n][G];

		// printGrid(K);

		ArrayList<Integer> resArray = new ArrayList<Integer>();

		g = G;

		// backtracking

		for (i = n; i > 0 && res > 0; i--) {

			if (res == K[i - 1][g])
				continue;
			else {

				// This item is included.
				resArray.add(P[i - 1]);

				// Since this weight is included its
				// value is deducted
				res = res - a[i - 1];
				g = g - w[i - 1];
			}
		}

		return resArray;
	}

	static void read(String file) {

		try {

			Scanner scanner = new Scanner(new File(file));

			// Array-Laenge bestimmen durch zahelen aller Paeckchen
			while (scanner.hasNextLine()) {
				N = N + scanner.nextInt();
				scanner.nextLine();
			}

			A = new int[N];
			W = new int[N];
			P = new int[N];

			scanner = new Scanner(new File(file));

			// Arrays fuellen
			int i = 0;
			int p = 1;

			int n = 0;
			int w = 0;
			int a = 0;

			while (scanner.hasNextInt()) {
				n = scanner.nextInt();
				w = scanner.nextInt() * scanner.nextInt();
				a = scanner.nextInt();

				for (int j = 0; j < n; j++) {
					W[i] = w;
					A[i] = a;
					P[i] = p;

					i++;
				}

				p++;

			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void printList(ArrayList<Integer> resArray) {

		// https://stackoverflow.com/questions/17630727/counting-repeated-elements-in-an-integer-array

		Map<Integer, Integer> counterMap = new HashMap<>();

		for (Integer i : resArray) {
			if (counterMap.containsKey(i)) {
				counterMap.put(i, counterMap.get(i) + 1);
			} else {
				counterMap.put(i, 1);
			}
		}

		for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			Integer anzahl = entry.getValue();
			Integer knaller = entry.getKey();

			System.out.printf("%2dx Knaller %d \n", anzahl, knaller);
		}

	}

	static void printGrid(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.printf("%4d ", a[i][j]);
			}
			System.out.println();
		}

	}

	public static void main(String[] arg) {

		read("KnapsackProblem/res/knaller.dat");

		ArrayList<Integer> ar = rucksack(N, W, A, 1000);

		printList(ar);

	}
}
