import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, K, A, B;
	static int ai[], bj[];
	static Customer[] customers;

	static class Customer {
		int num, arriveTime, aNum, bNum, endTime;

		Customer(int num, int arriveTime) {
			this.num = num;
			this.arriveTime = arriveTime;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			ai = new int[N + 1];
			bj = new int[M + 1];
			customers = new Customer[K + 1];

			st = new StringTokenizer(bf.readLine());
			for (int i = 1; i <= N; i++)
				ai[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(bf.readLine());
			for (int i = 1; i <= M; i++)
				bj[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(bf.readLine());
			for (int i = 1; i <= K; i++) {
				customers[i] = new Customer(i, Integer.parseInt(st.nextToken()));
			}

			System.out.println("#" + tc + " " + func());
		}
	}

	static int func() {
		Queue<Customer> waitA = new LinkedList<>(); // 접수 대기열
		// 1. 접수 종료시간 빠름 2. 창구번호 작음
		PriorityQueue<Customer> waitB = new PriorityQueue<>((c1, c2) -> {
			if (c1.endTime == c2.endTime)
				return Integer.compare(c1.aNum, c2.aNum);
			return Integer.compare(c1.endTime, c2.endTime);
		});

		Customer[] deskA = new Customer[N + 1];
		int[] timerA = new int[N + 1];
		Customer[] deskB = new Customer[M + 1];
		int[] timerB = new int[M + 1];

		int time = 0;
		int finishedCount = 0;
		int customerIdx = 1;

		while (finishedCount < K) {

			while (customerIdx <= K && customers[customerIdx].arriveTime <= time) {
				waitA.add(customers[customerIdx++]);
			}

			// 접수 창구 처리
			for (int i = 1; i <= N; i++) {
				if (deskA[i] != null) {
					timerA[i]--;
					if (timerA[i] == 0) {
						deskA[i].endTime = time; // 접수 완료 시간 기록하기
						waitB.add(deskA[i]); // 정비 대기열로 이동
						deskA[i] = null;
					}
				}
			}
			for (int i = 1; i <= N; i++) {
				if (deskA[i] == null && !waitA.isEmpty()) {
					Customer c = waitA.poll();
					c.aNum = i; // 이용한 접수 창구 기록하기
					deskA[i] = c;
					timerA[i] = ai[i];
				}
			}

			// 정비 창구 처리
			for (int i = 1; i <= M; i++) {
				if (deskB[i] != null) {
					timerB[i]--;
					if (timerB[i] == 0) {
						deskB[i] = null;
						finishedCount++; // 정비 끝
					}
				}
			}
			for (int i = 1; i <= M; i++) {
				if (deskB[i] == null && !waitB.isEmpty()) {
					Customer c = waitB.poll();
					c.bNum = i; // 이용한 정비 창구 기록
					deskB[i] = c;
					timerB[i] = bj[i];
				}
			}

			time++;
		}

		int ans = 0;
		for (int i = 1; i <= K; i++) {
			if (customers[i].aNum == A && customers[i].bNum == B) {
				ans += customers[i].num;
			}
		}

		return (ans == 0) ? -1 : ans;
	}
}