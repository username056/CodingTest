import java.io.*;
import java.util.*;

public class Main {
	static int K, W, H;
	static int map[][];
	static int arr[][][];
	static int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, -1, 0, 1 }, hx[] = { -2, -1, 1, 2, 2, 1, -1, -2 },
			hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(bf.readLine());

		StringTokenizer st = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		arr = new int[K + 1][H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = func();

		if (result == -1)
			System.out.println("-1");
		else
			System.out.println(result);

	}

	static int func() {

		ArrayDeque<int[]> q = new ArrayDeque<>();

		q.add(new int[] { 0, 0, 0, 1 }); // y, x, k, 이동 횟수
		arr[0][0][0] = 1;

		while (!q.isEmpty()) {

			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			int ck = cur[2];
			int cm = cur[3];

			if(cy == H-1 && cx == W-1)
				return cm-1;

			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || nx < 0 || ny > H - 1 || nx > W - 1)
					continue;
				if (map[ny][nx] == 1 || arr[ck][ny][nx] > 0) {
					continue; 
				}

				q.add(new int[] { ny, nx, ck, cm + 1 });
				arr[ck][ny][nx] = cm+1;

			}
			
			if (ck < K) {
				for (int j = 0; j < 8; j++) {
					int ky = cy + hy[j];
					int kx = cx + hx[j];
					if (ky < 0 || ky > H - 1 || kx < 0 || kx > W - 1)
						continue;
					if (map[ky][kx] == 1 || arr[ck + 1][ky][kx] > 0)
						continue;
					q.add(new int[] { ky, kx, ck + 1, cm + 1 });
					arr[ck+1][ky][kx] = cm+1;

				}
			}

		}
		return -1;

	}
}