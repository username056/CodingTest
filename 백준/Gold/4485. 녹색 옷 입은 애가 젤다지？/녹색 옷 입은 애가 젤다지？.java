import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, arr[][];
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int caseNum = 1;
		N = Integer.parseInt(bf.readLine());
		while (N != 0) {
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("Problem " + caseNum + ": " + func());

			N = Integer.parseInt(bf.readLine());
			caseNum++;
		}

	}

	static int func() {
		int temp[][] = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(temp[i], Integer.MAX_VALUE);
		temp[0][0] = arr[0][0];

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

		pq.add(new int[] { 0, 0, arr[0][0] });
		while (!pq.isEmpty()) {
			int cur[] = pq.poll();
			if (cur[0] == N - 1 && cur[1] == N - 1)
				return cur[2];

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if (ny < 0 || nx < 0 || ny > N - 1 || nx > N - 1)
					continue;
				if (temp[ny][nx] > cur[2] + arr[ny][nx]) {
					temp[ny][nx] = cur[2] + arr[ny][nx];
					pq.add(new int[] { ny, nx, cur[2] + arr[ny][nx] });
				}
			}
		}

		return -1;
	}

}