package Task3;

import java.util.*;
import java.io.*;

class State3 extends GlobalSimulation3 {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue1 = 0, numberInQueue2 = 0, accumulated = 0, noMeasurements = 0, noArrivals = 0;
	public double departureTime = 0, arrivalTime = 0, accumulatedTime = 0;
	
	List<Double> arrivalTimes = new ArrayList<Double>();
	List<Double> departureTimes = new ArrayList<Double>();

	
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

	private void arrival() {
		arrivalTimes.add(time);
		noArrivals++;

		//If no customers already in line, we service the customer right away
		if (numberInQueue1 == 0) {
			insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));
		}

		numberInQueue1++;
		//insertEvent(ARRIVAL, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0/2))); // Uppdateras till 2, 1.5, 1.1
		//insertEvent(ARRIVAL, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0/1.5))); // Uppdateras till 2, 1.5, 1.1
		insertEvent(ARRIVAL, time + 1.0*Math.log(1 - slump.nextDouble()) / (-(1.0/1.1))); // Uppdateras till 2, 1.5, 1.1

	}

	private void ready1() {
		numberInQueue1--;
		//If there are no customers in line 2, we send the customer to get service right away
		if (numberInQueue2 == 0) {
			insertEvent(READY2, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));
		}
		numberInQueue2++;
		
		//If there is a line, we service the first customer in line according to FIFO
		if (numberInQueue1 > 0)
			insertEvent(READY1, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));

	}

	private void ready2() {
		departureTimes.add(time);
		numberInQueue2--;
		//If there is a line, we service the first customer in line according to FIFO

		if (numberInQueue2 > 0)
			insertEvent(READY2, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0));

		
	}

	private void measure() {
		accumulated = accumulated + numberInQueue2 + numberInQueue1;
		//Method to calculate the time for every customer
		arrivalTime = arrivalTimes.remove(0);
		departureTime = departureTimes.remove(0);
		//Calculate average time spent in the system
		accumulatedTime = accumulatedTime + (departureTime-arrivalTime);
		noMeasurements++;
		//Measure 1000 measurements with an exponential distribution with a mean of 5 seconds
		insertEvent(MEASURE, time + 1.0*Math.log(1 - slump.nextDouble()) / (-1.0/5.0));

	}

}