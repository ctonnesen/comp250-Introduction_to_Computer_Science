import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int[][] df = new int[3][];

		try {

		    System.out.println(df[1][1]/0);

		} catch (ArithmeticException e) {

		    System.out.println("Bad math!");

		} catch (NullPointerException e) {

		    System.out.println("It is null!");

		} finally {

		    System.out.println("Finally!");

		}

			short x;

	        int a = 257;

	        double b = 128.5;

	        x = (short) a;

	        System.out.println("a = " + a + " x = " + x);

	        a = (int) b;

	        System.out.println("a = " + a + " b = " + b);

	        b = a; 

	        System.out.println("b = " + b + " x =" + x);

	    }    
	
	
	
	
	}
	
	
	
	
	
	
		    }    


