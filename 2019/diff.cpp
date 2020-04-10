#include <bits/stdc++.h>
using namespace std;

void scan(vector<string> &S) {
	string input, tmp;
	getline(cin, input);
	stringstream ss(input);
	while (ss >> tmp) S.push_back(tmp);
}

string algorithm1(vector<string> A, vector<string> B) {
	string ret;
	for (auto &a : A) {
		for (auto &b : B) {
			auto pos = b.find(a);
			if (pos != string::npos) {
				b.erase(pos, a.length());
				ret += a;
				break;
			}
		}
	}
	return ret;
}

string algorithm2(string A, string B) {
	string ret;
	for (auto c : A) {
		auto pos = B.find(c);
		if (pos != string::npos) {
			B.erase(0, pos + 1);
			ret += c;
		}
	}
	return ret;
}

int main() {
	string output[5];
	for (int i = 0; i < 5; i++) {
		vector<string> A, B;
		scan(A);
		scan(B);
		string C = algorithm1(A, B);
		string D = algorithm1(B, A);
		output[i] = algorithm2(C, D);
		if (output[i] == "") output[i] = "NONE";
		cout << output[i] << endl;
	}
}