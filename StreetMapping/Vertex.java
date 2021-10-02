

import java.util.*;

public class Vertex implements Comparable<Vertex> {
//	private String id;
	private double latitude;
	private double longitude;
	private ArrayList<Edge> adjList;
	private double dist;
	private boolean visited;
	private Vertex prev;
	private String id;
	
	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public Vertex() {
		
	}
	public Vertex(double latitude, double longitude, String id) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.adjList = new ArrayList<>();
		dist = Integer.MAX_VALUE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return id;
	}
	
	public void connect(Vertex v) {
		Edge e = new Edge(this, v);
		adjList.add(e);
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	public ArrayList<Edge> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<Edge> adjList) {
		this.adjList = adjList;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public int compareTo(Vertex o) {
		if (this.dist > o.getDist())
			return 1;
		else if (this.dist < o.getDist())
			return -1;
		else
			return 0;
	}

	
	
}
