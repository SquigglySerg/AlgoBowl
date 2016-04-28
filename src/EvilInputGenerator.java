/*
 * EvilInputGenerator.java
 * Written by Jason Ziolo as part of AlgoBowl project
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * The only external JAR file referenced is: jgrapht-core-0.9.2.jar
 * Download the library from jgrapht.org
 */

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class EvilInputGenerator {
    static public final int MAX_VERTICES = 1000;
    static public final int MAX_EDGES = 100000;
    static public final int MAX_EDGES_INPUT = MAX_EDGES;
    public final static String PREFIX = "./src/";
    static public final String PATH = PREFIX + "input.txt";
    
    public int vertex_count = 0;
    public int edge_count = 0;
    
    /*
     * The anticipated output file will look like this:
     * 1
     * 1 2 3 .... 500
     * 501 502 503 ... 1000
     */
    
    public static void main(String[] args) throws IOException {
        File input = new File(PATH);
        FileWriter fout = new FileWriter(input);
        EvilInputGenerator gen = new EvilInputGenerator();
        Set<String> edges = gen.get_input();
        fout.write(gen.vertex_count + " " + gen.edge_count + "\n"); 
        for(String i : edges) {
            fout.write(i + "\n");
        }
        fout.close();
    }
    
    public Set<String> get_input() {
        UndirectedGraph<Vertex, DefaultEdge> graph = new SimpleGraph<Vertex, DefaultEdge>(DefaultEdge.class);
        Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
        for(int i = 1; i <= MAX_VERTICES; i++) {
            Vertex next = new Vertex(i);
            graph.addVertex(next);
            vertices.put(i, next);
        }
        vertex_count = vertices.size();
        Random rand = new Random();
        Set<String> edges = new HashSet<String>();
        while(edge_count < MAX_EDGES_INPUT - 2) {
            Vertex vertex_a = vertices.get(rand.nextInt(MAX_VERTICES / 2) + 1);
            Vertex vertex_b = null;
            DefaultEdge edge = null;
            do {
                vertex_b = vertices.get(rand.nextInt(MAX_VERTICES / 2) + 1);
                if(vertex_a != vertex_b) {
                    edge = graph.addEdge(vertex_a, vertex_b);
                }
            } while(edge == null);
            edges.add(vertex_a.getId() + " " + vertex_b.getId());
            edge_count++;
            vertex_a = vertices.get(MAX_VERTICES - rand.nextInt(MAX_VERTICES / 2));
            do {
                vertex_b = vertices.get(MAX_VERTICES - rand.nextInt(MAX_VERTICES / 2));
                if(vertex_a != vertex_b) {
                    edge = graph.addEdge(vertex_a, vertex_b);
                }
            } while(edge == null);
            edges.add(vertex_a.getId() + " " + vertex_b.getId());
            edge_count++;
        }
        Vertex bridge_a = vertices.get(MAX_VERTICES / 2);
        Vertex bridge_b = vertices.get(MAX_VERTICES / 2 + 1);
        graph.addEdge(bridge_a, bridge_b);
        edge_count++;
        edges.add(bridge_a.getId() + " " + bridge_b.getId());
        return edges;
    }
}
