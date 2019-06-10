package dice;

public class Dice {
	public static void run(){
		dice.GUI myInterface = new dice.GUI();
		myInterface.pack();
		myInterface.setVisible(true);
		myInterface.setResizable(true);
	}
}

/*

Okay, so what is even the vision here?
--------------------------------------
* Individual combo objects that store the info. (2d6+1 is multi=2, d=6, c=1)

* GUI should have a "add", "remove", and "load"
	- "load"	loads a file containing all saved combos. Needs to be able to create new files, OR separate button?
	
	- "add"		adds a combo.
	- "remove"	removes a combo.
		* both should require you to load a DB first
		* Probably not a JMenu for these two?

* Need to be able to name them/rename them.
* SUGGESTED that they include a header? Like "Goblin: Punch"
	- Display them alphabetically

 */
