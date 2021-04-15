public class TrainNetwork {
	final int swapFreq = 2;
	TrainLine[] networkLines;

	public TrainNetwork(int nLines) {
		this.networkLines = new TrainLine[nLines];
	}

	public void addLines(TrainLine[] lines) {
		this.networkLines = lines;
	}

	public TrainLine[] getLines() {
		return this.networkLines;
	}

	public void dance() {
		System.out.println("The tracks are moving!");
		for (int i = 0; i < networkLines.length; i++) {
			networkLines[i].shuffleLine();
		}
	}

	public void undance() {
		System.out.println("The tracks are moving!");
		for (int i = 0; i < networkLines.length; i++) {
			networkLines[i].sortLine();
		}
	}

	public int travel(String startStation, String startLine, String endStation, String endLine) {
		int hoursCount = 0;
		try {
			TrainLine curLine = getLineByName(startLine);
			TrainStation curStation = curLine.findStation(startStation);
			TrainStation prevStation = curStation;
			TrainStation ferryCur = curStation;
			System.out.println("Departing from " + startStation);
			while (!((curStation.getName().equals(endStation)))) {
				if (hoursCount == 168) {
					System.out.println("Jumped off after spending a full week on the train. Might as well walk.");
					return hoursCount;
				}
				System.out.println("Traveling on line " + curLine.getName() + ":" + curLine.toString());
				System.out.println("Hour " + hoursCount + ". Current station: " + curStation.getName() + " on line "
						+ curLine.getName());
				System.out.println("=============================================");							
				ferryCur = curStation;
				curStation = curLine.travelOneStation(curStation, prevStation);
				prevStation = ferryCur;
				curLine = getLineByName(curStation.getLine().getName());
				hoursCount++;
				if (hoursCount % 2 == 0) {
					dance();				
				}
			}
			System.out.println("Arrived at destination after " + hoursCount + " hours!");
			return hoursCount;
		} catch (Exception e) {
			System.out.println("Current Hours: " + hoursCount + " hours!");
			return hoursCount;
		}
	}

	public TrainLine getLineByName(String lineName) {
		for (int i = 0; i < networkLines.length; i++) {
			if (lineName.equals(networkLines[i].getName())) {
				return networkLines[i];
			}
		}
		throw new LineNotFoundException("Line not found");
	}

	// prints a plan of the network for you.
	public void printPlan() {
		System.out.println("CURRENT TRAIN NETWORK PLAN");
		System.out.println("----------------------------");
		for (int i = 0; i < this.networkLines.length; i++) {
			System.out.println(this.networkLines[i].getName() + ":" + this.networkLines[i].toString());
		}
		System.out.println("----------------------------");
	}
}

//exception when searching a network for a LineName and not finding any matching Line object.
class LineNotFoundException extends RuntimeException {
	String name;

	public LineNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "LineNotFoundException[" + name + "]";
	}
}