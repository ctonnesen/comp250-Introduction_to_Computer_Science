public class HotelReservation extends Reservation {
	private Hotel hotel;
	private String roomType;
	private int numNights;
	private int roomCost;

	public HotelReservation(String resName, Hotel hotel, String roomType, int numNights) {
		super(resName);
		this.hotel = hotel;
		this.roomType = roomType;
		this.numNights = numNights;
		if (numNights<1) {
			throw new IllegalArgumentException("You cannot have a negative number of nights");
		}
		this.roomCost = hotel.reserveRoom(roomType);
	}

	public int getNumOfNights() {
		return numNights;
	}

	public int getCost() {
		return (this.roomCost * this.numNights);
	}

	public boolean equals(Object object) {
		if (object instanceof HotelReservation) {
			HotelReservation objectHotel = (HotelReservation)object;
				if (super.reservationName().equals(objectHotel.reservationName())) {
					if (this.hotel == objectHotel.hotel) {
					if (this.roomType.equals(objectHotel.roomType)) {
						if (this.numNights == objectHotel.numNights) {
							if (getCost() == objectHotel.getCost()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
	}
}
