import java.util.Arrays;

public class Intersection {

	public static void main(String[] args) {
		int [] x = {1, 2, 3, 4};
		int [] y = {5, 2, 3, 8};
		System.out.println(Arrays.toString(getIntersectionOne(x, y)));
	}
	
	public static int[] getIntersectionOne(int[] a, int[] b) {
		int size = 0;
		for (int i = 0; i<a.length; i++) {
			for (int j = 0; j<b.length; j++) { 
				if (a[i]==b[j]) {
					size++;
					break;
				}
			}
		}
		
	int[] c = new int[size];
	int index = 0;
	for (int i = 0; i<a.length; i++) {
		for (int j = 0; j<b.length; j++) { 
			if (a[i]==b[j]) {
				c[index]=a[i];
				index++;
			}
		}
	}
	return c;
	}	
}
