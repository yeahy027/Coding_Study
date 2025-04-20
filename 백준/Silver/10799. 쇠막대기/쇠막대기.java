import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int count = 0;
        int open = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                open--;
                if (str.charAt(i - 1) == '(') {
                    count += open;
                } else {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}