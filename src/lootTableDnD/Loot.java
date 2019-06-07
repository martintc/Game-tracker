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
		if(choice == 0){ 		//Special rules
			myInterface = new lootTableDnD.GUI(true);
		}else if(choice == 1){	//Generic rules
			myInterface = new lootTableDnD.GUI(false);
		}
		if(choice != 2){
			myInterface.setVisible(true);
			myInterface.setResizable(false);
		}
	}
	
	public static void getSpecialItem(String path, int reward, int hundreds, int tens, int ones, int coins, int multi){
		int lootNum;
		String line = "", type = "";
		boolean item = false;
		double multi2 = 1;
		
		if(reward == 1){		//Copper
			type = "Copper";
		}else if(reward == 2){	//Silver
			type = "Silver";
		}else if(reward == 3){	//Gold
			type = "Gold";
		}else if(reward == 4){	//Platinum
			type = "Platinum";
			multi2 = .5;
		}else if(reward == 5){	//Item + Copper
			type = "Copper";
			item = true;
		}else if(reward == 6){	//Item + Silver
			type = "Silver";
			item = true;
		}else if(reward == 7){	//Item + Gold
			type = "Gold";
			item = true;
		}else if(reward == 8){	//Item + Platinum
			type = "Platinum";
			multi2 = .5;
			item = true;
		}else if(reward == 9){	//Item
			item = true;
			coins = 0;
		}else if(reward == 10){	//Item
			item = true;
			coins = 0;
		}else if(reward == 11){	//Nothing
			JOptionPane.showMessageDialog(null, "Empty");
			return;
		}else if(reward == 12){	//Nothing
			JOptionPane.showMessageDialog(null, "Empty");
			return;
		}
		
		if(item){
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
			
			Popup popup = new Popup(((int)(coins*multi*multi2))+" "+type, line);
			popup.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Coins: "+((int)(coins*multi*multi2))+" "+type);
		}
	}
	
	public static void getGenericItem(String path, int lootNum){
		String line = "";
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
		if(line == null){
			JOptionPane.showMessageDialog(null, "There are fewer than "+lootNum+" items in the table.");
		}else{
			Popup popup = new Popup("0", line);
			popup.setVisible(true);
		}
	}
}
