package system;

import java.util.*;

import system.Scheduler;
import process.Event;
import resources.*;
import process.Process;


public class OS {

	private static Queue<Event> eventQueue;
	Scheduler scheduler; 

	public static int TIME;
	int quantum;
	int context_switch_cost;

	//Initialize the OS
	public OS()
	{
		TIME=0;
		quantum=5;
		context_switch_cost=1;
	}
	
	public void startOS(Vector<Process> ProcessList)
	{
		//Add Processes to os
		scheduler.addProcess(ProcessList);
	}
	
	public void runOS()
	{
		//add first event to queue
		Event curEvent=scheduler.getCurrentEvent();
		eventQueue.add(curEvent);
			
		while(!eventQueue.isEmpty())
		{
			int remTime=quantum;
			ResourceManager manager;
			while(remTime>0)
			{
				Event e=eventQueue.remove();
				int page_fault_count = manager.needs(e.getResourcesNeeded());
				remTime=e.DoEvent(remTime);
				if(remTime>0)
				{
					Event nextEvent=scheduler.getCurrentEvent();
					eventQueue.add(nextEvent);
				}
			}
			boolean check=scheduler.contextSwitch();
			if(check)
				OS.TIME += context_switch_cost;
			OS.TIME += quantum;			
		}
		
 	}	
}
