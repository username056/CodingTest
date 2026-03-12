import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static int G, P;
	static int[] gates;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		G = Integer.parseInt(bf.readLine());
		P = Integer.parseInt(bf.readLine());
		LinkedList<Integer> list = new LinkedList<Integer>();
		gates = new int[G];
		for (int i = 0; i < G; i++) {
			gates[i] = i;
		}

		for (int i = 0; i < P; i++) {
			int gi = Integer.parseInt(bf.readLine()) - 1;
			list.add(gi);
			boolean check = false;
			int idx = gi;
			if(func(idx)!= -1)
				cnt++;
			else
				break;
		}
		System.out.println(cnt);

	}

	static int func(int idx) {

		if (idx < 0)
			return -1;
		
		if (gates[idx] == idx) {
			gates[idx] = idx - 1;
			return idx;
		}
		return gates[idx] = func(gates[idx]);

	}
}