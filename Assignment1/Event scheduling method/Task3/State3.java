package Task3;

import java.util.*;
import java.io.*;

class State3 extends GlobalSimulation3 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue1 = 0, numberInQueue2 = 0, accumulated = 0, noMeasurements = 0, noArrivals = 0;
	public double timeMeasured = 0, timeCustomer = 0, accumulatedTime = 0;
	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event3 x) {
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
		timeCustomer = GlobalSimulation3.time;
		noArrivals++;
		// if numbers <10 gör det här annars rejected

		if (numberInQueue1 == 0) {
			insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));
		}

		numberInQueue1++;
		insertEvent(ARRIVAL, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0/1.5)); // Uppdateras till 2, 1.5, 1.1

	}

	private void ready1() {
		numberInQueue1--;
		if (numberInQueue2 == 0) {
			insertEvent(READY2, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));
		}
		numberInQueue2++;
		if (numberInQueue1 > 0)
			insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));

	}

	private void ready2() {
		numberInQueue2--;
		if (numberInQueue2 > 0)
			insertEvent(READY2, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));
		timeMeasured = GlobalSimulation3.time - timeCustomer;

		
	}

	private void measure() {
		accumulated = accumulated + numberInQueue2 + numberInQueue1;
		accumulatedTime = accumulatedTime + timeMeasured;
		noMeasurements++;
		insertEvent(MEASURE, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0/5.0));

	}

}