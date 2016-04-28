package bonusassignment;//.WithGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyButtons extends JFrame implements ActionListener {
	  public JButton red_button = new JButton("RED");
	  public JButton blue_button = new JButton("BLUE");
	  public JButton purple_button = new JButton("PURPLE");
	  public JButton orange_button = new JButton("ORANGE");
	  public JButton yellow_button = new JButton("YELLOW");
	  public JButton green_button = new JButton("GREEN");
	  public JButton submit_button = new JButton("SUBMIT");
	  public JButton redact_button = new JButton("REDACT");
	  
	  final static Color PURPLE = new Color(160, 32, 240);

	  public MyButtons() {
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
	    red_button.setLayout(null);
	    red_button.setLayout(null);
	    red_button.setLayout(null);
	    red_button.setLayout(null);
	    red_button.setLayout(null);
	    red_button.setLayout(null);
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
