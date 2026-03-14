import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int W, H, N, tot = 0, sNum = 0, fNum = 0;
	static int[] dh = { -1, 1, 0, 0, 0, 0 }, dx = { 0, 0, -1, 1, 0, 0 }, dy = { 0, 0, 0, 0, -1, 1 };
	static ArrayDeque<int[]> q;
	static int[][][] box;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][H][W];
		q = new ArrayDeque<>();

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < W; j++) {
					box[k][i][j] = Integer.parseInt(st.nextToken());
					if (box[k][i][j] == -1)
						continue;
					sNum++;
					if (box[k][i][j] == 1) {
						q.offer(new int[] { k, i, j });
						fNum++;
					}
				}
			}
		}

		if (sNum != fNum) {
			bfs();
			tot = sNum==fNum ? tot-1 : -1;
		}

		System.out.println(tot);

	}

	static void bfs() {

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int k = 0; k < 6; k++) {
				int nh = cur[0] + dh[k];
				int ny = cur[1] + dy[k];
				int nx = cur[2] + dx[k];
				if (nh < 0 || nh > N - 1 || ny < 0 || ny > H - 1 || nx < 0 || nx > W - 1)
					continue;
				if (box[nh][ny][nx] == 0) {
					box[nh][ny][nx] = box[cur[0]][cur[1]][cur[2]] + 1;
					tot = Math.max(box[nh][ny][nx], tot);
					fNum++;
					q.offer(new int[] { nh, ny, nx });
				}
			}
		}

	}
}