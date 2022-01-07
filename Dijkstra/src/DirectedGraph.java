import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectedGraph {
	public static final double INFINITY = Double.MAX_VALUE;
	private final Map<String, Node> nodes = new HashMap<String, Node>();

	public static DirectedGraph readGraph(String file) {

		DirectedGraph dg = new DirectedGraph();

		try {
			// https://www.w3schools.com/java/java_files_read.asp
			Scanner scanner = new Scanner(new File(file));
			while (scanner.hasNextLine()) {

				String[] parts = scanner.nextLine().split(" ");

				String nodeName1 = parts[0];
				String nodeName2 = parts[1];
				double edgeWeight = Double.parseDouble(parts[2]);

				if (!dg.nodeExists(nodeName1)) {
					dg.addNode(nodeName1);
				}

				if (!dg.nodeExists(nodeName2)) {
					dg.addNode(nodeName2);
				}

				Node node1 = dg.getNode(nodeName1);
				Node node2 = dg.getNode(nodeName2);

				Edge edgeToNode2 = new Edge(node2, edgeWeight);
				node1.neighbors.add(edgeToNode2);

			}

			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dg;

	}

	public boolean nodeExists(String key) {
		return nodes.containsKey(key);
	}

	public void addNode(String nodeName) {
		nodes.put(nodeName, new Node(nodeName));
	}

	public Node getNode(String nodeName) {
		return nodes.get(nodeName);
	}

	public boolean BFS(String start, String dest, int max) {
		Node startNode = getNode(start);
		Node destNode = getNode(dest);

		if (max < 1) {
			return false;
		}

		// Knoten nicht im Graph
		if (startNode == null || destNode == null) {
			return false;
		}

		for (Map.Entry<String, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			if (node != startNode) {
				node.prev = null;
				node.dist = DirectedGraph.INFINITY;
				node.visited = false;
			}
		}

		startNode.prev = null;
		startNode.dist = 0.0;
		startNode.visited = true;

		Node tmp;
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(startNode);

		while (!queue.isEmpty()) {
			tmp = queue.poll();

			if (tmp.dist > max) {
				return false;
			}

			if (tmp == destNode) {
				return true;
			}

			for (Edge e : tmp.neighbors) {
				if (e.dest.visited == false) {
					e.dest.visited = true;
					e.dest.dist = tmp.dist + 1;
					e.dest.prev = tmp;
					queue.add(e.dest);
				}
			}

		}

		return false;
	}

	public void dijkstra(String start) {
		Node startNode = getNode(start);

		MinPQ minPQ = new MinPQ(nodes.size());

		// Knoten nicht im Graph
		if (startNode == null) {
			return;
		}

		for (Map.Entry<String, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();

			node.prev = null;
			node.dist = DirectedGraph.INFINITY;
			node.visited = false;

			if (node == startNode) {
				startNode.dist = 0.0;
			}

			minPQ.insert(new PQElement(node.name, node.dist));
		}

		Node tmp;

		while (!minPQ.isEmpty()) {
			tmp = getNode(minPQ.extractElement().getData());
			tmp.visited = true;

			for (Edge e : tmp.neighbors) {
				if (e.dest.visited == false && e.dest.dist > tmp.dist + e.weight) {
					e.dest.dist = tmp.dist + e.weight;
					e.dest.prev = tmp;
					minPQ.update(e.dest.name, e.dest.dist);
				}
			}
		}
	}

	public void printPath(String dest) {

		Node n = getNode(dest);

		System.out.println("Dist.: " + n.dist);

		while (n != null) {
			System.out.print(n.name + " <- ");
			n = n.prev;
		}

		System.out.println();
	}
}
