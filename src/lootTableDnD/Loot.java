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
			myInterface.setSize(200,200); //change
		}
		if(choice != 2){
			myInterface.setVisible(true);
			myInterface.setResizable(false);
		}
	}
	
	public static void getItem(String path, int reward, int hundreds, int tens, int ones, int coins, int multi){
		int lootNum;
		String line = "";
		if(hundreds == 0 && tens == 0 && ones == 0){
			lootNum = 1000;
		}else{
			lootNum = (hundreds*100)+(tens*10)+(ones);
		}

		try{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			for(int i = 1; i<lootNum; ++i){
				reader.readLine();
			}
			
			line = reader.readLine();
			reader.close();
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		JOptionPane.showMessageDialog(null, line);
		
		//Do the display in this method. Just a really big JOptionPane.showMessageDialog ?
		//	-Potential issue: long text does NOT wrap...
		
		//The file was fucked up so they're going to remake it.
		
		//You should add a menu item "about" that explains the loot table requirements and the way the fields work.
	}

}
