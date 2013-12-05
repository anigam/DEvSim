package testing;
import java.util.ArrayList;
import java.util.Vector;

import process.Event;
import process.Process;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import resources.Block;
import system.Scheduler;;

public class SchedulerTest extends TestCase{

	private Vector<Process> ProcessList;
	Event e1,e2;
	ArrayList<Event> elist1,elist2;
	Process p1,p2;
	Scheduler s;
	Block b;
	ArrayList<Block> bList;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		b=new Block(1,3,false);
	
		bList=new ArrayList<Block>();
		bList.add(b);
		
		e1=new Event(7,bList);      //generic
		e2=new Event(3,bList);      //generic
		
		elist1=new ArrayList<Event>();
		elist1.add(e1);
		elist2=new ArrayList<Event>();
		elist1.add(e2);
		
		p1=new Process(1, elist1);
		p2=new Process(2, elist2);
		
		ProcessList=new Vector<Process>();
		ProcessList.add(p1);
		ProcessList.add(p2);
		
		s= new Scheduler(ProcessList);
		
	}

	@After
	public void tearDown() throws Exception {
		ProcessList=null;
		e1=null;
		e2=null;
		elist1=null;
		elist2=null;
		p1=null;
		p2=null;
		s=null;
		
		b=null;
		bList=null;
	}
	
	@Test
	public void testgetCurrentEvent()
	{
		Event e=s.getCurrentEvent();
		assertEquals(e, e1);
	}
	
	@Test
	public void testcontextSwitch()
	{	
		boolean val=s.contextSwitch();
		assertEquals(val, true);
	}
	
	@Test
	public void testaddEventback()
	{
		Event tmpevent =new Event(5,bList);
		s.addEventback(tmpevent);
		Event newevent=s.getCurrentEvent();
		assertEquals(newevent, tmpevent);
	}
	
	@Test
	public void testgetNumberactiveprocess()
	{
		int cur= s.getNumberactiveprocess();
		assertEquals(cur, 2);
	}
	
}
