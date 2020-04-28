package Task4;
import java.util.*;
import java.io.*;


public class MainSimulation4 extends GlobalSimulation4{
 
    public static void main(String[] args) throws IOException {
    	Event4 actEvent;
    	State4 actState = new State4(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL, 0);   
        insertEvent(MEASURE, 5);
    
        // The main simulation loop
    	while (time < 16005) { 
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	System.out.println("Mean number of customers: " +1.0 * actState.accumulated / actState.noMeasurements);
    	// Printing the result of the simulation, in this case a mean value
		System.out.println("Totalt i systemet: " + 1.0*actState.accumulated);
		System.out.println("Totalt arrivals: " + actState.noArrivals);
		System.out.println("Number of measurements: " + actState.noMeasurements);
		System.out.println("Accumulated: " + 1.0*actState.accumulated);
		System.out.println("Number served: " + actState.numberServed);

		System.out.println("Rejected: " + 1.0*actState.noRejected);
		actState.W.close();


    }
}