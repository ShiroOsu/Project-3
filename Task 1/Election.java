/**
* @author Niklas Granskog
*/
import java.util.ArrayList;

public class Election{
	private ArrayList<String> states, dVotes, rVotes, iVotes;

	public Election(){
		states = new ArrayList<>();
		dVotes = new ArrayList<>();
		rVotes = new ArrayList<>();
		iVotes = new ArrayList<>();
		System.out.println("Type in the name of the USA text file...");
		init();
	}
	
	private void getStates(String file){
		for(int i = -1; (i = file.indexOf("><b>", i + 1)) != -1; i++){
			int to = file.indexOf("</b>", i);

			String s = file.substring(i + 4, to);
		
			states.add(s);
		}
	}
	
	private void getVotes(String file){
		// first comes democraticVotes then republicanVotes and last independentVotes
		//oklahoma does not have any independentVotes
		int c = 0;
		int d = 0;
		boolean found = false;
		for(int i = -1; (i = file.indexOf("at\">", i + 1)) != -1; i++){
			int to = file.indexOf("</td", i);
			
			String s = file.substring(i + 4, to);
			c++;
			d++;
	
			if(c > 3){
				c = 1;
			}
			
			// democraticVotes
			if(c == 1){
				dVotes.add(s);
			} else if(c == 2){ // republicanVotes
				rVotes.add(s);
			}
			
			
			//check if state is oklahoma, loop runs 152 times
			//d == 110 is when we add republicanVotes to Oklahoma so we need to add 0 to independentVotes
			//then jump back to start of loop
			if(d == 110){
				iVotes.add("0");
				c = 0;
			}
			
			
			
			if(c == 3){ // independentVotes
				iVotes.add(s);
			}
		}
	}
	
	
	private void init(){
		//read a file
		In in = new In(StdIn.readString());
		String text = in.readAll();
		
		getStates(text);
		getVotes(text);
		
		//print everything out to console
		for(int i = 0; i < states.size(); i++){
			System.out.println("State: " + states.get(i) + " DemocraticVotes: " + dVotes.get(i) + "," + 
			" RepublicanVotes: " + rVotes.get(i) + "," + " IndependentVotes: " + iVotes.get(i));
		}
		
		
	}
	
	
	public static void main(String[] args){
		new Election();
	}
}
