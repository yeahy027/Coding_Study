import java.util.Scanner;

public class Main {
    static int N;
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        visited = new boolean[N + 1];  // 방문 체크
        result = new int[N];          // 현재 순열 결과 저장

        perm(0);
    }

    public static void perm(int depth) {
        if (depth == N) {
            // 순열 하나 완성되면 출력
            for (int i = 0; i < N; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = i;

                perm(depth + 1);

                // 백트래킹
                visited[i] = false;
            }
        }
    }
}
