import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static class Node implements Comparable<Node> {
		int x, y, value;

		Node(int y, int x, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int compareTo(Main.Node o) {
			return value - o.value;
		}
	}

	static int N;
	static int arr[][], dist[][];
	static boolean check[][];
	static int dx[] = { 0, 0, -1, 1 }, dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int curNum = 0;
		// 시작은 (0,0) 도착은(N-1,N-1)
		while (N != 0) {
			curNum++;
			check = new boolean[N][N];
			arr = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Dijkstra();

			System.out.println("Problem " + curNum + ": " + dist[N - 1][N - 1]);

			N = Integer.parseInt(bf.readLine());
		}

	}

	public static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, arr[0][0]));
		dist[0][0] = arr[0][0];

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.value != dist[cur.y][cur.x]) 
				continue;

			if (cur.x == N - 1 && cur.y == N - 1)
				return;
			
			check[cur.y][cur.x] = true;

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (dist[ny][nx] > dist[cur.y][cur.x] + arr[ny][nx]) {
					dist[ny][nx] = dist[cur.y][cur.x] + arr[ny][nx];

					pq.add(new Node(ny, nx, dist[ny][nx]));
				}
			}

		}

	}
}