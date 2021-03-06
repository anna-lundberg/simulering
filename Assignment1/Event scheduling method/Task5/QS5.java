package Task5;

import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS5 extends Proc5 {
	public int numberInQueue = 0, accumulated, noMeasurements, arrivals;
	public Proc5 sendTo;
	public double departureTime = 0, arrivalTime = 0, accumulatedTime = 0;

	Random slump = new Random();
	List<Double> arrivalTimes = new ArrayList<Double>();
	List<Double> departureTimes = new ArrayList<Double>();

	public void TreatSignal(Signal5 x) {
		switch (x.signalType) {

		case ARRIVAL: {
			arrivals++;
			numberInQueue++;
			arrivalTimes.add(time);

			if (numberInQueue == 1) {
				SignalList5.SendSignal(READY, this, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-1.0 / 0.5));
			}

		}
			break;

		case READY: {
			numberInQueue--;

			departureTimes.add(time);

			if (sendTo != null) {
				SignalList5.SendSignal(ARRIVAL, sendTo, time);
			}
			if (numberInQueue > 0) {
				SignalList5.SendSignal(READY, this, time + 1.0 * Math.log(1 - slump.nextDouble()) / (-1.0 / 0.5));
			}

		}
		
			if((arrivalTimes.get(0)) != null)
			timeInSystem();
			break;

		case MEASURE: {

			noMeasurements++;
			accumulated = accumulated + numberInQueue;

			SignalList5.SendSignal(MEASURE, this, time + 1);
			
		}
			break;
		}
	}

	private void timeInSystem() {
		// Method to calculate the time for every customer
		arrivalTime = arrivalTimes.remove(0);
		departureTime = departureTimes.remove(0);
		// Calculate average time spent in the system
		accumulatedTime = accumulatedTime + (departureTime - arrivalTime);

	}
}