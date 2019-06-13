import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MTGLifeTracker implements ActionListener {

	private ArrayList<Player> players;
	private JFrame mainFrame;
	private JButton exitButton;
	private JButton damageButton;
	private JButton healButton;

	public void run () {
		players = new ArrayList<>();
		setPlayers();
		setLifePoints();
	
		mainGUI();
	}

	public JFrame mainGUI () {


		mainFrame = new JFrame("Magic The Gathering");
		mainFrame.setVisible(true);
		mainFrame.setDefaultLookAndFeelDecorated(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(500, 500);
		
		mainFrame.add(titlePanel(), BorderLayout.NORTH);	
		mainFrame.add(playerPanel(), BorderLayout.CENTER);
		mainFrame.add(gameOptionsPanel(), BorderLayout.SOUTH);

		return mainFrame;
	}

	private JPanel titlePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Magic The Gather: Life Points Tracker");
		panel.add(label);
		return panel;
	}

	private JPanel playerPanel () {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(players.size(), 2));
		
		for (Player p : players) {
			JLabel playerLabel = new JLabel(p.getPlayerName());
			panel.add(playerLabel);
			JLabel playerLifePoints = new JLabel(Integer.toString(p.getPlayerLifePoints()));
			panel.add(playerLifePoints);
		}
		
		return panel;
	}

	private JPanel gameOptionsPanel () {
		JPanel panel = new JPanel();
		
		damageButton = new JButton("Damage Player");
		damageButton.addActionListener(this);
		healButton = new JButton("Heal Player");
		healButton.addActionListener(this);

		panel.add(damageButton);
		panel.add(healButton);
		
		return panel;
	}

	private void setPlayers () {
		String playerNames = JOptionPane.showInputDialog("Enter Player names (seperated by commas" + "\n" + "(ex) jim,john,tom");
		
		String[] playerNamesSplit = playerNames.split(",");

		for (int i = 0; i < playerNamesSplit.length; i++) {
			Player p = new Player(playerNamesSplit[i]);
			players.add(p);
		}
	}

	private void setLifePoints() {
		int lifePoints = 0;

		try {
			lifePoints = Integer.parseInt(JOptionPane.showInputDialog("Enter life points (must be an integer)"));
		} catch (Exception e) {
			setLifePoints();
		}

		for (Player p : players) {
			p.setPlayerLifePoints(lifePoints);
		}
	}


	private void doDamage () {
		String playerName = JOptionPane.showInputDialog("Enter player to damage");
		int damage = Integer.parseInt(JOptionPane.showInputDialog("Damage to Player:"));
		for (Player p : players) {
			if (playerName.equals(p.getPlayerName())) {
				p.damagePlayer(damage);
			}
		}
	}
	
	private void doHeal () {
		String playerName = JOptionPane.showInputDialog("Enter player to heal");
		int heal = Integer.parseInt(JOptionPane.showInputDialog("Point to add:"));
		for (Player p : players) {
			if (playerName.equals(p.getPlayerName())) {
				p.healPlayer(heal);
			}
		}
	}

	public void actionPerformed (ActionEvent e) {
		String event = e.getActionCommand();
		if (event == "Damage Player") {
			doDamage();
		} if (event == "Heal Player") {
			doHeal();
		}
		
		mainFrame.dispose();
		mainGUI();

	}


	private class Player {

		private String playerName;
		private int playerLifePoints;

		public Player (String initPlayerName, int initPlayerLifePoints) {
			playerName = initPlayerName;
			playerLifePoints = initPlayerLifePoints;
		}	

		public Player (String initPlayerName) {
			playerName = initPlayerName;
		}
		
		public void setPlayerName (String initPlayerName) {
			playerName = initPlayerName;
		}

		public void setPlayerLifePoints (int initPlayerLifePoints) {
			playerLifePoints = initPlayerLifePoints;
		}

		public String getPlayerName () {
			return playerName;
		}


		public int getPlayerLifePoints () {
			return playerLifePoints;
		}

		public void damagePlayer (int damageTaken) {
			playerLifePoints = playerLifePoints - damageTaken;
		}

		public void healPlayer (int healthGained) {
			playerLifePoints = playerLifePoints + healthGained;
		}

		public String toString () {
			return playerName;
		}
	}
}

	
