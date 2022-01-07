public class MinPQ {
	private final PQElement[] heap;
	private final int maxsize;
	private int currentsize;

	public MinPQ(int max) {
		this.maxsize = max;
		this.currentsize = 0;
		this.heap = new PQElement[this.maxsize + 1];
	}

	private int parent(int i) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 37
		return (i / 2);
	}

	private int leftChild(int i) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 37
		return (2 * i);
	}

	private int rightChild(int i) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 37
		return (2 * i) + 1;
	}

	private void swap(int l, int r) {
		PQElement temp = this.heap[l];
		this.heap[l] = this.heap[r];
		this.heap[r] = temp;
	}

	private void minHeapify(int i) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 41

		int l = leftChild(i);
		int r = rightChild(i);
		int smallest = i;

		if (l <= currentsize && heap[l].getPrio() < heap[smallest].getPrio()) {
			smallest = l;
		}

		if (r <= currentsize && heap[r].getPrio() < heap[smallest].getPrio()) {
			smallest = r;
		}

		if (smallest != i) {
			swap(i, smallest);
			minHeapify(smallest);
		}

	}

	public boolean isEmpty() {
		return this.currentsize < 1;
	}

	public boolean insert(PQElement n) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 63

		if (currentsize == maxsize) {
			return false;
		}

		currentsize++;
		heap[currentsize] = n;

		int i = currentsize;

		while (i > 1 && heap[parent(i)].getPrio() > heap[i].getPrio()) {
			swap(i, parent(i));
			i = parent(i);
		}

		return true;
	}

	public boolean insert(String d, double p) {
		PQElement element = new PQElement(d, p);
		return this.insert(element);
	}

	public PQElement extractElement() {
		// Folien_4_2_Suchen_Sortieren.pdf S. 62

		if (this.isEmpty()) {
			throw new IllegalStateException();
		} else {
			PQElement min = this.heap[1];
			this.heap[1] = heap[this.currentsize];
			this.currentsize--;
			this.minHeapify(1);
			return min;
		}
	}

	public String extractData() {
		return this.extractElement().getData();
	}

	public void update(String s, double n) {
		// Folien_4_2_Suchen_Sortieren.pdf S. 64

		if (this.isEmpty()) {
			throw new IllegalStateException();
		} else {
			for (int i = 1; i <= this.currentsize; i++) {
				if (heap[i].getData().equals(s) && n < heap[i].getPrio()) {
					heap[i].setPrio(n);

					while (i > 1 && heap[parent(i)].getPrio() > heap[i].getPrio()) {
						swap(i, parent(i));
						i = parent(i);
					}

					return;
				}

			}

		}

	}

}
