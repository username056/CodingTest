import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, V, cnt = 0;
	static LinkedList<Integer>[] list;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 1번에 연결된 애들만 찾기
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		V = Integer.parseInt(bf.readLine());

		// N컴퓨터의 수
		list = new LinkedList[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<Integer>();
		}
		// V 간선의 수
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;

			list[a].add(b);
			list[b].add(a);
			// 무향 그래프니까
		}

		search(0);
		// 링크드 리스트로 연결

		System.out.println(cnt);

	}

	static void search(int start) {
		// 지금 들어온 곳은 들렀다고 방문 체크해주기
		check[start] = true;

		for (int nextV : list[start]) {
			if (!check[nextV]) {
				search(nextV);
				cnt++;
			}
		}

	}
}