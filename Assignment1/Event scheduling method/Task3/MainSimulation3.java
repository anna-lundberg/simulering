package Task3;
import java.util.*;
import java.io.*;


public class MainSimulation3 extends GlobalSimulation3{
 
    public static void main(String[] args) throws IOException {
    	Event3 actEvent;
    	State3 actState = new State3(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL, 0);   
        insertEvent(MEASURE, 5);
    
        // The main simulation loop
    	while (time < 5500) { 
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	
    	System.out.println("Mean number of customers: " +1.0 * actState.accumulated / actState.noMeasurements);
    	// Printing the result of the simulation, in this case a mean value
		System.out.println("Totalt i systemet: " + 1.0*actState.accumulated);
		System.out.println("Totalt arrivals: " + 1.0*actState.noArrivals);
		System.out.println("Number of measurements: " +1.0*actState.noMeasurements);
		System.out.println("Mean in line 1: " + 1.0 * actState.numberInQueue1 / actState.noMeasurements);
		System.out.println("Mean in line 2: " + 1.0 * actState.numberInQueue2 / actState.noMeasurements);
		System.out.println("Accumulated: " + 1.0*actState.accumulated);
		System.out.println("Mean time in system: " + 1.0*actState.accumulatedTime/actState.noMeasurements);

    }
}