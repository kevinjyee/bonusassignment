package bonusassignment;

import javax.swing.JOptionPane;

public class Mastermind {

	public static int numGuess = 12;
	public static int numColors = 6;//B, G, O, P , R , Y
	public static int numPegs = 4;
	
	public static void main(String[] args) {
		Board game = new Board();
		JOptionPane frame = new JOptionPane();
		GameMessages.intro();
	
		while(true){
			
			if(game.getNumGuesses() == numGuess)
			{
				GameMessages.outofGuesses(game);
			}
			
			int guessleft = 12 - game.getNumGuesses();
			
			
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
					if(pegs.getBlackPegs() == numPegs){
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
