package process;

import java.util.*;

import resources.Block;

public class Event {
	public int duration_time;
	public int remaining_time;
	public ArrayList<Block> BlockList;
	//public int block_num;

	public Event(int duration_time, ArrayList<Block> BlockList) {
		this.duration_time = duration_time;
		this.remaining_time = duration_time;
	
		this.BlockList = BlockList;
		//this.block_num = this.BlockList.size();
	}

	public int doEvent(int t){
		if(this.remaining_time > t) {
			//execute the task for t, the whole quantum will be spent to execute this task.
			this.remaining_time -= t;
			//here, scheduler should folk Process.PushFirst to put this event back into the EventList.
			return 0;
		}
		else {
			//execute the task for remaining_time, and the whole task will finish before the end of this quantum.
			int quantum_left = t - this.remaining_time;
			this.remaining_time = 0;
			return quantum_left;
		}

	}
	
	public int isEmptyBlockList() {
		return this.BlockList.size();
	}
	
	public int block_num(){
		return this.BlockList.size();
	}
	
	public ArrayList<Block> getResourcesNeeded(){
		return this.BlockList;
	}
	
	public boolean eventFinished()
	{
		if(this.remaining_time==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
