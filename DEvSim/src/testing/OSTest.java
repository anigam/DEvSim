package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import process.Event;
import process.Process;
import resources.Block;
import system.*;

public class OSTest extends TestCase{
	
	OS myOS;
	Vector<Process> ProcessList;
	Event e1,e2,e3,e4;
	ArrayList<Event> elist1,elist2;
	Process p1,p2;
	Scheduler s;
	Block b;
	ArrayList<Block> bList;
	
	public void setUp() throws Exception {
		super.setUp();
		
		b=new Block(1,3,false);
		bList=new ArrayList<Block>();
		bList.add(b);
		
		e1=new Event(1,bList);      //generic
		e2=new Event(1,bList);      //generic
		e3=new Event(6,bList);      //generic
		e4=new Event(1,bList);      //generic
		
		elist1=new ArrayList<Event>();
		elist1.add(e1);
		elist1.add(e3);
		elist2=new ArrayList<Event>();
		elist2.add(e2);
		elist2.add(e4);
		
		p1=new Process(1, elist1);
		p2=new Process(2, elist2);
		
		ProcessList=new Vector<Process>();
		ProcessList.add(p1);
		ProcessList.add(p2);
		
		myOS=new OS(ProcessList);		
	}

	@After
	public void tearDown() throws Exception {

		myOS=null;
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
	public void testrunOS()
	{
		myOS.runOS();
	}

}
