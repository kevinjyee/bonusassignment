package bonusassignment;

import javax.swing.JOptionPane;

public class Mastermind {

	public static void main(String[] args) {
		Board game = new Board();
		 //String[] buttons = { "Yes", "No" };
		JOptionPane frame = new JOptionPane();
		while(game.getNumGuesses() < 12){
			int guessleft = 12 - game.getNumGuesses();
			
			
			String guess = JOptionPane.showInputDialog("You have " + guessleft + " guesses left. What is your next guess? \nType in the characters for your guess"
				+ " and press enter. \nEnter guess:");
			try{
				game.setNextGuess(guess);
				ResultPegs pegs = game.checkLastGuess();
				pegs.printResult();
				if(pegs.getBlackPegs() == 4){
					System.out.println("Hooray! You've won!");
					System.exit(0);
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
			/*
		    opt = JOptionPane.showOptionDialog(null,
		    		"Would you like to make another transaction?",
		    		"Confirmation", 0, JOptionPane.QUESTION_MESSAGE, null,
		    		buttons, buttons[1]);
		    if(opt == 1){  = false; }
		    */
		 }
		System.out.println("You've Ran out of Guesses!");
	 }
	
}

