package Task2;

public class EventListClass2 {
	
	private Event2 list, last; // Used to build a linked list
	
	EventListClass2(){
		list = new Event2();
    	last = new Event2();
    	list.next = last;
	}
	
	// The method insertEvent creates a new event, and searches the list of events for the 
	// right place to put the new event.
	
	public void InsertEvent(int type, double TimeOfEvent){
 	Event2 dummy, predummy;
 	Event2 newEvent = new Event2();
 	newEvent.eventType = type;
 	newEvent.eventTime = TimeOfEvent;
 	predummy = list;
 	dummy = list.next;
 	while ((dummy.eventTime < newEvent.eventTime) & (dummy != last)){
 		predummy = dummy;
 		dummy = dummy.next;
 	}
 	predummy.next = newEvent;
 	newEvent.next = dummy;
 }
	
	
	
	// The following method removes and returns the first event in the list. That is the
	// event with the smallest time stamp, i.e. the next thing that shall take place.
	
	public Event2 fetchEvent(){
		Event2 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}