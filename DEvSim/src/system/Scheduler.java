package system;

import java.util.Queue;
import java.util.Vector;

import process.Event;
import process.Process;

public class Scheduler {

	private Vector<Process> ProcessList;
	
	public void addProcess(Vector<Process> ProcessList)
	{
		this.ProcessList=ProcessList;
	}
	
	public Event getCurrentEvent()
	{
		Event e=null;
		Process curProcess=ProcessList.elementAt(0);
		e=curProcess.popEvent();
		return e;
	}
	
	public boolean contextSwitch()
	{
		 Process curProcess = ProcessList.remove(0);
		 if (curProcess.get_remaining_event_num()>0)
		 {
			 ProcessList.add(curProcess);
			 return true;
		 }
		 if(ProcessList.isEmpty())
			 return false;
		 return false;
	}
}
