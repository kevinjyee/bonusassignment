package bonusassignment;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.Console;
public class Mastermind {

	public static int numGuess = 12;
	public static int numColors = 6;//B, G, O, P , R , Y 
	public static int numPegs = 4;
	
	public static void main(String[] args) {
		
		JOptionPane frame = new JOptionPane();
		GameMessages.intro();
		
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
	    
        MasterGUI gameBoard = new MasterGUI(numGuess, game.getSecretCode());
	    
		while(true){
			
			if(game.getNumGuesses() == numGuess)
			{
				gameBoard.gameover();
				GameMessages.outofGuesses(game);
				
			}
			
			int guessleft = numGuess - game.getNumGuesses();
			
			
			String guess = JOptionPane.showInputDialog("You have " + guessleft + " guesses left. What is your next guess? \nType in the characters for your guess"
				+ " and press enter. \nOr enter 'history' to see game history \nEnter guess:");
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

	 }
	
}
