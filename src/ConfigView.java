/* GameSettingsView.java
 */

/**
 * Class for game settings
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class ConfigView implements ActionListener {
    private JFrame win;
	private JTextField numLanesF;
  
    private JTextField maxPatronsF;
   
	private JButton submitButton;

    public int numLanes;
    public int maxPatrons;
  
    private boolean formSubmitted;


    public ConfigView() {


        numLanes = 3;
        maxPatrons = 6 ;
      
        formSubmitted = false;


        win = new JFrame("Enter Value");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(5, 5));

    

		JPanel maxPatronsPanel = new JPanel();
		maxPatronsPanel.setLayout(new FlowLayout());
		maxPatronsPanel.setBorder(new TitledBorder("Max Number of Patrons Per Party"));

		

        JPanel submitPanel = new JPanel();
		submitPanel.setLayout(new FlowLayout());
		
		
	

        maxPatronsF = new JTextField(20);
        maxPatronsPanel.add(maxPatronsF);
        
       

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitPanel.add(submitButton);

		colPanel.add(maxPatronsPanel);
		
		colPanel.add(submitPanel);
		

        win.getContentPane().add("Center", colPanel);

		win.pack();
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.show();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(submitButton)) {
        	
            try {
                String maxPatronsstr = maxPatronsF.getText();
                maxPatrons = Integer.parseInt(maxPatronsstr);
            } catch (NumberFormatException exception) {
                maxPatrons = 6;
            }
          

            formSubmitted = true;
        }
        win.hide();
    }

    public void getGameParameters() {

        while(!formSubmitted) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

}
