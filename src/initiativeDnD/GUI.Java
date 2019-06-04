package initiativeDnD;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private Mob currentMob = null;
	
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem save;
	private JMenuItem load;
	
	private JFileChooser chooser;
	
	private JButton add;
	private JButton damage;
	private JButton next;
	private JButton clear;
	
	private JTextArea display;
	
	private JScrollPane scrollPane;
	
	public GUI(){
		super("Initiative Tracker");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
/*----------------------------------
 * 			Menu Bar
 */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);
		save = new JMenuItem("Save");
		file.add(save);
		load = new JMenuItem("Load");
		file.add(load);
		
		chooser = new JFileChooser();
		
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
		scrollPane = new JScrollPane(display);
		add(scrollPane, c);
		
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
		
		save.addActionListener(handler);
		load.addActionListener(handler);
	}
	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==add){	
				if(DnD.initiativeSpot == 0){
					Mob newMob = new Mob();
					newMob.setName(JOptionPane.showInputDialog("Enter the name: ").replaceAll(":", "")); //null pointer if they click cancel...
					boolean bad = false;
					do{
						try{
							newMob.setHp(Integer.parseInt(JOptionPane.showInputDialog("Enter the max HP: ")));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "HP value must be a number.");
							bad = true;
							continue;
						}
						bad = false;
					}while(bad);
					do{
						try{
							newMob.addInitiative(Integer.parseInt(JOptionPane.showInputDialog("Enter their initiative: ")));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Initiative value must be a number.");
							bad = true;
							continue;
						}
						bad = false;
					}while(bad);
					
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
				int n = 0, d = 0;
				boolean bad = false;
				do{
					try{
						n = Integer.parseInt(JOptionPane.showInputDialog("Mob number: "));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Mob number must be a positive integer.");
						bad = true;
						continue;
					}
					if(n<1){
						JOptionPane.showMessageDialog(null, "Mob number must be a positive integer.");
						bad = true;
						continue;
					}
					bad = false;
				}while(bad);
				do{
					try{
						d = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of damage to be done: "));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Damage must be a positive integer.");
						bad = true;
						continue;
					}
					bad = false;
				}while(bad);

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
			}else if(event.getSource()==save){
				if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					try{
						PrintWriter writer = new PrintWriter(chooser.getSelectedFile());
						Mob tempMob = DnD.head;
						while(tempMob != null){
							writer.print(tempMob.getName()+":"+tempMob.getHp()+":"+tempMob.getDmg()+":"+Arrays.toString(tempMob.getInitiative())+"\n");
							tempMob=tempMob.getNextMob();
						}
						writer.close();
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}else if(event.getSource()==load){
				JOptionPane.showMessageDialog(null, "Any unsaved progress will be lost.");
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					try{
						BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
						String line;
						String lineArr[];
						Mob newHead = new Mob();
						Mob tempMob = newHead;
						while((line = reader.readLine()) != null){
							lineArr = line.split(":");
							tempMob.setName(lineArr[0]);
							tempMob.setHp(Integer.parseInt(lineArr[1]));
							tempMob.addDmg(Integer.parseInt(lineArr[2]));
							line = lineArr[3];
							line = line.replaceAll("\\[", "");
							line = line.replaceAll("\\]", "");
							line = line.replaceAll(" ", "");
							lineArr = line.split(",");
							for(int i = 0; i<lineArr.length; ++i){
								tempMob.addInitiative(Integer.parseInt(lineArr[i]));
							}
							tempMob.setNextMob(new Mob());
							tempMob = tempMob.getNextMob();
						}
						reader.close();
						DnD.head = newHead;
						display.setText(DnD.genDisplayText());
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		}
	}
}
