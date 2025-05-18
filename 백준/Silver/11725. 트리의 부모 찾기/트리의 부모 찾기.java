import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        visited = new boolean[n + 1];

        // 트리 초기화
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        // 루트 노드인 1부터 dfs
        dfs(1);

        // 부모 노드 출력
        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }

    }

    static void dfs(int node) {
        visited[node] = true;

        for (int next : tree.get(node)) {
            if (!visited[next]) {
                parent[next] = node;
                dfs(next);
            }
        }
    }
}