import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i) {
        	String query = sc.next();
        	String origin = sc.next();
        	System.out.println(new Main().kmp(origin, query));
        }

	}
	
	public int kmp(String origin, String query) {
		int count = 0, n = origin.length(), m = query.length();
		if (m > n) return 0;
		int[] next = getNext(query);
		for (int i = 0, j = 0; i < n; ++i) {
			if (query.charAt(j) == origin.charAt(i)) {
				if (j == m-1) {
					j = next[j];
					count++;
				}
				else j++;
			}
			else if (j > 0){
				j = next[j-1];
				--i;
			}
		}
		return count;
	}
	
	public int[] getNext(String query) {
		int n = query.length();
		int[] next = new int[n];
		next[0] = 0;
		for (int i = 1; i < n; ++i) {
			int j = i-1;
			while (j > 0 && query.charAt(next[j]) != query.charAt(i)) {
				j = next[j-1];
			}
			if(query.charAt(next[j]) == query.charAt(i))
				next[i] = next[j]+1;
			else next[i] = 0;
		}
		return next;
	}
	
}
