package Task7;

import java.util.*;

import Task6a.SignalList6;

import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS7 extends Proc7 {
	public int numberInQueue = 0, accumulated, noMeasurements, working, noRejected; 
	public double startTime = 0, endTime =0;
	public Proc7 sendTo;

	Random slump = new Random();


	public void TreatSignal(Signal7 x) {
		switch (x.signalType) {

		case ARRIVAL: {		


			working++;

			if (working > 0)
				SignalList7.SendSignal(READY, this, time + (1.0 + 4.0 * slump.nextDouble()));
		}

			break;

		// Components break according to uniform distribution
		case READY: {

			working--;
			
			if (working == 0) {
				SignalList7.SendSignal(SETEND, this, time);

			}
			SignalList7.SendSignal(MEASURE, this, time);

		}

			break;

		case MEASURE: {
			accumulated = accumulated+ working;
			noMeasurements++;

			SignalList7.SendSignal(MEASURE, this, time + 1);

		}
			break;
			
		case SETEND: {
			System.out.println("Slut");
			
		}
		break;
		}

	}


}