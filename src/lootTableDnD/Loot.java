package lootTableDnD;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class Loot {
	public static void run(){
		int choice;
		lootTableDnD.GUI myInterface = null;
		
		/*Special rules are those used by Freak's friends. (Requires a loot table with 1000 items)
			D12		determines what you get
			D1000	determines what kind of item you get
			D20		decides number of coins (if platinum, then halved)
			D10		decides coin multiplier
		*/
		choice = JOptionPane.showConfirmDialog(null, "Would you like to use the special rules?");
		if(choice == 0){ //Use special rules
			myInterface = new lootTableDnD.GUI(true);
			myInterface.setSize(250,300);
		}else if(choice == 1){
			myInterface = new lootTableDnD.GUI(false);
			myInterface.setSize(100,100); //change
		}
		if(choice != 2){
			myInterface.setVisible(true);
			myInterface.setResizable(false);
		}
	}
	
	public void getItem(int itemNum){
		String[] item;
		try{
			BufferedReader reader = new BufferedReader(new FileReader("loot.csv")); // Change so they can choose.
			for(int i = 1; i<itemNum; ++i){
				reader.readLine();
			}
			item = reader.readLine().split(";");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Do the display in this method. Just a really big JOptionPane.showMessageDialog ?
		//	-Potential issue: long text does NOT wrap...
	}

}
