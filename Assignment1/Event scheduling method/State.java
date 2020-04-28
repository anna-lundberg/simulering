import java.util.*;
import java.io.*;

class State extends GlobalSimulation {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public double numberInQueue1 = 0, numberInQueue2 = 0, accumulated = 0, noMeasurements = 0, noRejected = 0,
			noArrivals = 0, accumulated1 = 0, accumulated2 = 0;

	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event x) {
		switch (x.eventType) {
		case ARRIVAL:
			arrival();
			break;
		case READY1:
			ready1();
			break;
		case READY2:
			ready2();
			break;
		case MEASURE:
			measure();
			break;

		}
	}

	// The following methods defines what should be done when an event takes place.
	// This could
	// have been placed in the case in treatEvent, but often it is simpler to write
	// a method if
	// things are getting more complicated than this.

	private void arrival() {
		noArrivals++;
		// if numbers <10 gör det här annars rejected

		if (numberInQueue1 < 10) {

			if (numberInQueue1 == 0) {
				insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0 / 2.1)));
			}
			numberInQueue1++;
		} else {
			noRejected++;
		}
		insertEvent(ARRIVAL, time + 5.0); // Uppdateras till 1, 2, 5

	}

	private void ready1() {
		numberInQueue1--;
		if (numberInQueue2 == 0)
			insertEvent(READY2, time + 2.0);

		numberInQueue2++;
		if (numberInQueue1 > 0)
			insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0 / 2.1)));

	}

	private void ready2() {
		numberInQueue2--;
		if (numberInQueue2 > 0)
			insertEvent(READY2, time + 2);
	}

	private void measure() {

		accumulated = accumulated + numberInQueue2;
		accumulated1 = accumulated1 + numberInQueue1;
		accumulated2 = accumulated2 + numberInQueue2;

		noMeasurements++;
		insertEvent(MEASURE, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0 / 5.0)));

	}
}