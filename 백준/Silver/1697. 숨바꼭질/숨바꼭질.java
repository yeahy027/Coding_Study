import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int MAX = 100001;
        boolean[] visited = new boolean[MAX];
        int[] time = new int[MAX]; // 각 위치까지 걸린 시간

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visited[N] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == K) {
                System.out.println(time[current]);
                break;
            }

            // 이동 가능한 세 가지 위치
            int[] next = {current - 1, current + 1, current * 2};

            for (int n : next) {
                if (n >= 0 && n < MAX && !visited[n]) {
                    visited[n] = true;
                    time[n] = time[current] + 1;
                    q.add(n);
                }
            }
        }
    }
}
