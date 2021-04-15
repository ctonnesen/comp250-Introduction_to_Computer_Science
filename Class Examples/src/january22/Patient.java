package january22;

import java.util.Arrays;

public class Patient {
	public String name;
	private int age;
	private double[] temps;
	private static double tempFever = 37.5;
	
	public Patient() {
		System.out.println("Creating a new patient");
	}
	
	public Patient(String n, int a) {
		this.name = n;
		this.age = a;
	}
	
	public Patient(String n, int a, double[] t) {
		this.name = n;
		this.age = a;
		this.temps = new double[t.length];
		this.temps = t;
		/*for (int i = 0; i<t.length; i++) {
			//this.temps[i]=t[i];
		} */
	}
		
	public static void main(String[] args) {
		Patient p = new Patient();
		double [] x = {37.8, 37.9, 40.2, 37.6, 36.5};
		Patient q = new Patient("John", 35,x);
		x[0]=1;
		p.name = "Sam";
		System.out.println(q.name);
		System.out.println(Arrays.toString(q.temps));
		
		/*
		Patient.tempFever = 40;
		System.out.println(Patient.tempFever);
		*/
	}
}	