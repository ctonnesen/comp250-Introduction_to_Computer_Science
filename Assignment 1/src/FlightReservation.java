public class FlightReservation extends Reservation {
	private Airport departure;
	private Airport arrival;

	public FlightReservation(String name, Airport departure, Airport arrival) {
		super(name);
		if (departure.equals(arrival)) {
			throw new IllegalArgumentException("The two airports are the same");
		}
		this.departure = departure;
		this.arrival = arrival;
	}

	public int getCost() {
		double distance = Airport.getDistance(departure, arrival);
		int airFees1 = (int)  Math.ceil(((distance)/167.52)*124);
		int airFees2 =  departure.getFees() + arrival.getFees()+5375;
		return airFees1+airFees2;
	}

	public boolean equals(Object object) {
		if (object instanceof FlightReservation) {
				FlightReservation objectFlight = (FlightReservation)object;
				if (super.reservationName().equals(objectFlight.reservationName())) {
					if (this.arrival == objectFlight.arrival) {
						if (this.departure == objectFlight.departure) {
							return true;
						}
					}
				}
			}
		return false;
	}

	public static void main(String[] args) {

	}

}
