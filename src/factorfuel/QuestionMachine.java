package factorfuel;
import java.io.*;
import java.util.*;


public class QuestionMachine {
	
	public QuestionMachine() throws FileNotFoundException, IOException {
		
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(
	         this.getClass().getClassLoader().getResourceAsStream("resources/factorquestions.txt")));
	String Qline = br.readLine();
	private String questions[] = Qline.split(","); // stores questions
	
	BufferedReader br2 = new BufferedReader(new InputStreamReader(
	         this.getClass().getClassLoader().getResourceAsStream("resources/factoranswers.txt")));
	String Aline = br2.readLine();
	private String answers[] = Aline.split(","); // stores answers
	
	BufferedReader br3 = new BufferedReader(new InputStreamReader(
	         this.getClass().getClassLoader().getResourceAsStream("resources/factoranswersalt.txt")));
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
