import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConnectedComponents {

    /*
     * TODO
     */
    public static <V> void
    connected_components(Graph<V> G, Map<V, V> representative) {
        Map<V,Boolean> visited = new HashMap<>();
        for (V v : G.vertices()) visited.put(v, false);

        for (V v : G.vertices()) {
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
}
