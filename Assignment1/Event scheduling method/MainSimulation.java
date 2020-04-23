import java.util.*;
import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	State actState = new State(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL, 0);   
        insertEvent(MEASURE, 5);
    
        // The main simulation loop
    	while (time < 6000) { 
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	System.out.println(1.0 * actState.accumulated / actState.noMeasurements);
    	// Printing the result of the simulation, in this case a mean value
		System.out.println("Totalt i systemet: " + actState.accumulated);
		System.out.println("Totalt arrivals: " + actState.noArrivals);
		System.out.println("Number of measurements: " +actState.noMeasurements);
		System.out.println("Mean in line 1: " + 1.0 * actState.accumulated1 / actState.noMeasurements);
		System.out.println("Mean in line 2: " + 1.0 * actState.accumulated2 / actState.noMeasurements);
		System.out.println("Number rejected: " + actState.noRejected);
		System.out.println("Accumulated: " + actState.accumulated);
		System.out.println("Probability of being rejected: " + 1.0*actState.noRejected / (actState.noArrivals));

    }
}