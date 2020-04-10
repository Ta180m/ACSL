import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner SC = new Scanner(System.in);
        for (int TC = 0; TC < 5; TC++) {
            long N = SC.nextLong(); int P = SC.nextInt();
            String S = String.valueOf(N); int l = S.length();
            for (int i = 0; i < l; i++) {
                if (i == l - P) {
                    int cnt = 0; long tmp = N;
                    for (int p = 2; p <= Math.sqrt(N); p++) { if (tmp%p==0) cnt++; while (tmp%p==0) tmp/=p; }
                    System.out.print(cnt+(tmp>1?1:0));
                }
                else System.out.print(Math.abs((S.charAt(i)-'0')+(i<l-P?1:-1)*(S.charAt(l-P)-'0')));
            }
            System.out.println();
        }
	}
}