package lootTableDnD;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private boolean type;
	private JButton randomRolls;
	private JButton myRolls;
	
	public GUI(boolean t){
		super("Loot Table Tool");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//Insert a way for them to select their loot table
		//Use the notes organizer program for reference
		
		type = t;
		if(t == true){
			String labelText[] = {	"<html><pre>Reward------d12:  </pre></html>",
									"<html><pre>Hundreds----d10:  </pre></html>",
									"<html><pre>Tens--------d10:  </pre></html>",
									"<html><pre>Ones--------d10:  </pre></html>",
									"<html><pre>Coins-------d20:  </pre></html>",
									"<html><pre>Multiplier--d10:  </pre></html>"};
			JLabel labels[] = new JLabel[6];
			for(int i = 0; i<labels.length; ++i){
				labels[i] = new JLabel(labelText[i]);
				c.gridx = 0;
				c.gridy = i;
				add(labels[i], c);
			}
			JTextField fields[] = new JTextField[6];
			for(int i = 0; i<fields.length; ++i){
				fields[i] = new JTextField(5);
				c.gridx = 1;
				c.gridy = i;
				add(fields[i], c);
			}
		}else{
			//Probably just a single JLabel for d(table length) and a single JTextField
		}
		
		randomRolls = new JButton("Random Rolls");
		myRolls		= new JButton("Use My Rolls");
		
		
/*---------------------------------
 * event handlers and action listeners
 */
		EventHandler handler = new EventHandler();
		randomRolls.addActionListener(handler);
	}
	private class EventHandler implements ActionListener{	
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==randomRolls){
				
			}else if(event.getSource()==myRolls){
				
			}
		}
	}
}
