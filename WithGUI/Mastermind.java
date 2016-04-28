package bonusassignment;
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
	public static ArrayList<String> thisGuess = new ArrayList<String>();
	public static String guess = "";
	public static MasterGUI runningGame;
	public static void main(String[] args) {
		
		boolean continue_playing = true;
		boolean replay = false;
		
		while(continue_playing){
			
			try{

				JOptionPane frame = new JOptionPane();
				MyButtons clickable = new MyButtons();
				JButton[] buttons = clickable.getButtonArray();
				
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

				MasterGUI gameBoard = new MasterGUI(numGuess, game.getSecretCode(), buttons);
				runningGame = gameBoard;
				while(true){
					

					if(game.getNumGuesses() == numGuess)
					{
						gameBoard.gameover();
						GameMessages.outofGuesses(game);

					}

					try{

						//System.out.println("TEST");
						
						
							if(guess.length() == numPegs){
								game.setNextGuess(guess);
								ResultPegs pegs = game.checkLastGuess();
								String result = pegs.getResult();
								System.out.println(guess + " --->Result:" + result);
								game.setGuessResult(game.getNumGuesses()-1, result);
								gameBoard.submitFeedback(guess,pegs.getBlackPegs() , pegs.getWhitePegs());
								guess = "";
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
	
	
	
	public static class MyButtons extends JFrame implements ActionListener {
		  public JButton red_button;
		  public JButton blue_button;
		  public JButton purple_button;
		  public JButton orange_button;
		  public JButton yellow_button;
		  public JButton green_button;
		  public JButton submit_button;
		  public JButton clear_button;
		  
		  final Color PURPLE = new Color(160, 32, 240);

		  public MyButtons() {
			red_button = new JButton("");  
			blue_button = new JButton("");
			purple_button = new JButton("");
			orange_button = new JButton("");
			yellow_button = new JButton("");
			green_button = new JButton("");
			submit_button = new JButton("SUBMIT");
			clear_button = new JButton("CLEAR");
			
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
		    clear_button.addActionListener(this);

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
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("R");
		    		runningGame.repaint();}
		    } else if (src == blue_button) {
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("B");
		    		runningGame.repaint();}
		    } else if (src == purple_button) {
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("P");
		    		runningGame.repaint();}
			} else if (src == orange_button) {
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("O");
		    		runningGame.repaint();}
			} else if (src == yellow_button) {
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("Y");
		    		runningGame.repaint();}
		    } else if (src == green_button) {
		    	if(thisGuess.size() < numPegs){
		    		thisGuess.add("G");
		    		runningGame.repaint();}
			} else if (src == submit_button){
				if(thisGuess.size() == numPegs){
					for(int i = 0; i < numPegs; i++){
						guess += thisGuess.get(i);
					}
					thisGuess.clear();
				}
			} else if (src == clear_button){
				thisGuess.clear();
				runningGame.repaint();
			}
		  }
		  
		  public JButton[] getButtonArray(){
			  JButton[] buttons = { this.red_button, this.blue_button, this.green_button, this.purple_button, this.orange_button,
					  this.yellow_button,this.submit_button,this.clear_button
			  };
			  return buttons;
		  }
	}

	
}
