package Task5;
// Denna klass definierar signallistan. Om man vill skicka mer information i signalen �n minimum, s� kan
// man skriva ytterligare variante av SendSignal som inneh�ller fler parametrar.

// This class defines the signal list. If one wants to send more information than here,
// one can add the extra information in the Signal class and write an extra sendSignal method 
// with more parameters. 

public class SignalList5{
	private  static Signal5 list, last;

	SignalList5(){
    	list = new Signal5();
    	last = new Signal5();
    	list.next = last;
	}

	public static void SendSignal(int type, Proc5 dest, double arrtime){
 	Signal5 dummy, predummy;
 	Signal5 newSignal = new Signal5();
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

	public static Signal5 FetchSignal(){
		Signal5 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
