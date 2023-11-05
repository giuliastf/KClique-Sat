import java.util.*;
import java.io.*;
public class Graph {
    boolean adjM[][];
    int v;
    int k;
    static ArrayList<Integer> var_used = new ArrayList<>(); // nr de variable folosite

    // constructor
    public Graph(int v, int k) {
        this.v = v;
        this.k = k;
        adjM = new boolean[v][v];
    }

    // adauga muchie
    public void addEdge(int i, int j) {
        adjM[i][j] = true;
        adjM[j][i] = true;
    }

    // verifica daca exista muchia
    public int getEdge(int i, int j) {
        if(adjM[i][j] == true)
            return 1;
        return 0;
    }

    public static ArrayList<List<Integer>> cond1(Graph g){
        ArrayList<List<Integer>> c1 = new ArrayList<List<Integer>>();
        List<Integer> clauza;
        int literal;
        for(int i = 1; i <= g.k; i++) {
            clauza = new ArrayList<Integer>();
            for (int v = 1; v <= g.v; v++) {
                literal = (i - 1) * g.v + v;
                clauza.add(literal);
                if(var_used.contains(literal) == false)
                    var_used.add(literal);
            }
            c1.add(clauza);
        }
        return c1;
    }

    public static ArrayList<List<Integer>> cond2(Graph g){
        ArrayList<List<Integer>> c2 = new ArrayList<List<Integer>>();
        List<Integer> clauza;
        int literal1, literal2;
        for(int v = 1; v <= g.v; v++)
            for(int i = 1; i < g.k; i++) //
                for(int j = i + 1; j <= g.k; j++) {
                    clauza = new ArrayList<Integer>();
                    literal1 = ((i - 1) * g.v + v);
                    literal2 = ((j - 1) * g.v + v);
                    clauza.add(-literal1);
                    clauza.add(-literal2);
                    if(var_used.contains(literal1) == false)
                        var_used.add(literal1);
                    if(var_used.contains(literal2) == false)
                        var_used.add(literal2);
                    c2.add(clauza);
                }
        return c2;
    }

    public static ArrayList<List<Integer>> cond3(Graph g){
        ArrayList<List<Integer>> c3 = new ArrayList<List<Integer>>();
        List<Integer> clauza;
        int literal1, literal2;
        for(int u = 1; u < g.v; u++) {
            for (int v = u + 1; v <= g.v; v++) {
                for (int i = 1; i <= g.k; i++) {
                    for (int j = 1; j <= g.k; j++) {
                        if (i != j && g.getEdge(u-1, v-1) == 0){
                            clauza = new ArrayList<Integer>();
                            literal1 = ((i - 1) * g.v + u);
                            clauza.add(-literal1);
                            literal2 = ((j - 1) * g.v + v);
                            clauza.add(-literal2);
                            if(var_used.contains(literal1) == false)
                                var_used.add(literal1);
                            if(var_used.contains(literal2) == false)
                                var_used.add(literal2);
                            c3.add(clauza);
                        }
                    }
                }
            }
        }
        return c3;
    }
}