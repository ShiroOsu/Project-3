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
		//oklahoma does not have any independentVotes (state = 36) 
		int c = 1;
		int d = 0;
		boolean found = false;
		for(int i = -1; (i = file.indexOf("at\">", i + 1)) != -1; i++){
			int to = file.indexOf("</td", i);
			
			String s = file.substring(i + 4, to);
			
			// democraticVotes
			if(c == 1){
				dVotes.add(s);
			} 
			
			// republicanVotes
			if(c == 2){
				rVotes.add(s);
			}
			
			//TODO add iVotes.add("0"); when we reach oklahoma and then jump back to c = 1
			
			// independentVotes
			if(c == 3){
				iVotes.add(s);
			}
			
			c++;
			
			if(c > 3){
				c = 1;
			}
			
			
		}
	}
	
	
	private void init(){
		//read a file
		In in = new In(StdIn.readString());
		String text = in.readAll();
		
		getStates(text);
		getVotes(text);
		
		for(int i = 0; i < states.size(); i++){
			System.out.println("State: " + states.get(i) + " democraticVotes: " + dVotes.get(i) + " republicanVotes: " + rVotes.get(i) + " independentVotes: " + iVotes.get(i));
		}
		
		
	}
	
	
	public static void main(String[] args){
		new Election();
	}
}