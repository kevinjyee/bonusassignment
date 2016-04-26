package bonusassignment;

import javax.swing.JOptionPane;

public class Mastermind {

	public static void main(String[] args) {
		Board game = new Board();
		 //String[] buttons = { "Yes", "No" };
		JOptionPane frame = new JOptionPane();
		while(game.getNumGuesses() < 12){
			String guess = JOptionPane.showInputDialog("What is your next guess? \nType in the characters for your guess"
				+ " and press enter. \nEnter guess:");
			try{
				game.setNextGuess(guess);
				ResultPegs pegs = game.checkLastGuess();
			} catch(IllegalCodeException e){
				JOptionPane.showMessageDialog(frame,
					    "INVALID GUESS",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			/*
		    opt = JOptionPane.showOptionDialog(null,
		    		"Would you like to make another transaction?",
		    		"Confirmation", 0, JOptionPane.QUESTION_MESSAGE, null,
		    		buttons, buttons[1]);
		    if(opt == 1){  = false; }
		    */
		 }
	 }
	
}
