import java.util.*;

class Solution {
    
    class Edge implements Comparable<Edge> {
        int node;
        int cost;
        
        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    int[] dijkstra(int n, int start, List<List<Edge>> graph) {
        
        // 2. 다익스트라
        int INF = Integer.MAX_VALUE;
        int[] costs = new int[n + 1];
        Arrays.fill(costs, INF);
        costs[start] = 0;
        
        Queue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        
        // 3. while문 반복
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            for (Edge next : graph.get(cur.node)) {
                int nextCost = costs[cur.node] + next.cost;
                if (nextCost < costs[next.node]) {
                    costs[next.node] = nextCost;
                    pq.offer(new Edge(next.node, nextCost));
                }
            }
        }
        
        return costs;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 1. 그래프 변환
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];
            
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        
        // 3번의 다익스트라 진행
        int[] startS = dijkstra(n, s, graph);
        int[] startA = dijkstra(n, a, graph);
        int[] startB = dijkstra(n, b, graph);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (startS[i] == Integer.MAX_VALUE || startA[i] == Integer.MAX_VALUE
                || startB[i] == Integer.MAX_VALUE) continue;
            
            answer = Math.min(answer, startS[i] + startA[i] + startB[i]);
        }
        
        return answer;
    }
}