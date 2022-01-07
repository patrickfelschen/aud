import java.util.Random;

public class TestPQ {

	public static void main(String[] args) {

		int anzahl = 1000;
		Random r = new Random();
		MinPQ minPQ = new MinPQ(anzahl);

		// Aufgabe 1, Einf�gen
		for (int i = 1; i <= anzahl; i++) {
			double prio = Math.round(r.nextDouble() * 100000.0) / 100000.0;
			String data = "Nr." + i;
			minPQ.insert(data, prio);
		}

		// Aufgabe 2, jedes zweite Element verringern
		for (int i = 1; i <= anzahl; i++) {
			if (i % 2 == 0) {
				minPQ.update("Nr." + i, 0.001);
			}
		}

		// Ausgeben
		for (int i = 1; i <= anzahl; i++) {
			PQElement p = minPQ.extractElement();
			System.out.println(p.getPrio() + "\t: " + p.getData());
		}

	}

}
