package bonusassignment;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

//class represents a JPanel with a master-mind-board drawn on it.
class MasterMindBoard extends JPanel
{

	//Font
	final static Font DIRECTION_FONT = new Font("Arial",Font.BOLD,12);
	final Font defFont = new JLabel().getFont();


	//board dimensions
	final static int BOARD_HEIGHT = 500;
	final static int BOARD_WIDTH = 270;
	final static int FIRST_ROW_OFFSET = 25;
	final static int SIDE_OFFSET = 35;
	final static int RESULTS_OFFSET = 6;


	//colors
	//final static Color boardColor = new Color(151, 195, 10);
	final static Color boardColor = new Color(184, 134, 11);
	//184-134-11
	//240-230-140
	final static Color holeColor = new Color(10,10,10);
	final static Color guessedCode = new Color( 85, 85, 85);
	final static Color KHAKI = new Color(240, 230, 140);
	final static Color PURPLE = new Color(160, 32, 240);

	private boolean gameOver = false;
	private int numGuesses = 1;
	private int rowSpace;
	private ArrayList<String> prevGuesses = new ArrayList<String>();
	private int[] numBlack;
	private int[] numWhite;
	private String correctGuess = "????";
	private JButton[] clickable;


	public MasterMindBoard(int pNumGuesses, String secretCode, JButton[] butt)
	{
		numGuesses = pNumGuesses;
		correctGuess = secretCode;
		rowSpace = 425 / numGuesses; //spaces to leave between each row
		numBlack = new int [numGuesses];
		numWhite = new int [numGuesses];
		clickable = butt;
	}

	//physically paints the panel
	@Override
	public void paintComponent(Graphics g)
	{

		drawBoard(g,100,60);
		paintDirections(g,117,65 + BOARD_HEIGHT);
		paintGuessBuffer(g, 117, BOARD_HEIGHT - 28);
		
//		add(clickable.red_button);
//		add(clickable.blue_button);
//		add(clickable.green_button);
//		add(clickable.purple_button);
//		add(clickable.orange_button);
//		add(clickable.yellow_button);
//		add(clickable.submit_button);
//		add(clickable.redact_button);


		for(int i = 0; i < clickable.length; i++){
			if(i < 6){
				clickable[i].setBounds(20 + (i * 70), BOARD_HEIGHT + 150, 70, 30);
				add(clickable[i]);
			} else{
				clickable[i].setBounds(230 + ((i - 7) * 90), BOARD_HEIGHT + 115, 90, 30);
				add(clickable[i]);
			}
		}

		
		
	}

	//stores the information of a guess, so that the board can display the appropriate images
	public void registerGuess(String guess, int Black, int White)
	{
		int index = prevGuesses.size();
		if(index < numGuesses)
		{
			prevGuesses.add(guess);
			numBlack[index] = Black;
			numWhite[index] = White;
		}
	}

	//Tells the board that the game is over, so it can display the solution
	public void gameOver()
	{
		gameOver = true;
	}

	public void setSolution(String newSolution)
	{
		correctGuess = newSolution;
	}

