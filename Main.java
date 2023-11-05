import java.io.*;
import java.util.*;

public class Main {
    public static Graph read(String input) {
        Graph graph = null;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String[] vk = (br.readLine()).split(" ");
            int v = Integer.parseInt(vk[0]);
            int k = Integer.parseInt(vk[1]);
            graph = new Graph(v,k);
            for(int i = 1; i <= v - 1; i++){
                line = br.readLine();
                String[] edges = line.split(" ");
                if(edges[0].equals("")) // pt liniile goale de la finalul fisierelor (acolo unde se stiu deja muchiile pt nod)
                    break;
                for(int j = 0; j < edges.length; j++) {
                    graph.addEdge(i-1, Integer.parseInt(edges[j])-1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit");
        } catch (IOException e) {
        }
        return graph;
    }

    public static void print(String path, ArrayList<List<Integer>> cond){
        try (FileWriter fw = new FileWriter( path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for(int i = 0; i < cond.size(); i++){
                List<Integer> clauza = new ArrayList<Integer>();
                clauza = cond.get(i);
                for(int j = 0; j < clauza.size(); j++)
                    out.print(clauza.get(j)+" ");
                out.println("0");
            }
        } catch (IOException e) { }
    }

    public static void main(String[] args) {
        Graph graph = read(args[0]);
        String path = args[1];

        ArrayList<List<Integer>> cond1 = Graph.cond1(graph);
        ArrayList<List<Integer>> cond2 = Graph.cond2(graph);
        ArrayList<List<Integer>> cond3 = Graph.cond3(graph);

        int n = Graph.var_used.size();
        int m = cond1.size() + cond2.size() + cond3.size();

        try (FileWriter fw = new FileWriter(path , true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
                out.println("p cnf " +  n + " " + m);
        } catch (IOException e) { }
        print(path, cond1);
        print(path, cond2);
        print(path, cond3);
    }
}