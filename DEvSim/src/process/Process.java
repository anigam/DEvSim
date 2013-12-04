package process;
import java.util.*;

import process.Event;

public class Process {
	private int pid;
	public ArrayList<Event> EventList;
	//private int remaining_event_num;
	
	public Process(Integer pid, ArrayList<Event> EventList) {
		this.pid = pid;
		this.EventList = EventList;
		//this.remaining_event_num = this.EventList.size();
	}

	//get the num of the remaining events to judge whether the whole process has finished or not.
	public int get_remaining_event_num(){
		return this.EventList.size();
	}

	public Event popEvent() {
		return this.EventList.remove(0);
	}

	//at the end of one quantum, if one event E still does not finish, add it into the first place of the EventList, so that next time when this Process obtain the right to use CPU, it can continue to execute E.
	public void pushFirst(Event event){
		this.EventList.add(0, event);
	}
}
