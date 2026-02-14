import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
public class Main {
	public static class Coord {
		int x;
		int y;
		Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };
	static int N, M, minValue = Integer.MAX_VALUE;
	static int[][] arr, dist;
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = bf.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		ArrayDeque<Coord> coords = new ArrayDeque<>();
		coords.add(new Coord(0, 0));

		while (!coords.isEmpty()) {
			Coord cur = coords.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1)
					continue;
				if (arr[ny][nx] == 0 || dist[ny][nx] != 0)
					continue;
				dist[ny][nx] = dist[cur.y][cur.x] + 1;
				coords.add(new Coord(ny, nx));
			}
		}
		System.out.println(dist[N-1][M-1]+1);
	}
}