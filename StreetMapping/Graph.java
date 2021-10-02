import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph extends JPanel {
	private HashMap<String, Vertex> i = new HashMap<String, Vertex>();
	private HashMap<String, Edge> r = new HashMap<String, Edge>();
	private HashMap<String, Edge> shortestpathRoad = new HashMap<String, Edge>();
	private static HashMap<String, Edge> mst = new HashMap<String, Edge>();
	private ArrayList<Double> latitudes = new ArrayList<Double>();
	private ArrayList<Double> longitudes = new ArrayList<Double>();
	private double max_latitude = -99999999;
	private double min_latitude = 99999999;
	private double  max_longitude = -99999999;
	double min_longitude = 99999999;
	static JFrame frame = new JFrame("Graph");

	public Graph() {

	}

	public Graph(HashMap<String, Vertex> intersection, HashMap<String, Edge> road) {
		this.i = intersection;
		this.r = road;
	}

	public HashMap<String, Vertex> getI() {
		return i;
	}

	public void setI(HashMap<String, Vertex> i) {
		this.i = i;
	}

	public HashMap<String, Edge> getR() {
		return r;
	}

	public void setR(HashMap<String, Edge> r) {
		this.r = r;
	}

	// create graph from file
	public static Graph createFromFile(String filename) throws FileNotFoundException {
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();// id, vertex
		HashMap<String, Edge> edges = new HashMap<String, Edge>(); // id, edge

		Scanner file = new Scanner(new File(filename));
		while (file.hasNext()) {
			String ir = file.next();

			if (ir.equals("i")) {
				String id = file.next();
				double latitude = Double.parseDouble(file.next());
				double longitude = Double.parseDouble(file.next());
				Vertex v = new Vertex(latitude, longitude, id);
				vertices.put(id, v);
			}
			if (ir.equals("r")) {
				String id = file.next();
				String vertex1id = file.next();
				String vertex2id = file.next();
				Vertex vertex1 = vertices.get(vertex1id);
				Vertex vertex2 = vertices.get(vertex2id);

				vertex1.connect(vertex2);
				vertex2.connect(vertex1);

				Edge e = new Edge(vertex1, vertex2, id);
				edges.put(id, e);
			}
		}

		Graph g = new Graph(vertices, edges);
		return g;
	}

	public void dijkstrasAlgorithm(Vertex start) {
		start.setDist(0);
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);
		start.setVisited(true);

		while (!vertexQueue.isEmpty()) {
			Vertex v = vertexQueue.poll();

			for (Edge edge : v.getAdjList()) {
				Vertex ver = edge.getW();
				if (!ver.isVisited()) {
					double newDist = v.getDist() + edge.getWeight();

					if (newDist < ver.getDist()) {
						vertexQueue.remove(ver);
						ver.setDist(newDist);
						ver.setPrev(v);
						vertexQueue.add(ver);
					}
				}
			}
			v.setVisited(true);
		}
	}

	public List<Vertex> getShortestPathTo(Vertex end) {
		List<Vertex> path = new ArrayList<>();
		List<String> road_names = new ArrayList<>();
		double total_dist = 0;
		for (Vertex vertex = end; vertex != null; vertex = vertex.getPrev()) {
			path.add(vertex);
		}
		int index = 0;
		//get the road names the vertex traveled
		for (Vertex i = path.get(path.size() - 1); i != null; i = path.get(path.size() - 1 - index)) {
			if (index >= 1) {
				for (Edge e : r.values()) {
					Vertex v = e.getV();
					Vertex w = e.getW();

					if ((i.equals(v) && i.getPrev().equals(w))) {
//						System.out.println(v + " to " + w + " through " + e.getId());
						shortestpathRoad.put(e.getId(), e);
						total_dist += Math.abs(e.getWeight());
					}else if (i.getPrev().equals(v) && i.equals(w)) {
//						System.out.println(v + " to " + w + " through Road " + e.getId());
						shortestpathRoad.put(e.getId(), e);
						total_dist += Math.abs(e.getWeight());
					}
				}
			}
			if (index == path.size() - 1) 
				break;

			index++;
		}
		System.out.println("Total distance: " + total_dist);

		Collections.reverse(path);
		return path;
	}

	public void KruskalMST(Graph tree) {
		PriorityQueue<Edge> q = new PriorityQueue<>(r.size(), Comparator.comparingDouble(i -> i.getWeight()));
		
		// add all the edges to priority queue
		for (Edge e : r.values()) {
			q.add(e);
		}

		// create a parent Vertex[]
		Vertex[] parent = new Vertex[i.size()];
		int index = 0;
		for (Vertex i : i.values()) {
			parent[index] = i;
			index++;
		}

//		Graph tree = new Graph();
		while (!q.isEmpty()) {
			Edge edge = q.poll();
//			System.out.println("Weight; " + edge.getWeight());
			Vertex v = edge.getV();
			Vertex w = edge.getW();
			
			String vName = v.getId(); //vertexV
			Vertex vInTree = tree.i.get(vName);
			if (vInTree == null) {
				// v is not in tree yet
				tree.insert(edge);
				continue;
			}

			String wName = w.getId();//vertexW
			Vertex wInTree = tree.i.get(wName);
			if (wInTree == null) {
				tree.insert(edge);
				continue;
			}

			// if wInTree is connected to vIntree
			tree.dijkstrasAlgorithm(vInTree);
			if (wInTree.getDist() < Double.MAX_VALUE) {
				// there is a path from vInTree to wIntTree
				continue;
			}
			tree.insert(edge);
		}
	}

	public void insert(Edge e) {
		Vertex v = e.getV();
		String vName = v.getId();
		Vertex v2 = i.get(vName);
		if (v2 != null) {
			v = v2;
		} else {
			i.put(vName, v);
		}

		Vertex w = e.getW();
		String wName = w.getId();
		Vertex w2 = i.get(wName);
		if (w2 != null) {
			w = w2;
		} else {
			i.put(wName, w);
		}
		
		String eName = v.getId() + "-> " + w.getId();
		
		e = new Edge(v, w, e.getId());
		v.getAdjList().add(e);

		e = new Edge(w, v, e.getId()); // maybe unnecessary?
		w.getAdjList().add(e);

		r.put(eName, e);
	}

	public void printGraph() {
		for (Edge e : r.values()) { // E, V
			System.out.println(
			"Edge/Road-" + e.getId() + " source: " + e.getV() + " Destination: " + e.getW() + " weight: " + e.getWeight());
		}
	}
	
	public int scaleLatitude(double point) {
		return (int)(frame.getWidth()/10 + ((frame.getWidth()-frame.getWidth()/3)/(max_latitude-min_latitude))*(point-min_latitude));
	}
	
	public int scaleLongitude(double point) {
		return (int)(frame.getHeight()/10 + ((frame.getHeight()-frame.getHeight()/3)/(max_longitude-min_longitude))*(point-min_longitude));
	}
	
	private void drawMap(Graphics g) {
		int height = frame.getHeight();
		int width = frame.getWidth();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		
		for (int i = 0; i < latitudes.size(); i++) {
			if (latitudes.get(i) > max_latitude) {
				max_latitude = latitudes.get(i);
			}
			if (latitudes.get(i) < min_latitude) {
				min_latitude = latitudes.get(i);
			}
		}
		
		for (int i = 0; i < longitudes.size(); i++) {
			if (longitudes.get(i) > max_longitude) {
				max_longitude = longitudes.get(i);
			}
			if (longitudes.get(i) < min_longitude) {
				min_longitude = longitudes.get(i);
			}
		}
		for (Edge e : r.values()) {
			Vertex v1 = e.getV();
			Vertex v2 = e.getW();
//			g2d.drawLine(100, 100, 200, 100);
//			System.out.println("latitude ------： " + (int) Math.round(v1.getLatitude()));
			g2d.drawLine(scaleLongitude(v1.getLongitude()), height - scaleLatitude(v1.getLatitude()),  scaleLongitude(v2.getLongitude()), height - scaleLatitude(v2.getLatitude()));
//			g2d.drawLine(scaleLatitude(v1.getLatitude()), height - scaleLongitude(v1.getLongitude()),
//					scaleLatitude(v2.getLatitude()), height - scaleLongitude(v2.getLongitude()));
		}
		for (Edge e : shortestpathRoad.values()) {
			Vertex v1 = e.getV();
			Vertex v2 = e.getW();
			g2d.setColor(Color.blue);
			g2d.drawLine(scaleLongitude(v1.getLongitude()), height - scaleLatitude(v1.getLatitude()),  scaleLongitude(v2.getLongitude()), height - scaleLatitude(v2.getLatitude()));
		}
		for (Edge e : mst.values()) {
			Vertex v1 = e.getV();
			Vertex v2 = e.getW();
			g2d.setColor(Color.red);
//			g2d.drawLine(scaleLongitude(v1.getLongitude()), height - scaleLatitude(v1.getLatitude()),  scaleLongitude(v2.getLongitude()), height - scaleLatitude(v2.getLatitude()));
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMap(g);
	}

	public static void main(String[] args) throws FileNotFoundException {
		// String startingpoint=args[0];
		// String endingpoint=args[1];

		// Can take a while to run
		Graph g = createFromFile("nys.txt");
		g.dijkstrasAlgorithm(g.i.get("i10"));
		System.out.println("Shortest Path:\n" + g.getShortestPathTo(g.i.get("i100")));
		
		// Next steps: 
		//  javac Graph.java
		//  Java Graph LOVEJOY RETTNER
		// Graph g = createFromFile("ur.txt");
		// g.dijkstrasAlgorithm(g.i.get("LOVEJOY"));
		// System.out.println("Shortest Path:\n" + g.getShortestPathTo(g.i.get("RETTNER")));

		for (Vertex v: g.i.values()) {
			g.latitudes.add(v.getLatitude());
			g.longitudes.add(v.getLongitude());
		}
		
		System.out.println();
		// create a new Graph for mst
		Graph tree = new Graph();
		
		g.KruskalMST(tree);
		
		System.out.println("Minimum Spanning Tree");
		tree.printGraph();
		for (Edge e: tree.r.values()) {
			mst.put(e.getId(), e);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(g);
		frame.setSize(900, 900);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
