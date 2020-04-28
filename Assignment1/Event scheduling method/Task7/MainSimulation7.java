package Task7;

import java.util.*;

import Task7.QS7;

import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation7 extends Global7 {

	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal �r den senast
		// utplockade signalen i huvudloopen nedan.
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.

		Signal7 actSignal;
		new SignalList7();

		// H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges
		// v�rden.
		// Here process instances are created (two queues and one generator) and their
		// parameters are given values.

		QS7 Q1 = new QS7();
		QS7 Q2 = new QS7();
		Q2.sendTo = null;
		QS7 Q3 = new QS7();
		QS7 Q4 = new QS7();
		Q4.sendTo = null;
		QS7 Q5 = new QS7();
		Q5.sendTo = null;

		Q1.sendTo = Q2;
		Q1.sendTo = Q5;

		Q3.sendTo = Q4;

		// H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
		// To start the simulation the first signals are put in the signal list

		SignalList7.SendSignal(ARRIVAL, Q1, 0);
		SignalList7.SendSignal(ARRIVAL, Q2, 0);
		SignalList7.SendSignal(ARRIVAL, Q3, 0);
		SignalList7.SendSignal(ARRIVAL, Q4, 0);
		SignalList7.SendSignal(ARRIVAL, Q5, 0);

	
		double systemBreakdown = 0;

		int allComponents = Q1.working + Q2.working + Q3.working + Q4.working + Q5.working;
		// Detta �r simuleringsloopen:
		// This is the main loop
		while (time < 10000 && allComponents <= 5) {
			if (allComponents == 0) {
				systemBreakdown = time;

			}
			actSignal = SignalList7.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		// Finally the result of the simulation is printed below:
		System.out.println("Breakdown: " + systemBreakdown);		

		System.out.println("Mean number of customers in queuing system: " + 1.0
				* (Q1.accumulated + Q2.accumulated + Q3.accumulated + Q4.accumulated + Q5.accumulated)
				/ (Q1.noMeasurements + Q2.noMeasurements + Q3.noMeasurements + Q4.noMeasurements + Q5.noMeasurements));

		System.out.println(
				"Accumulated: " + (Q1.accumulated + Q2.accumulated + Q3.accumulated + Q4.accumulated + Q5.accumulated));

		System.out.println("Measurements: "
				+ (Q1.noMeasurements + Q2.noMeasurements + Q3.noMeasurements + Q4.noMeasurements + Q5.noMeasurements));
		System.out.println("Mean number of customers in queuing system 1: " + Q1.accumulated);
		System.out.println("Mean number of customers in queuing system 2: " + Q2.accumulated);
		System.out.println("Mean number of customers in queuing system 3: " + Q3.accumulated);
		System.out.println("Mean number of customers in queuing system 4: " + Q4.accumulated);

		System.out.println("Mean number of customers in queuing system 5: " + Q5.accumulated);

	}
}