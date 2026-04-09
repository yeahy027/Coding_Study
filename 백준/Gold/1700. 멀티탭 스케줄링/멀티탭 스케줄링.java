import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] use = new int[K];

        for (int i = 0; i < K; i++) {
            use[i] = sc.nextInt();
        }

        Set<Integer> multitap = new HashSet<>();
        int answer = 0;

        for (int i = 0; i < K; i++) {
            int cur = use[i];

            if (multitap.contains(cur)) continue;

            if (multitap.size() < N) {
                multitap.add(cur);
                continue;
            }

            int farthest = -1;
            int remove = -1;

            for (int plugged : multitap) {
                int nextUse = K;

                for (int j = i + 1; j < K; j++) {
                    if (use[j] == plugged) {
                        nextUse = j;
                        break;
                    }
                }

                if (nextUse > farthest) {
                    farthest = nextUse;
                    remove = plugged;
                }
            }
            multitap.remove(remove);
            multitap.add(cur);
            answer++;
        }

        System.out.println(answer);
    }
}