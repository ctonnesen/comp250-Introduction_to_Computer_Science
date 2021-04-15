public class Airport { 
	private int xcoord;
	private int ycoord;
	public int airFees;
	
	public Airport(int xcoord, int ycoord, int airFees) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		if (airFees<0) {
			throw new IllegalArgumentException("airFees cannot be negative");
		}
		this.airFees = airFees;
	}
	
	public int getFees() {
		return airFees;
	}
	
	public static int getDistance(Airport A1, Airport A2) {
		int A1xcoord = A1.xcoord;
		int A1ycoord = A1.ycoord;
		int A2xcoord = A2.xcoord;
		int A2ycoord = A2.ycoord;
		int dis = (int) Math.ceil((Math.sqrt((Math.pow((A2xcoord-A1xcoord), 2.0) + Math.pow((A2ycoord-A1ycoord), 2.0))))); 
		return dis;
	}
	
	public static void main(String[] args) {	
	}	
}
