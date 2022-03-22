import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

public class Queries implements ActionListener{
    
    private final JFrame win1;
    private final JButton max;
    private final JButton min;
    private final JButton count;
    private final JButton players;
    private final JButton close;
    private JLabel query;
    private final JPanel answer;

    public Queries(){
        
    	win1 = new JFrame("Query selection window");
        win1.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win1.getContentPane()).setOpaque(false);
        

        
        JPanel colPanel = new JPanel();
        colPanel.setLayout(new BorderLayout());
        
        
        JPanel controlsPanel =  new JPanel();
        controlsPanel.setLayout(new GridLayout(4, 10));

       
        max = new JButton("Score");
        JPanel local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(max);
        controlsPanel.add(local_button_panel);
        
        max.addActionListener(this);

        
        min = new JButton("Lowest Score");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(min);
        controlsPanel.add(local_button_panel);
        
        min.addActionListener(this);

   

     
        
        count = new JButton("Number of games played");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(count);
        controlsPanel.add(local_button_panel);
        count.addActionListener(this);

        
        players = new JButton("Number of players in database");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(players);
        controlsPanel.add(local_button_panel);
        players.addActionListener(this);

        
        answer = new JPanel();
        answer.setLayout(new FlowLayout());
        query = new JLabel("Answer to the query");
        answer.add(query);
        
       
        JPanel closePanel = new JPanel();
        closePanel.setLayout(new FlowLayout());
        
       
        close = new JButton("Close");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(close);
        controlsPanel.add(local_button_panel);
        close.addActionListener(this);
        
        
        
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win1.setLocation(
                ((screenSize.width) / 2) - ((win1.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win1.getSize().height) / 2));
        win1.setVisible(true);
        
        colPanel.add(controlsPanel, "North");
        colPanel.add(answer, "Center");
        colPanel.add(closePanel, "South");

        win1.getContentPane().add("Center", colPanel);
        win1.pack();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(close)) {
            win1.setVisible(false);
        }
        if (e.getSource().equals(max)) {
            try {
                String ans = ScoreHistoryFile.getMax();
                query.setText("Highest Score : "+ ans);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource().equals(min)) {
            try {
                String ans = ScoreHistoryFile.getMin();
                query.setText("Lowest Score : "+ ans);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
  
        if (e.getSource().equals(count)) {
            try {
                int ans = ScoreHistoryFile.getCount();
                query.setText("Total number of games played : "+ ans);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource().equals(players)) {
            try {
                int ans = BowlerFile.getCount();
                query.setText("Total number of players in database : "+ ans);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
