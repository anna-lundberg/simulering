package Task6;

import java.util.*;
import java.io.*;

class State6 extends GlobalSimulation6 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue = 0, numberServed = 0, accumulated = 0, noMeasurements = 0, noArrivals = 0, noRejected = 0;
	public double timeMeasured = 0, lastJob = 0, accumulatedTime = 0, timeOfDay = 0.0, arrivalTime = 0,
			departureTime = 0;
	Random slump = new Random(); // This is just a random number generator
	List<Double> arrivalTimes = new ArrayList<Double>();
	List<Double> departureTimes = new ArrayList<Double>();

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event6 x) {
		switch (x.eventType) {
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
		arrivalTimes.add(time);

		if (timeOfDay + time <= 28800) {
			noArrivals++;

			if (numberInQueue == 0) {
				insertEvent(READY, time + (600.0 + 600.0 * slump.nextDouble()));

			}

		} else {
			noRejected++;
		}

		insertEvent(ARRIVAL, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-1.0 / 4.0));

	}

	private void ready() {
		numberServed++;
		departureTimes.add(time);

		insertEvent(READY, time + (600.0 + 600.0 * slump.nextDouble()));

		timeInSystem();
		lastJob = departureTime;

	}

	private void measure() {
		accumulated = accumulated + numberServed;
		accumulatedTime = accumulatedTime + timeMeasured;
		noMeasurements++;
		insertEvent(MEASURE, time + 1);

	}

	private void timeInSystem() {
		// Method to calculate the time for every customer
		arrivalTime = arrivalTimes.remove(0);
		departureTime = departureTimes.remove(0);
		// Calculate average time spent in the system
		accumulatedTime = accumulatedTime + (departureTime - arrivalTime);

	}
}