import java.util.*;
public class Main {
	public static boolean find(int a[], int v) {
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == v) return true;
		}
		return false;
	}
	public static void main(String[] args) {
		int TC = 5;
		Scanner sc = new Scanner(System.in);
		int pr[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47 };
		int sq[] = { 9, 16, 25, 36, 49 };
		int bd[] = { 3, 7, 8, 12, 13, 17, 18, 22, 26, 27, 31, 35, 36, 40, 41, 45, 46, 50 };
		for (int t = 0; t < TC; ++t) {
			int a[] = new int[3], b[] = new int[3];
			for (int i = 0; i < 3; ++i) a[i] = sc.nextInt();
			for (int i = 0; i < 3; ++i) b[i] = sc.nextInt();
			Arrays.sort(a);
			Arrays.sort(b);
			boolean oc[] = new boolean[100];
			for (int i = 0; i < 100; ++i) oc[i] = false;
			for (int i = 0; i < 3; ++i) {
				oc[a[i]] = true;
				oc[b[i]] = true;
			}
			int r;
			r = sc.nextInt();
			for (int i = 0; i < r; ++i) {
				int c = (i % 2 == 0 ? a[0] : b[0]);
				oc[c] = false;
				int d = sc.nextInt();
				if (c + d > 52 || oc[c + d]) {
					// Do nothing
				}
				else if (c + d == 52) {
					c = 100;
				}
				else if (find(pr, c + d)) {
					c += d;
					for (int j = 0; j < 6; ++j) {
						if (oc[c + 1]) break;
						++c;
					}
				}
				else if (find(sq, c + d)) {
					c += d;
					for (int j = 0; j < 6; ++j) {
						if (oc[c - 1]) break;
						--c;
					}
				}
				else if (c + d != 1 && c + d != 4) {
					boolean flag = true;
					for (int j = 0; j < bd.length && flag; ++j) {
						if (c < bd[j] && c + d > bd[j]) flag = false;
					}
					c += d;
					if (!flag) {
						for (int j = 0; j < d; ++j) {
							if (c % d == 0 && !oc[c]) break;
							--c;
						}
					}
				}
				else {
					c += d;
				}
				oc[c] = true;    
				if (i % 2 == 0) a[0] = c;
				else b[0] = c;
				Arrays.sort(a);
				Arrays.sort(b);
			}
			int sa = 0, sb = 0;
			for (int i = 0; i < 3; ++i) {
				if (a[i] != 100) sa += a[i];
				if (b[i] != 100) sb += b[i];
			}
			System.out.printf("%d %d\n", sa, sb);
		}
	}
}
