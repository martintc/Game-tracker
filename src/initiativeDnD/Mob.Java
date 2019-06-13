package initiativeDnD;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Mob {
	private int hp;
	private String name = null;
	private int initiative[] = {};
	private int dmgTaken = 0;
	private Mob nextMob = null;
	
	public Mob(){
		
	}
/*----------------------------------
 * 		  		Getters
 */
	
	public int getDmg(){
		return dmgTaken;
	}
	public int getHp(){
		return hp;
	}
	public boolean isAlive(){
		if(dmgTaken < hp){
			return true;
		}else{
			return false;
		}
	}
	public String getName(){
		return name;
	}
	public Mob getNextMob(){
		return nextMob;
	}
	public int[] getInitiative(){
		return initiative;
	}
/*----------------------------------
 * 		  		Setters
 */
	public void setHp(int theHp){
		hp = theHp;
	}
	public void addDmg(int dmg){
		dmgTaken += dmg;
		if(dmgTaken > hp){
			dmgTaken = hp;
		}else if(dmgTaken < 0){
			dmgTaken = 0;
		}
	}
	public void addInitiative(int num){
		initiative = Arrays.copyOf(initiative, initiative.length+1);
		initiative[initiative.length-1] = num;
	}
	public void setName(String theName){
		name = theName;
	}
	public void setNextMob(Mob theNextMob){
		nextMob = theNextMob;
	}
	public void insertSelf(Mob current, Mob last){
		boolean bad;
		for(int i = 0;; i++){
			if(this.getInitiative()[i] > current.getInitiative()[i]){
				if(last == null){
					this.nextMob = DnD.head;
					DnD.head = this;
				}else{
					this.nextMob = current;
					last.setNextMob(this);
				}
				break;
			}else if(this.getInitiative()[i] < current.getInitiative()[i]){
				if(current.nextMob == null){
					current.setNextMob(this);
				}else{
					insertSelf(current.nextMob, current);
				}
				break;
			}else{
				bad = false;
				if(i+1 == this.getInitiative().length ){
					do{
						try{
							this.addInitiative(Integer.parseInt(JOptionPane.showInputDialog(this.getName() + " must roll again for initiative:")));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Initiative value must be a number.");
							bad = true;
							continue;
						}
						bad = false;
					}while(bad);
				}
				if(i+1 == current.getInitiative().length){
					do{
						try{
							current.addInitiative(Integer.parseInt(JOptionPane.showInputDialog(current.getName() + " must roll again for initiative:")));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Initiative value must be a number.");
							bad = true;
							continue;
						}
						bad = false;
					}while(bad);
				}
			}
		}
	}
}
