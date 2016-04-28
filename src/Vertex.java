public class Vertex {
	private int id;
	
	public int getId() {
		return id;
	}

	public Vertex(int id){
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "" + id + " ";
	}
}
