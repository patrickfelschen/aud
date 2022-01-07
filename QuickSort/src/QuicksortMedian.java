public class QuicksortMedian {

	public static void main(String[] args) {

		QuicksortMedian qm = new QuicksortMedian();
		Data d = new Data();

		// Pseudozufallszahlen einlesen
		int[] test1 = d.readFile("100000.txt", 100000);
		int[] test2 = d.readFile("1000000.txt", 1000000);
		int[] test3 = d.readFile("5000000.txt", 5000000);
		int[] test4 = d.readFile("100000_sortiert.txt", 1000000);

		// Dauer der Sortierung der drei Testdatensaetze.
		long start, end;

		System.out.println("Zeiten fuer Quicksort (Median-von-3 Lomuto Partition): ");

		start = System.currentTimeMillis();
		qm.quicksort(test1, 0, 100000 - 1);
		end = System.currentTimeMillis();
		System.out.println("100.000 Zahlen:\t\t\t" + (end - start) + "ms");

		start = System.currentTimeMillis();
		qm.quicksort(test2, 0, 1000000 - 1);
		end = System.currentTimeMillis();
		System.out.println("1.000.000 Zahlen:\t\t" + (end - start) + "ms");

		start = System.currentTimeMillis();
		qm.quicksort(test3, 0, 5000000 - 1);
		end = System.currentTimeMillis();
		System.out.println("5.000.000 Zahlen:\t\t" + (end - start) + "ms");

		// Dauer der Sortierung f�r 100.000 bereits sortierter Daten.
		start = System.currentTimeMillis();
		qm.quicksort(test4, 0, 100000 - 1);
		end = System.currentTimeMillis();
		System.out.println("100.000 sortierte Zahlen:\t" + (end - start) + "ms");

	}

	void swap(int[] A, int l, int r) {
		int temp = A[l];
		A[l] = A[r];
		A[r] = temp;
	}

	void quicksort(int[] A, int p, int r) {
		// Aus Pseudocode Folien_4_1_Suchen_Sortieren.pdf S.13
		if (p < r) {
			int s = partition(A, p, r);
			quicksort(A, p, s - 1);
			quicksort(A, s + 1, r);
		}
	}

	int partition(int[] A, int p, int r) {
		// Median-of-Three
		// https://examples.javacodegeeks.com/quicksort-java-algorithm-code-example
		// Folien_4_1_Suchen_Sortieren.pdf S.26

		int m = (p + r) / 2;

		if (A[p] > A[m])
			swap(A, p, m);

		if (A[p] > A[r])
			swap(A, p, r);

		if (A[m] > A[r])
			swap(A, m, r);

		swap(A, m, r);


		// Lomuto
		// Aus Pseudocode Folien_4_1_Suchen_Sortieren.pdf S.14
		int pivot = A[r];
		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= pivot) {
				i = i + 1;
				swap(A, i, j);
			}
		}

		swap(A, i + 1, r);

		return i + 1;
	}

}
