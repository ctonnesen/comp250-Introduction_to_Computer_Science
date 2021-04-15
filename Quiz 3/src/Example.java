
public class Example {

	public static void main(String[] args) {
		System.out.println(mysterious(2,3));
		
	}

	
	public static int mysterious(int x, int y) {

	    if(x == 0)

	        return y;

	    else

	        return mysterious(x-1,  x+y);

	}

}
