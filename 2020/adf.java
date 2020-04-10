import java.util.Scanner;
public class Main {
    static StringBuilder s, t;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) { s = readString(); t = readString(); System.out.println(solve(0, s.length(), 0, t.length())); }
	}
	public static StringBuilder readString() {
        String a; do a = sc.nextLine(); while (a.equals("")); StringBuilder ret = new StringBuilder();
        for (int i = 0; i < a.length(); i++) if ((a.charAt(i) >= 'a' && a.charAt(i) <= 'z') || (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z')) ret.append(Character.toUpperCase(a.charAt(i)));
        return ret;
	}
	public static int solve(int sl, int sr, int tl, int tr) {
        if (sl >= sr || tl >= tr) return 0;
        int m = 0, sm = -1, tm = -1;
        for (int i = sl; i < sr; i++) {
            for (int j = tl; j < tr; j++) {
                int len = 0; while (i + len < sr && j + len < tr && s.charAt(i + len) == t.charAt(j + len)) len++;
                if (len > m || (m > 0 && len == m && s.substring(i, i + len).compareTo(s.substring(sm, sm + m)) < 0)) { m = len; sm = i; tm = j; }
            }
        }
        return sm != -1 ? m + solve(sl, sm, tl, tm) + solve(sm + m, sr, tm + m, tr) : 0;
    }
}