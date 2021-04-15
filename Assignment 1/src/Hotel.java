public class Hotel {
	private String hotelName;
	private Room[] rooms;
	
	public Hotel(String hotel, Room[] roomsIn) {
		this.hotelName = hotel;
		this.rooms = new Room [roomsIn.length];
		for (int i = 0; i < this.rooms.length; i++) {
			rooms[i] = new Room(roomsIn[i]);
		}
	}

	public int reserveRoom(String roomType) {
		Room firstAvailable = Room.findAvailableRoom(rooms, roomType);
		if (firstAvailable == null) {
			throw new IllegalArgumentException("No rooms Available");
		}
		firstAvailable.changeAvailability();
		return firstAvailable.getPrice();
	}

	public boolean cancelRoom(String roomType) {
		if (Room.makeRoomAvailable(rooms, roomType)) {
			return true;
		} else {
			return false;
		}

	}

	public static void main(String[] args) {
	}
}
