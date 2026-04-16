import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] ward;
	static int N, minDif;
	static int[] population;
	static boolean picked[];

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		ward = new ArrayList[N];
		for (int i = 0; i < N; i++)
			ward[i] = new ArrayList<>();
		population = new int[N];
		picked = new boolean[N];
		minDif = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				ward[i].add(a);
			}
		}

		makePart(0);
		System.out.println(minDif == Integer.MAX_VALUE ? -1 : minDif);
	}

	// 부분 집합
	static void makePart(int n) {
		if (n == N) {
			// 확인하기
			checkNum();
			return;
		}

		picked[n] = true;
		makePart(n + 1);
		picked[n] = false;
		makePart(n + 1);
	}

	static void checkNum() {
		// 나눠진 선거구가 이어지는지 확인하기
		// 각각 개수 맞는지 세야함
		int pNum = func(true);
		int unpNum = func(false);

		
		if (pNum == 0 || unpNum == 0 || pNum + unpNum != N)
			return;

		int pickedCnt = 0;
		int unPickedCnt = 0;

		for (int i = 0; i < N; i++) {
			if (picked[i])
				pickedCnt += population[i];
			else
				unPickedCnt += population[i];
		}
		minDif = Math.min(minDif, Math.abs(pickedCnt - unPickedCnt));

	}

	static int func(boolean in) {
		boolean use[] = new boolean[N];

		int start = -1;
		for (int i = 0; i < N; i++) {
			if (picked[i] == in) {
				start = i;
				break;
			}
		}
		if (start == -1)
			return 0;

		int cnt = 1;

		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		use[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < ward[cur].size(); i++) {
				int n = ward[cur].get(i);
				if(use[n] || picked[n] != in)
					continue;
				use[n] = true;
				q.add(n);
				cnt++;
			}
		}
		return cnt;
	}
}