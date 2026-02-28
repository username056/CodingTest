import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	// 1 43
	static int N, M, result = Integer.MAX_VALUE;
	static int arr[][], temp[][][];
	static int dx[] = { 0, 0, -1, 1 }, dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = bf.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		findPath();

		result = result == Integer.MAX_VALUE ? -1 : result+1;
		System.out.println(result);

	}

	static void findPath() {

		if(N == 1 && M == 1 && arr[N-1][M-1] == 0) {
			result = 0;
			return;
		}

		temp = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j][0] = arr[i][j];
			}
		}

		ArrayDeque<int[]> q = new ArrayDeque<>();
		// bfs를 돌리기
		// 상태로 관리하기
		q.add(new int[] { 0, 0, 0 });

		while (!q.isEmpty()) {
			// 0은 y, 1은 x
			int coo[] = q.poll();
			int ay = coo[0];
			int ax = coo[1];
			for (int i = 0; i < 4; i++) {
				int check = coo[2];
				int ny = ay + dy[i];
				int nx = ax + dx[i];
				if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1)
					continue;
				if (arr[ny][nx] != 0) {
					if (check != 0)
						continue;
					check = 1;
				}
				if (temp[ny][nx][check] != 0)
					continue;
				temp[ny][nx][check] = temp[coo[0]][coo[1]][coo[2]] + 1;
				if (ny == N - 1 && nx == M - 1) {
					result = result > temp[ny][nx][check] ? temp[ny][nx][check] : result;
					continue;
				}
				q.add(new int[] { ny, nx, check });
			}

		}
	}
}
