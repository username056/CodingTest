import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int W, H, K, total;
	static int map[][], arr[][];
	static int dy[] = { -1, 1, 0, 0 }, dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(bf.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			total = 0;
			map = new int[H][W];
			arr = new int[H][W];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[b][a] = 1;
			}

			System.out.println(func());
		}

	}

	static int func() {

		ArrayDeque<int[]> q = new ArrayDeque<>();
		int cnt = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] == 0 && map[i][j] == 1) {
					cnt++;
					q.add(new int[] { i, j });
					arr[i][j] = cnt;
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						for (int k = 0; k < 4; k++) {
							int ny = cur[0] + dy[k];
							int nx = cur[1] + dx[k];
							if (ny < 0 || nx < 0 || ny > H - 1 || nx > W - 1)
								continue;
							if (arr[ny][nx] == 0 && map[ny][nx] == 1) {
								q.add(new int[] { ny, nx });
								arr[ny][nx] = cnt;
							}
						}
					}

				}
			}
		}

		return cnt;
	}
}