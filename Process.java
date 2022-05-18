
public class Process {
	private int size ; 
	private int id ; 
	private Node location ; 
	/** Assign a new id to each  one 
	 * of the processes*/
	private  static int counter = 1 ; 
	
	public Process(int size ) {
		this.setSize(size) ; 
		this.setId(getCounter()) ; 
	}
	/** inc counter and return it   */ 
	private static 	int 	getCounter() 	{	return counter++;	}
	
	public			Node 	getLocation() 	{	return location;	}
	
	public			void   	setLocation(Node location) 		{this.location = location;}
	
	public 			int 	getId()			{	return id;}
	
	public 			void setId(int id) {	this.id = id;}

	public int getSize() {		return size;	}

	public void setSize(int size) {		this.size = size;}

	@Override
	public String toString() {
		return "p: " + getId() + " , size "+ getSize() +  " , Location :" + getLocation().getLocation() + " to "+ (getLocation().getEnd()-1)+ getInternalFragmentation();	
	}
	// return a string of the internal fragmentation
	private String getInternalFragmentation() {
		if (this.location.getSize()>this.size) {
			return new String('\n'+ "internal fragmentation :" + (this.location.getSize()-this.size));
		}
		return "";
	}
}
