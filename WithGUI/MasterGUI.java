package bonusassignment;//.WithGUI;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class MasterGUI extends JFrame
{
	//fonts
	final static Font TEXT_FONT = new Font("arial",Font.BOLD,16);
	final static Font MESSAGE_FONT = new Font("arial",Font.BOLD,12);
	private MasterMindBoard graphic;

	//Constructor makes a JFrame, adds the guess menu, and the board
	//needs the number of guesses for this game and an object to register button clicks
	public MasterGUI(int numGuesses, String correctGuess, MyButtons clickable)
	{
		//super("MasterMind");

		//layouts for the window and the menu-panel
		BorderLayout org = new BorderLayout();
		FlowLayout org2 = new FlowLayout();


		graphic = new MasterMindBoard(numGuesses, correctGuess, clickable);
		//registers the guess with the MasterMindBoard and resets the textbox
		

		//sets the values for the window
		setLayout(org);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,650);
		add(graphic);
		setVisible(true);
	}

	public void submitFeedback(String guess, int numRight, int numClose)
	{
			graphic.registerGuess(guess,numRight,numClose);
	
			repaint();
		}
	
	public void gameover(){
		graphic.gameOver();
	}
	
}


