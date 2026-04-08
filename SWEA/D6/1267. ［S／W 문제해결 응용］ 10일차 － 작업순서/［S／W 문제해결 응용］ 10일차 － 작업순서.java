import java.io.*;
import java.util.*;

public class Solution {
	static LinkedList<Integer>[] direct;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {

			int V, E;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			direct = new LinkedList[V];
			for(int i =0 ;i < V; i++)
				direct[i] = new LinkedList<Integer>();
			int[] in = new int[V];

			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				direct[a].add(b);
				in[b]++;

			}

			ArrayDeque<Integer> q = new ArrayDeque<Integer>();

			for (int i = 0; i < V; i++) {
				if (in[i] == 0) {
					q.add(i);
				}
			}

			System.out.print("#" + tc + " ");

			ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
			while (!q.isEmpty()) {

				int cur = q.poll();
				System.out.print(cur + 1 + " ");
                
				for (int i = 0; i < direct[cur].size(); i++) {
					int a = direct[cur].get(i);
					in[a]--;
					if (in[a] == 0)
						q.add(a);
				}
			}
			System.out.println();

		}
	}
}