import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    String[] output = new String[5];
	    Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
		    String input = sc.nextLine();
		    String[] A = input.split("\\s+");
		    input = sc.nextLine();
		    String[] B = input.split("\\s+");
            String C = algorithm1(A, B);
            //System.out.println(C);
            String D = algorithm1(B, A);
            //System.out.println(D);
            output[i] = algorithm2(C, D);
            if (output[i] == "") output[i] = "NONE";
            System.out.println(output[i]);
		}
	}
	
	static String algorithm1(String[] X, String[] Y) {
        String ret = "";
        String[] A = new String[X.length];
        String[] B = new String[Y.length];
        
        for (int i = 0; i < X.length; i++) {
            A[i] = X[i];
        }
        for (int i = 0; i < Y.length; i++) {
            B[i] = Y[i];
        }
        
        for (String a : A) {
            for (int i = 0; i < B.length; i++) {
                int pos = B[i].indexOf(a);
                if (pos != -1) {
                    //System.out.println("Before: " + B[i] + " " + i + " " + a);
                    B[i] = B[i].replaceFirst(a, "");
                    //System.out.println("After: " + B[i] + " " + i);
                    ret += a;
                    break;
                }
            }
        }
        return ret;
    }
    
    static String algorithm2(String X, String Y) {
        String ret = "", A = X, B = Y;
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            int pos = B.indexOf(c);
            if (pos != -1) {
                B = B.substring(pos + 1);
                ret += c;
            }
        }
        return ret;
    }
}