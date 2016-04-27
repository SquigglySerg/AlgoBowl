import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class minCut {
	private SimpleGraph<Vertex, DefaultEdge> graph;
	private String inputFile;
	private int numVertices = 0;
	private int numEdges = 0;
	private Map<Integer, Vertex> vertices;
	
	minCut(String filename){
		inputFile = filename;
		vertices = new HashMap<>();
		graph = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		
	}
	
	void readFromFile(){
		try {
			FileReader graphReader = new FileReader(inputFile);
			Scanner scan = new Scanner(graphReader);
			
			numVertices = Integer.parseInt(scan.next());
			numEdges = Integer.parseInt(scan.next());
			
			while(scan.hasNextLine()){
				int vertexId = Integer.parseInt(scan.next());
				int vertex2Id = Integer.parseInt(scan.next());
				vertices.put(vertexId, new Vertex(vertexId));
				vertices.put(vertex2Id, new Vertex(vertex2Id));
				graph.addVertex(vertices.get(vertexId));
				graph.addVertex(vertices.get(vertex2Id));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
