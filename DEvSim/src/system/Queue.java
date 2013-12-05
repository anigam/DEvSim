package system;
import java.util.LinkedList;
import process.*;

public class Queue<Event> {

	protected LinkedList<Event> list;

    public Queue() {
        list = new LinkedList<Event>();
    }

    public void add( Event element) {
        list.add( element);
    }

    public Event removeFirst() {
        return list.removeFirst();
    }   
    
    public int size()
    {
    	return list.size();
    }
    
    public boolean isEmpty()
    {
    	return list.isEmpty();
    }
}
