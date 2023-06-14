package factorfuel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.Border;

public class FactorFuel {

	private JFrame frame;
	private JPanel intro;
	private JPanel instructions;
	private JPanel game;
	private Timer timer;
	private JLabel timerLabel;
    private long lastTickTime;
    private JLabel questionLabel;
    private JLabel FactorFuel2;
    private JLabel answerLabel;
    private JTextField answerBox;
    private QuestionMachine machine;
    public int correctAnsCounter;
    private JLabel correctAns;
    private JLabel factorThisLabel;
    private JButton Enter;
    private String strAns;
    private JLabel answerToLastLabel;
    private JLabel showProgress;
    private int progress = 5;
    private long runningTime;
    private String elapsedTime;
    private JLabel elapsedTimeLabel;
    private JButton Quit;
    private Racecar car;
    
	GridBagConstraints c = new GridBagConstraints();
	
	public FactorFuel() {
		prepareAPP();
	}
	
	private void prepareAPP() {
	 
		frame = new JFrame(); // sets up frame
		frame.setVisible(true);
		frame.setTitle("Factor Fuel!");
		frame.setLayout(new BorderLayout());
		Border border = BorderFactory.createLineBorder(new Color(0x2b2d42),10);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		intro = new JPanel(); // sets up introductory panel
		intro.setBackground(new Color(0x8d99ae));
		intro.setBorder(border);
		intro.setLayout(new GridBagLayout());
		
		instructions = new JPanel(); // sets up instructions panel
		instructions.setBackground(new Color(0x8d99ae));
		instructions.setBorder(border);
		instructions.setLayout(new GridBagLayout());

		game = new JPanel(); // sets up game panel
		game.setBackground(new Color(0x8d99ae));
		game.setBorder(border);
		game.setLayout(new GridBagLayout());

		frame.add(intro);
		frame.pack();
		frame.setSize(1920, 1080);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}

