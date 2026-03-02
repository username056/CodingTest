import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, cnt, min = Integer.MAX_VALUE;
	static int map[][], dx[] = { 0, 0, -1, 1 }, dy[] = { -1, 1, 0, 0 };
	static int nMap[][], check[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		checkLand();
		checkShortestDist();
		System.out.println(min);

	}

	static void checkLand() {

		nMap = new int[N][N];
		cnt = 0;
		ArrayDeque<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && nMap[i][j] == 0) {
					cnt++;
					nMap[i][j] = cnt;
					queue.add(new int[] { i, j });

				}
				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					for (int k = 0; k < 4; k++) {
						int nx = cur[1] + dx[k];
						int ny = cur[0] + dy[k];
						if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
							continue;
						if (map[ny][nx] != 0 && nMap[ny][nx] == 0) {
							nMap[ny][nx] = cnt;
							queue.add(new int[] { ny, nx });
						}

					}
				}
			}
		}

	}

	static void checkShortestDist() {

		// check는 여기서 초기화
		for (int c = 1; c <= cnt; c++) {
			ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
			int temp[][] = new int[N][N];
			// cnt에 해당하는 걸 전부 큐에 넣기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (nMap[i][j] == c) {
						queue.add(new int[] { i, j });
						temp[i][j] = 1;
					}
				}
			}

			// bfs로 퍼져가면서 최소값 찾기
			while (!queue.isEmpty()) {
				int cur[] = queue.poll();
				for (int k = 0; k < 4; k++) {
					int ny = cur[0] + dy[k];
					int nx = cur[1] + dx[k];
					if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
						continue;
					if (nMap[ny][nx] == 0) {
						if (temp[ny][nx] != 0)
							continue;
						temp[ny][nx] = temp[cur[0]][cur[1]] + 1;
						queue.add(new int[] { ny, nx });
					} else if (nMap[ny][nx] != c) {
						// 다른 지역을 만났을 때 최소값 갱신
						min = Math.min(min, temp[cur[0]][cur[1]]-1);
					}
				}

			}
		}

	}

}