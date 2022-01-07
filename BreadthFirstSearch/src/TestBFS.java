public class TestBFS {

	public static void main(String[] args) {

		DirectedGraph dg = DirectedGraph.readGraph("BreadthFirstSearch/res/OS_Map.txt");

		System.out.println("OS - NK in 3: " + dg.BFS("Neuenkirchen", "Osnabrueck", 3));
		dg.printPath("Osnabrueck");


		System.out.println("NK - OS in 2: " + dg.BFS("Osnabrueck", "Neuenkirchen", 2));
		dg.printPath("Neuenkirchen");


		System.out.println("OS - OS in 2: " + dg.BFS("Osnabrueck", "Osnabrueck", 2));
		dg.printPath("Osnabrueck");
	}

}
