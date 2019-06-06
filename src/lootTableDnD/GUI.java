package lootTableDnD;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem selectTable;
	
	private JButton randomRolls;
	private JButton myRolls;
	
	private JLabel[] specialLabels;
	private JLabel genericLabel;
	
	private JTextField[] fields;
	
	private JFileChooser chooser;
	
	boolean type;
	
	String path = "";
	public GUI(boolean t){
		super("Loot Table Tool");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);
		selectTable = new JMenuItem("Select Loot Table");
		file.add(selectTable);
		
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("CSV Files" , "csv"));
		
		type = t;
		if(t){		//Special GUI -------------------------------------------------
			String labelText[] = {	"<html><pre>Reward------d12:  </pre></html>",
									"<html><pre>Hundreds----d10:  </pre></html>", //
									"<html><pre>Tens--------d10:  </pre></html>", // Might consider putting blank labels to separate these 3 from the others
									"<html><pre>Ones--------d10:  </pre></html>", //
									"<html><pre>Coins-------d20:  </pre></html>",
									"<html><pre>Multiplier--d10:  </pre></html>"};
			specialLabels = new JLabel[6];
			for(int i = 0; i<specialLabels.length; ++i){
				specialLabels[i] = new JLabel(labelText[i]);
				c.gridx = 0;
				c.gridy = i;
				add(specialLabels[i], c);
			}
			fields = new JTextField[6];
			for(int i = 0; i<fields.length; ++i){
				fields[i] = new JTextField(5);
				c.gridx = 1;
				c.gridy = i;
				add(fields[i], c);
			}
			myRolls = new JButton("Get Loot");
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 2;
			add(myRolls, c);
			randomRolls = new JButton("Random Loot");
			c.gridx = 0;
			c.gridy = 7;
			c.gridwidth = 2;
			add(randomRolls, c);
		}else{		//Generic GUI -------------------------------------------------
			genericLabel = new JLabel("Item number: ");
			c.gridx = 0;
			c.gridy = 0;
			add(genericLabel, c);
			
			//insert a field here.
			
			myRolls = new JButton("Get Loot");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			add(myRolls, c);
			randomRolls = new JButton("Random Loot");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2;
			add(randomRolls, c);
		}
		
		
/*---------------------------------
 * event handlers and action listeners
 */
		getRootPane().setDefaultButton(myRolls);
		
		EventHandler handler = new EventHandler();
		randomRolls.addActionListener(handler);
		myRolls.addActionListener(handler);
		selectTable.addActionListener(handler);
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
								(Integer.parseInt(fields[1].getText()) > 10 || Integer.parseInt(fields[1].getText()) < 1) ||
								(Integer.parseInt(fields[2].getText()) > 10 || Integer.parseInt(fields[2].getText()) < 1) ||
								(Integer.parseInt(fields[3].getText()) > 10 || Integer.parseInt(fields[3].getText()) < 1) ||
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
							Loot.getItem(path,
										 Integer.parseInt(fields[0].getText()),
										 Integer.parseInt(fields[1].getText()),
										 Integer.parseInt(fields[2].getText()),
										 Integer.parseInt(fields[3].getText()),
										 Integer.parseInt(fields[4].getText()),
										 Integer.parseInt(fields[5].getText()));
						}
					}else{		//Regular rules
						JOptionPane.showMessageDialog(null, "Not yet implemented.");
					}
				}
			}else if(event.getSource()==randomRolls){
				if(path == ""){
					JOptionPane.showMessageDialog(null, "First select a loot table.");
				}else{
					if(type){	//Special rules
						JOptionPane.showMessageDialog(null, "Not yet implemented.");
					}else{		//Regular rules
						JOptionPane.showMessageDialog(null, "Not yet implemented.");
					}
				}
			}else if(event.getSource() == selectTable){
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					path = chooser.getSelectedFile().getAbsolutePath();
				}
			}
		}
	}
}
