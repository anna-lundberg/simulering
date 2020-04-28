package Task6a;

import java.util.*;

import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation6 extends Global6 {

	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal �r den senast
		// utplockade signalen i huvudloopen nedan.
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.

		Signal6 actSignal;
		new SignalList6();
		List<Double> lastJobs = new ArrayList<Double>();
		List<Double> dailyCustomers = new ArrayList<Double>();

		// H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges
		// v�rden.
		// Here process instances are created (two queues and one generator) and their
		// parameters are given values.

		QS6 Q1 = new QS6();
		Q1.sendTo = null;

		Gen6 Generator = new Gen6();
		Generator.lambda = 4.0 / 3600;// Generator ska generera 4 kunder per timme (3600 sekunder) //
		Generator.sendTo = Q1;

		// H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
		// To start the simulation the first signals are put in the signal list

		SignalList6.SendSignal(READY, Generator, time);
		SignalList6.SendSignal(MEASURE, Q1, time);

		// Detta �r simuleringsloopen:
		// This is the main loop
		int numberOfDays = 1;
		while (time < 28800000) {

			if (time / numberOfDays == 28000) {
				Q1.timeOfDay = 0.0;
				numberOfDays++;
				lastJobs.add(Q1.lastJob);

			}
			actSignal = SignalList6.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		double totalTime = 0;

		for (int i = 0; i < lastJobs.size(); i++) {
			totalTime = totalTime + lastJobs.get(i);
		}

		double mean = totalTime / numberOfDays;

		System.out.println("Average working time: " + mean / 3600 + " hours");
		System.out.println("Average over working time: " + (mean - 28800) / 60 + " minutes");

		System.out.println("Average work day ending time: 17:" + (int) ((mean - 28800) / 60));

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		// Finally the result of the simulation is printed below:
		System.out.println("Mean time in system: " + 1.0 * (Q1.accumulatedTime / (Q1.accumulated / numberOfDays)) / 60
				+ " minutes");
		System.out.println("Days: " + 1.0 * (numberOfDays));
		System.out.println("Accumulated: " + (Q1.accumulated));

		System.out.println("Last job: " + (Q1.lastJob));

		System.out.println("Measurements: " + (Q1.noMeasurements));

	}
}