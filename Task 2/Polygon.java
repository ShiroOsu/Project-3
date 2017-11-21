import java.util.ArrayList;
public class Polygon{
	private In in;
	private double[] x, y;
	
	public Polygon(In in){
		this.in = in;
		
		x = new double[in.readInt()];
		y = new double[x.length];
		
		for(int i = 0; i < x.length; i++){
			x[i] = in.readDouble();
			y[i] = in.readDouble();
		}
	}
	
	public void draw(){
		StdDraw.filledPolygon(x, y);
	}
	
	public static void main(String[] args){
		Polygon p = new Polygon(new In("Where your file with coordinates is located"));
		p.draw();
	}
}
