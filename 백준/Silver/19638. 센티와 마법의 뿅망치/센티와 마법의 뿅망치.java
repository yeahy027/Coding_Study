import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int count = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // pq에 거인의 키를 모두 넣기
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        
        while (t > 0) {
            int max = pq.peek();
            
            // 횟수를 다 사용하지 않고도 모든 거인의 키가 센티의 키보다 작은 경우
            // 계속 실행할 필요 없음
            if (max < h) break;
            
            // 1인 경우 더 줄일 수 없음
            if (max == 1) break;
            
            pq.poll();
            pq.add(max/2);
            count++;
            t--;
        }
        
        if (pq.peek() < h) {
            System.out.println("YES");
            System.out.println(count);
        } else {
            System.out.println("NO");
            System.out.println(pq.peek());
        }
    }
}
