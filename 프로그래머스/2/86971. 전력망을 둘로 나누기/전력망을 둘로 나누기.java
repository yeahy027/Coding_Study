import java.util.*;

class Solution {
    int answer;
    boolean[] visited;
    Map<Integer, List<Integer>> graph;
    
    public int solution(int n, int[][] wires) {
        answer = n;
        
        for (int i = 0; i < wires.length; i++) {
            graph = new HashMap<>();
        
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                int a = wires[j][0];
                int b = wires[j][1];
                graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
                graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            }
        
            visited = new boolean[n + 1];
            dfs(1, n);
        }
        
        return answer;
    }
    
    private int dfs(int cur, int n) {
        int count = 1;
        visited[cur] = true;
        
        for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited[next]) {
                count += dfs(next, n);
            }
        }
        if (cur == 1) {
            answer = Math.min(answer, Math.abs(n - count * 2));   
        }
        
        return count;
    }
}