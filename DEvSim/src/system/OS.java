package system;

import java.util.Vector;
import system.Scheduler;
import process.Event;
import resources.*;
import process.Process;
import system.Queue;

public class OS {

	Queue<Event> eventQueue;
	private static ResourceManager manager;
	Scheduler scheduler; 

	public static int TIME;
	int quantum;
	int context_switch_cost;

	//Initialize the OS
	public OS(Vector<Process> ProcessList)
	{
		TIME=0;
		quantum=5;
		context_switch_cost=1;
		
		manager = new ResourceManager(0, 0, 0);
		scheduler=new Scheduler(ProcessList);
		eventQueue = new Queue<Event>();
		
	}
	
	/*public void startOS(Vector<Process> ProcessList)
	{
		scheduler=new Scheduler(ProcessList);
	}*/
	
	public void runOS()
	{
		//add first event to queue
		Event curEvent=scheduler.getCurrentEvent();
		eventQueue.add(curEvent);
				
		while(!eventQueue.isEmpty())
		{
			int remTime=quantum;
			while(remTime>0)
			{
				System.out.println("Number of events in the queue: "+eventQueue.size());
				Event e=eventQueue.removeFirst();
				int page_fault_count = OS.manager.needs(e.getResourcesNeeded());
				System.out.println("Page fault is: "+ page_fault_count);
				remTime=e.doEvent(remTime);
				System.out.println("remainder time is:"+remTime);
				if(remTime>0)
				{
					System.out.println("Fetching another event from the current process");
					Event nextEvent=scheduler.getCurrentEvent();
					if(nextEvent!=null)
					{
						eventQueue.add(nextEvent);
					}
					else
					{
						break;
					}
				}
				else
				{
					boolean eventcheck=e.eventFinished();
					System.out.println("Event finished: " +  eventcheck);
					if(eventcheck==false)
					{
						System.out.println("The event is not finished...adding it back");						
						scheduler.addEventback(e);
					}
				}
			}
			if(remTime>=0)  //> because might be the event was null 
			{
				OS.TIME += quantum;
				System.out.println("The time now is:"+ OS.TIME);
			}
			
			boolean switchcost=true;
			if(scheduler.getNumberactiveprocess()==1)  //dont add switch cost if its just last one process
			{
				switchcost=false;
			}
			boolean check=scheduler.contextSwitch();
			if(check)
			{
				if(switchcost)
				{
					OS.TIME += context_switch_cost;
				}
				curEvent=scheduler.getCurrentEvent();
				eventQueue.add(curEvent);
			}
		}

		System.out.println("Total time is:"+ OS.TIME);
	}
}
