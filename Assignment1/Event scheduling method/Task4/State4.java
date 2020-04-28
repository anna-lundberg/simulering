package Task4;

import java.util.*;
import java.io.*;

class State4 extends GlobalSimulation4 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements.
	//For task 4 we added numberServed, M, n, lambda, x and T and updated for the different simulations
	public int  numberServed = 0, accumulated = 0, noMeasurements = 0, noArrivals = 0, noRejected = 0,
			M = 4000, n = 100;
	public double x = 10.0, lambda = 4.0, T = 4.0;
	Random slump = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	
	SimpleFileWriter4 W = new SimpleFileWriter4("number.m", false);

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
		noArrivals++;
		// if numbers <10 gör det här annars rejected

		if (numberServed < n) {
			numberServed++;
			insertEvent(READY, time + x);

		} else {
			noRejected++;
		}
		insertEvent(ARRIVAL, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-1.0 * lambda));

	}

	private void ready() {
		numberServed--;

	}

	private void measure() {
		accumulated = accumulated + numberServed; //får konstigt resultat, kan vara fel i alla uppgifter :) 
		noMeasurements++;
		insertEvent(MEASURE, time + T);
		W.println(String.valueOf(numberServed));

		
	}

}