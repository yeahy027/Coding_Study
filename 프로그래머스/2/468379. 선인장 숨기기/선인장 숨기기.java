import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        // 1. 각 칸에 비가 내리는 시간
        int[][] rain = new int[m][n];
        int INF = Integer.MAX_VALUE;
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(rain[i], INF);
        }
        
        for (int i = 0; i < drops.length; i++) {
            rain[drops[i][0]][drops[i][1]] = i + 1;
        }

        // 2. 가로 방향 슬라이딩 윈도우 최솟값 계산
        int[][] rowMin = new int[m][n - w + 1];
        for (int i = 0; i < m; i++) {
            rowMin[i] = getSlidingWindowMin(rain[i], w);
        }

        // 3. 세로 방향 슬라이딩 윈도우 계산 및 결과 저장
        int[][] resultGrid = new int[m - h + 1][n - w + 1];
        
        for (int j = 0; j <= n - w; j++) {
            int[] colData = new int[m];
            for (int i = 0; i < m; i++) {
                colData[i] = rowMin[i][j];
            }
            
            int[] colMin = getSlidingWindowMin(colData, h);
            for (int i = 0; i <= m - h; i++) {
                resultGrid[i][j] = colMin[i];
            }
        }

        // 4. 결과 (최상단, 최좌측)
        int bestTime = -1;
        int[] answer = new int[2];
        
        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                // 초과(>)를 통해 값이 같을 때는 먼저 발견된(위쪽, 왼쪽) 좌표를 유지
                if (resultGrid[i][j] > bestTime) {
                    bestTime = resultGrid[i][j];
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }

        return answer;
    }

    private int[] getSlidingWindowMin(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]) deque.pollLast();
            deque.addLast(i);
            if (i >= k - 1) result[i - k + 1] = arr[deque.peekFirst()];
        }
        return result;
    }
}