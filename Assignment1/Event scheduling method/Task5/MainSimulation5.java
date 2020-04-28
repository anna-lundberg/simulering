package Task5;

import java.util.*;

import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation5 extends Global5 {

	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal �r den senast
		// utplockade signalen i huvudloopen nedan.
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.

		Signal5 actSignal;
		new SignalList5();

		// H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges
		// v�rden.
		// Here process instances are created (two queues and one generator) and their
		// parameters are given values.

		QS5 Q1 = new QS5();
		Q1.sendTo = null;
		QS5 Q2 = new QS5();
		Q2.sendTo = null;
		QS5 Q3 = new QS5();
		Q3.sendTo = null;
		QS5 Q4 = new QS5();
		Q4.sendTo = null;
		QS5 Q5 = new QS5();
		Q5.sendTo = null;

		Gen5 Generator = new Gen5();
		Generator.lambda = 1.0 / 0.12; // Generator ska generera nio kunder per sekund //Generator shall generate 9
		// customers per second

		// H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
		// To start the simulation the first signals are put in the signal list

		SignalList5.SendSignal(READY, Generator, time);
		SignalList5.SendSignal(MEASURE, Q1, time);
		SignalList5.SendSignal(MEASURE, Q2, time);
		SignalList5.SendSignal(MEASURE, Q3, time);
		SignalList5.SendSignal(MEASURE, Q4, time);
		SignalList5.SendSignal(MEASURE, Q5, time);

		// Detta �r simuleringsloopen:
		// This is the main loop

		while (time < 200000) {
			actSignal = SignalList5.FetchSignal();

			int kö = (Generator.slump.nextInt(5) + 1);
			// Slumpar vilken kö vi ska skicka till

			if (kö == 1)
				Generator.sendTo = Q1; // De genererade kunderna ska skickas till k�systemet QS // The generated
										// customers shall be sent to Q1-Q5				
			if (kö == 2)
				Generator.sendTo = Q2;
			if (kö == 3)
				Generator.sendTo = Q3;
			if (kö == 4)
				Generator.sendTo = Q4;
			if (kö == 5)
				Generator.sendTo = Q5;

			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		// Finally the result of the simulation is printed below:
		System.out.println("Mean time in system: " + 1.0
				* (Q1.accumulatedTime + Q2.accumulatedTime + Q3.accumulatedTime + Q4.accumulatedTime
						+ Q5.accumulatedTime)
				/ (Q1.noMeasurements + Q2.noMeasurements + Q3.noMeasurements + Q4.noMeasurements + Q5.noMeasurements)/5);

		System.out.println("Little: " +(1/0.12)*(Q1.accumulatedTime + Q2.accumulatedTime + Q3.accumulatedTime + Q4.accumulatedTime
				+ Q5.accumulatedTime)
		/ (Q1.accumulated + Q2.accumulated + Q3.accumulated + Q4.accumulated + Q5.accumulated));

		System.out.println("Mean number of customers in queuing system: " + 1.0
				* (Q1.accumulated + Q2.accumulated + Q3.accumulated + Q4.accumulated + Q5.accumulated)
				/ (Q1.noMeasurements + Q2.noMeasurements + Q3.noMeasurements + Q4.noMeasurements + Q5.noMeasurements));

		System.out.println(
				"Accumulated: " + (Q1.accumulated + Q2.accumulated + Q3.accumulated + Q4.accumulated + Q5.accumulated));

		System.out.println("Measurements: "
				+ (Q1.noMeasurements + Q2.noMeasurements + Q3.noMeasurements + Q4.noMeasurements + Q5.noMeasurements));
		System.out.println("Mean number of customers in queuing system 1: " + 1.0 * Q1.accumulated / Q1.noMeasurements);
		System.out.println("Mean number of customers in queuing system 2: " + 1.0 * Q2.accumulated / Q2.noMeasurements);
		System.out.println("Mean number of customers in queuing system 3: " + 1.0 * Q3.accumulated / Q3.noMeasurements);
		System.out.println("Mean number of customers in queuing system 4: " + 1.0 * Q4.accumulated / Q4.noMeasurements);
		System.out.println("Mean number of customers in queuing system 5: " + 1.0 * Q5.accumulated / Q5.noMeasurements);

	}
}