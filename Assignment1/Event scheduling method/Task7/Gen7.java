package Task7;
import java.util.*;
import java.io.*;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen7 extends Proc7{

	//Slumptalsgeneratorn startas:
	//The random number generator is started:
	Random slump = new Random();

	//Generatorn har tv� parametrar:
	//There are two parameters:
	public Proc7 sendTo;    //Anger till vilken process de genererade kunderna ska skickas //Where to send customers
	public double lambda;  //Hur m�nga per sekund som ska generas //How many to generate per second

	//H�r nedan anger man vad som ska g�ras n�r en signal kommer //What to do when a signal arrives
	public void TreatSignal(Signal7 x){
		switch (x.signalType){
			case READY:{
				SignalList7.SendSignal(ARRIVAL, sendTo, time);
				SignalList7.SendSignal(READY, this, time + (2.0/lambda)*slump.nextDouble());}
				break;
		}
	}
}