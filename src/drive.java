public class drive {

	public static ControlDesk getNewControlDesk( int numLanes) {
		ControlDesk controlDesk = new ControlDesk( numLanes);
		return controlDesk;
	}

	public static void main(String[] args) {

		ConfigView cv = new ConfigView();
		cv.getGameParameters();
		int numLanes = cv.numLanes;
		int maxPatronsPerParty = cv.maxPatrons;
		

		
		ControlDesk controlDesk = getNewControlDesk(numLanes);

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		controlDesk.subscribe( cdv );

	}
}
