import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static Mob head = null;
	public static int initiativeSpot = 0;
	
	public static void main(String args[]){
		GUI myInterface = new GUI();
		myInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myInterface.setSize(370,600);
		myInterface.setVisible(true);
		myInterface.setResizable(false);
	}
	
	public static String genDisplayText(){
		String displayText = "";
		Mob tempMob = head;
		for(int i = 1; tempMob != null; i++){
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
			damage(num, dmg, thisMob.getNextMob());
		}
	}
}
