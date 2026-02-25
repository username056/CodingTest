import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] adj;
	static int[] population;
	static int N, startIdx, total = 0, result = Integer.MAX_VALUE;
	static boolean pick[];
	static ArrayDeque<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());

		population = new int[N];
		adj = new ArrayList[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			total += population[i];
			adj[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int nearN = Integer.parseInt(st.nextToken());

			for (int j = 0; j < nearN; j++) {
				adj[i].add(Integer.parseInt(st.nextToken()) - 1);
			}

		}

		pick = new boolean[N];
		dfs(0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	static void dfs(int start) {
		if (start == N) {
			// 로직
			check();
			return;
		}

		pick[start] = true;
		dfs(start + 1);

		pick[start] = false;
		dfs(start + 1);
	}

	static void check() {

		int aCnt = 0, sum = 0;

		for (int i = 0; i < N; i++) {
			if (pick[i]) {
				aCnt++;
				sum += population[i];
			}
		}

		if (!isConnected(true, aCnt))
			return;
		if (!isConnected(false, N - aCnt))
			return;

		result = Math.min(result, Math.abs(sum - (total - sum)));
	}

	// 검사할 그룹 값. true면 pick이 true인 것들, false면 pick이 false인 것들 연결성 검사
	// 해당 그룹에 속한 정점 개수 넘겨주기
	static boolean isConnected(boolean target, int needCnt) {
		int start = -1;
		// 그룹 내부에 있는 정점으로 시작점 잡기
		for (int i = 0; i < N; i++) {
			if (pick[i] == target) {
				start = i;
				break;
			}
		}
		if (start == -1)
			return false;

		boolean[] check = new boolean[N]; // 방문 체크
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		check[start] = true;
		int cnt = 1;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt : adj[cur]) {
				if (pick[nxt] != target)
					continue; // 같은 그룹에 속한 정점으로만 이동
				if (check[nxt])
					continue;
				check[nxt] = true;
				queue.add(nxt);
				cnt++;
			}
		}
		return cnt == needCnt;

	}

}