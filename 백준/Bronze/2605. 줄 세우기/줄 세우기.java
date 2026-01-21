import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		List<Integer> list = new ArrayList<>();
		for(int i =0; i <n; i++) {
			int check = sc.nextInt();
			list.add(i - check, i+1);
		}
			
		for(int i =0; i < n ; i++)
			System.out.print(list.get(i)+ " ");
		
	}
}
