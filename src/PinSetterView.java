/*
 * PinSetterView/.java
 *
 * Version:
 *   $Id$
 *
 * Revision:
 *   $Log$
 */

/**
 *  constructs a prototype PinSetter GUI
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;


public class PinSetterView implements PinsetterObserver {


	private PinSetterViewProduct pinSetterViewProduct;
	private Vector pinVect = new Vector ( );
    private JPanel secondRoll;
    private  JPanel emojiPanel;
    private JLabel emoji;
    public PinSetterView(int laneNum) {

        
        this.pinSetterViewProduct = new PinSetterViewProduct(new JFrame("Lane " + laneNum + ":"));
		pinSetterViewProduct.getFrame().getContentPane().setLayout(new BorderLayout());
        ((JPanel) pinSetterViewProduct.getFrame().getContentPane()).setOpaque(false);

        Container cpanel = pinSetterViewProduct.getFrame().getContentPane();

        
        JPanel pins = new JPanel();
        pins.setLayout(new GridLayout(4, 7));

        //Top of GUI indicates first or second roll

        JPanel top = new JPanel();

        JPanel firstRoll = new JPanel();
        firstRoll.setBackground(Color.yellow);

        secondRoll = new JPanel();
        secondRoll.setBackground(Color.black);

        emojiPanel = new JPanel();
        emoji = new JLabel("");
        emojiPanel.add(emoji);

        top.add(firstRoll, BorderLayout.WEST);

        top.add(secondRoll, BorderLayout.EAST);

        top.add(emojiPanel, BorderLayout.EAST);



        var icon = createImageIcon("pin_standing.png");
        JPanel one = new JPanel();
        JLabel oneL = new JLabel(icon);
        one.add(oneL);
        JPanel two = new JPanel();
        JLabel twoL = new JLabel(icon);
        two.add(twoL);
        JPanel three = new JPanel();
        JLabel threeL = new JLabel(icon);
        three.add(threeL);
        JPanel four = new JPanel();
        JLabel fourL = new JLabel(icon);
        four.add(fourL);
        JPanel five = new JPanel();
        JLabel fiveL = new JLabel(icon);
        five.add(fiveL);
        JPanel six = new JPanel();
        JLabel sixL = new JLabel(icon);
        six.add(sixL);
        JPanel seven = new JPanel();
        JLabel sevenL = new JLabel(icon);
        seven.add(sevenL);
        JPanel eight = new JPanel();
        JLabel eightL = new JLabel(icon);
        eight.add(eightL);
        JPanel nine = new JPanel();
        JLabel nineL = new JLabel(icon);
        nine.add(nineL);
        JPanel ten = new JPanel();
        JLabel tenL = new JLabel(icon);
        ten.add(tenL);

        

        pinVect.add(oneL);
        pinVect.add(twoL);
        pinVect.add(threeL);
        pinVect.add(fourL);
        pinVect.add(fiveL);
        pinVect.add(sixL);
        pinVect.add(sevenL);
        pinVect.add(eightL);
        pinVect.add(nineL);
        pinVect.add(tenL);


        
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(seven);
        pins.add(new JPanel());
        pins.add(eight);
        pins.add(new JPanel());
        pins.add(nine);
        pins.add(new JPanel());
        pins.add(ten);
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());

       

        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(four);
        pins.add(new JPanel());
        pins.add(five);
        pins.add(new JPanel());
        pins.add(six);
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());

       

        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(two);
        pins.add(new JPanel());
        pins.add(three);
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());

       
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(one);
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
        pins.add(new JPanel());
       

        top.setBackground(Color.black);

        cpanel.add(top, BorderLayout.NORTH);

        pins.setBackground(Color.black);
        pins.setForeground(Color.yellow);
        
        cpanel.add(pins, BorderLayout.CENTER);
        
        
        JPanel temp1 = new JPanel();
        JPanel gif = new JPanel();
        ImageIcon image = new ImageIcon("bowling_gif.gif");
        JLabel gifL = new JLabel(image);
        gif.add(gifL);
        temp1.add(gif);
        cpanel.add(temp1,BorderLayout.SOUTH);
       
        
        pinSetterViewProduct.getFrame().pack();

    }
    
    

    
    protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
    		return new ImageIcon(imgURL, "Bowling Pin");
		} else {
    		System.err.println("Couldn't find path: " + path);
    	return null;
		}
    }
    
    public void receivePinsetterEvent(PinsetterEvent pe) {
        int count = 0;
        var icon = createImageIcon("pin_standing.png");
        var icon2 = createImageIcon("pin_knocked.jpg");
        if (!(pe.isFoulCommited())) {
            JLabel tempPin;
            for (int c = 0; c < 10; c++) {
                boolean pin = pe.pinKnockedDown(c);
                tempPin = (JLabel) pinVect.get(c);
                if (pin) {
                    count++;
                    tempPin.setIcon(icon2);
                }
		else tempPin.setIcon(icon);
            }
        }
        if (pe.getThrowNumber() == 1) {
            secondRoll.setBackground(Color.yellow);
        }
        if (pe.pinsDownOnThisThrow() == -1) {
            for (int i = 0; i != 10; i++) {
                ((JLabel) pinVect.get(i)).setIcon(icon);
            }
            secondRoll.setBackground(Color.black);
        }

        System.out.println("Count: "+ count);
        if (count == 0) {
            emoji.setText("\uD83D\uDE36");
            System.out.println("\uD83D\uDE36");
        } else if (count < 2) {
            emoji.setText("\uD83D\uDE14");
            System.out.println("\uD83D\uDE14");
        } else if (count < 4) {
            emoji.setText("\uD83D\uDE35");
            System.out.println("\uD83D\uDE35");
        } else if (count < 6) {
            emoji.setText("\uD83D\uDE1B");
            System.out.println("\uD83D\uDE1B");
        } else if (count < 8) {
            emoji.setText("\uD83D\uDE0A");
            System.out.println("\uD83D\uDE0A");
        } else if (count < 10) {
            emoji.setText("\uD83D\uDE04");
            System.out.println("\uD83D\uDE04");
        }
    }
    
    public void show() {
        pinSetterViewProduct.show();
    }

    public void hide() {
        pinSetterViewProduct.hide();
    }

    public static void main(String[] args) {
        PinSetterView pg = new PinSetterView(1);
    }
    
}
