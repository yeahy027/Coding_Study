import java.util.*;

public class Main {
    static int k;
    static char[] signs;
    static boolean[] visited = new boolean[10];
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        signs = new char[k];

        for (int i = 0; i < k; i++) {
            signs[i] = sc.next().charAt(0);
        }

        dfs(0, "");

        // 정렬해서 가장 큰 값과 가장 작은 값을 출력
        Collections.sort(result);
        System.out.println(result.get(result.size() - 1)); // 최대
        System.out.println(result.get(0)); // 최소
    }

    static void dfs(int depth, String num) {
        if (depth == k + 1) {
            result.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                if (depth == 0 || check(num.charAt(depth - 1) - '0', i, signs[depth - 1])) {
                    visited[i] = true;
                    dfs(depth + 1, num + i);
                    visited[i] = false;
                }
            }
        }
    }

    static boolean check(int a, int b, char sign) {
        if (sign == '<') return a < b;
        else return a > b;
    }
}
