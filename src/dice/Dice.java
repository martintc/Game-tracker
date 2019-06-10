package dice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Dice extends JFrame{
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem load;
	
	private JLabel commonLabel;
	private JButton[] commonButtons;
	private String[] commonNames = {"d4","d6","d8","d10","d12","d20"};
	
	private JButton myCombos;

	private String path = "";
	public Dice(){
		super("Dice Manager");
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
		load = new JMenuItem("Load");
		file.add(load);
		
/*----------------------------------
 * 			Other
 */
		//Add all the Insets for spacing
		
		commonLabel = new JLabel("Common: ");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		add(commonLabel, c);
		
		commonButtons = new JButton[6];
		c.gridy = 1;
		for(int i = 0; i<(int)(commonButtons.length/2); ++i){
			commonButtons[i] = new JButton(commonNames[i]);
			add(commonButtons[i], c);
			c.gridx++;
		}
		c.gridy = 2;
		c.gridx = 0;
		for(int i = (int)(commonButtons.length/2); i<commonButtons.length; ++i){
			commonButtons[i] = new JButton(commonNames[i]);
			add(commonButtons[i], c);
			c.gridx++;
		}
		
		myCombos = new JButton("My Combos"); //add to its own panel? I don't want it to affect the size of the other buttons.
		c.gridy = 3;
		c.gridx = 0;
		add(myCombos, c);
/*----------------------------------
 * 			Events
 */
		EventHandler handler = new EventHandler();
		load.addActionListener(handler);
		for(int i = 0; i<commonButtons.length; ++i){
			commonButtons[i].addActionListener(handler);
		}
		myCombos.addActionListener(handler);
		
	}
	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==load){	
				
			}else if(event.getSource()==myCombos){
				
			}else if(event.getSource()==commonButtons[0]){
				
			}else if(event.getSource()==commonButtons[1]){
				
			}else if(event.getSource()==commonButtons[2]){
				
			}else if(event.getSource()==commonButtons[3]){
				
			}else if(event.getSource()==commonButtons[4]){
				
			}else if(event.getSource()==commonButtons[5]){
				
			}
		}
	}
	
	public static void run(){
		Dice myInterface = new Dice();
		myInterface.pack();
		myInterface.setVisible(true);
		myInterface.setResizable(true);
		
		//attempt to load a default database
	}
}

/*

* Need to be able to name them/rename them.
* SUGGESTED that they include a header? Like "Goblin: Punch"
	- Display them alphabetically

* I want to be able to graph the probability distribution of a dice combination.

* Each combo should be stored in the DB in 1 line. Just use different delimiters. Commas separate items within a combo, semicolons separate combos.

 */
