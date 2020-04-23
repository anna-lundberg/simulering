package Task5;
import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS5 extends Proc5{
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc5 sendTo;
	Random slump = new Random();

	public void TreatSignal(Signal5 x){
		switch (x.signalType){

			case ARRIVAL:{
				numberInQueue++;
				if (numberInQueue == 1){
					SignalList5.SendSignal(READY,this, time + 0.2*slump.nextDouble());
				}
			} break;

			case READY:{
				numberInQueue--;
				if (sendTo != null){
					SignalList5.SendSignal(ARRIVAL, sendTo, time);
				}
				if (numberInQueue > 0){
					SignalList5.SendSignal(READY, this, time + 0.2*slump.nextDouble());
				}
			} break;

			case MEASURE:{
				noMeasurements++;
				accumulated = accumulated + numberInQueue;
				SignalList5.SendSignal(MEASURE, this, time + 2*slump.nextDouble());
			} break;
		}
	}
}