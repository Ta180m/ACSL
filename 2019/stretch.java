import java.util.*;
public class Main {
	static int len_x[] = { 0, 2, 1, 2, 1 }, len_y[] = { 2, 0, 1, 1, 2 }, len[] = { 3, 3, 3, 4, 4 };
	static int dx[][] = { { 0, 0, 0 }, { 0, 1, 2 }, { 0, 1, 1 }, { 0, 0, 1, 2 }, { 0, 0, 1, 1 } };
	static int dy[][] = { { 0, 1, 2 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 1, 1, 2 } };

	static int rdx[][] = { { 0, 0, 0 }, { 0, 1, 2 }, { 0, 0, 1 }, { 0, 1, 2, 2 }, { 0, 0, 1, 1 } };
	static int rdy[][] = { { 0, 1, 2 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 0, 0, 1 }, { 0, 1, 1, 2 } };

	static char int_to_char[] = { 'A', 'B', 'C', 'D', 'E' };

	static int r, c;
	static boolean G[][];

	static Vector<Integer> ans;

	enum Tiles { A, B, C, D, E; };

	public static void main(String[] args) {
		String[] output = { "", "", "", "", "" };
		Scanner sc = new Scanner(System.in);
		ans = new Vector<>();
		G = new boolean[100][100];

		for (int i = 0; i < 5; i++) {
			r = sc.nextInt();
			c = sc.nextInt();
			int s = sc.nextInt();
			int n = sc.nextInt();

			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) G[j][k] = false;
			}

			if (s % c != 0) {
				for (int j = 0; j < n; j++) {
					int x = sc.nextInt();
					x--;
					G[x / c][x % c] = true;
				}

				s--;
				solve(s / c, s % c, 0);
			}
			else {
				for (int j = 0; j < n; j++) {
					int x = sc.nextInt();
					x--;
					x = r * c - x - 1;
					G[x / c][x % c] = true;
				}

				s--;
				s = r * c - s - 1;
				rsolve(s / c, s % c, 0);
			}

			for (int j = 0; j < ans.size(); j++) {
				output[i] = output[i] + int_to_char[ans.get(j)];
			}
			ans.clear();
			System.out.println(output[i]);
		}
	}

	static void solve(int x, int y, int n) {
		if (y == c) return;

		int prev = (n + 4) % 5;
		for (int i = 0; i < 5; i++) {
			int cur = (n + i) % 5;
			if ((cur == 1 || cur == 2) && ans.isEmpty()) continue;

			if (prev == 1 && cur == 1) { // B to B
				if (x - 4 >= 0 && y < c) {
					boolean valid = true;
					for (int j = 0; j < 3; j++) {
						if (G[x + dx[1][j] - 4][y]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						solve(x - 2, y + 1, 2);
						break;
					}
				}
			}
			else if ((prev == 0 || prev == 2 || prev == 4) && cur == 1) { // A OR C OR E to B
				if (x - 2 >= 0 && y < c) {
					boolean valid = true;
					for (int j = 0; j < 3; j++) {
						if (G[x + dx[1][j] - 2][y]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						solve(x, y + 1, 2);
						break;
					}
				}
			}
			else if (prev == 1 && (cur == 0 || cur == 3 || cur == 4)) { // B to A OR D OR E
				if (y + len_y[cur] < c) {
					boolean valid = true;
					for (int j = 0; j < len[cur]; j++) {
						if (G[x + dx[cur][j] - 2][y + dy[cur][j]]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						solve(x + len_x[cur] - 2, y + len_y[cur] + 1, (cur + 1) % 5);
						break;
					}
				}
			}
			if (x + len_x[cur] < r && y + len_y[cur] < c) {
				boolean valid = true;
				for (int j = 0; j < len[cur]; j++) {
					if (G[x + dx[cur][j]][y + dy[cur][j]]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.add(cur);
					solve(x + len_x[cur], y + len_y[cur] + 1, (cur + 1) % 5);
					break;
				}
			}
		}
	}

	static void rsolve(int x, int y, int n) {
		if (y == c) return;

		int prev = (n + 4) % 5;
		for (int i = 0; i < 5; i++) {
			int cur = (n + i) % 5;
			if ((cur == 1 || cur == 3) && ans.isEmpty()) continue;

			if (prev == 1 && cur == 1) { // B to B
				if (x - 4 >= 0 && y < c) {
					boolean valid = true;
					for (int j = 0; j < 3; j++) {
						if (G[x + rdx[1][j] - 4][y]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						rsolve(x - 2, y + 1, 2);
						break;
					}
				}
			}
			else if ((prev == 0 || prev == 3 || prev == 4) && cur == 1) { // A OR D OR E to B
				if (x - 2 >= 0 && y < c) {
					boolean valid = true;
					for (int j = 0; j < 3; j++) {
						if (G[x + rdx[1][j] - 2][y]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						rsolve(x, y + 1, 2);
						break;
					}
				}
			}
			else if (prev == 1 && (cur == 0 || cur == 2 || cur == 4)) { // B to A OR C OR E
				if (y + len_y[cur] < c) {
					boolean valid = true;
					for (int j = 0; j < len[cur]; j++) {
						if (G[x + rdx[cur][j] - 2][y + rdy[cur][j]]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						ans.add(cur);
						rsolve(x + len_x[cur] - 2, y + len_y[cur] + 1, (cur + 1) % 5);
						break;
					}
				}
			}
			if (x + len_x[cur] < r && y + len_y[cur] < c) {
				boolean valid = true;
				for (int j = 0; j < len[cur]; j++) {
					if (G[x + rdx[cur][j]][y + rdy[cur][j]]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.add(cur);
					rsolve(x + len_x[cur], y + len_y[cur] + 1, (cur + 1) % 5);
					break;
				}
			}
		}
	}
}