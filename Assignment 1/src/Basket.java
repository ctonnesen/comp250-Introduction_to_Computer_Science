public class Basket {
	private Reservation reservations[];

	public Basket() {
		this.reservations = new Reservation[0];
	}

	public Reservation[] getProducts() {
		Reservation copy[] = reservations;
		return copy;
	}

	public int add(Reservation reservation) {
		int i;
		Reservation[] tempCopy = new Reservation[reservations.length+1];
		for (i = 0; i < reservations.length; i++) {
				tempCopy[i] = reservations[i];
		}
		tempCopy[reservations.length] = reservation;
		reservations = tempCopy;
		return reservations.length;
	}

	public boolean remove(Reservation reservation) {
		for (int i = 0; i < reservations.length; i++) {
			if (this.reservations[i].equals(reservation)) {
				reservations[i] = null;
				int counter = 0;
				Reservation tempCopy[] = new Reservation[reservations.length-1];
				for (int j = 0; j < reservations.length; j++) {
					if (!(reservations[j] == null)) {
						tempCopy[counter] = reservations[j];
						counter++;;
					}
				}
				reservations = tempCopy;
				return true;
			}
		}
		return false;
	}

	public void clear() {
		reservations = new Reservation[0];
	}

	public int getNumOfReservations() {
		return reservations.length;
	}

	public int getTotalCost() {
		int totalCosts = 0;
		for (int i = 0; i < reservations.length; i++) {
			totalCosts += reservations[i].getCost();
		}
		return totalCosts;
	}

	public static void main(String[] args) {
	}
	

}
