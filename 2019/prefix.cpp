#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
using namespace std;

int main() {
	string input;
	while (getline(cin, input)) {
		stringstream ss(input);
		vector<string> vec;
		string tmp;
		while (ss >> tmp) vec.push_back(tmp);
		reverse(vec.begin(), vec.end());

		vector<int> S;
		for (auto str : vec) {
			if (str == "|") {
				int a = S.back();
				S.pop_back();
				S.push_back(abs(a));
			}
			else if (str == "+") {
				int a = S.back();
				S.pop_back();
				int b = S.back();
				S.pop_back();
				S.push_back(a + b);
			}
			else if (str == "-") {
				int a = S.back();
				S.pop_back();
				int b = S.back();
				S.pop_back();
				S.push_back(a - b);
			}
			else if (str == "*") {
				int a = S.back();
				S.pop_back();
				int b = S.back();
				S.pop_back();
				S.push_back(a * b);
			}
			else if (str == "@") {
				int a = S.back();
				S.pop_back();
				int b = S.back();
				S.pop_back();
				int c = S.back();
				S.pop_back();
				S.push_back(a > 0 ? b : c);
			}
			else if (str == ">") {
				int a = S.back();
				S.pop_back();
				int b = S.back();
				S.pop_back();
				int c = S.back();
				S.pop_back();
				S.push_back(max({ a, b, c }));
			}
			else S.push_back(stoi(str));
		}

		cout << S.back() << endl;
	}
}