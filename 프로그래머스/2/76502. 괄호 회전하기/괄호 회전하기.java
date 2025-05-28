import java.util.*;

class Solution {
    
    boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            // 여는 괄호일 때 스택에 넣기
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { // 닫는 괄호일 때
                
                // 스택이 비어있다 == 짝이 없다
                if (stack.isEmpty()) return false;
                
                char target = stack.pop();
                
                // 괄호가 달라 짝을 이루지 않는 경우
                if (c == ')' && target != '(') return false;
                if (c == '}' && target != '{') return false;
                if (c == ']' && target != '[') return false;
            }
        }
        
        // 올바른 경우일 때 return true
        return stack.isEmpty();
    }
    
    public int solution(String s) {
        
        int answer = 0;
        String origin = s;
        int len = s.length();
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : queue) {
                sb.append(c);
            }
            
            if (isValid(sb.toString())) {
                answer++;
            }
            
            queue.offer(queue.poll());
        }
        
        return answer;
    }
}