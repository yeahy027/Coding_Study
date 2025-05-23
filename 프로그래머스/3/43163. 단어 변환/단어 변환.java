import java.util.*;

class Solution {
    
    class Word {
        String word;
        int count;
        
        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    int getDiffCount(String word1, String word2) {
        int count = 0;
        
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        
        return count;
    }
    
    public int solution(String begin, String target, String[] words) {
        Queue<Word> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        // 시작점
        queue.offer(new Word(begin, 0));
        
        while (!queue.isEmpty()) {
            
            Word cur = queue.poll();
            
            // begin == target일 때
            if (cur.word.equals(target)) return cur.count;
            
            // 계속해서 변환해야 한다면
            for (int i = 0; i < words.length; i++) {
                String next = words[i];
                if (!visited[i] && getDiffCount(cur.word, next) == 1) {
                    visited[i] = true;
                    queue.offer(new Word(next, cur.count + 1));
                }
            } 
        }
        
        return 0;
    }
}