import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	public static class Node implements Comparable<Node> {
		int x, y, val;

		Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}

	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };
	static int N, minValue;
	static int[][] arr, dist;
	static boolean isVisited[][];

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(bf.readLine());
			arr = new int[N][N];
			minValue = Integer.MAX_VALUE;
			isVisited = new boolean[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++)
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			for (int i = 0; i < N; i++) {
				String str = bf.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			Dijkstra();

			System.out.println("#" + tc + " " + dist[N-1][N-1]);
		}

	}

	public static void Dijkstra() {

		dist[0][0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));

		while (!pq.isEmpty()) {

			Node n = pq.poll();
			if (isVisited[n.y][n.x] == true)
				continue;
			isVisited[n.y][n.x] = true;

			if(n.y == N-1 && n.x == N-1) return;
			
			for (int i = 0; i < 4; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (dist[ny][nx] > dist[n.y][n.x] + arr[ny][nx]) {
					dist[ny][nx] = dist[n.y][n.x] + arr[ny][nx];
					pq.offer(new Node(nx, ny, dist[ny][nx]));
				}
			}
		}
	}
}