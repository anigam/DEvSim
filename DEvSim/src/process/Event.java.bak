package process;

import java.util.*;

import resources.Block;

public class Event {
	private int duration_time;
	private int remaining_time;
	private ArrayList<Block> BlockList;
	private int block_num;

	public Event(int duration_time, ArrayList<BlockList> BlockList) {
		this.duration_time = duration_time;
		this.remaining_time = duration_time;
		//initialize the block list according to the input file.
		//BlockList.add(<Block>);
		this.BlockList = BlockList;
		this.block_num = this.BlockList.size();
	}

	public int DoEvent(int t){
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
	
	public int IsEmptyBlockList() {
		return this.block_num;
	}
	
	public ArrayList<Block> getResourcesNeeded(){
		return this.BlockList;
	}
}
