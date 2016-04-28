package bonusassignment;//.WithGUI;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.Console;
public class Mastermind {

	public static int numGuess = 12;
	public static int numColors = 6;//B, G, O, P , R , Y 
	public static int numPegs = 4;
	
	public static void main(String[] args) {
		
		boolean continue_playing = true;
		boolean replay = false;
		
		while(continue_playing){
			
			try{

				JOptionPane frame = new JOptionPane();
				MyButtons clickable = new MyButtons();
				if(!replay){
					GameMessages.intro();
				}

				int reply = JOptionPane.showConfirmDialog(null,
						"Would you like to change the number of Guesses, Colors, and Pegs away from the default?"
						, "Change parameters", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					GameMessages.chooseNumGuesses();
					GameMessages.chooseNumPegs();
				}
				else {
					JOptionPane.showMessageDialog(null, "Default Values will be used\nYou will have 12 guesses, 6 colors, and 4 pegs");
				}

				Board game = new Board();

				MasterGUI gameBoard = new MasterGUI(numGuess, game.getSecretCode(), clickable);

				while(true){

					if(game.getNumGuesses() == numGuess)
					{
						gameBoard.gameover();
						GameMessages.outofGuesses(game);

					}

					int guessleft = numGuess - game.getNumGuesses();


					String guess = JOptionPane.showInputDialog("You have " + guessleft + " guesses left. What is your next guess? \n"
							+ "Type in the characters for your guess."
							+ " and press enter. Options include BGOPRY. \nOr enter 'history' to see game history \nEnter guess:");
					try{
						if(guess.toLowerCase().equals("history")){
							game.getHistory();
						}
						else{
							game.setNextGuess(guess);
							ResultPegs pegs = game.checkLastGuess();
							String result = pegs.getResult();
							System.out.println(guess + " --->Result:" + result);
							game.setGuessResult(game.getNumGuesses()-1, result);
							gameBoard.submitFeedback(guess,pegs.getBlackPegs() , pegs.getWhitePegs());
							if(pegs.getBlackPegs() == numPegs){
								gameBoard.gameover();
								GameMessages.winMessage(game);
							}
						}


					} catch(IllegalCodeException e){
						JOptionPane.showMessageDialog(frame,
								"INVALID GUESS",
								"Inane warning",
								JOptionPane.WARNING_MESSAGE);
					} catch(NullPointerException e){
						System.out.println("Game Exited");
						System.exit(0);
					}
					
				}
			} catch(RestartException e){
				continue_playing = true;
				replay = true;
			}
		}

	 }
	
	public class Buttons extends JFrame implements ActionListener {
		  public JButton red_button = new JButton("RED");
		  public JButton blue_button = new JButton("BLUE");
		  public JButton purple_button = new JButton("PURPLE");
		  public JButton orange_button = new JButton("ORANGE");
		  public JButton yellow_button = new JButton("YELLOW");
		  public JButton green_button = new JButton("GREEN");
		  public JButton submit_button = new JButton("SUBMIT");
		  public JButton redact_button = new JButton("REDACT");
		  
		  final Color PURPLE = new Color(160, 32, 240);

		  public Buttons() {
		    red_button.addActionListener(this);
		    red_button.setBackground(Color.RED);
		    blue_button.addActionListener(this);
		    blue_button.setBackground(Color.BLUE);
		    purple_button.addActionListener(this);
		    purple_button.setBackground(PURPLE);
		    orange_button.addActionListener(this);
		    orange_button.setBackground(Color.ORANGE);
		    yellow_button.addActionListener(this);
		    yellow_button.setBackground(Color.YELLOW);
		    green_button.addActionListener(this);
		    green_button.setBackground(Color.GREEN);
		    submit_button.addActionListener(this);
		    redact_button.addActionListener(this);
		    //... add buttons to frame ...
		    /*
		    red_button.setLayout(null);
		    red_button.setLayout(null);
		    red_button.setLayout(null);
		    red_button.setLayout(null);
		    red_button.setLayout(null);
		    red_button.setLayout(null);
		    */
		  }

		  public void actionPerformed(ActionEvent evt) {
		    Object src = evt.getSource();
		    if (src == red_button) {
		      //... perform action for button 1
		    	System.out.println("LOLOLOL");
		    } else if (src == blue_button) {
		      //... perform action for button 2
		    } else if (src == purple_button) {
			      //... perform action for button 2
			} else if (src == orange_button) {
			      //... perform action for button 2
			} else if (src == yellow_button) {
			      //... perform action for button 2
		    } else if (src == green_button) {
				      //... perform action for button 2
			} else if (src == submit_button){
				
			} else if (src == redact_button){
				
			}
		  }
	}

	
}
