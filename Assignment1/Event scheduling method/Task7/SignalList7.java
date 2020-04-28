package Task7;
// Denna klass definierar signallistan. Om man vill skicka mer information i signalen �n minimum, s� kan
// man skriva ytterligare variante av SendSignal som inneh�ller fler parametrar.

// This class defines the signal list. If one wants to send more information than here,
// one can add the extra information in the Signal class and write an extra sendSignal method 
// with more parameters. 

public class SignalList7{
	private  static Signal7 list, last;

	SignalList7(){
    	list = new Signal7();
    	last = new Signal7();
    	list.next = last;
	}

	public static void SendSignal(int type, Proc7 dest, double arrtime){
 	Signal7 dummy, predummy;
 	Signal7 newSignal = new Signal7();
 	newSignal.signalType = type;
 	newSignal.destination = dest;
 	newSignal.arrivalTime = arrtime;
 	predummy = list;
 	dummy = list.next;
 	while ((dummy.arrivalTime < newSignal.arrivalTime) & (dummy != last)){
 		predummy = dummy;
 		dummy = dummy.next;
 	}
 	predummy.next = newSignal;
 	newSignal.next = dummy;
 }

	public static Signal7 FetchSignal(){
		Signal7 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
