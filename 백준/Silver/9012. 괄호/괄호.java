import java.util.*;

public class Main {
    public static String solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return "NO";
                stack.pop();
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            System.out.println(solution(input));
        }
    }
}
