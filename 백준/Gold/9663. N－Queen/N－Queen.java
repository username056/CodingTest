import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, sum;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		sum = 0;
		arr = new int[N];
		func(0);
		System.out.println(sum);
	}

	static void func(int cnt) {
		if (cnt == N) {
			sum++;
			return;
		}
		
		for(int i =0; i < N; i++) {
			arr[cnt] = i;
			if (isPossible(cnt)) 
                func(cnt + 1);
		}
	}
	
	static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            if (arr[i] == arr[row]) 
                return false;
            
            if (Math.abs(row - i) == Math.abs(arr[row] - arr[i])) 
                return false;      
        }
        return true;
    }
}