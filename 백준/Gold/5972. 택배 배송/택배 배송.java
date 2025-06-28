import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static class Cow implements Comparable<Cow> {
        int node;
        int cost;

        public Cow(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cow o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        List<List<Cow>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] tokens = br.readLine().split(" ");

            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            int w = Integer.parseInt(tokens[2]);
            graph.get(u).add(new Cow(v, w));
            graph.get(v).add(new Cow(u, w));
        }

        // 다익스트라
        int INF = Integer.MAX_VALUE;
        int[] costs = new int[n + 1];
        Arrays.fill(costs, INF);
        costs[1] = 0;

        PriorityQueue<Cow> pq = new PriorityQueue<>();
        pq.add(new Cow(1, 0));

        while (!pq.isEmpty()) {
            // 방문
            Cow cur = pq.poll();
            // 이미 더 짧은 경로로 방문한 적이 있다면 skip
            if (costs[cur.node] < cur.cost) continue;

            // 예약
            for (Cow next : graph.get(cur.node)) {
                int nextCost = costs[cur.node] + next.cost;
                if (costs[next.node] > nextCost) {
                    costs[next.node] = nextCost;
                    pq.add(new Cow(next.node, nextCost));
                }
            }
        }

        System.out.println(costs[n]);
    }
}
