

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import org.jgrapht.alg.util.Pair;

public class minCutAlgorithmB {
    private Set<Integer> A;
    private Set<Integer> B;
    private int num_vertices;
    private Set<Pair<Integer, Integer>> edges;
    
    final public static String PATH = "./src/input.txt";
    
    /*
     * Constructor will read input from file and store
     * the edge relationships and the number of vertices
     */
    public minCutAlgorithmB(String filepath) throws IOException {
        edges = new HashSet<Pair<Integer, Integer>>();
        File f = new File(filepath);
        FileReader fin = new FileReader(f);
        Scanner scan = new Scanner(fin);
        num_vertices = scan.nextInt();
        scan.nextInt();
        while(scan.hasNext()) {
            int temp_a = scan.nextInt();
            Pair<Integer, Integer> edge = new Pair<Integer, Integer>(temp_a, scan.nextInt());
            edges.add(edge);
        }
        A = new HashSet<Integer>();
        B = new HashSet<Integer>();
        scan.close();
        fin.close();
    }

    public static void main(String[] args) throws IOException {
        minCutAlgorithmB use = new minCutAlgorithmB(PATH);
        use.initialize();
        while(!use.done()) {
            int next = use.getBestVertex(); // Find the best vertex to move to set A
            use.shiftVertex(next); // Shift the best vertex to set A
        }
        System.out.println(use.getOutput());
    }
    
    private String getOutput() {
        String out = "";
        int count = 0;
        for(Pair<Integer, Integer> e : edges) {
            if((A.contains(e.first) && A.contains(e.second))
                    || B.contains(e.first) && B.contains(e.second))  {
                continue;
            }
            count++;
        }
        out += count;
        out += '\n';
        for(int i : A) {
            out += i + " ";
        }
        out = out.trim();
        out += '\n';
        for(int i : B) {
            out += i + " ";
        }
        out = out.trim();
        return out;
    }

    public boolean done() {
        return (A.size() == B.size()); // we are done when the two sets each contain n/2 vertices
    }
    
    public void shiftVertex(int vertex) {
        B.remove(vertex);
        A.add(vertex);
    }
    
    public void initialize() {
        for(int i = 1; i <= num_vertices; i++) {
            B.add(i);
        }
        Random rand = new Random();
        int ID = rand.nextInt(num_vertices) + 1;
        B.remove(ID);
        A.add(ID);
    }
    
    public int getBestVertex() {
        Vector<Integer> weights = new Vector<Integer>(num_vertices);
        for(int i = 0; i < num_vertices; i++) {
            weights.add(0);
        }
        for(Pair<Integer, Integer> e : edges) {
            if((A.contains(e.first) && A.contains(e.second))
                    || B.contains(e.first) && B.contains(e.second))  {
                continue;
            }
            int v;
            if(B.contains(e.first)) {
                v = e.first;
            } else {
                v = e.second;
            }
            weights.set(v - 1, weights.get(v - 1) + 1);
        }
        int best_weight = 0;
        int best_vertex = -1;
        for(int i = 0; i < weights.size(); i++) {
            int next = weights.get(i);
            if(next > best_weight) {
                best_weight = next;
                best_vertex = i + 1;
            }
        }
        return best_vertex;
    }
}
