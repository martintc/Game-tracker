import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Main {
	
	/*
	 * Main entry point of program
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	/*
	 * Creates the JFrame that holds the Main menu
	 */
	public void run() {

		JFrame mainFrame = new JFrame("Main Menu");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 500);
		mainFrame.add(gameMenuPanel());


	}
	
	/*
	 * Generates the panel of game options and populates.
	 */
	protected JPanel gameMenuPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.setVisible(true);
		ArrayList<JButton> gameButtons = this.gameChoicesButtons();
		for (JButton game : gameButtons) {
			panel.add(game);
			game.addActionListener(new EventHandler());
		}
		return panel;
	}

	/*
	 * Generates a button for each game name.
	 */
	protected ArrayList<JButton> gameChoicesButtons() {
		ArrayList<JButton> games = new ArrayList<>();
		
		ArrayList<String> gameNames = this.gameNames();

		for (String game: gameNames) {
			games.add(new JButton(game));
		}

		return games;
	}
	
	/*
	 * Creates the list of game names.
	 */
	protected ArrayList<String> gameNames() {
		ArrayList<String> games = new ArrayList<>();
		games.add("DnD");

		return games;
	}

	/*
	 * Class that will handle events within this class.
	 */
	private class EventHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
		 	String choice = e.getActionCommand();
		  	if (choice.equals("DnD")) {
				DnD dnd = new DnD();
				dnd.run();	
			}	
		}	
	
	}

}
