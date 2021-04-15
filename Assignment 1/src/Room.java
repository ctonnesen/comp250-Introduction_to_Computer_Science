public class Room {
	private String roomType;
	private int roomFees;
	private boolean available;

	public Room(String roomType) {
		if (roomType.equalsIgnoreCase("double")) {
			this.roomType = roomType;
			this.roomFees = 9000;
			this.available = true;
		} else {
			if (roomType.equalsIgnoreCase("queen")) {
				this.roomType = roomType;
				this.roomFees = 11000;
				this.available = true;
			} else {
				if (roomType.equalsIgnoreCase("king")) {
					this.roomType = roomType;
					this.roomFees = 15000;
					this.available = true;

				} else {
					throw new IllegalArgumentException("A room of the provided type cannot be created");
				}
			}
		}
	}

	public Room(Room input) {
		this.roomType = input.roomType;
		this.roomFees = input.roomFees;
		this.available = input.available;
	}

	public String getType() {
		return roomType;
	}

	public int getPrice() {
		return roomFees;
	}

	public void changeAvailability() {
		if (available) {
			available = false;
		} else {
			available = true;
		}
	}

	public static Room findAvailableRoom(Room[] rooms, String inputType) {
		if (rooms == null) {
			return null;
		}
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getType().equalsIgnoreCase(inputType)) {
				if (rooms[i].available) {
					return rooms[i];
				}
			}
		}
		return null;
	}

	public static boolean makeRoomAvailable(Room[] rooms, String inputType) {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].roomType.equalsIgnoreCase(inputType)) {
				if (!(rooms[i].available)) {
					rooms[i].changeAvailability();
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

	}
}
