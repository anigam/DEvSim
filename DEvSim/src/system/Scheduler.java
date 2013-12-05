package system;

import java.util.Queue;
import java.util.Vector;

import process.Event;
import process.Process;

public class Scheduler {

	private Vector<Process> ProcessList;

	public Scheduler(Vector<Process> ProcessList)
	{
		this.ProcessList=ProcessList;
	}
	
	public Event getCurrentEvent()
	{
		Event e=null;
		Process curProcess=ProcessList.elementAt(0);
		System.out.println("The current procees is P:"+ curProcess.getpid());
		System.out.println("Number of events in process:" + curProcess.get_remaining_event_num());
		e=curProcess.popEvent();
		return e;
	}
	
	public void addEventback(Event e)
	{
		Process curProcess=ProcessList.elementAt(0);
		System.out.println("The current procees is P:"+ curProcess.getpid());
		curProcess.pushFirst(e);
	}
	
	public int getNumberactiveprocess()
	{
		return ProcessList.size();
	}
	
	public boolean contextSwitch()
	{
		 Process curProcess = ProcessList.remove(0);
		 System.out.println("before context The current procees is P:"+ curProcess.getpid());
		 System.out.println("For the process witching out the number of remaining events are: "+ curProcess.get_remaining_event_num());
		 
		 if (curProcess.get_remaining_event_num()>0)
		 {
			 ProcessList.add(curProcess);
			 return true;
		 }
		 if(!ProcessList.isEmpty())
			 return true;
		 
		 return false;
	}
}
