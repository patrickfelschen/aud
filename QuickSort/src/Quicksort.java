public class Quicksort {

    public static void main(String[] args) {

        Quicksort q = new Quicksort();
        Data d = new Data();

        // Pseudozufallszahlen einlesen
        int[] test1 = d.readFile("100000.txt", 100000);
        int[] test2 = d.readFile("1000000.txt", 1000000);
        int[] test3 = d.readFile("5000000.txt", 5000000);
        int[] test4 = d.readFile("100000_sortiert.txt", 1000000);

        // Dauer der Sortierung der drei Testdatens�tze.
        long start, end;

        System.out.println("Zeiten fuer Quicksort (Standard-Lomuto Partition): ");

        start = System.currentTimeMillis();
        q.quicksort(test1, 0, 100000 - 1);
        end = System.currentTimeMillis();
        System.out.println("100.000 Zahlen:\t\t\t" + (end - start) + "ms");

        start = System.currentTimeMillis();
        q.quicksort(test2, 0, 1000000 - 1);
        end = System.currentTimeMillis();
        System.out.println("1.000.000 Zahlen:\t\t" + (end - start) + "ms");

        start = System.currentTimeMillis();
        q.quicksort(test3, 0, 5000000 - 1);
        end = System.currentTimeMillis();
        System.out.println("5.000.000 Zahlen:\t\t" + (end - start) + "ms");

        // Dauer der Sortierung f�r 100.000 bereits sortierter Daten.
        start = System.currentTimeMillis();
        q.quicksort(test4, 0, 100000 - 1);
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