	//draws the board at the given location of the JPanel
	private void drawBoard(Graphics g, int xOffset, int yOffset)
	{
		Images dog = new Images();
		try{
		dog.initImages();
		}
		catch(Exception e){
			
		}
		g.translate(xOffset,yOffset);
		g.drawImage(dog.DOGE_IMAGE, 0, 0, null);
//		g.setColor(boardColor);
//		g.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
		
		int x = 30;
		int y = 30;
		drawTitle(g, 50, -10);


		//draws the player guess area
		for(int row = 0; row < numGuesses; row++)
		{
			if(row < prevGuesses.size())
			{
				paintGuess(g,x,y+row*rowSpace,prevGuesses.get(row));
			}
			else
			{
				paintNonGuess(g,x,y+row*rowSpace);
			}
			paintResults(g, x + Mastermind.numPegs*SIDE_OFFSET, y + row*rowSpace - 3,numBlack[row],numWhite[row]);
		}


		//draws the solution area
		g.setColor(KHAKI);
		g.fillRect(x-10,500 - (int)(1.5 * y) - 20, (130 / 4) * Mastermind.numPegs ,y);
		//paintHiddenCode(g, x, 513 - (int)(1.5 * y));
		g.setColor(KHAKI);

		if(gameOver)
		{
			paintGuess(g,x,500-y - 20,correctGuess);
		}
		else
		{
			String mystery = "";
			for(int i =0; i < Mastermind.numPegs; i++){
				mystery+= "?";
			}
			paintHiddenCode(g,x,500-y,mystery);
		}

		//return the graphics object to its original orientation
		g.translate(-xOffset,-yOffset);
	}

	
	private void drawTitle(Graphics g, int xOffset, int yOffset){
		
		Font title_font = new Font("SansSerif",Font.BOLD,25);
		g.setFont(title_font);
		g.setColor(Color.BLACK);
		g.drawString("MASTERMIND", xOffset - 4, yOffset);
		g.setFont(defFont);
	}
	
	//paints one "empty" row of the gameboard
	private void paintNonGuess(Graphics g, int xOffset, int yOffset)
	{
		g.setColor(holeColor);
		for(int count = 0; count < Mastermind.numPegs; count++)
		{
			g.fillArc(xOffset + count*SIDE_OFFSET - 6,yOffset - 6,12,12,0,360);
		}
	}

	//paints a guess on the board
	private void paintGuess(Graphics g, int xOffset, int yOffset, String code)
	{
		Color[] guessCode = colorSelector(code);
		for(int count = 0; count < Mastermind.numPegs; count++)
		{
			g.setColor(guessCode[count]);
			g.fillArc(xOffset + count*SIDE_OFFSET - 6,yOffset - 6,12,12,0,360);
		}		
	}
	
	private void paintHiddenCode(Graphics g, int xOffset, int yOffset, String code){
		boolean qMarkFlag = true;
		for(int i = 0; i < code.length(); i++){
			String character = code.substring(i, i + 1);
			if(!character.equals("?")){
				qMarkFlag = false;
			}
		}
		if(qMarkFlag)
		{
			g.setColor(guessedCode);
			g.setFont(new Font("arial",Font.BOLD,16));
			for(int count = 0; count < Mastermind.numPegs && count < code.length(); count++)
			{
				g.drawString("" + code.charAt(count),xOffset + count*SIDE_OFFSET - 5,yOffset - 15);
			}
			g.setColor(Color.RED);
			g.drawString("SECRET", xOffset - 100, yOffset - 15);
			g.drawString("GUESS", xOffset - 100, yOffset + 14);
		}
	}

	//paints the results of an unspecified guess on the board
	private void paintResults(Graphics g, int xOffset, int yOffset, int numBlack, int numWhite)
	{
		for(int i = 0; i < Mastermind.numPegs; i++){
			switch(i){
				case 1: yOffset += RESULTS_OFFSET;
					break;
				case 2: xOffset += RESULTS_OFFSET;
					yOffset -= RESULTS_OFFSET;
					break;
				case 3: yOffset += RESULTS_OFFSET;
					break;
				case 4: xOffset += RESULTS_OFFSET;
					yOffset -= RESULTS_OFFSET;
					break;
				case 5: yOffset += RESULTS_OFFSET;
					break;
			}
			g.setColor(getColor(numBlack,numWhite,i + 1));
			g.fillOval(xOffset - 3, yOffset - 3, 6, 6);
			g.fillOval(xOffset - 3, yOffset - 3, 6, 6);
			/*
			g.setColor(getColor(numBlack,numWhite,1));
			g.fillOval(xOffset - 3,yOffset - 3,6,6);
			g.setColor(getColor(numBlack,numWhite,2));
			g.fillOval(xOffset + RESULTS_OFFSET - 3,yOffset - 3,6,6);
			g.setColor(getColor(numBlack,numWhite,3));
			g.fillOval(xOffset - 3,yOffset + RESULTS_OFFSET - 3,6,6);
			g.setColor(getColor(numBlack,numWhite,4));
			g.fillOval(xOffset + RESULTS_OFFSET - 3,yOffset + RESULTS_OFFSET - 3,6,6);
			*/
		}
	}

