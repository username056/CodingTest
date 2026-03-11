import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int H, W, result;
	static int[] dy = { -1, 0, 1 }, dx = { 1, 1, 1 };
	static int map[][], arr[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		arr = new int[H][W];
		result = 0;

		for (int i = 0; i < H; i++) { // 맵 입력받기
			String str = bf.readLine();
			for (int j = 0; j < W; j++) {
				if (str.charAt(j) == 'x')
					map[i][j] = 1;
			}
		}

		for (int i = 0; i < H; i++)
			if (func(i, 0))
				result++;

		System.out.println(result);

	}

	static boolean func(int y, int x) {
		map[y][x] = 1;
		if (x == W - 1)
			return true;

		for (int k = 0; k < 3; k++) {
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny < 0 || nx > W - 1 || ny > H - 1 || map[ny][nx] == 1) {
				continue;
			}
			if (func(ny, nx))
				return true;
		}
		return false;
	}
}