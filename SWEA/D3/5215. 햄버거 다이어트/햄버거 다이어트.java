import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합
public class Solution {
	static class Ing {
		int sc, cal;

		Ing(int sc, int cal) {
			this.sc = sc;
			this.cal = cal;
		}
	}

	static int N, L, highestSc;
	static Ing ings[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= testCase; tc++) {

			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ings = new Ing[N];
			highestSc = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				int sc = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				ings[i] = new Ing(sc, cal);
			}

			for (int i = 1; i <= N; i++) {
				comb(0, 0, i, 0, 0);
			}

			System.out.println("#" + tc + " " + highestSc);
		}
	}

	static void comb(int start, int picked, int a, int curSc, int curCal) {

		if (curCal > L)
			return;
		if (picked == a) {
			highestSc = Math.max(highestSc, curSc);
			return;
		}
		if (start == N)
			return;

		for (int i = start; i < N; i++) {
			comb(i + 1, picked + 1, a, curSc + ings[i].sc, curCal + ings[i].cal);
		}

	}
}