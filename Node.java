public class Node {
		private Node LeftChild , RightChild , parent ;
		private boolean occupied ;
		private final int size , start , end ;   // as a pow 2 
		private int location ; 
		
		public Node (int size ,  Node  parent  ,int start , int end ) {
			this.start= start; 
			this.end = end ; 
			this.size = size ; 
			this.setOccupied(false); 
			this.parent = parent; 
			this.LeftChild = this.RightChild = null ; 
			this.location = start ; 
		}
		/**
		 * Checking if the memory can be allocated here  
		 * Allocates the memory if it is the ideal place
		 * @return null if not fond 
		 */
		public Node findFreeSpace (int size ) {
			Node newlocation = null  ;  
			if (isOccupied() ) 
				return newlocation ; // this space is  not  free 
			// this space is free 
			if (size> this.size)	
				return newlocation ; // Need more space 
			if ((this.size >=size )&&(this.size/2 <size) ) {
				// The appropriate size
				// Check if one of his children is occupied this spcec 
				if (!rec_isOccupied())
					return null ; 
				// The place here is free 
				this.LeftChild = this.RightChild = null ; 	
				this.occupied = true ; 
				newlocation = this ; 
			}else {// It is possible in less space...try Childs 
				newlocation =Childs_findFreeSpace( size ); 
			}
			return newlocation ; 
		}
		/**
		 * Recursively looking for a free place in child 
		 * @param size
		 * @return null if not fond free space 
		 */
		public Node Childs_findFreeSpace(int size ) {
			Node newlocation = null  ;   
			if (this.size <= 0 ) return newlocation ; // impossible allocate memory when not space
			if (LeftChild== null ) {
				// no Left Child
				LeftChild= new Node (this.size/2, this , this.start , this.end/2);	
			}// end LeftChild ?  null
			if (!this.LeftChild.isOccupied()) {
				newlocation = this.LeftChild.findFreeSpace(size ); 
			}
			if (newlocation==null  ) { // Impossible in-Left Child
				if (RightChild== null ) {
					// no Right  Child
					RightChild= new Node (this.size/2, this , this.start+this.size/2 , this.end);
				}//end RightChild ?  null
				//A right child failed
				if (!this.RightChild.isOccupied()) 
					newlocation = this.RightChild.findFreeSpace(size );
			}	
			return newlocation ; 
		}
		/** @return true is this node as no occupid in is Childs
		 */
		public boolean rec_isOccupied() {
			if ( isOccupied() ) 
				return false ; // !! @Stop conditions !!
			if(this.LeftChild != null ) {
				if (! LeftChild.rec_isOccupied() )
					return false ;// this node LeftChild is occupied
			}// end LeftChild 
			if (this.RightChild != null ) {
				if (this.RightChild.rec_isOccupied())
					return false ; // this node RightChild is occupied
			}// end RightChild 
			return true ; 
		}		
		public boolean isOccupied() {		return occupied; 	}
		public void setOccupied(boolean occupied) {this.occupied = occupied;}
		public int getSize() {	return size;} 
		public int getLocation() {return location;}
		public void setLocation(int location) {this.location = location;}
		public void free() 		{ this.occupied = false ; }
		public int getEnd () 	{ return this.end; }
	}