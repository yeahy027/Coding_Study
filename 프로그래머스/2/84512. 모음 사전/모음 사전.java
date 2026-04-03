import java.util.*;

class Solution {
    
    static int answer = 0;
    static int count = 0;
    
    public int solution(String word) {
        dfs("", word);
        return answer;
    }
    
    public void dfs(String cur, String target) {
        if (cur.length() > 5) return;
        
        if (cur.equals(target)) {
            answer = count;
            return;
        }
        
        count++;
        
        for (String s : new String[]{"A", "E", "I", "O", "U"}) {
            dfs(cur + s, target);
        }
    }
}