import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        int count = 1;
        
        while (a != b) {
            if (a > b) {
                count = -1;
                break;
            }
            if (b % 10 == 1) {
                b /= 10;
            } else if (b % 2 == 0) {
                b /= 2;
            } else {
                count = -1;
                break;
            }
            count++;
        }
        System.out.println(count);
    }
}