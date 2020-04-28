package Task6a;
import java.util.*;
import java.io.*;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen6 extends Proc6{

	//Slumptalsgeneratorn startas:
	//The random number generator is started:
	Random slump = new Random();

	//Generatorn har tv� parametrar:
	//There are two parameters:
	public Proc6 sendTo;    //Anger till vilken process de genererade kunderna ska skickas //Where to send customers
	public double lambda;  //Hur m�nga per sekund som ska generas //How many to generate per second

	//H�r nedan anger man vad som ska g�ras n�r en signal kommer //What to do when a signal arrives
	public void TreatSignal(Signal6 x){
		switch (x.signalType){
			case READY:{
				SignalList6.SendSignal(ARRIVAL, sendTo, time);
				SignalList6.SendSignal(READY, this, time + (2.0/lambda)*slump.nextDouble());}
				break;
		}
	}
}