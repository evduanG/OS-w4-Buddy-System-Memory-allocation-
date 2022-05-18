import java.util.Scanner;

public class UserInterface {
	public Scanner sc; 
	public UserInterface() {
	 sc = new Scanner(System.in);
	}
	public  menu_enum user_menu_selection () {
		switch (valid_input(4 ,false,Runner.printArrStr[0] +Runner.printArrStr[2])) {
			case 1 : return menu_enum.Enter_process; 
			case 2 : return menu_enum.Exit_process; 
			case 3 : return menu_enum.Print_status ;
			default: return menu_enum.Exit; 
		}
	}
	public  int valid_input(int maxInput , boolean is_pow_of_2 , final String str  ) {
		int ans =-1 ; 
		while((ans <= 0 )&& (ans<=maxInput)) {
			if (is_pow_of_2) {
				System.out.println(str );
				 if (  ! isPowerOfTwo(ans = sc.nextInt() ) ) 	
					 ans = -1 ;  // not a pow of 2 		
			}else {
				System.out.println(str );
				ans= sc.nextInt(); 
				}/*end if  */  }/*end while*/ 
		return ans ; 
	}
	  /* Function to check if x is power of 2*/
	public   boolean isPowerOfTwo(int n) {
        return (int)(Math.ceil((Math.log(n) / Math.log(2)))) == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }
	public  void  sc_close () {sc.close();}
}
