package Task2;

import java.util.*;
import java.io.*;

class State2 extends GlobalSimulation2 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int accumulated = 0, noOfA = 0, noOfB = 0, noOfDelay = 0, noMeasurements = 0, accumulatedA = 0,
			accumulatedB = 0;

	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event2 x) {
		switch (x.eventType) {
		case ARRIVAL:
			arrival();
			break;
		case READY:
			ready();
			break;
		case DELAY:
			delay();
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
		if (noOfA == 0)
			insertEvent(READY, time + 0.002);
		insertEvent(ARRIVAL, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-150.0));
		noOfA++;

	}

	private void ready() {

		// for task 2.3: Priority A

		if (noOfA > 0) {
			noOfA--;
			 insertEvent(DELAY, time + 1); // Konstant 1 sekund för 2.3
			insertEvent(READY, time + 0.002);

		} else if (noOfB > 0) {
			noOfB--;
			insertEvent(READY, time + 0.004);

		}

		// for task 2.1 and 2.2: Priority B

		/*
		 * if (noOfB > 0) { noOfB--; insertEvent(READY, time + 0.004); } else if (noOfA
		 * > 0) { insertEvent(DELAY, time + Math.log(1 - slump.nextDouble())/(-(1.0)));
		 * // Exponentialfördelat 1 för 2.2 //insertEvent(DELAY, time + 1); // Konstant
		 * 1 för 2.1
		 * 
		 * insertEvent(READY, time + 0.002); noOfA--;
		 * 
		 * }
		 */

	}

	private void delay() { // Event läggs till för delay efter jobb A
		noOfB++;
		noOfDelay++;

	}

	private void measure() {
		accumulated = accumulated + noOfA + noOfB;
		accumulatedA = accumulatedA + noOfA;
		accumulatedB = accumulatedB + noOfB;

		noMeasurements++;
		insertEvent(MEASURE, time + 0.1);

	}

}