	//Prints the directions for what the board means
	private void paintDirections(Graphics g,  int xOffset, int yOffset)
	{
		xOffset += 10;
		g.setFont(DIRECTION_FONT);

		//paint Background
		g.setColor(Color.YELLOW);
		g.fillRect(xOffset-5,yOffset,220,46);

		//paint peg representations
		g.setColor(Color.BLACK);
		g.fillOval(xOffset,yOffset + 4,6,6);
		g.setColor(Color.BLACK);
		g.fillOval(xOffset,yOffset + 20,6,6);
		g.setColor(Color.BLACK);
		g.fillOval(xOffset,yOffset + 36,6,6);

		//paint words
		g.setColor(Color.BLACK);
		g.drawString("Select colors using colored buttons.",xOffset + 8,yOffset + 12);
		g.drawString("Click SUBMIT to submit guess.",xOffset + 8,yOffset + 28);
		g.drawString("Click CLEAR to clear selection.",xOffset + 8,yOffset + 44);
		/*
		g.drawString("= correct color and place",xOffset + 8,yOffset + 12);
		g.drawString("= correct color, wrong place",xOffset + 8,yOffset + 28);
		*/
	}
	
	
	private void paintGuessBuffer(Graphics g,  int xOffset, int yOffset)
	
	{
		if(Mastermind.thisGuess.size() == 0){
			
		emptyGuess(g, xOffset+12, yOffset);
		}
		else{
			nonemptyGuess(g, xOffset+12,yOffset);
		}
		
		
	}
	
	
	private void emptyGuess(Graphics g, int xOffset, int yOffset){
		
		g.setColor(holeColor);
		for(int count = 0; count < Mastermind.numPegs; count++)
		{
			g.fillArc(xOffset + count*SIDE_OFFSET - 6,yOffset + 60,12,12,0,360);
		}
	}
	
	private void nonemptyGuess(Graphics g, int xOffset, int yOffset){
		ArrayList<String> guessArray = Mastermind.thisGuess;
		String code = "";
		
		for(int i =0; i < Mastermind.thisGuess.size(); i++){
			code+=Mastermind.thisGuess.get(i);
		}
		
		
		Color[] guessCode = colorSelector(code);
		for(int count = 0; count < Mastermind.thisGuess.size(); count++)
		{
			g.setColor(guessCode[count]);
			g.fillArc(xOffset + count*SIDE_OFFSET - 6,yOffset + 60,12,12,0,360);
		}
		
		int emptypegs = Mastermind.numPegs - Mastermind.thisGuess.size();
		
		
		for(int count = Mastermind.thisGuess.size(); count<Mastermind.numPegs; count++){
			g.setColor(holeColor);
			g.fillArc(xOffset + count*SIDE_OFFSET - 6,yOffset + 60,12,12,0,360);
			
		}
		
		
		
	}
	//returns the color of a results peg, given that it is the numpeg'th peg t be placed, and that there were the given number of correct guesses and correct colors
	private Color getColor(int numRight, int numWhite, int numPeg)
	{
		if(numPeg <= numRight)
			return Color.RED;
		if(numPeg <= numRight + numWhite)
			return Color.WHITE;
		return Color.BLACK;
	}
	
	private Color[] colorSelector(String code){
		//"BGOPRY"
		Color[] result = new Color[code.length()];
		for(int i = 0; i < code.length(); i++){
			String character = code.substring(i, i + 1);
			switch(character){
				case "B": case "b": result[i] = Color.BLUE;
					break;
				case "G": case "g": result[i] = Color.GREEN;
					break;
				case "O": case "o": result[i] = Color.ORANGE;
					break;
				case "P": case "p": result[i] = PURPLE;
					break;
				case "R": case "r": result[i] = Color.RED;
					break;
				case "Y": case "y": result[i] = Color.YELLOW;
					break;
				default:
					break;
			}
		}
		return result;
	}
}

