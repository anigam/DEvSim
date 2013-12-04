package testing;

import process.Event;
import process.Process;
import resources.Block;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class ProcessTest {	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	public void eventPrint(Event event1) {
		System.out.print( event1.toString()+"\n");
		System.out.print("event duration_time: " + event1.duration_time + "\n");
		System.out.print("event remaining_time: " + event1.remaining_time + "\n");
		
		//this part will print all the block info included in BlockList
		int i = 0;
		for (Block block : event1.BlockList) {
			System.out.print("event-block" + i + "-diskPos: " + block.diskPos + "\n");
			System.out.print("event-block" + i + "-lastAccess: " + block.lastAccess + "\n");
			System.out.print("event-block" + i + "-dirty: " + block.dirty + "\n");
			i++;
		}
		System.out.print("event block_num: " + event1.block_num()+ "\n");
		System.out.print("event-func-IsEmptyBlockList:"+ event1.isEmptyBlockList() + "\n\n");
		
	}

	public ArrayList<Block> blocklistCreation(ArrayList<Integer> poslist) {
		ArrayList<Block> BlockList = new ArrayList<Block>();
		for (Integer x : poslist) { 
			Block block1 = new Block(x, 0, false);
			BlockList.add(block1);		
		}
		return BlockList;
	}


	public void eventInitTest() {
		//generate private ArrayList<Block> BlockList to initialize event1
		ArrayList<Block> BlockList = new ArrayList<Block>();
		Block block1 = new Block(1, 0, false);
		Block block2 = new Block(2, 0, false);
		Block block3 = new Block(3, 0, false);
		BlockList.add(block1);		
		BlockList.add(block2);		
		BlockList.add(block3);		

		Event event1 = new Event(5, BlockList);

		//event1.BlockList = BlockList;
		//event1.block_num = event1.BlockList.size();
		eventPrint(event1);
		
		
		Event event2 = new Event(10, event1.getResourcesNeeded());
		eventPrint(event2);
		
		int remain_quantum = event1.doEvent(15);
		System.out.print(event1.toString() + "'s remain-quantum is: " + remain_quantum +"\n");
		this.eventPrint(event1);
		
		remain_quantum = event2.doEvent(5);
		System.out.print(event2.toString() + "'s remain-quantum is: " + remain_quantum +"\n");
		this.eventPrint(event2);
		
	}

	public ArrayList<Event> eventlistCreation() {
		return null;
		
	}
	
	public void processEventListPrint(Process proc){
		int i = 1;
		for (Event event : proc.EventList){
			System.out.print("event" + i + ": " + event.duration_time + "\n");
			i++;
		}
	}


	@Test
	public void test() {
		//eventInitTest();
		ArrayList<Integer> BlockDiskPosList1 = new ArrayList<Integer> ();
		BlockDiskPosList1.add(1);
		BlockDiskPosList1.add(2);
		BlockDiskPosList1.add(3);
		
		ArrayList<Integer> BlockDiskPosList2 = new ArrayList<Integer> ();
		BlockDiskPosList2.add(4);
		BlockDiskPosList2.add(5);
		BlockDiskPosList2.add(6);

		Event event1 = new Event(5, blocklistCreation(BlockDiskPosList1));
		Event event2 = new Event(10, blocklistCreation(BlockDiskPosList2));
		this.eventPrint(event1);
		this.eventPrint(event2);
		
		assertEquals(event1.BlockList.size(),3);
		assertEquals(event2.BlockList.size(),3);
		assertEquals(event1.duration_time,5);
		assertEquals(event2.duration_time,10);
		assertEquals(event1.remaining_time,5);
		assertEquals(event2.remaining_time,10);
		
	

		ArrayList<Event> EventList = new ArrayList<Event>();
		EventList.add(event1);
		EventList.add(event2);
		Process proc1 = new Process(0, EventList);
		System.out.print("remaining_event_num is: " + proc1.get_remaining_event_num() + "\n");
		System.out.print(proc1.toString() + "\n");
		this.processEventListPrint(proc1);
		
		// Size of event list
		assertEquals(proc1.EventList.size(), 2);
		
		Event event3 = proc1.popEvent();
		Event event4 = proc1.popEvent();
		
		// Size of event list
		assertEquals(proc1.EventList.size(), 0);
		
		assertEquals(event3.duration_time, 5);
		assertEquals(event4.duration_time, 10);
		
		System.out.print(proc1.toString() + "\n");
		this.processEventListPrint(proc1);

		ArrayList<Event> EventList1 = new ArrayList<Event>();
		Process proc2 = new Process(1, EventList1);
		System.out.print(proc2.toString() + "\n");
		this.processEventListPrint(proc2);
		
		assertEquals(proc2.EventList.size(), 0);
		proc2.pushFirst(event3);
		proc2.pushFirst(event4);
		this.processEventListPrint(proc2);
		assertEquals(proc2.EventList.size(), 2);
	}


}
