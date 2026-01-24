import java.util.Scanner;

public class Main {
	static class IJN {
		int i;
		int j;
		int n;

		IJN(int i, int j, int n) {
			this.i = i;
			this.j = j;
			this.n = n;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		IJN[] list = new IJN[26];

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				int idx = sc.nextInt();
				list[idx] = new IJN(i, j, idx);
			}
		}

		boolean[][] bingo = new boolean[5][5];
		int[] rCnt = new int[5];
		int[] cCnt = new int[5];
		int[] crossCnt = new int[2];
		int bCnt = 0;
		int cnt = 0;
		int ans = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cnt++;
				int idx = sc.nextInt();

				bingo[list[idx].i][list[idx].j] = true;

				rCnt[list[idx].i]++;
				cCnt[list[idx].j]++;
				
				if (list[idx].i == list[idx].j) 
					crossCnt[0]++;
				
				if (list[idx].i == 5 - list[idx].j - 1) 
					crossCnt[1]++;
				

				if (rCnt[list[idx].i] == 5) bCnt++;

				if (cCnt[list[idx].j] == 5) bCnt++;
				
				
				if (list[idx].i == list[idx].j && crossCnt[0] == 5) 
					bCnt++;
				
				if (list[idx].i + list[idx].j == 4 && crossCnt[1] == 5) 
					bCnt++;
				

				if (bCnt >= 3 && ans == 0) 
					ans = cnt;		
			}
		}
		
		System.out.println(ans);
		
		
	}
}
