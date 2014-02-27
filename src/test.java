import java.util.ArrayList;

/**
 * Old class, not used in app just for testing purposes
 * @author Sander
 *
 */


public class test{
	public static ArrayList<Integer> test1;
	public static ArrayList<Integer> test2;
	public test(){
		
	}
	
	
	public static void main(String[] args) {
		test1=new ArrayList<Integer>();
		test2=new ArrayList<Integer>();
		test1.add(new Integer(9));
		test1.add(new Integer(1));
		test1.add(new Integer(31));
		test1.add(new Integer(7));
		test1.add(new Integer(4));
		test2.add(new Integer(1));
		test2.addAll(test1);
		System.out.println(test2);
		
		
		
		
		
		

	}

}
