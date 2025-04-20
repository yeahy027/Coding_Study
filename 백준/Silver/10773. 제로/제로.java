import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            
            if (num == 0) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(num);
            }
        }
        
        int sum = 0;
        for (int num : stack) {
            sum += num;
        }
        
        System.out.println(sum);
        
    }
}