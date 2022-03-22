import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class QueryView implements ActionListener, ListSelectionListener {
    private final JFrame win;
    private Vector bowlerdb;
    private final JList partyList;
    private final JList allBowlers;
    private final JButton highestplayer, lowestplayer, highestoverall, lowestoverall,  totalgamesplayed, totalnumberplayers;
    private String selectedNick;


    public QueryView(){
    	
    	
        
    	win = new JFrame("Query");
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);
        
        
        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 3));
        
        
        JPanel partyPanel =  new JPanel();
        partyPanel.setLayout(new FlowLayout());   
        partyPanel.setBorder(new TitledBorder("Query Result"));
        Vector empty = new Vector();
        empty.add("(Empty)");

        partyList = new JList(empty);
        partyList.setFixedCellWidth(200);
        partyList.setVisibleRowCount(13);
        partyList.addListSelectionListener(this);
        JScrollPane partyPane = new JScrollPane(partyList);
             
        partyPanel.add(partyPane);
        JPanel bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        bowlerPanel.setBorder(new TitledBorder("Bowler List"));

        try {
            bowlerdb = new Vector(BowlerFile.getBowlers());
        } catch (Exception e) {
            System.err.println("File Error");
            bowlerdb = new Vector();
        }
        allBowlers = new JList(bowlerdb);
        allBowlers.setVisibleRowCount(13);
        allBowlers.setFixedCellWidth(120);
        JScrollPane bowlerPane = new JScrollPane(allBowlers);
        bowlerPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allBowlers.addListSelectionListener(this);
        bowlerPanel.add(bowlerPane);
        
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1));
        
        Insets buttonMargin = new Insets(4, 4, 4, 4);

        
        highestplayer = new JButton("Highest score for player");
        JPanel local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(highestplayer);
        buttonPanel.add(local_button_panel);
        highestplayer.addActionListener(this);
        
       
        lowestplayer = new JButton("Lowest score for player");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(lowestplayer);
        buttonPanel.add(local_button_panel);
        
        lowestplayer.addActionListener(this);
        
        
        highestoverall = new JButton("Highest score overall");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(highestoverall);
        buttonPanel.add(local_button_panel);
        
        highestoverall.addActionListener(this);
        
        
        lowestoverall = new JButton("Lowest score overall");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(lowestoverall);
        buttonPanel.add(local_button_panel);
        
        lowestoverall.addActionListener(this);
        
       
        
        totalgamesplayed = new JButton("Total games played");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(totalgamesplayed);
        buttonPanel.add(local_button_panel);
        totalgamesplayed.addActionListener(this);
        
        
        
        totalnumberplayers  = new JButton("Total number of players");
        local_button_panel = new JPanel();
        local_button_panel.setLayout(new FlowLayout());
        local_button_panel.add(totalnumberplayers);
        buttonPanel.add(local_button_panel);
        totalnumberplayers.addActionListener(this);
        
       

        
        colPanel.add(partyPanel);
        colPanel.add(bowlerPanel);
        colPanel.add(buttonPanel);
        
        win.getContentPane().add("Center", colPanel);
        win.pack();
        
       
       
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);
    }

    public Vector getHighestAndLowestCaller(String selectedNick, Boolean isGeneral)
    {
        Vector returnVector = new Vector<>();
        try {
            returnVector =  ScoreHistoryFile.getHighestAndLowest(selectedNick, isGeneral);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return returnVector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(highestplayer)) {
            if (selectedNick != null) {
                Vector returnedVector = getHighestAndLowestCaller(selectedNick, false);

                Vector<String> partyVector = new Vector<>();
                partyVector.add("Player: " + selectedNick);
                partyVector.add(("Highest: " + returnedVector.get(0)));
                partyList.setListData(partyVector);
            }
        }
        else if (e.getSource().equals(lowestplayer)) {
            if (selectedNick != null) {
                Vector returnedVector = getHighestAndLowestCaller(selectedNick, false);

                Vector<String> partyVector = new Vector<>();
                partyVector.add("Player: " + selectedNick);
                partyVector.add(("Lowest: " + returnedVector.get(2)));
                partyList.setListData(partyVector);
            }
        }
  
        else if (e.getSource().equals(highestoverall)) {
            Vector returnedVector = getHighestAndLowestCaller(selectedNick, true);

            Vector<String> partyVector = new Vector<>();
            partyVector.add("Highest overall:");
            partyVector.add("Player: "+returnedVector.get(1));
            partyVector.add("Score: "+ returnedVector.get(0));
            partyList.setListData(partyVector);
        }
        else if (e.getSource().equals(lowestoverall)) {
            Vector returnedVector = getHighestAndLowestCaller(selectedNick, true);

            Vector<String> partyVector = new Vector<>();
            partyVector.add("Lowest overall:");
            partyVector.add("Player: "+returnedVector.get(3));
            partyVector.add("Score: "+ returnedVector.get(2));
            partyList.setListData(partyVector);
        }
    
        else if (e.getSource().equals(totalgamesplayed)) {
            try {
                int ans = ScoreHistoryFile.getCount();
                Vector<String> partyVector = new Vector<>();
                partyVector.add("Total number of games");
                partyVector.add(" played : "+ ans);
                partyList.setListData(partyVector);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource().equals(totalnumberplayers)) {
            try {
                int ans = BowlerFile.getCount();
                Vector<String> partyVector = new Vector<>();
                partyVector.add("Total number of\n players : "+ ans);
                partyList.setListData(partyVector);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(allBowlers)) {
            selectedNick =
                    ((String) ((JList) e.getSource()).getSelectedValue());
        }
    }
}
