public class Edge {
	private Vertex v;
	private Vertex w;
	private double weight;
	private String id;
	
	public Edge() {

	}

	public Edge(Vertex v, Vertex w) {
		this.v = v;
		this.w = w;
		this.weight = calculateDistance();
	}
	
	public Edge(Vertex v, Vertex w, String id) {
		this.v = v;
		this.w = w;
		this.weight = calculateDistance();
		this.id = id;
	}
	
	public Edge(Vertex v, Vertex w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = calculateDistance();
	}
	
	public String toString(){
		return v + " to " + w;
	}
	
	public double calculateDistance() {
		return Math.sqrt(Math.pow(v.getLongitude() - w.getLongitude(), 2) + Math.pow(v.getLatitude() - w.getLatitude(), 2));
	}

	public double getWeight() {
		return weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}

	public Vertex getW() {
		return w;
	}

	public void setW(Vertex w) {
		this.w = w;
	}

}
