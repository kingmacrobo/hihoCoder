#include <iostream>
#include <string>
using namespace std;

struct TrieNode {
	int count;
	char ch;
	TrieNode ** childNodes;
	TrieNode(char c) : count(0), ch(c) {
		childNodes = new TrieNode * [26];
		for (int i = 0; i < 26; ++i) {
			childNodes[i] = NULL;
		}
	}
	~TrieNode() {
		delete[] childNodes;
	}
};

void buildTrie(TrieNode * root, string & str, int index) {
	if (str[index] == 0) {
		return;
	}
	if (root->childNodes[str[index] - 'a'] == NULL) {
		root->childNodes[str[index] - 'a'] = new TrieNode(str[index]);
	}
	root = root->childNodes[str[index] - 'a'];
	root->count += 1;
	buildTrie(root, str, index + 1);
}

int numIntrie(TrieNode * root, string & str, int index) {
	if (root == NULL ) {
		return 0;
	}
	else if (str[index + 1] == 0) {
		return root->count;
	}
	return numIntrie(root->childNodes[str[index + 1] - 'a'], str, index + 1);
}

int main() {
	int M;
	string str;
	TrieNode root('#');
	cin >> M;
	while (M--) {
		cin >> str;
		buildTrie(&root, str, 0);
	}
	cin >> M;
	while (M--){
		cin >> str;
		if (str.length() == 0) {
			cout << 0;
		} else {
			cout << numIntrie(root.childNodes[str[0] - 'a'], str, 0) << endl;
		}
	}
	return 0;
}
