import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Bus implements Comparable<Bus> {
        int node;
        int cost;

        public Bus(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Bus>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            String[] tokens = br.readLine().split(" ");

            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);

            graph.get(u).add(new Bus(v, cost));
        }

        String[] startEnd = br.readLine().split(" ");
        int start = Integer.parseInt(startEnd[0]);
        int end = Integer.parseInt(startEnd[1]);

        // 다익스트라 알고리즘
        int INF = Integer.MAX_VALUE;
        int[] costs = new int[n + 1];
        Arrays.fill(costs, INF);

        // 초기값 넣기
        Queue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));
        costs[start] = 0;

        while(!pq.isEmpty()) {
            // 방문
            Bus cur = pq.poll();
            // 이미 더 짧은 경로로 방문한 적이 있다면 skip
            if (costs[cur.node] < cur.cost) continue;

            // 예약
            for (Bus next : graph.get(cur.node)) {
                int nextCost = costs[cur.node] + next.cost;
                if (costs[next.node] > nextCost) {
                    pq.offer(new Bus(next.node, nextCost));
                    costs[next.node] = nextCost;
                }
            }
        }

        System.out.println(costs[end] == INF ? -1 : costs[end]);
    }
}