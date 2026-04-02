import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, (a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        int answer = 0;
        int point = 0;
        
        for (int i = 0; i < targets.length; i++) {
            if (targets[i][0] >= point) {
                answer++;
                point = targets[i][1];
            }
        }
        
        return answer;
    }
}