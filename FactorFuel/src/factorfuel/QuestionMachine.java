package factorfuel;
import java.io.*;
import java.util.*;


public class QuestionMachine {
	
	public QuestionMachine() throws FileNotFoundException, IOException {
		
	}

	FileReader fr = new FileReader("factorquestions.txt"); // reads questions from a file
	BufferedReader br = new BufferedReader(fr);
	String Qline = br.readLine();
	private String questions[] = Qline.split(","); // stores questions
	
	FileReader fr2 = new FileReader("factoranswers.txt"); // reads answers from a file
	BufferedReader br2 = new BufferedReader(fr2);
	String Aline = br2.readLine();
	private String answers[] = Aline.split(","); // stores answers
	
	FileReader fr3 = new FileReader("factoranswersalt.txt"); // reads alternate answers from a file
	BufferedReader br3 = new BufferedReader(fr3);
	String AltAline = br3.readLine();
	private String answersAlt[] = AltAline.split(","); // stores alternate answers
	
	private ArrayList<Integer> used = new ArrayList<>(); // creates ArrayList to store previously used indexes
	private int index;
	private String answer;
	private String correct = "Correct";
	private boolean check;
	private String nextQ;
	private int lngt = questions.length; // length of array
	
	public String getQuestion() {
		
		do {
		Random rand = new Random(); // creates random number generator object
	    index = rand.nextInt(lngt); // stores random number into index
	    check = used.contains(index); // checks if index has already been used 
		} while(check==true); // repeats until unused index is generated

		nextQ = questions[index]; 
		return nextQ; // returns question at that index
	}
	public void setAnswer(String ans) {
		answer = ans; // stores user answer
	}
	public String result() {

		if(answer.equalsIgnoreCase(answers[index]) || answer.equalsIgnoreCase(answersAlt[index])) { // checks if questions is answered correctly
			used.add(index); // adds index to used ArrayList so question will not be asked again
			return correct; 
		}
		else{
			return answers[index]; // returns the correct answer to the question
		}	
	}
	
}
