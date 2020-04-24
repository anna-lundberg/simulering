package Task6;

public class EventListClass6 {
	
	private Event6 list, last; // Used to build a linked list
	
	EventListClass6(){
		list = new Event6();
    	last = new Event6();
    	list.next = last;
	}
	
	// The method insertEvent creates a new event, and searches the list of events for the 
	// right place to put the new event.
	
	public void InsertEvent(int type, double TimeOfEvent){
 	Event6 dummy, predummy;
 	Event6 newEvent = new Event6();
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
	
	public Event6 fetchEvent(){
		Event6 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}