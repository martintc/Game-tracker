package dice;

public class Combo {
	private String name;
	private int multiplier;
	private int die;
	private int constant;
	
	public Combo(String n, int m, int d, int c){
		name = n;
		multiplier = m;
		die = d;
		constant = c;
	}
/*----------------------------------
 * 		  		Getters
 */
	public int getRoll(){
		return (multiplier*die) + constant;
	}
	public String getName(){
		return name;
	}
	
/*----------------------------------
 * 		  		Setters
 */

	
}
