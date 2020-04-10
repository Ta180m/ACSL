import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] output = new int[5];
		for (int i = 0; i < 5; i++) {
			String[] input = sc.nextLine().split(" ");
			Collections.reverse(Arrays.asList(input));
			Vector<Integer> S = new Vector<>();
			for (String str : input) {
				if (str.equals("|")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					S.add(Math.abs(a));
				}
				else if (str.equals("+")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					int b = S.lastElement();
					S.remove(S.size() - 1);
					S.add(a + b);
				}
				else if (str.equals("-")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					int b = S.lastElement();
					S.remove(S.size() - 1);
					S.add(a - b);
				}
				else if (str.equals("*")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					int b = S.lastElement();
					S.remove(S.size() - 1);
					S.add(a * b);
				}
				else if (str.equals("@")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					int b = S.lastElement();
					S.remove(S.size() - 1);
					int c = S.lastElement();
					S.remove(S.size() - 1);
					S.add(a > 0 ? b : c);
				}
				else if (str.equals(">")) {
					int a = S.lastElement();
					S.remove(S.size() - 1);
					int b = S.lastElement();
					S.remove(S.size() - 1);
					int c = S.lastElement();
					S.remove(S.size() - 1);
					S.add(Math.max(Math.max(a, b), c));
				}
				else S.add(Integer.parseInt(str));
			}
			
			output[i] = S.lastElement();
			System.out.println(output[i]);
		}
	}
}