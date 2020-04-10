import java.util.*;
public class Main {
    static int x;
	public static void main(String[] args) {
	    Scanner SC = new Scanner(System.in);
		int TC = 5;
		for (int t = 0; t < TC; t++) {
		    String input = SC.nextLine();
		    x = Integer.parseInt(input.trim(), 16);
		    
		    //debug();
		    
		    ArrayList<String> ans = new ArrayList<String>();
		    
		    // Group 8 adjacent X's
		    int b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(3, i); b &= get(2, i); }
		    if (b == 1) {
		        ans.add("B");
		        for (int i = 0; i < 4; i++) { clear(3, i); clear(2, i); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(2, i); b &= get(1, i); }
		    if (b == 1) {
		        ans.add("D");
		        for (int i = 0; i < 4; i++) { clear(2, i); clear(1, i); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(1, i); b &= get(0, i); }
		    if (b == 1) {
		        ans.add("~B");
		        for (int i = 0; i < 4; i++) { clear(1, i); clear(0, i); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(i, 3); b &= get(i, 2); }
		    if (b == 1) {
		        ans.add("A");
		        for (int i = 0; i < 4; i++) { clear(i, 3); clear(i, 2); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(i, 2); b &= get(i, 1); }
		    if (b == 1) {
		        ans.add("C");
		        for (int i = 0; i < 4; i++) { clear(i, 2); clear(i, 1); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(i, 1); b &= get(i, 0); }
		    if (b == 1) {
		        ans.add("~A");
		        for (int i = 0; i < 4; i++) { clear(i, 1); clear(i, 0); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(3, i); b &= get(0, i); }
		    if (b == 1) {
		        ans.add("~D");
		        for (int i = 0; i < 4; i++) { clear(3, i); clear(0, i); }
		    }
		    b = 1;
		    for (int i = 0; i < 4; i++) { b &= get(i, 3); b &= get(i, 0); }
		    if (b == 1) {
		        ans.add("~C");
		        for (int i = 0; i < 4; i++) { clear(i, 3); clear(i, 0); }
		    }
		    
		    //debug();
		    
		    // Group 4 Adjacent X's
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        for (int j = 0; j < 4; j++) { b &= get(i, j); }
		        if (b == 1) {
		            if (i == 3) ans.add("B~D");
		            else if (i == 2) ans.add("BD");
		            else if (i == 1) ans.add("~BD");
		            else ans.add("~B~D");
		            for (int j = 0; j < 4; j++) { clear(i, j); }
		        }
		    }
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        for (int j = 0; j < 4; j++) { b &= get(j, i); }
		        if (b == 1) {
		            if (i == 3) ans.add("A~C");
		            else if (i == 2) ans.add("AC");
		            else if (i == 1) ans.add("~AC");
		            else ans.add("~A~C");
		            for (int j = 0; j < 4; j++) { clear(j, i); }
		        }
		    }
		    for (int i = 2; i >= 0; i--) {
		        for (int j = 2; j >= 0; j--) {
		            b = 1;
		            b = get(i, j) & get(i + 1, j) & get(i, j + 1) & get(i + 1, j + 1);
		            if (b == 1) {
		                StringBuilder str = new StringBuilder();
		                if (j == 0) str.append("~A");
		                else if (j == 2) str.append("A");
		                if (i == 0) str.append("~B");
		                else if (i == 2) str.append("B");
		                if (j == 1) str.append("C");
		                if (i == 1) str.append("D");
		                
		                ans.add(str.toString());
		                clear(i, j); clear(i + 1, j); clear(i, j + 1); clear(i + 1, j + 1);
		            }
		        }
		    }
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        b = get(i, 0) & get(i, 3) & get(i + 1, 0) & get(i + 1, 3);
		        if (b == 1) {
		            if (i == 0) ans.add("~B~C");
		            else if (i == 1) ans.add("~CD");
		            else ans.add("B~C");
		            clear(i, 0); clear(i, 3); clear(i + 1, 0); clear(i + 1, 3);
		        }
		    }
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        b = get(0, i) & get(3, i) & get(0, i + 1) & get(3, i + 1);
		        if (b == 1) {
		            if (i == 0) ans.add("~A~D");
		            else if (i == 1) ans.add("C~D");
		            else ans.add("A~D");
		            clear(0, i); clear(3, i); clear(0, i + 1); clear(3, i + 1);
		        }
		    }
		    b = 1;
		    b = get(0, 0) & get(0, 3) & get(3, 0) & get(3, 3);
		    if (b == 1) {
		        ans.add("~C~D");
		        clear(0, 0); clear(0, 3); clear(3, 0); clear(3, 3);
		    }
		    
		    //debug();
		    
		    // Group 2 adjacent X's
		    for (int i = 3; i >= 0; i--) {
		        for (int j = 2; j >= 0; j--) {
		            b = 1;
		            b = get(i, j) & get(i, j + 1);
		            if (b == 1) {
		                int arr[] = { 0, 0, 0, 0 };
		                if (i == 0) { arr[1] = 2; arr[3] = 2; }
		                else if (i == 1) { arr[1] = 2; arr[3] = 1; }
		                else if (i == 2) { arr[1] = 1; arr[3] = 1; }
		                else if (i == 3) { arr[1] = 1; arr[3] = 2; }
		                if (j == 0) arr[0] = 2;
		                else if (j == 1) arr[2] = 1;
		                else if (j == 2) arr[0] = 1;
		                StringBuilder str = new StringBuilder();
		                for (int k = 0; k < 4; k++) {
		                    if (arr[k] == 2) str.append("~");
		                    if (arr[k] > 0) str.append((char)('A' + k));
		                }
		                ans.add(str.toString());
		                clear(i, j); clear(i, j + 1);
		            }
		        }
		    }
		    for (int j = 3; j >= 0; j--) {
		        for (int i = 2; i >= 0; i--) {
		            b = 1;
		            b = get(i, j) & get(i + 1, j);
		            if (b == 1) {
		                int arr[] = { 0, 0, 0, 0 };
		                if (i == 0) arr[1] = 2;
		                else if (i == 1) arr[3] = 1;
		                else if (i == 2) arr[1] = 1;
		                if (j == 0) { arr[0] = 2; arr[2] = 2; }
		                else if (j == 1) { arr[0] = 2; arr[2] = 1; }
		                else if (j == 2) { arr[0] = 1; arr[2] = 1; }
		                else if (j == 3) { arr[0] = 1; arr[2] = 2; }
		                StringBuilder str = new StringBuilder();
		                for (int k = 0; k < 4; k++) {
		                    if (arr[k] == 2) str.append("~");
		                    if (arr[k] > 0) str.append((char)('A' + k));
		                }
		                ans.add(str.toString());
		                clear(i + 1, j); clear(i, j);
		            }
		        }
		    }
		    
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        b = get(i, 0) & get(i, 3);
		        if (b == 1) {
		            if (i == 3) ans.add("B~C~D");
    		        else if (i == 2) ans.add("B~CD");
    		        else if (i == 1) ans.add("~B~CD");
    		        else ans.add("~B~C~D");
    		        clear(i, 0); clear(i, 3);
		        }
		    }
		    for (int i = 3; i >= 0; i--) {
		        b = 1;
		        b = get(0, i) & get(3, i);
		        if (b == 1) {
    		        if (i == 3) ans.add("A~C~D");
    		        else if (i == 2) ans.add("AC~D");
    		        else if (i == 1) ans.add("~AC~D");
    		        else ans.add("~A~C~D");
    		        clear(0, i); clear(3, i);
		        }
		    }
		    
		    // Group single X's
		    for (int i = 3; i >= 0; i--) {
		        for (int j = 3; j >= 0; j--) {
		            b = 1;
		            b = get(i, j);
		            if (b == 1) {
		                int arr[] = { 0, 0, 0, 0 };
		                if (i == 0) { arr[1] = 2; arr[3] = 2; }
		                else if (i == 1) { arr[1] = 2; arr[3] = 1; }
		                else if (i == 2) { arr[1] = 1; arr[3] = 1; }
		                else if (i == 3) { arr[1] = 1; arr[3] = 2; }
		                if (j == 0) { arr[0] = 2; arr[2] = 2; }
		                else if (j == 1) { arr[0] = 2; arr[2] = 1; }
		                else if (j == 2) { arr[0] = 1; arr[2] = 1; }
		                else if (j == 3) { arr[0] = 1; arr[2] = 2; }
		                StringBuilder str = new StringBuilder();
		                for (int k = 0; k < 4; k++) {
		                    if (arr[k] == 2) str.append("~");
		                    if (arr[k] > 0) str.append((char)('A' + k));
		                }
		                ans.add(str.toString());
		                clear(i, j);
		            }
		        }
		    }
		    
		    for (int i = 0; i < ans.size(); i++) {
		        if (i > 0) System.out.print("+");
		        System.out.print(ans.get(i));
		    }
		    System.out.println();
		}
	}
	public static int get(int i, int j) {
	    return 1 & (x >> ((i << 2) + j));
	}
	public static void clear(int i, int j) {
	    x &= ~(1 << ((i << 2) + j));
	}
	public static void debug() {
	    System.out.println(x);
		for (int i = 3; i >= 0; i--) {
		    for (int j = 3; j >= 0; j--) System.out.print(get(i, j));
		    System.out.println();
		}
	}
}
