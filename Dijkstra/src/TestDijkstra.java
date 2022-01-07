public class TestDijkstra {

	public static void main(String[] args) {

		DirectedGraph dg = DirectedGraph.readGraph("Dijkstra/res/OS_Map.txt");

		System.out.println("Osnabrueck - Osnabrueck");
		dg.dijkstra("Osnabrueck");
		dg.printPath("Osnabrueck");
		System.out.println();

		System.out.println("Osnabrueck - Neuenkirchen");
		dg.dijkstra("Osnabrueck");
		dg.printPath("Neuenkirchen");
		System.out.println();

		System.out.println("Osnabrueck - Melle");
		dg.dijkstra("Osnabrueck");
		dg.printPath("Melle");
		System.out.println();

		System.out.println("Osnabrueck - Lengerich");
		dg.dijkstra("Osnabrueck");
		dg.printPath("Lengerich");
		System.out.println();

		System.out.println("Bad_Iburg - Bad_Essen");
		dg.dijkstra("Bad_Iburg");
		dg.printPath("Bad_Essen");
		System.out.println();

	}

}