	void showAPP(){	    
		 // introductory panel components
		 JLabel FactorFuel = new JLabel("Factor Fuel!"); // creates title
		 FactorFuel.setFont(new java.awt.Font("Helvetica", 1, 72));
		 c.gridx = 0;
		 c.gridy = 0;
		 c.anchor = GridBagConstraints.CENTER;
		 c.insets = new Insets(0, 0, 100, 0);
		 intro.add(FactorFuel, c);

		 JButton Play = new JButton("PLAY"); // creates play button 
		 Play.setBackground(new Color(0xedf2f4));
		 Play.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 frame.remove(intro);
				 frame.add(instructions); // adds instructions panel and progresses user forward
				 frame.setVisible(true);
			 }
		 });
		 c.gridx = 0;
		 c.gridy = 1;
		 c.fill = GridBagConstraints.BOTH;
		 c.insets = new Insets(0, 110, 25, 110);
		 c.anchor = GridBagConstraints.CENTER;
		 intro.add(Play, c);

		 Quit = new JButton("QUIT"); // creates quit button
		 Quit.setBackground(new Color(0xedf2f4));
		 Quit.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.exit(0); // closes program window
			 }
		 });
		 c.gridx = 0;
		 c.gridy = 2;
		 c.insets = new Insets(0, 110, 0, 110);
		 c.anchor = GridBagConstraints.CENTER;
		 intro.add(Quit, c);
			
		 // instructions panel components
		 JLabel instructionsLabel = new JLabel("Instructions."); // creates instructions title
		 instructionsLabel.setFont(new java.awt.Font("Helvetica", 1, 36));
		 c.gridx = 0;
		 c.gridy = 0;
		 c.anchor = GridBagConstraints.CENTER;
		 c.insets = new Insets(0, 0, 50, 0);
		 instructions.add(instructionsLabel, c);
	
		 JLabel instructionsTxt = new JLabel(); // creates instructions text
		 instructionsTxt.setBackground(new Color(0x8d99ae));
		 instructionsTxt.setText("<html>Welcome to Factor Fuel, where you’re going to fuel your car<br/>"
							   + "with your factoring abilities! Answer the questions as fast as<br/>"
							   + "possible to propel yourself towards the finish line. Answer in<br/>"
							   + "the form \"(5x-2)(2x+1)\" or \"(x+3)(x+2)\", and be sure to leave<br/>"
							   + "out spaces they'll only slow your car down. For each incorrect<br/>"
							   + "answer, a correct answer will be displayed for you to jot down<br/>"
							   + "in your notes and revisit later. Push your car to the limits out<br/>"
							   + "there, and goodluck setting personal bests! You got this!<html>"); // sets text of label
		 instructionsTxt.setFont(new java.awt.Font("Helvetica", 1, 16));
		 c.gridx = 0;
		 c.gridy = 1;
		 c.fill = GridBagConstraints.BOTH;
	  	 c.insets = new Insets(0, 0, 25, 0);
		 c.anchor = GridBagConstraints.CENTER;
		 instructions.add(instructionsTxt, c);
	
		 JButton Continue = new JButton("CONTINUE"); // creates continue button
		 Continue.setBackground(new Color(0xedf2f4));
		 Continue.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 frame.remove(instructions);
				 frame.add(game); // adds game panel and progresses user forward
				 frame.setVisible(true);
			 }
		 });
		 c.gridx = 0;
		 c.gridy = 2;
		 c.insets = new Insets(0, 150, 0, 150);
		 c.anchor = GridBagConstraints.CENTER;
		 instructions.add(Continue, c);

		 // game panel components
		 try { 
			 machine = new QuestionMachine(); // creates object from QuestionMachine class, is used for providing and marking questions
	     }  
		 catch (FileNotFoundException e) { 
		 } 
		 catch (IOException e) { 
		 } 
		 
		 FactorFuel2 = new JLabel("Factor Fuel!"); // creates title for game panel
		 FactorFuel2.setFont(new java.awt.Font("Helvetica", 1, 24));
		 c.gridx = 0;
		 c.gridy = 0;
		 c.insets = new Insets(50, 50, 50, 50);
		 game.add(FactorFuel2, c); 
		 
		 timerLabel = new JLabel(String.format("%02d:%02d.%03d", 0, 0, 0)); // creates timer with minutes seconds and milliseconds
		 timerLabel.setFont(new java.awt.Font("Helvetica", 1, 16));
         timer = new Timer(100, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 runningTime = System.currentTimeMillis() - lastTickTime;
                 Duration duration = Duration.ofMillis(runningTime);
                 long minutes = duration.toMinutes();
                 duration = duration.minusMinutes(minutes);
                 long millis = duration.toMillis();
                 long seconds = millis / 1000;
                 millis -= (seconds * 1000);
                 timerLabel.setText(String.format("%02d:%02d.%03d", minutes, seconds, millis)); // adds timer to label to display elapsed time
             }
         });
         c.gridx = 0;
		 c.gridy = 1;
		 c.gridwidth = 1;
		 game.add(timerLabel, c);
		 
		 JButton Start = new JButton("START"); // creates start button
		 Start.setBackground(new Color(0xedf2f4));
		 Start.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (!timer.isRunning()) {
                     lastTickTime = System.currentTimeMillis();
                     timer.start(); // starts timer when start button is clicked
                 }
				 car = new Racecar(); // creates object from Racecar class to display race car and its movement
				 frame.remove(game);
				 frame.setLayout(new GridBagLayout());
				 frame.getContentPane().setBackground(new Color(0x8d99ae));
				 ((JComponent) frame.getContentPane()).setBorder(BorderFactory.createLineBorder(new Color(0x2b2d42),10));
				 c.gridx = 0;
				 c.gridy = 0;
				 c.insets = new Insets(0, 0, 100, 0);
				 frame.add(game, c);
				 c.gridx = 0;
				 c.gridy = 1;
				 frame.add(car, c);
				 game.setBorder(BorderFactory.createLineBorder(new Color(0x2b2d42),5));
				 // adds several already prepared components using GridBagLayout below
				 game.remove(FactorFuel2);
				 c.gridx = 0;
				 c.gridy = 0;
				 c.gridwidth = 2;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(factorThisLabel, c);
				 c.gridx = 1;
				 c.gridy = 0;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(questionLabel, c);
				 c.gridx = 1;
				 c.gridy = 1;
				 c.insets = new Insets(50, 50, 50, 0);
				 game.add(answerLabel, c);
				 c.gridx = 3;
				 c.gridy = 1;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(answerBox, c);
				 c.gridx = 0;
				 c.gridy = 2;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(answerToLastLabel, c);
				 correctAns = new JLabel(); // correct answer label created to display correct answer for the user
				 correctAns.setFont(new java.awt.Font("Helvetica", 1, 16));
				 correctAns.setText("               ");
				 c.gridx = 1;
				 c.gridy = 2;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(correctAns, c);
				 game.remove(Start);
				 c.gridx = 3; 
				 c.gridy = 0; 
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(Enter, c);
				 c.gridx = 3;
				 c.gridy = 2;
				 c.insets = new Insets(50, 50, 50, 50);
				 game.add(showProgress, c);
				 frame.setVisible(true);
			 }
		 });
		 c.gridx = 1;
		 c.gridy = 0;
		 c.insets = new Insets(20, 20, 20, 20);
		 game.add(Start, c);
		 
		 Enter = new JButton("ENTER"); // creates enter button which is used to submit an answer
		 Enter.setBackground(new Color(0xedf2f4));
		 Enter.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent e){
		    	 strAns = answerBox.getText(); // stores answer submitted into strAns
		    	 machine.setAnswer(strAns); // passes strAns into the setAnswer method
		    	 String result = machine.result(); // call result method, if answer is correct will store "Correct" in result and store correct answer to the question if wrong
				 String correct = "Correct"; // created to use as a comparison 
				
				 if(result.equals(correct)) { // checks if question was correctly answered 
					 ++correctAnsCounter;
					 progress-= 1;
					 showProgress.setText(progress+" to go!");
					 correctAns.setText("Nice one!");
					 car.go(); //calls go method to move the race car across the screen
					 
					 if(correctAnsCounter == 5) { // checks if 5 questions have been answered correctly signaling completion of game
						 timer.stop(); // stops the timer
						 Instant instant = Instant.ofEpochMilli(runningTime);
						 ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
						 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
						 elapsedTime = formatter.format(zdt); // stores elapsed time in properly formatted style
						 elapsedTimeLabel = new JLabel("You took "+elapsedTime+" minutes. Hope to see you racing again soon!"); //creates label to display elapsed time
						 elapsedTimeLabel.setBorder(BorderFactory.createLineBorder(new Color(0x2b2d42),5));
						 elapsedTimeLabel.setFont(new java.awt.Font("Helvetica", 1, 32));
						 game.setBorder(BorderFactory.createLineBorder(new Color(0x2b2d42),0));
						 // removes components below
						 game.remove(factorThisLabel);
						 game.remove(questionLabel);
						 game.remove(answerLabel);
						 game.remove(answerToLastLabel);
						 game.remove(answerBox);
						 game.remove(correctAns);
						 game.remove(Enter);
						 game.remove(showProgress);
						 game.remove(timerLabel);
						 game.remove(FactorFuel2);						 
						 c.gridx = 0;
						 c.gridy = 0;
						 c.insets = new Insets(0, 0, 100, 0);
						 game.add(elapsedTimeLabel); // displays final label
						 frame.setVisible(true);
					 }
				 }
				 else {
					 correctAns.setText(result); //displays correct answer to the user
				 }
		    	 	answerBox.setText(""); // resets text field
		    	 	questionLabel.setText(machine.getQuestion()); // calls getQuestion method to provide the user with another question
		     }
		 });
		 
		 // prepared components of game panel that are displayed start button is clicked
		 questionLabel = new JLabel(machine.getQuestion()); // creates question label, uses getQuestion method to provide an initial question
		 questionLabel.setFont(new java.awt.Font("Helvetica", 1, 16));

		 factorThisLabel = new JLabel("Factor this:"); // creates factor this label
		 factorThisLabel.setFont(new java.awt.Font("Helvetica", 1, 16));

		 answerLabel = new JLabel("Answer here:"); // creates answer here label
		 answerLabel.setFont(new java.awt.Font("Helvetica", 1, 16));

		 answerToLastLabel = new JLabel("Correct answer:"); // creates correct answer label
		 answerToLastLabel.setFont(new java.awt.Font("Helvetica", 1, 16));

		 answerBox = new JTextField(8); // creates text field for user to submit answers
		 answerBox.setBackground(new Color(0xedf2f4));

		 showProgress = new JLabel(progress+" to go!"); // creates label that is updated with user progress
		 showProgress.setFont(new java.awt.Font("Helvetica", 1, 16));
	 }	 
}
