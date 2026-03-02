import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int arr[], pick[];
	static StringBuilder sb = new StringBuilder();
	static boolean isPicked[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		pick = new int[M];
		isPicked = new boolean[N];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		func(0);

		System.out.println(sb);

	}

	static void func(int cnt) {
		if (cnt == M) {
			for(int i =0 ; i < M; i++) 
				sb.append(pick[i]).append(" ");
			sb.append("\n");
			return;
		}
		int past = -1;	
		for (int i = 0; i < N; i++) {
			if (isPicked[i])
				continue;
			if (past == arr[i])
				continue;
			
			isPicked[i] = true;
			
			pick[cnt] = arr[i];
			past = arr[i];
			
			func(cnt + 1);
			isPicked[i] = false;
		}

	}
}