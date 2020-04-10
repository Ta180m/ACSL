#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int len_x[5] = { 0, 2, 1, 2, 1 }, len_y[5] = { 2, 0, 1, 1, 2 }, len[5] = { 3, 3, 3, 4, 4 };
vector<int> dx[5] = { { 0, 0, 0 }, { 0, 1, 2 }, { 0, 1, 1 }, { 0, 0, 1, 2 }, { 0, 0, 1, 1 } };
vector<int> dy[5] = { { 0, 1, 2 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 1, 1, 2 } };

vector<int> rdx[5] = { { 0, 0, 0 }, { 0, 1, 2 }, { 0, 0, 1 }, { 0, 1, 2, 2 }, { 0, 0, 1, 1 } };
vector<int> rdy[5] = { { 0, 1, 2 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 0, 0, 1 }, { 0, 1, 1, 2 } };

int r, c;
bool G[100][100];
vector<int> ans;

enum { A = 0, B, C, D, E };

void solve(int x, int y, int n) {
	if (y == c) return;

	int prev = (n + 4) % 5;
	for (int i = 0; i < 5; i++) {
		int cur = (n + i) % 5;
		if ((cur == B || cur == C) && ans.empty()) continue;

		if (prev == B && cur == B) { // B to B
			if (x - 4 >= 0 && y < c) {
				bool valid = true;
				for (int j = 0; j < 3; j++) {
					if (G[x + dx[B][j] - 4][y]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					solve(x - 2, y + 1, C);
					break;
				}
			}
		}
		else if ((prev == A || prev == C || prev == E) && cur == B) { // A OR C OR E to B
			if (x - 2 >= 0 && y < c) {
				bool valid = true;
				for (int j = 0; j < 3; j++) {
					if (G[x + dx[B][j] - 2][y]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					solve(x, y + 1, C);
					break;
				}
			}
		}
		else if (prev == B && (cur == A || cur == D || cur == E)) { // B to A OR D OR E
			if (y + len_y[cur] < c) {
				bool valid = true;
				for (int j = 0; j < len[cur]; j++) {
					if (G[x + dx[cur][j] - 2][y + dy[cur][j]]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					solve(x + len_x[cur] - 2, y + len_y[cur] + 1, (cur + 1) % 5);
					break;
				}
			}
		}
		if (x + len_x[cur] < r && y + len_y[cur] < c) {
			bool valid = true;
			for (int j = 0; j < len[cur]; j++) {
				if (G[x + dx[cur][j]][y + dy[cur][j]]) {
					valid = false;
					break;
				}
			}
			if (valid) {
				ans.push_back(cur);
				solve(x + len_x[cur], y + len_y[cur] + 1, (cur + 1) % 5);
				break;
			}
		}
	}
}

void rsolve(int x, int y, int n) {
	if (y == c) return;

	int prev = (n + 4) % 5;
	for (int i = 0; i < 5; i++) {
		int cur = (n + i) % 5;
		if ((cur == B || cur == D) && ans.empty()) continue;

		if (prev == B && cur == B) { // B to B
			if (x - 4 >= 0 && y < c) {
				bool valid = true;
				for (int j = 0; j < 3; j++) {
					if (G[x + rdx[B][j] - 4][y]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					rsolve(x - 2, y + 1, C);
					break;
				}
			}
		}
		else if ((prev == A || prev == D || prev == E) && cur == B) { // A OR D OR E to B
			if (x - 2 >= 0 && y < c) {
				bool valid = true;
				for (int j = 0; j < 3; j++) {
					if (G[x + rdx[B][j] - 2][y]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					rsolve(x, y + 1, C);
					break;
				}
			}
		}
		else if (prev == B && (cur == A || cur == C || cur == E)) { // B to A OR C OR E
			if (y + len_y[cur] < c) {
				bool valid = true;
				for (int j = 0; j < len[cur]; j++) {
					if (G[x + rdx[cur][j] - 2][y + rdy[cur][j]]) {
						valid = false;
						break;
					}
				}
				if (valid) {
					ans.push_back(cur);
					rsolve(x + len_x[cur] - 2, y + len_y[cur] + 1, (cur + 1) % 5);
					break;
				}
			}
		}
		if (x + len_x[cur] < r && y + len_y[cur] < c) {
			bool valid = true;
			for (int j = 0; j < len[cur]; j++) {
				if (G[x + rdx[cur][j]][y + rdy[cur][j]]) {
					valid = false;
					break;
				}
			}
			if (valid) {
				ans.push_back(cur);
				rsolve(x + len_x[cur], y + len_y[cur] + 1, (cur + 1) % 5);
				break;
			}
		}
	}
}

int main() {
	for (int i = 0; i < 5; i++) {
		int s, n;
		cin >> r >> c >> s >> n;
		memset(G, 0, sizeof(G));
		if (s % c != 0) {
			for (int j = 0; j < n; j++) {
				int x;
				cin >> x;
				x--;
				G[x / c][x % c] = true;
			}

			s--;
			solve(s / c, s % c, 0);
		}
		else {
			for (int j = 0; j < n; j++) {
				int x;
				cin >> x;
				x--;
				x = r * c - x - 1;
				G[x / c][x % c] = true;
			}

			s--;
			s = r * c - s - 1;
			rsolve(s / c, s % c, 0);
		}
		for (auto x : ans) cout << (char)('A' + x);
		ans.clear();
		cout << endl;
	}
}