package Task6a;

import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS6 extends Proc6 {
	public int numberInQueue = 0, accumulated, noMeasurements, arrivals, noRejected;
	public Proc6 sendTo;
	public double departureTime = 0, arrivalTime = 0, accumulatedTime = 0, timeOfDay = 0.0, lastJob = 0;

	Random slump = new Random();
	List<Double> arrivalTimes = new ArrayList<Double>();
	List<Double> departureTimes = new ArrayList<Double>();


	public void TreatSignal(Signal6 x) {
		switch (x.signalType) {

		case ARRIVAL: {

			arrivals++;
			arrivalTimes.add(time);

			if (timeOfDay + time <= 28800) {
				numberInQueue++;

				if (numberInQueue == 1) {
				
				SignalList6.SendSignal(READY, this, time + (600.0 + 600.0 * slump.nextDouble()));
			}
			}
			
			else if (timeOfDay + time > 28000) {
				noRejected++;
			}
		}
			break;

		case READY: {

			departureTimes.add(time);

			numberInQueue--;

			if (sendTo != null) {
				SignalList6.SendSignal(ARRIVAL, sendTo, time);
			}
			if (numberInQueue > 0) {
				SignalList6.SendSignal(READY, this, time + (600.0 + 600.0 * slump.nextDouble()));
			}
			timeInSystem();

		}

			break;

		case MEASURE: {

			noMeasurements++;
			accumulated = accumulated + numberInQueue;

			SignalList6.SendSignal(MEASURE, this, time + 1);

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
		lastJob = departureTime;

	}
}