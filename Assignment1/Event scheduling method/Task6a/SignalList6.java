package Task6a;
// Denna klass definierar signallistan. Om man vill skicka mer information i signalen �n minimum, s� kan
// man skriva ytterligare variante av SendSignal som inneh�ller fler parametrar.

// This class defines the signal list. If one wants to send more information than here,
// one can add the extra information in the Signal class and write an extra sendSignal method 
// with more parameters. 

public class SignalList6{
	private  static Signal6 list, last;

	SignalList6(){
    	list = new Signal6();
    	last = new Signal6();
    	list.next = last;
	}

	public static void SendSignal(int type, Proc6 dest, double arrtime){
 	Signal6 dummy, predummy;
 	Signal6 newSignal = new Signal6();
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

	public static Signal6 FetchSignal(){
		Signal6 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
