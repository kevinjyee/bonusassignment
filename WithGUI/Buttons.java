package bonusassignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Buttons extends JFrame implements ActionListener {
	  private JButton button1 = new JButton("Click me!");
	  private JButton button2 = new JButton("Click me too!");

	  public Buttons() {
	    button1.addActionListener(this);
	    button2.addActionListener(this);
	    //... add buttons to frame ...
	  }

	  public void actionPerformed(ActionEvent evt) {
	    Object src = evt.getSource();
	    if (src == button1) {
	      //... perform action for button 1
	    } else if (src == button2) {
	      //... perform action for button 2
	    }
	  }
}
