import java.util.*;

class Solution {
    public int solution(String numbers) {
        
        Set<Integer> res = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];
        
        permute("", numbers, visited, res);
        
        int count = 0;
        for (int num : res) {
            if (isPrime(num)) count++;
        }
        
        return count;
    }
    
    // 순열 생성
    private void permute(String current, String numbers, boolean[] visited, Set<Integer> res) {
        if (!current.isEmpty()) {
            int num = Integer.parseInt(current);
            res.add(num);
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permute(current + numbers.charAt(i), numbers, visited, res);
                visited[i] = false;
            }
        }
    }
    
    // 소수 판별
    private boolean isPrime(int num) {
        if (num < 2) return false;
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
}