import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[][] arr = new String[n][2];  // [나이, 이름]

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.next(); // 나이
            arr[i][1] = sc.next(); // 이름
        }

        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Integer.parseInt(a[0]) - Integer.parseInt(b[0]); // 나이 오름차순
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
