package Task5;
import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation5 extends Global5{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.
    	// The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
    	// signal list in the main loop below.

    	Signal5 actSignal;
    	new SignalList5();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.
    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS5 Q1 = new QS5();
    	Q1.sendTo = null;

    	Gen5 Generator = new Gen5();
    	Generator.lambda = 9; //Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    	Generator.sendTo = Q1; //De genererade kunderna ska skickas till k�systemet QS  // The generated customers shall be sent to Q1

    	//H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
    	//To start the simulation the first signals are put in the signal list

    	SignalList5.SendSignal(READY, Generator, time);
    	SignalList5.SendSignal(MEASURE, Q1, time);


    	// Detta �r simuleringsloopen:
    	// This is the main loop

    	while (time < 100000){
    		actSignal = SignalList5.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:
    	//Finally the result of the simulation is printed below:

    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);

    }
}