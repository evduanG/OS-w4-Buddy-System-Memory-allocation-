import java.util.ArrayList;
import java.util.Comparator;

public class Memory {
	private Node root ; 
	private ArrayList<Process > processList  ; 
	private final int size ; 
	
	public Memory (int bytes) {
		this.root = new Node(bytes, null ,0 , bytes );
		this.processList = new ArrayList<Process>(); 
		this.size= bytes; 
		System.out.println(Runner.printArrStr[8] + bytes );
	}
	/**
	 * @param size of the {@link Process} 
	 * @return <code>false</code> is no location fond 
	 * @throws NullPointerException
	 */
	public boolean addProcess (int size ) throws  NullPointerException{
		Process p = new Process(size); 
		Node location = root.findFreeSpace(size);
		p.setLocation(location);
		if (location == null ) 
			return false ; // not ad to the list 
		processList.add(p);
		System.out.println(p.toString());
		return true  ; 	
	}
	/**
	 * @param pid id to remove 
	 * @return <code>false</code> if no {@link Process}  find in the {@link  processList}
	 */
	public boolean  removProcess(int pid ) {
		 Process pi =  findProcessByID(pid); 
		if (pi == null ) {
			// the system dont have a proccess in this id 
			return false ; 
		}else {
			// fund the Process  and del it 
			processList.remove(pi);// remove form list 
			Node n = pi .getLocation();
			n.free();
		}
		return true ;	
	}
	
	public boolean  getProcessListId() {
		if (processList.isEmpty()) { // the system has no Process running 
			System.out.println(Runner.printArrStr [7]); 
			return false ; 
		}
		String str = ""; 
		for (Process process : processList) {
			String pi = str +"p:" + process.getId() +"  ,"; 
			str = pi ; 
		}
		System.out.println(str );
		return true ; 
	}
	
	public String toString () {
		String str = ""; 
		for (Process process : processList) {
			String pi = str +'\n' +process.toString(); 
			str = pi ; 
		}// end for loop 
		return str ; 
	}
	/**@param id of a {@link Process}  to find 
	 * @return {@link Process} if find or null if not fund  */
	private Process findProcessByID(int id ) {
		for (Process process : processList) {
			if (process.getId() == id )
				return process ; 
		}// end for loop 
		return null ; 
	}
	/**@return string of the Status */
	public String printStatus() {
		String str = ""; 
		// sort the processList by Location 
		processList.sort(new Comparator<Process>() {
			@Override 
			public int compare(Process p1, Process p2) {
				return p1.getLocation().getLocation()-p2.getLocation().getLocation();
		}/* end compare */}/*end new Comparator */);
		int ef = 0 ; // ef is to check if there is a External fragmentation
		for (Process process : processList) {
			if(ef<process.getLocation().getLocation()) {
				// there is a External fragmentation 
				String temp =str+'\n' +" form  "+ ef+" to "+ (process.getLocation().getLocation()-1)+" is empty ";
				str= temp ; 
			}// end if 
			String temp = str + '\n'+ process.toString() ;
			str = temp ; 
			ef = process.getLocation().getEnd(); 
		}// end for loop `
		if (ef<this.size) {
			// there is a External fragmentation  
			String temp = str + '\n'+" form "+ ef +" to "  + (this.size-1) +" is empty " ; 
			str = temp ; 
		}
		return str ; 
	}

}
