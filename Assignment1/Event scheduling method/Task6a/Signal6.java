package Task6a;
// Denna klass definerar vad som ska finnas i en signal. Det som finns h�r �r ett minimum. Man kan l�gga till mer
// om man vill att en signal ska kunna skicka mer information.

// This class defines a signal. What can be seen here is a mainimum. If one wants to add more
// information just do it here. 

class Signal6{
	public Proc6 destination;
	public double arrivalTime;
	public int signalType;
	public Signal6 next;
}