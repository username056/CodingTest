import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, result;
	static int playPos[], godPos[];
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };
	static ArrayList<int[]> devPos;

	static int arr[][];

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			result = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N][M];
			playPos = new int[2];
			godPos = new int[2];

			for (int i = 0; i < N; i++) { // 맵 처리
				String str = bf.readLine();
				for (int j = 0; j < M; j++) {
					char c = str.charAt(j);
					if (c == '.')
						arr[i][j] = 0;
					else if (c == 'S') {
						arr[i][j] = 0;
						playPos[0] = i;
						playPos[1] = j;
					} else if (c == 'D') {
						arr[i][j] = 3000;
						godPos[0] = i;
						godPos[1] = j;
					} else if (c == 'X')
						arr[i][j] = 4000;
					else if (c == '*')
						arr[i][j] = -1;
				}
			}

			simulation();

			System.out.print("#" + tc + " ");
			if (result == 0)
				System.out.println("GAME OVER");
			else
				System.out.println(result - 1);
		}

	}

	static void simulation() {

		int[][] fireMap = new int[N][M];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		fireMap = fireSimul();

		int y = playPos[0];
		int x = playPos[1];
		q.add(new int[] { y, x, 1 });
		arr[y][x] = 1;


		while (!q.isEmpty()) {
			// 사람 움직이기

			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];

			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || nx < 0 || ny > N - 1 || nx > M - 1 ||( arr[ny][nx] > 0 && arr[ny][nx] < 3000))
					continue;
				if (arr[ny][nx] == 3000) {
					result = cur[2] + 1;
					return;
				} else if (arr[ny][nx] == 4000)
					continue;
				else if (fireMap[ny][nx] > 0 && fireMap[ny][nx] <= cur[2] + 1)
					continue;
				arr[ny][nx] = cur[2] + 1;
				q.add(new int[] { ny, nx, cur[2] + 1 });
			}

		}

	}

	static int[][] fireSimul() {

		int fireMap[][] = new int[N][M];
		ArrayDeque<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == -1) {
					fireMap[i][j] = 1;
					q.add(new int[] { i, j, 1 });
				}
			}
		}

		while (!q.isEmpty()) {

			int cur[] = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if (ny < 0 || nx < 0 || ny > N - 1 || nx > M - 1 || arr[ny][nx] >= 3000)
					continue;
				if (fireMap[ny][nx] > 0)
					continue;
				fireMap[ny][nx] = cur[2] + 1;
				q.add(new int[] { ny, nx, cur[2] + 1 });
			}

		}
		return fireMap;
	}
}