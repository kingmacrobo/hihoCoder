// 建立Trie字典树，在建立的过程中进行统计，每个节点用wordCount来表示过这个节点的单词数目。
// 在递归建立的过程中，每过一个节点，就把这个节点的wordCount加1，从而实现统计。
// 最后查询到这个节点，输出wordCount，即为以此为前缀的单词数目。

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TrieNode root = new TrieNode();
        for (int i = 0; i < n; ++i)
        	root.add(sc.next(),0);
        int m = sc.nextInt();
        for (int i = 0; i < m; ++i) {
        	String query = sc.next();
        	System.out.println(root.search(query,0));
        }
	}
	
	static class TrieNode {
		int wordCount;
		HashMap<Character, TrieNode> table;
		public TrieNode() {
			wordCount = 0;
			table = new HashMap<Character, TrieNode>();
		}
		public void add(String word, int index) {
			if (index == word.length()) {
				wordCount += 1;
				return;
			}
			char c = word.charAt(index);
			if (!table.containsKey(c)) {
				table.put(c, new TrieNode());
			}
			table.get(c).add(word,index+1);
			wordCount += 1;
		}
		public int search(String word, int index) {
			if (index == word.length()) {
				return wordCount;
			}
			char c = word.charAt(index);
			if (!table.containsKey(c)) return 0;
			return table.get(c).search(word, index+1);
		}
		
	}
}
