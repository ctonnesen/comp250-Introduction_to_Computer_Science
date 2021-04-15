
public class Customer {
	private String name;
	private int balance;
	private Basket cart;

	public Customer(String name, int initBal) {
		this.name = name;
		this.balance = initBal;
		if (initBal<0) {
			throw new IllegalArgumentException("You cannot have a negative balance");
		}
		this.cart = new Basket();
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public Basket getBasket() {
		return cart;
	}

	public int addFunds(int deposit) {
		if (deposit < 0) {
			throw new IllegalArgumentException("Deposit cannot be negative value");
		}
		balance += deposit;
		return balance;
	}

	public int addToBasket(Reservation reservation) {
		if (name.equals(reservation.reservationName())) {
			cart.add(reservation);
			return cart.getNumOfReservations();
		}
		throw new IllegalArgumentException("The name does not match the reservation");
	}


	public int addToBasket(Hotel hotel, String roomType, int numOfNights, boolean breakf) {
		if (breakf == false) {
			Reservation hotelRes = new HotelReservation(name, hotel, roomType, numOfNights);
			addToBasket(hotelRes);
			return cart.getNumOfReservations();
		} else {
			Reservation BnB = new BnBReservation(name, hotel, roomType, numOfNights);
			addToBasket(BnB);
			return cart.getNumOfReservations();
		}
	}

	public int addToBasket(Airport departure, Airport arrival) {
		Reservation flightRes = new FlightReservation(name, departure, arrival);
		addToBasket(flightRes);
		return cart.getNumOfReservations();
	}

	public boolean removeFromBasket(Reservation reservation) {
		return cart.remove(reservation);
	}

	public int checkOut() {
		if (balance < cart.getTotalCost()) {
			throw new IllegalArgumentException("There is currently not enough funds");
		}
		balance = balance - cart.getTotalCost();
		cart.clear();
		return balance;
	}

	public static void main(String[] args) {
	
	}
}
