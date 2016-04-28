import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class minCut {
	private SimpleGraph<Vertex, DefaultEdge> graph;
	private String inputFile;
	private int numVertices = 0;
	private int numEdges = 0;
	private Map<Integer, Vertex> vertices;
	private Set<Integer> keySet;
	
	minCut(String filename){
		inputFile = filename;
		vertices = new HashMap<>();
		graph = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
		keySet = new HashSet<>();
		
		readFromFile();
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
				
				keySet = vertices.keySet();
				if(!keySet.contains(vertexId)){
					vertices.put(vertexId, new Vertex(vertexId));
					graph.addVertex(vertices.get(vertexId));
				}
				if(!keySet.contains(vertex2Id)){
					vertices.put(vertex2Id, new Vertex(vertex2Id));
					graph.addVertex(vertices.get(vertex2Id));
				}
				
				graph.addEdge(vertices.get(vertexId), vertices.get(vertex2Id));
			}
			
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		Set<DefaultEdge> edges = graph.edgeSet();
		for(DefaultEdge e: edges){
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		minCut temp = new minCut("src/tempInput.txt");

	}

}
