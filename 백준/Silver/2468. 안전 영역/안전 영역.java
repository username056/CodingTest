import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N, maxArea = Integer.MIN_VALUE, maxHeight = 0;
	static int arr[][], dx[] = { 0, 0, -1, 1 }, dy[] = { -1, 1, 0, 0 };
	static boolean check[][];
	static ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 비가 내렸을 때 물에 잠기지 않은 안전한 영역이 최대 몇 개 만들어지는지 조사
		// 비의 양에 따라 일정한 높이 이하의 지점은 모두 물에 잠긴다
		// 꼭짓점은 안쳐줌
		// 비가 내리지 않은 것부터 0부터~ 최대 높이의 개수까지 세보기
		// max값 갱신하기
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}

		for (int i = 0; i < maxHeight + 1; i++) {
			check = new boolean[N][N];
			findMaxArea(i);
		}
		System.out.println(maxArea);
	}

	static void findMaxArea(int height) {

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j] && arr[i][j] > height) {
					queue.add(new int[] { i, j });
					check[i][j] = true;
					cnt++;
				}

				while (!queue.isEmpty()) {
					int cur[] = queue.poll();
					for (int k = 0; k < 4; k++) {
						int nx = cur[1] + dx[k];
						int ny = cur[0] + dy[k];
						if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
							continue;
						if (!check[ny][nx] && arr[ny][nx] > height) {
							queue.add(new int[] { ny, nx });
							check[ny][nx] = true;
						}
					}
				}
			}
		}
		maxArea = Math.max(cnt, maxArea);

	}

}