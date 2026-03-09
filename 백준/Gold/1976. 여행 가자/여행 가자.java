import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int head[];

	static void initSets() {
		head = new int[N];
		for (int i = 0; i < N; i++) {
			head[i] = i;
		}
	}

	static int findHead(int a) {
		if (a == head[a])
			return a;
		return head[a] = findHead(head[a]);
	}

	static void unionSet(int a, int b) {
		int rootA = findHead(a);
		int rootB = findHead(b);
		if (rootA == rootB)
			return;
		head[rootB] = rootA;
		return;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());

		initSets();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 1)
					unionSet(i, j);
			}
		}

		StringTokenizer st = new StringTokenizer(bf.readLine());

		int idx = -1;
		boolean check = true;
		for (int i = 0; i < M; i++) {
			int cur = Integer.parseInt(st.nextToken())-1;
			int curIdx = findHead(cur);
			if (idx == -1)
				idx = curIdx;
			else {
				if (idx != curIdx) {
					check = false;
					break;
				}
			}

		}
		if(check)
			System.out.println("YES");
		else
			System.out.println("NO");

	}

}