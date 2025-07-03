import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int node;
        int intensity;
        
        Node(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        
        // 출입구와 산봉우리 구분
        Set<Integer> gateSet = new HashSet<>();
        for (int g : gates) gateSet.add(g);
        
        Set<Integer> summitSet = new HashSet<>();
        for (int s : summits) summitSet.add(s);
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        Queue<Node> pq = new PriorityQueue<>();
        
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (intensity[cur.node] < cur.intensity) continue;
            if (summitSet.contains(cur.node)) continue;
            
            for (Node next : graph.get(cur.node)) {
                if (gateSet.contains(next.node)) continue;  // 출입구 재방문 금지
                
                int maxIntensity = Math.max(intensity[cur.node], next.intensity);
                if (intensity[next.node] > maxIntensity) {
                    intensity[next.node] = maxIntensity;
                    pq.offer(new Node(next.node, maxIntensity));
                }
            }
        }
        
        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;
        
        Arrays.sort(summits);
        
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensity[summit];
            }
        }
        
        return new int[] {minSummit, minIntensity};
    }
}