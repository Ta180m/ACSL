import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int n;
		String input;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
		    input = sc.next();
		    n = sc.nextInt();
		    while (input.length() % n != 0) input += '0';
		    
            int len = input.length() / n;
            long ans = 0;
            for (int i = 0; i < len; i++) {
                ans += Long.parseLong(input.substring(i * n, (i + 1) *n));
            }
		    
		    System.out.println(ans);
		}
	}
}