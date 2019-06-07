package lootTableDnD;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class GUI extends JDialog{
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem selectTable;
	private JMenuItem about;
	
	private JButton randomRolls;
	private JButton myRolls;
	
	private JLabel[] specialLabels;
	private JLabel genericLabel;
	
	private JTextField[] fields;
	
	private JFileChooser chooser;
	
	private boolean type;
	private String path = "";
	public GUI(boolean t){
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);
		selectTable = new JMenuItem("Select Loot Table");
		file.add(selectTable);
		about = new JMenuItem("About");
		menuBar.add(about);
		
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("CSV Files" , "csv"));
		
		type = t;
		if(t){		//Special GUI -------------------------------------------------
			String labelText[] = {	"<html><pre>Reward------d12:  </pre></html>",
						"<html><pre>Hundreds----d10:  </pre></html>",
						"<html><pre>Tens--------d10:  </pre></html>",
						"<html><pre>Ones--------d10:  </pre></html>",
						"<html><pre>Coins-------d20:  </pre></html>",
						"<html><pre>Multiplier--d10:  </pre></html>"};
			specialLabels = new JLabel[6];
			for(int i = 0; i<specialLabels.length; ++i){
				specialLabels[i] = new JLabel(labelText[i]);
				c.gridx = 0;
				c.gridy = i;
				c.insets = new Insets(2,10,2,0);
				add(specialLabels[i], c);
			}
			
			fields = new JTextField[6];
			for(int i = 0; i<fields.length; ++i){
				fields[i] = new JTextField(5);
				c.gridx = 1;
				c.gridy = i;
				c.insets = new Insets(2,0,2,10);
				add(fields[i], c);
			}
			
			myRolls = new JButton("Get Loot");
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 2;
			c.insets = new Insets(2,10,2,10);
			add(myRolls, c);
			randomRolls = new JButton("Random Loot");
			c.gridx = 0;
			c.gridy = 7;
			c.gridwidth = 2;
			c.insets = new Insets(2,10,8,10);
			add(randomRolls, c);
		}else{		//Generic GUI -------------------------------------------------
			genericLabel = new JLabel("Item number: ");
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(2,10,2,0);
			add(genericLabel, c);
			
			fields = new JTextField[1];
			fields[0] = new JTextField(5);
			c.gridx = 1;
			c.gridy = 0;
			c.insets = new Insets(2,0,2,10);
			add(fields[0], c);
			
			myRolls = new JButton("Get Loot");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.insets = new Insets(2,10,2,10);
			add(myRolls, c);
			randomRolls = new JButton("Random Loot");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2;
			c.insets = new Insets(2,10,8,10);
			add(randomRolls, c);
		}
		pack();
		
/*---------------------------------
 * event handlers and action listeners
 */
		getRootPane().setDefaultButton(myRolls);
		
		EventHandler handler = new EventHandler();
		randomRolls.addActionListener(handler);
		myRolls.addActionListener(handler);
		selectTable.addActionListener(handler);
		about.addActionListener(handler);
	}
	private class EventHandler implements ActionListener{	
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==myRolls){
				if(path == ""){
					JOptionPane.showMessageDialog(null, "First select a loot table.");
				}else{
					if(type){	//Special rules
						boolean check = true;
						try{	//Check that entries are valid.
							if(	(Integer.parseInt(fields[0].getText()) > 12 || Integer.parseInt(fields[0].getText()) < 1) || 
								(Integer.parseInt(fields[1].getText()) > 9 || Integer.parseInt(fields[1].getText()) < 0)  ||
								(Integer.parseInt(fields[2].getText()) > 9 || Integer.parseInt(fields[2].getText()) < 0)  ||
								(Integer.parseInt(fields[3].getText()) > 9 || Integer.parseInt(fields[3].getText()) < 0)  ||
								(Integer.parseInt(fields[4].getText()) > 20 || Integer.parseInt(fields[4].getText()) < 1) ||
								(Integer.parseInt(fields[5].getText()) > 10 || Integer.parseInt(fields[5].getText()) < 1) ){
								JOptionPane.showMessageDialog(null, "Invalid entry. Make sure all values are within the bounds.");
								check = false;
							}
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Invalid entry: Make sure all values are integers.");
							check = false;
						}
						
						if(check){
							Loot.getSpecialItem(path,
										Integer.parseInt(fields[0].getText()),
										Integer.parseInt(fields[1].getText()),
										Integer.parseInt(fields[2].getText()),
										Integer.parseInt(fields[3].getText()),
										Integer.parseInt(fields[4].getText()),
										Integer.parseInt(fields[5].getText()));
						}
					}else{		//Generic rules
						try{
							Loot.getGenericItem(path, Integer.parseInt(fields[0].getText()));
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Invalid entry: Make sure all values are integers.");
						}
					}
				}
			}else if(event.getSource()==randomRolls){
				if(path == ""){
					JOptionPane.showMessageDialog(null, "First select a loot table.");
				}else{
					Random rand = new Random();
					if(type){	//Special rules
						fields[0].setText(Integer.toString(rand.nextInt(12)+1));
						fields[1].setText(Integer.toString(rand.nextInt(10)));
						fields[2].setText(Integer.toString(rand.nextInt(10)));
						fields[3].setText(Integer.toString(rand.nextInt(10)));
						fields[4].setText(Integer.toString(rand.nextInt(20)+1));
						fields[5].setText(Integer.toString(rand.nextInt(10)+1));
						Loot.getSpecialItem(path,
									Integer.parseInt(fields[0].getText()),
									Integer.parseInt(fields[1].getText()),
									Integer.parseInt(fields[2].getText()),
									Integer.parseInt(fields[3].getText()),
									Integer.parseInt(fields[4].getText()),
									Integer.parseInt(fields[5].getText()));
					}else{		//Generic rules
						int lineCount = 0;
						try{
							BufferedReader reader = new BufferedReader(new FileReader(path));
							while((reader.readLine()) != null){
								++lineCount;
							}
							reader.close();
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, e);
						}
						fields[0].setText(Integer.toString(rand.nextInt(lineCount)+1));
						Loot.getGenericItem(path, Integer.parseInt(fields[0].getText()));
					}
				}
			}else if(event.getSource()==selectTable){
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					path = chooser.getSelectedFile().getAbsolutePath();
				}
			}else if(event.getSource()==about){
				JOptionPane.showMessageDialog(null,  "This program automatically calculates rewards and grabs loot from a standardized loot table.\n\n"
									+"Loot table specificatinos:\n"
									+"* Must be .csv format.\n"
									+"* Must be formatted as: \"Name,Description,Value,Rarity,Weight,Category,Properties,Requirements,Author\"\n"
									+"* Loot tables may leave out any of those categories, but must contain the same or more commas.\n\n"
									+"Special rules:\n"
									+"* Loot tables must have exactly 1000 items.\n"
									+"* Uses many dice to determine loot (Documented in both the \"run\" and \"getSpecialItem\" methods of the Loot.java class)\n");
			}
		}
	}
}
