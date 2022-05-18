
public class Runner {
	/** string to ptint  
	 *  menu_str =0 ,Power of 2 = 1  , choice = 2  , memory 4 pro = 3 ,
	 *   Goodbye = 4 ,process to delete =5 , not free space = 6  ,memory is empty = 7   , System memory = 8 
	 */
	public final static String [] printArrStr = { "1. Enter process\r\n"+ "2. Exit process\r\n"+ "3. Print status\r\n"+ "4. Exit\r\n",
			"Please enter the memory size ,need to be power of 2", "Enter your choice:" ,"Enter the amount of memory the process needs" ,"Goodbye",
			"Select a process to delete" ,"There is not enough free space", "The memory is empty", "System memory is :"
	};

	public static void main(String[] args) {
		UserInterface  ui= new UserInterface(); 
		Memory sytem_Memory = new Memory( ui.valid_input(Integer.MAX_VALUE, true ,printArrStr[1])); 
		menu_enum run = menu_enum.Exit ; 
		do {
			switch (run) {
				case Enter_process:
					if (!sytem_Memory.addProcess(ui.valid_input(Integer.MAX_VALUE, false , printArrStr[3]))) 
						System.out.println(printArrStr[6]);
					break ; 
				case Exit_process:
					if (sytem_Memory.getProcessListId())
						sytem_Memory.removProcess(ui.valid_input(Integer.MAX_VALUE, false , printArrStr[5]));
					break ; 
				case Print_status: 
					System.out.println(sytem_Memory.printStatus());
					break ;
				case Exit: break ; 
			default:	
				break;	
			}	
			run = ui.user_menu_selection() ;
		}while (run !=menu_enum.Exit ); 
		System.out.println( printArrStr[4]);
		ui.sc_close();
	}
}
