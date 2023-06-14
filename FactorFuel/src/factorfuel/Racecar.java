package factorfuel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Racecar extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private final int panelWidth = 1000;
	private final int panelHeight = 100;
	private Image car;
	private Timer raceTimer;
	private int xVelocity = 1;
	private int y = 0; 
	private int x = 0; // race car position
	private int distance = 120;
	private JLabel factorFuel;
	
	Racecar(){
		factorFuel = new JLabel("Factor Fuel!"); // creates title 
		factorFuel.setFont(new java.awt.Font("Helvetica", 1, 72));
		this.setLayout(new GridBagLayout());
		this.add(factorFuel);
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		Border border = BorderFactory.createMatteBorder(0, 0, 20, 0, new Color(0x5c5E58));
		this.setBorder(border);
		this.setBackground(new Color(0x8d99ae));
		car = new ImageIcon("racecar.png").getImage(); // sets image of race car
		raceTimer = new Timer(5 , this); // changes speed of race car, a lower number meaning faster	
		
	}

	public void go() {
		raceTimer.start(); // moves race car
	}

	public void paint(Graphics g) {
		super.paint(g); // paints background
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(car, x, y, 100, 100, null); // draws race car
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(x == distance) { // moves race car in increments of 120 pixels each time the go method is called in the FactorFuel class
			raceTimer.stop(); 
			distance = distance +120;
			if(x==600) { // game is completed at this point
				raceTimer.start(); // moves race car
				distance = 0; // assigns distance 0 to no longer move race car in increments
			}
		}
		else if(x == 1200) {
			raceTimer.stop(); // race car is no longer visible to user, but is stopped so it doesn't go on forever
		}
		
		x = x + xVelocity; // moves race car at a velocity of 1 to the right
		repaint();
	}
	
}
