import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name,
			boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {
		TrainStation current = this.leftTerminus;
		int i = 0;
		while (current != null) {
			i++;
			current = current.getRight();
		}
		return i;
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {
		findStation(current.getName());
		if (current.hasConnection == true) {
			if (current.getTransferStation().equals(previous)) {
				return getNext(current);
			} else {
				return current.getTransferStation();
			}
		} else {
			return getNext(current);
		}
	}

	public TrainStation getNext(TrainStation station) {
		findStation(station.getName());
		if (station.isRightTerminal() && goingRight) {
			reverseDirection();
		}
		if (station.isLeftTerminal() && !goingRight) {
			reverseDirection();
		}
		if (goingRight == true) {
			return station.getRight();
		} else {
			return station.getLeft();
		}
	}

	public TrainStation findStation(String name) {
		TrainStation current = this.leftTerminus;
		while (current != null) {
			if (current.getName().equals(name)) {
				return current;
			}
			current = current.getRight();
		}
		throw new StationNotFoundException("Station does not exist on line");
	}

	public void sortLine() {
		TrainStation current = leftTerminus;
		TrainStation endOfSort = null;
		TrainStation unsortStart = current;
		TrainStation lowest = current;
		int ogSize = getSize();
		for (int i = 0; i < ogSize; i++) {
			current = unsortStart;
			lowest = current;
			while (current != null) {
				if (current.getName().compareTo(lowest.getName()) < 0) {
					lowest = current;
				}
				if ((current.getRight() == null) && (unsortStart.equals(lowest))) {
					unsortStart = unsortStart.getRight();
				}
				current = current.getRight();
			}

			if (i == 0) {
				leftTerminus = lowest;
				if (!(lowest.getLeft() == null)) {
					lowest.getLeft().setRight(lowest.getRight());
				}
				if (!(lowest.getRight() == null)) {
					lowest.getRight().setLeft(lowest.getLeft());
				}
				lowest.setNonTerminal();
				lowest.setLeftTerminal();
				lowest.setLeft(null);
				lowest.setRight(null);
				endOfSort = lowest;
				continue;
			}

			if (i == ogSize - 1) {
				rightTerminus = lowest;
				lowest.setNonTerminal();
				lowest.setRightTerminal();
				lowest.setLeft(endOfSort);
				lowest.setRight(null);
				endOfSort.setRight(rightTerminus);
				continue;
			}

			if (!(lowest.getLeft() == null)) {
				lowest.getLeft().setRight(lowest.getRight());
			}
			if (!(lowest.getRight() == null)) {
				lowest.getRight().setLeft(lowest.getLeft());
			}
			lowest.setNonTerminal();
			endOfSort.setRight(lowest);
			lowest.setLeft(endOfSort);
			lowest.setRight(null);
			endOfSort = lowest;
		}
		lineMap = getLineArray();
	}

	public TrainStation[] getLineArray() {
		TrainStation[] line = new TrainStation[getSize()];
		TrainStation current = this.leftTerminus;
		for (int i = 0; i < line.length; i++) {
			line[i] = current;
			if (!(i == line.length - 1)) {
				current = current.getRight();
			} else {
				continue;
			}
		}
		return line;
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();
		rand.setSeed(11);
		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);
		for (int i = 0; i < shuffledArray.length; i++) {
			if (i == 0) {
				leftTerminus = shuffledArray[i];
				shuffledArray[i].setNonTerminal();
				shuffledArray[i].setLeftTerminal();
				shuffledArray[i].setLeft(null);
				shuffledArray[i].setRight(shuffledArray[i + 1]);
				continue;
			}
			if (i == (shuffledArray.length - 1)) {
				rightTerminus = shuffledArray[i];
				shuffledArray[i].setNonTerminal();
				shuffledArray[i].setRightTerminal();
				shuffledArray[i].setRight(null);
				shuffledArray[i].setLeft(shuffledArray[i - 1]);
				continue;
			}
			shuffledArray[i].setNonTerminal();
			shuffledArray[i].setLeft(shuffledArray[i - 1]);
			shuffledArray[i].setRight(shuffledArray[i + 1]);
		}
	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}
