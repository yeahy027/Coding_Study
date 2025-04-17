import java.util.Scanner;

public class Main {
    
    // 최대공약수
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // 최소공배수
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        System.out.println(gcd(a, b));
        System.out.println(lcm(a, b));
    }
}