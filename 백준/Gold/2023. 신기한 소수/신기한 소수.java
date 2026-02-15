import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static void func(int val, int cnt) {
		if (cnt == N) {
			System.out.println(val);
			return;
		} 
		
		int cand[] = (cnt== 0) ? 
				new int[]{2, 3, 5, 7} : new int[]{1, 3, 7, 9};
		
		for(int digit : cand) {
			int next = val * 10 + digit;
			if(isPrime(next)) func(next, cnt+1);
		}
		
	}

	private static boolean isPrime(int a) {
		if(a<2) return false;
		
		for(int i = 2; i*i <= a;i++) 
			if(a % i == 0)
				return false;
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
	
		func(0, 0);

	}

}