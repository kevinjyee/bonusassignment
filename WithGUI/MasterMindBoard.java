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


	public MasterMindBoard(int pNumGuesses, String secretCode)
	{
		numGuesses = pNumGuesses;
		correctGuess = secretCode;
		rowSpace = 425 / numGuesses; //spaces to leave between each row
		numBlack = new int [numGuesses];
		numWhite = new int [numGuesses];
	}

	//physically paints the panel
	@Override
	public void paintComponent(Graphics g)
	{
		drawBoard(g,100,15);
		paintDirections(g,117,30 + BOARD_HEIGHT);
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
		g.translate(xOffset,yOffset);
		g.setColor(boardColor);
		g.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
		int x = 30;
		int y = 30;


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
		g.fillRect(x-10,500 - (int)(1.5 * y), 130,y);
		//paintHiddenCode(g, x, 513 - (int)(1.5 * y));
		g.setColor(KHAKI);

		if(gameOver)
		{
			paintGuess(g,x,500-y,correctGuess);
		}
		else
		{
			String mystery = "";
			for(int i =0; i < Mastermind.numPegs; i++){
				mystery+= "?";
			}
			paintGuess(g,x,500-y,mystery);
		}

		//return the graphics object to its original orientation
		g.translate(-xOffset,-yOffset);
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
		if(code.equals("????"))
		{
			g.setColor(guessedCode);
			g.setFont(new Font("arial",Font.BOLD,16));
			for(int count = 0; count < Mastermind.numPegs && count < code.length(); count++)
			{
				g.drawString("" + code.charAt(count),xOffset + count*SIDE_OFFSET - 5,yOffset + 5);
			}
		}
		
	}
	
	// Draws question marks in secret code area.
	private void paintHiddenCode(Graphics g, int xOffset, int yOffset){
		g.setColor(guessedCode);
		g.setFont(new Font("arial",Font.BOLD,16));
		for(int i = 0; i < Mastermind.numPegs; i++)
		{
			g.drawString("?",xOffset + i*SIDE_OFFSET - 5,yOffset + 5);
		}
	}

	//paints the results of an unspecified guess on the board
	private void paintResults(Graphics g, int xOffset, int yOffset, int numBlack, int numWhite)
	{
		g.setColor(getColor(numBlack,numWhite,1));
		g.fillOval(xOffset - 3,yOffset - 3,6,6);
		g.setColor(getColor(numBlack,numWhite,2));
		g.fillOval(xOffset + RESULTS_OFFSET - 3,yOffset - 3,6,6);
		g.setColor(getColor(numBlack,numWhite,3));
		g.fillOval(xOffset - 3,yOffset + RESULTS_OFFSET - 3,6,6);
		g.setColor(getColor(numBlack,numWhite,4));
		g.fillOval(xOffset + RESULTS_OFFSET - 3,yOffset + RESULTS_OFFSET - 3,6,6);
	}

	//Prints the directions for what the board means
	private void paintDirections(Graphics g,  int xOffset, int yOffset)
	{
		g.setFont(DIRECTION_FONT);

		//paint Background
		g.setColor(Color.YELLOW);
		g.fillRect(xOffset-5,yOffset,175,34);

		//paint peg representations
		g.setColor(getColor(1,0,1));
		g.fillOval(xOffset,yOffset + 4,6,6);
		g.setColor(getColor(0,1,1));
		g.fillOval(xOffset,yOffset + 20,6,6);

		//paint words
		g.setColor(Color.BLACK);
		g.drawString("= correct color and place",xOffset + 8,yOffset + 12);
		g.drawString("= correct color, wrong place",xOffset + 8,yOffset + 28);
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

