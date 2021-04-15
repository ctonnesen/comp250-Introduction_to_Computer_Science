
public class BnBReservation extends HotelReservation{
	
	public BnBReservation(String name, Hotel hotel, String roomType, int numOfNights) {
		super(name, hotel, roomType, numOfNights);
		if (numOfNights<1) {
			throw new IllegalArgumentException("You cannot have a negative number of nights");
		}
	}
	
	public int getCost() {
		int totalCost = super.getNumOfNights() * 1000 + super.getCost();
		return totalCost;
	}
	
	public static void main(String[] args) {
	}
}
