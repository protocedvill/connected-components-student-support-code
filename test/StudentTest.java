import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StudentTest {
    @Test
    public void connected_components_demo() {
        UndirectedAdjacencyList G = new UndirectedAdjacencyList(7);
        G.addEdge(0,1);
        G.addEdge(1,2);
        G.addEdge(3,4);
        G.addEdge(3,3);
        G.addEdge(1,1);

        G.addEdge(5,5);
        G.addEdge(5,6);



        HashMap<Integer, Integer> v = new HashMap<>();

        ConnectedComponents.connected_components(G, v);

        for (Integer i : G.vertices()) {
            System.out.println(i + " -> " + v.get(i));
        }
    }

    @Test
    public void empty() {
        UndirectedAdjacencyList G = new UndirectedAdjacencyList(0);

        HashMap<Integer,Integer> testMap = new HashMap<>();
        ConnectedComponents.connected_components(G, testMap);

        assertTrue(testMap.isEmpty());
    }

    @Test
    public void largeRandom() {
        for (int j = 0; j < 4; j++) {
            int CAP = 1000;
            UndirectedAdjacencyList G = new UndirectedAdjacencyList(CAP);
            Random r = new Random();


            for (int i = 0; i < CAP; i++) {
                if (r.nextBoolean()) G.addEdge(r.nextInt(CAP), r.nextInt(CAP));
                if (j > 0) G.addEdge(r.nextInt(CAP), r.nextInt(CAP));
                if (j > 1) G.addEdge(r.nextInt(CAP), r.nextInt(CAP));
                if (j > 2) G.addEdge(r.nextInt(CAP), r.nextInt(CAP));
            }

            HashMap<Integer, Integer> oracleMap = new HashMap<>();
            oracle(G, oracleMap);

            HashMap<Integer, Integer> testMap = new HashMap<>();
            ConnectedComponents.connected_components(G, testMap);

            for (Integer i : G.vertices()) {
                assertEquals(oracleMap.get(i), testMap.get(i));
            }
        }
    }

    private void oracle(UndirectedAdjacencyList G, HashMap<Integer, Integer> representative) {
        HashMap<Integer,Boolean> visited = new HashMap<>();
        for (Integer v : G.vertices()) visited.put(v, false);

        for (Integer v : G.vertices()) {
            if (!visited.get(v)) {
                dfs(G, v, visited, v, representative);
            }
        }
    }

    static <V> void
    dfs(Graph<V> G, V start, Map<V,Boolean> visited, V head, Map<V,V> rep) {
        if (!visited.get(start)) {
            visited.put(start, true);
            rep.put(start, head);
            for (V v : G.adjacent(start)) {
                if (!visited.get(v)) {
                    rep.put(v, head);
                    dfs(G, v, visited, head, rep);
                }
            }
        }
    }


    @Test
    public void test() {
        connected_components_demo();
        largeRandom();
    }

}
