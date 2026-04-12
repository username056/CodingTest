import java.io.*;
import java.util.*;

public class Main {
	// 03:11
	static int H, W, T;
	static int arr[][];
	static int airF = -1;
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1 && airF == -1)
					airF = i;
			}
		}

		simulation();

		int cnt = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] > 0)
					cnt += arr[i][j];
			}
		}
		System.out.println(cnt);

	}

	static void simulation() {

		for (int i = 0; i < T; i++) {
			diffusion();
			circulation();
		}
	}

	static void diffusion() {

		int[][] temp = new int[H][W];
		temp[airF][0] = -1;
		temp[airF + 1][0] = -1;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] > 0) {
					int dif = arr[i][j] / 5;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (ny < 0 || nx < 0 || nx > W - 1 || ny > H - 1 || arr[ny][nx] == -1)
							continue;
						temp[ny][nx] += dif;
						cnt++;
					}
					temp[i][j] += (arr[i][j] - dif * cnt);
					
				}
			}
		}
		

		arr = temp;

	}

	static void circulation() {

		int[][] temp = new int[H][W];

		// 위 ↓ ↑→←
		for (int i = airF - 1; i > 0; i--) {
			arr[i][0] = arr[i - 1][0];
		}

		// 아래 ↑
		for (int i = airF + 1; i < H - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}

		for (int i = 0; i < W - 1; i++) {
			arr[0][i] = arr[0][i + 1];
			arr[H - 1][i] = arr[H - 1][i + 1];
		}

		for (int i = 0; i < airF; i++) {
			arr[i][W - 1] = arr[i + 1][W - 1];
		}

		for (int i = H - 1; i >= airF + 1; i--) {
			arr[i][W - 1] = arr[i - 1][W - 1];
		}

		for (int i = W - 1; i > 1; i--) {
			arr[airF][i] = arr[airF][i - 1];
			arr[airF + 1][i] = arr[airF + 1][i - 1];
		}

		arr[airF][1] = 0;
		arr[airF + 1][1] = 0;
		arr[airF][0] = -1;
		arr[airF + 1][0] = -1;

	}
}