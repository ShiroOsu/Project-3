public class Region{
	private String name;
	private String code;
	private Polygon poly;
	
	public Region(String name, String code, Polygon poly){
		this.name = name;
		this.code = code;
		this.poly = poly;
	}
	
	public void draw(){
		poly.draw();
	}
	
	public String toString(){
		String s = name + ", " + code;
		return s;
	}
	
	
	public static void main(String[] args){
		Region r = new Region("Square", "center", new Polygon(new In("C:/Users/Niklas/JavaProjects/points.txt")));
		System.out.println(r);
		r.draw();
	}
}