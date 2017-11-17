/**
* @author Niklas Granskog
*
* Compilation:  javac CompareTxt.java
* Execution:    java CompareTxt 5 10000
* Data files:   http://www.cs.princeton.edu/introcs/33design/Constitution.txt
*               http://www.cs.princeton.edu/introcs/33design/TomSawyer.txt
*               http://www.cs.princeton.edu/introcs/33design/HuckFinn.txt
*               http://www.cs.princeton.edu/introcs/33design/Prejudice.txt
*               http://www.cs.princeton.edu/introcs/33design/Picture.java
*/
import java.util.ArrayList;

public class CompareTxt{
	/**
	* Fields
	*/
	private boolean readingFiles = true;
	private ArrayList<String> filenames;
	private ArrayList<Sketch> sketches;
	private String name = "";
	private double c = 0.0;
	private int k, d;
	
	/**
	* Constructor
	*/
	public CompareTxt(int k, int d){
		this.k = k;
		this.d = d;
		
		// initialize ArrayLists
		filenames = new ArrayList<>();
		sketches = new ArrayList<>();
		init(); //move to init();
	}
	
	private void init(){
		System.out.println("Type in the name of the file you wish to compare other files with...");
		filenames.add(StdIn.readString());
		
		read(); // move to read();
	}
	
	private void read(){
		// You can type in all the textfiles in the same commandrow or one by one
		System.out.println("Type the name of the files you want to compare your file with");
		System.out.println("When you have entered all of them type done and hit enter");
			while(readingFiles){
				String s = StdIn.readString();
				if(s.equals("done")){
					readingFiles = false; // end loop
				} else {
					filenames.add(s); // add textfiles to ArrayList filenames
				}
			}
		createSketches(); // move to createSketches();
	}
	
	private void createSketches(){
			for (int i = 0; i < filenames.size(); i++) {
				In in = new In(filenames.get(i)); // read one file
				String text = in.readAll(); // read that file and move it into text
				sketches.add(new Sketch(text, k, d)); // make a sketch for that text and add it to the ArrayList
			}
			results(); // move to results();
	}
	

	
	private void results(){
		System.out.println(filenames.get(0) + " compared to: ");
		
		// Notice that i == 1, there is no need to compare the text file to itself
		for(int i = 1; i < filenames.size(); i++){
			System.out.printf(filenames.get(i) + "\t" + "%.2f", (sketches.get(0).similarTo(sketches.get(i))));
			System.out.println("");
		}
		System.out.println("The text that is more similar to " + filenames.get(0) + " is " + fileName());
	}
	
	private String fileName(){
		// Loop through the filenames and give the highest "similar value" to c
		for(int i = 1; i < filenames.size(); i++){
			if((sketches.get(0).similarTo(sketches.get(i))) > c){
				c = (sketches.get(0).similarTo(sketches.get(i)));
			}
		}
		// if the filename has the same "similar value" as c, return that name 
		for(int i = 1; i < filenames.size(); i++){
			if(c == (sketches.get(0).similarTo(sketches.get(i)))){
				name = filenames.get(i);
			}
		}
		return name;
	}
	

	public static void main(String[] args) {
			int a = Integer.parseInt(args[0]); // k
			int b = Integer.parseInt(args[1]); // d
			new CompareTxt(a, b); // move to Constructor
	}
}