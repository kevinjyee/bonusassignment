package bonusassignment;

import javax.swing.JOptionPane;

public class GameMessages {

	GameMessages(){
		
	}
	
	public static void intro(){
		int reply = JOptionPane.showConfirmDialog(null,
				"Welcome to Mastermind. Here are the rules:\n"
				+ "This is a text version of the classic board game Mastermind.\n"
				+ "With default settings, the computer will think of a secret code consisting of 4 colored pegs.\n"
		        + "The pegs may be one of six colors: blue, green, orange, purple, red, or yellow.\n" 
				+ "A color may appear more than once in any given code.\n" 
		        + "You try to guess what colored pegs are in the code and what order they are in.\n" 
		        + "After you make a valid guess the result (feedback) will be displayed.\n"
		        + "The result consists of a red peg for each peg you have guessed exactly correct\n"
		        + "(color and position) in your guess.\n" 
		        + "For each peg in the guess that is the correct color, but is out of position, you get a white peg.\n" 
				+ "For each peg, which is fully incorrect, you get no feedback (black).\n"
		        + "Colored pegs are displayed on the board for past and current guesses.\n"
		        + "When entering guesses you must select the color you want using any of the six colored buttons.\n"
		        + "After you select enough colors to make up a valid guess, click SUBMIT to submit the guess.\n"
		        + "If you would like to clear your current guess to try a new combination, click the CLEAR button.\n"
		        + "With default settings, you have 12 guesses to figure out the secret code or you lose the game.\n\n"
		        + "Are you ready to play? (Y/N):"
		        , "Instructions", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			          
			        }
			        else {
			           JOptionPane.showMessageDialog(null, "GOODBYE");
			           System.exit(0);
			        }
	}
	
	public static void outofGuesses(Board game){
		int reply = JOptionPane.showConfirmDialog(null,
				"You've run out of guesses!\n"
				+ "Would you like to play again?"
		        , "Confirmation", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			         game.guesses =0;
			         throw new RestartException();
			         /*
			         int useDefault = chooseStartingValues();
			         if(useDefault == JOptionPane.YES_OPTION){
			        	 Mastermind.numGuess = 12;
			        	 Mastermind.numPegs = 4;
			         }
			         else{
			        	 chooseNumGuesses();
			        	 chooseNumPegs();
			         }
			         */
			        }
			        else {
			           JOptionPane.showMessageDialog(null, "GOODBYE");
			           System.exit(0);
			        }
	}
	
	
	public static void winMessage(Board game){
		int reply = JOptionPane.showConfirmDialog(null,
				  "Hooray! You've won!"
				+ "Would you like to play again?"
		        , "Confirmation", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			         game.guesses =0;
			         throw new RestartException();
			         /*
			         int useDefault = chooseStartingValues();
			         if(useDefault == JOptionPane.YES_OPTION){
			        	 Mastermind.numGuess = 12;
			        	 Mastermind.numPegs = 4;
			         }
			         else{
			        	 chooseNumGuesses();
			        	 chooseNumPegs();
			         }
			         */
			        }
			        else {
			           JOptionPane.showMessageDialog(null, "GOODBYE");
			           System.exit(0);
			        }
	}
	
	  public static void chooseNumGuesses() {
		    String[] choices = { "15", "14", "13", "12", "11", "10" };
		    String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
		        "Number of Guesses", JOptionPane.QUESTION_MESSAGE, null, // Use
		                                                                        // default
		                                                                        // icon
		        choices, // Array of choices
		        choices[3]); // Initial choice
		    try{
		    Mastermind.numGuess = Integer.parseInt(input);
		    }
		    catch(NumberFormatException e){
		    	JOptionPane.showMessageDialog(null, "The Number of Guesses will default to 12");
		    	Mastermind.numGuess = 12;
		    }
		  }
	  
	  public static void chooseNumPegs() {
		    String[] choices = { "6", "5", "4", "3", "2", "1" };
		    String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
		        "Number of Guesses", JOptionPane.QUESTION_MESSAGE, null, // Use
		                                                                        // default
		                                                                        // icon
		        choices, // Array of choices
		        choices[2]); // Initial choice
		    try{
		    Mastermind.numPegs= Integer.parseInt(input);
		    }
		    catch(NumberFormatException e){
		    	JOptionPane.showMessageDialog(null, "The Number of Pegs will default to 4");
		    	Mastermind.numPegs = 4;
		    }
		  }
	  
	  public static int chooseStartingValues(){
		  int reply = JOptionPane.showConfirmDialog(null,
				  "Would you like to use default starting values?"
		        , "Confirmation", JOptionPane.YES_NO_OPTION);
			        return reply;
	  }
		}
	

