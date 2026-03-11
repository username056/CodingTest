import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static class Node implements Comparable<Node> {
		int head, weight;

		public Node(int head, int weight) {
			super();
			this.head = head;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int makeWellV[], minValue[];
	static boolean check[];
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		makeWellV = new int[N];
		minValue = new int[N];
		Arrays.fill(minValue, Integer.MAX_VALUE);

		int minIdx = 0;
		check = new boolean[N];
		for (int i = 0; i < N; i++) { // 우물 만들때 드는 비용 받기
			makeWellV[i] = Integer.parseInt(bf.readLine());
			minIdx = makeWellV[minIdx] < makeWellV[i] ? minIdx : i;
		}

		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (i == j)
					list[i].add(new Node(j, makeWellV[j]));
				list[i].add(new Node(j, a));
			}
		}

		int cnt = 0;
		minValue[minIdx] = 0;
		int total = 0;
		
		for(int i =0; i < N; i++)
			pq.add(new Node(i, makeWellV[i]));

		while (!pq.isEmpty()) {
			if (cnt == N)
				break;

			Node cur = pq.poll();
			
			if (check[cur.head])
				continue;
			total += cur.weight;
			cnt++;

			check[cur.head] = true;
			for (Node n : list[cur.head]) {
				if (check[n.head])
					continue;
				if (minValue[n.head] > n.weight) {
					minValue[n.head] = n.weight;
					pq.add(new Node(n.head, n.weight));
				}
			}

		}
		System.out.println(total);
	}
}