package Task2;
import java.util.*;
import java.io.*;


public class MainSimulation2 extends GlobalSimulation2{
 
    public static void main(String[] args) throws IOException {
    	Event2 actEvent;
    	State2 actState = new State2(); // The state that shoud be used
    	// Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL, 0);   
        insertEvent(MEASURE, 20.1);


    
        // The main simulation loop
    	while ( time < 1000) { //&& actState.noMeasurements==1000){
    		actEvent = eventList.fetchEvent();
    		time = actEvent.eventTime;
    		actState.treatEvent(actEvent);
    	}
    	 System.out.println("Mean number of jobs in buffer: " + 1.0 * actState.accumulated / actState.noMeasurements);

    	// Printing the result of the simulation, in this case a mean value
		System.out.println("Number of measurements: " + actState.noMeasurements);
		System.out.println("Number accumulated: " + actState.accumulated);
		System.out.println("Number of A: " + actState.accumulatedA);
		System.out.println("Number of B: " + actState.accumulatedB);


		

    }
}