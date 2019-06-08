package lootTableDnD;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Popup extends JFrame{
	private JLabel 		coinsLabel;
	private JTextField	coinsField;
	
	private JLabel		nameLabel;
	private JTextField	nameField;
	private JLabel		descriptionLabel;
	private JTextArea	descriptionArea;
	private JScrollPane	descriptionScroll;
	private JLabel 		valueLabel;
	private JTextField	valueField;
	private JLabel		rarityLabel;
	private JTextField	rarityField;
	private JLabel		weightLabel;
	private JTextField	weightField;
	private JLabel		categoryLabel;
	private JTextField	categoryField;
	private JLabel		propertiesLabel;
	private JTextArea	propertiesArea;
	private JScrollPane	propertiesScroll;
	private JLabel		requirementsLabel;
	private JTextArea	requirementsArea;
	private JScrollPane	requirementsScroll;
	private JLabel		authorLabel;
	private JTextField	authorField;
	
	private Font theFont;
	public Popup(String coinsLine, String itemLine){
		super("Your loot");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		theFont = new Font("Calibri", Font.PLAIN, 16);
		UIManager.put("TextField.font", theFont);
		UIManager.put("TextArea.font", theFont);
		theFont = new Font("Calibri", Font.BOLD, 16);
		UIManager.put("Label.font", theFont);
		
		int first = 0, second;
		while(true){
			first = itemLine.indexOf("\"", first);
			second = itemLine.indexOf("\"", first+1);
			if(first < 0 || second < 0){
				break;
			}else{
				itemLine = itemLine.substring(0, first) + itemLine.substring(first, second).replaceAll(",", "") + itemLine.substring(second, itemLine.length());
				first = second;
			}
		}
		String itemArr[] = (itemLine+",,,,,,,,").replaceAll("\"", "").split(","); //I add commas to avoid ArrayIndexOutOfBoundsException later.
		
		try{
			Integer.parseInt(coinsLine.replaceAll("\\s",""));
		}catch(Exception e){
			coinsLabel = new JLabel("Coins:  ");
			coinsLabel.setFont(theFont);
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(10,10,60,10);
			add(coinsLabel, c);
			coinsField = new JTextField(coinsLine){@Override public void setBorder(Border border){}};
			coinsField.setEditable(false);
			c.gridx = 1;
			c.gridy = 0;
			add(coinsField, c);
		}
		
		c.insets = new Insets(4,10,4,10);
		if(!itemArr[0].isEmpty()){
			nameLabel = new JLabel("Item name:  ");
			c.gridx = 0;
			c.gridy = 1;
			add(nameLabel, c);
			nameField = new JTextField(itemArr[0]){@Override public void setBorder(Border border){}};
			nameField.setEditable(false);
			c.gridx = 1;
			c.gridy = 1;
			add(nameField, c);
		}
		
		if(!itemArr[1].isEmpty()){
			descriptionLabel = new JLabel("Description:");
			c.gridx = 0;
			c.gridy = 2;
			add(descriptionLabel, c);
			descriptionArea = new JTextArea(8,20);
			descriptionArea.setLineWrap(true);
			descriptionArea.setText(itemArr[1]);
			descriptionArea.setEditable(false);
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 2;
			add(descriptionArea, c);
			descriptionScroll = new JScrollPane(descriptionArea);
			add(descriptionScroll, c);
		}
		
		if(!itemArr[2].isEmpty()){
			valueLabel = new JLabel("Value: ");
			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 1;
			add(valueLabel, c);
			valueField = new JTextField(itemArr[2]){@Override public void setBorder(Border border){}};
			valueField.setEditable(false);
			c.gridx = 1;
			c.gridy = 4;
			add(valueField, c);
		}
		
		if(!itemArr[3].isEmpty()){
			rarityLabel = new JLabel("Rarity: ");
			c.gridx = 0;
			c.gridy = 5;
			add(rarityLabel, c);
			rarityField = new JTextField(itemArr[3]){@Override public void setBorder(Border border){}};
			rarityField.setEditable(false);
			c.gridx = 1;
			c.gridy = 5;
			add(rarityField, c);
		}
		
		if(!itemArr[4].isEmpty()){
			weightLabel = new JLabel("Weight: ");
			c.gridx = 0;
			c.gridy = 6;
			add(weightLabel, c);
			weightField = new JTextField(itemArr[4]){@Override public void setBorder(Border border){}};
			weightField.setEditable(false);
			c.gridx = 1;
			c.gridy = 6;
			add(weightField, c);
		}
		
		if(!itemArr[5].isEmpty()){
			categoryLabel = new JLabel("Category: ");
			c.gridx = 0;
			c.gridy = 7;
			add(categoryLabel,c);
			categoryField = new JTextField(itemArr[5]){@Override public void setBorder(Border border){}};
			categoryField.setEditable(false);
			c.gridx = 1;
			c.gridy = 7;
			add(categoryField, c);
		}
		
		if(!itemArr[6].isEmpty()){
			propertiesLabel = new  JLabel("Properties: ");
			c.gridx = 0;
			c.gridy = 8;
			add(propertiesLabel, c);
			propertiesArea = new JTextArea(6, 20);
			propertiesArea.setLineWrap(true);
			propertiesArea.setText(itemArr[6]);
			propertiesArea.setEditable(false);
			c.gridx = 0;
			c.gridy = 9;
			c.gridwidth = 2;
			add(propertiesArea, c);
			propertiesScroll = new JScrollPane(propertiesArea);
			add(propertiesScroll, c);
		}
		
		if(!itemArr[7].isEmpty()){
			requirementsLabel = new JLabel("Requirements: ");
			c.gridx = 0;
			c.gridy = 10;
			c.gridwidth = 1;
			add(requirementsLabel, c);
			requirementsArea = new JTextArea(4, 20);
			requirementsArea.setLineWrap(true);
			requirementsArea.setText(itemArr[7]);
			requirementsArea.setEditable(false);
			c.gridx = 0;
			c.gridy = 11;
			c.gridwidth = 2;
			add(requirementsArea, c);
			requirementsScroll = new JScrollPane(requirementsArea);
			add(requirementsScroll, c);
		}
		
		if(!itemArr[8].isEmpty()){
			authorLabel = new JLabel("Author: ");
			c.insets = new Insets(10,10,4,10);
			c.gridx = 0;
			c.gridy = 12;
			c.gridwidth = 1;
			add(authorLabel, c);
			authorField = new JTextField(itemArr[8]){@Override public void setBorder(Border border){}};
			authorField.setEditable(false);
			c.gridx = 1;
			c.gridy = 12;
			add(authorField, c);
		}
		
		pack();
	}
}
