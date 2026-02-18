import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int picked[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		picked = new int[M];
		func(0,0);
		
	}

	static void func(int start, int cnt) {
		
		if(cnt == M){
			for(int i =0; i <M; i++) {
				System.out.print(picked[i]+" ");
			}System.out.println();
			return;
		}
	
		
		for(int i = start; i<N; i++) {
			picked[cnt] = i+1;
			func(i+1, cnt+1);
		}
	}
	
}