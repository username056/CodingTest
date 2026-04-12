import java.io.*;
import java.util.*;

public class Solution {
	static int N, Q;
	static int arr[][];
	static int startPos[], endPos[], dx[] = { 0, -1, 0, 1 }, dy[] = { -1, 0, 1, 0 }; 

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= testCase; tc++) {

			N = Integer.parseInt(bf.readLine());
			arr = new int[N][N];
			startPos = new int[2];
			endPos = new int[2];

			for (int i = 0; i < N; i++) {
				String str = bf.readLine();
				for (int j = 0; j < N; j++) {
					char a = str.charAt(j);
					if (a == 'G')
						arr[i][j] = 0;
					else if (a == 'T')
						arr[i][j] = -1;
					else if (a == 'X') {
						arr[i][j] = 0;
						startPos[0] = i;
						startPos[1] = j;
					} else if (a == 'Y') {
						arr[i][j] = 0;
						endPos[0] = i;
						endPos[1] = j;
					}

				}
			}

			int cur[] = new int[2];
			
			Q = Integer.parseInt(bf.readLine());
			System.out.print("#"+tc);
			for (int i = 0; i < Q; i++) {
				cur[0] = startPos[0];
				cur[1] = startPos[1];
				
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int len = Integer.parseInt(st.nextToken());

				String order = st.nextToken();
				int dir = 0;
				for (int j = 0; j < len; j++) {
					char o = order.charAt(j);
					if(o == 'A') {
						int ny = cur[0] + dy[dir];
						int nx = cur[1] + dx[dir];
						if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1)
							continue;
						if(arr[ny][nx] == -1)
							continue;
						cur[0] = ny;
						cur[1] = nx;
						
					}
					else if( o =='L')
						dir = (dir+1)%4;
					else if(o == 'R')
						dir = (dir+3)%4;
				}
				
				if(cur[0] == endPos[0] && cur[1] == endPos[1])
					System.out.print(" 1");
				else 
					System.out.print(" 0");

			}

			System.out.println();
		}
	}

}