package resources;

public class Block {
	
	int diskPos;
	int lastAccess;
	boolean dirty;
	
	public Block(int diskPos, int lastAcces, boolean dirty)
	{
		this.diskPos=diskPos;
		this.lastAccess=lastAcces;
		this.dirty=dirty;
	}

}
