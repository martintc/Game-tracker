package initiativeDnD;

import javax.swing.JOptionPane;

public class DnD {
	public static Mob head = null;
	public static int initiativeSpot = 0;
	
	public static void run(){
		head = null;
		initiativeDnD.GUI myInterface = new initiativeDnD.GUI();
		myInterface.setSize(370,630);
		myInterface.setVisible(true);
		myInterface.setResizable(true);
	}
	
	public static String genDisplayText(){
		String displayText = "";
		Mob tempMob = head;
		for(int i = 1; tempMob != null; i++){
			if(tempMob.getName() == null){
				break;
			}
			displayText += i + ": " + tempMob.getName() + "\t" + "( " + (tempMob.getHp()-tempMob.getDmg()) + " / " + tempMob.getHp() + " )";
			if(i == initiativeSpot){
				displayText += "***";
			}
			displayText += "\n";
			tempMob = tempMob.getNextMob();
		}
		return displayText;
	}
	
	public static void damage(int num, int dmg, Mob thisMob){
		num--;
		if(num == 0){
			thisMob.addDmg(dmg);
			if(! thisMob.isAlive()){
				JOptionPane.showMessageDialog(null, thisMob.getName() + " is dead!");
			}else if(thisMob.getDmg() > thisMob.getHp()/2){
				JOptionPane.showMessageDialog(null, thisMob.getName() + " is looking bloody! (Past half HP)");
			}
		}else{
			if(thisMob == null){
				JOptionPane.showMessageDialog(null, "That was an invalid mob number.");
			}else if(thisMob.getNextMob() == null){
				JOptionPane.showMessageDialog(null, "That was an invalid mob number.");
			}else{
				damage(num, dmg, thisMob.getNextMob());
			}
		}
	}
}
