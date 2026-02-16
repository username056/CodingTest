import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, count;
    static int[] ban;       // ban[i] i번 재료와 같이 쓸 수 없는 재료들의 비트집합
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            ban = new int[N];
            count = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                ban[a] |= (1 << b);
                ban[b] |= (1 << a);
            }

            func(0, 0);

            System.out.println("#" + tc + " " + count);
        }
    }

    // idx: 현재 결정할 재료 인덱스
    // mask: 지금까지 선택된 재료들의 비트집합
    static void func(int idx, int mask) {
        if (idx == N) {
            count++;
            return;
        }

        // idx 재료 선택X
        func(idx + 1, mask);

        //idx 재료 선택 (이미 선택된 재료와 충돌하면 넘어가기)
        if ((mask & ban[idx]) == 0) {
            func(idx + 1, mask | (1 << idx));
        }
    }
}