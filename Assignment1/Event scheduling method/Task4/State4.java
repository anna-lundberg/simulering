package Task4;

import java.util.*;
import java.io.*;

class State4 extends GlobalSimulation4 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue = 0, numberServed = 0, accumulated = 0, noMeasurements = 0, noArrivals = 0, noRejected = 0,
			M = 1000, n = 1000;
	public double timeMeasured = 0, timeCustomer = 0, accumulatedTime = 0, x = 100.0, lambda = 8.0, T = 1.0;
	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event4 x) {		switch (x.eventType) {
		case ARRIVAL:
			arrival();
			break;
		case READY:
			ready();
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
		timeCustomer = GlobalSimulation4.time;
		noArrivals++;
		// if numbers <10 gör det här annars rejected

		if (numberServed < n) {
			insertEvent(READY, time + x);

		} else {
			noRejected++;
		}
		insertEvent(ARRIVAL, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-1.0 * lambda));

	}

	private void ready() {
		numberServed++;
		timeMeasured = GlobalSimulation4.time - timeCustomer;

	}

	private void measure() {
		accumulated = accumulated + numberServed; //får konstigt resultat, kan vara fel i alla uppgifter :) 
		accumulatedTime = accumulatedTime + timeMeasured;
		noMeasurements++;
		insertEvent(MEASURE, time + T);
		System.out.println(accumulated);
		
		
	}

}