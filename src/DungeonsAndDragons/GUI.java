package DungeonsAndDragons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private Mob currentMob = null;
	
	private JButton add;
	private JButton damage;
	private JButton next;
	private JButton clear;
	
	private JTextArea display;
	
	public GUI(){
		super("Initiative Tracker");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
/*----------------------------------
 * 			Display stuff
 */
		display = new JTextArea(30, 30);
		display.setText("<Mob info will appear here>");
		display.setEditable(false);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		add(display, c);
		
/*----------------------------------
 * 			Button stuff
 */
		add = new JButton("Add a mob");
		add.setToolTipText("Add another enemy, NPC, or player character to the encounter.");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(add, c);
		
		damage = new JButton("Do damage");
		damage.setToolTipText("Record damage that was done to an enemy, NPC, or player character.");
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(damage, c);
		
		next = new JButton("Next ->");
		next.setToolTipText("Skip to the next enemy, NPC, or player character in initiative.");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 1;
		add(next, c);
		
		clear = new JButton("Clear");
		next.setToolTipText("Delete this encounter.");
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(clear, c);
		
/*---------------------------------
 * event handlers and action listeners
 */
		EventHandler handler = new EventHandler();
		add.addActionListener(handler);
		damage.addActionListener(handler);
		next.addActionListener(handler);
		clear.addActionListener(handler);
	}
	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==add){
				if(DnD.initiativeSpot == 0){
					Mob newMob = new Mob();
					newMob.setName(JOptionPane.showInputDialog("Enter the name: "));
					newMob.setHp(Integer.parseInt(JOptionPane.showInputDialog("Enter the max HP: ")));
					newMob.addInitiative(Integer.parseInt(JOptionPane.showInputDialog("Enter their initiative: ")));
					if(DnD.head == null){
						DnD.head = newMob;
					}else{
						newMob.insertSelf(DnD.head, null);
					}
					display.setText(DnD.genDisplayText());
				}else{
					JOptionPane.showMessageDialog(null, "You may only add new participants at the top of initiative.");
				}
			}else if(event.getSource()==damage){
				int n = Integer.parseInt(JOptionPane.showInputDialog("Mob number: "));
				int d = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of damage to be done: "));
				DnD.damage(n, d, DnD.head);
				display.setText(DnD.genDisplayText());
			}else if(event.getSource()==next){
				if(DnD.head != null){
					do{
						if(currentMob == null){
							currentMob = DnD.head;
							DnD.initiativeSpot++;
						}else{
							if(currentMob.getNextMob() == null){
								currentMob = null;
								DnD.initiativeSpot = 0;
							}else{
								currentMob = currentMob.getNextMob();
								DnD.initiativeSpot++;
							}
						}
						if(currentMob == null){ //ugh...
							break;
						}
					}while(!currentMob.isAlive());
					display.setText(DnD.genDisplayText());
					if(DnD.initiativeSpot == 0){
						JOptionPane.showMessageDialog(null, "Top of initiative! You may now add new mobs to the encounter, or press Next again to keep playing.");
					}else{
						JOptionPane.showMessageDialog(null, "It's " + currentMob.getName() + "'s turn!");
					}
				}
			}else if(event.getSource()==clear){
				if(JOptionPane.showConfirmDialog(null, "Are you SURE you want to clear?") == 0){
					DnD.head = null;
					DnD.initiativeSpot = 0;
					display.setText(DnD.genDisplayText());
				}
			}
		}
	}
}
