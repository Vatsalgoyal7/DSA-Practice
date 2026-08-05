import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        // Step 2: Find all suspicious methods starting from k
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);

        // Step 3: Check if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                // Cannot remove suspicious methods; return all methods
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Step 4: Return remaining non-suspicious methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] isSuspicious) {
        isSuspicious[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!isSuspicious[neighbor]) {
                dfs(neighbor, graph, isSuspicious);
            }
        }
    }
}