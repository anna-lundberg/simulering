package Task3;

public class EventListClass3 {
	
	private Event3 list, last; // Used to build a linked list
	
	EventListClass3(){
		list = new Event3();
    	last = new Event3();
    	list.next = last;
	}
	
	// The method insertEvent creates a new event, and searches the list of events for the 
	// right place to put the new event.
	
	public void InsertEvent(int type, double TimeOfEvent){
 	Event3 dummy, predummy;
 	Event3 newEvent = new Event3();
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
	
	public Event3 fetchEvent(){
		Event3 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}