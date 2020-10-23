import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class NBodies{
	private String name;
	private int mass;
	private int xCoordinate;
	private int yCoordinate;
	private int xDirectVelocity;
	private int yDirectVelocity;
	private int bodySize;

	private static class CelestBody{
		private String name;
		private int mass;
		private int xCoordinate;
		private int yCoordinate;
		private int xDirectVelocity;
		private int yDirectVelocity;
		private int bodySize;

		public CelestBody(String name, int mass, int xCoordinate, int yCoordinate, int xDirectVelocity, int yDirectVelocity, int size){
			this.name=name;
			this.mass=mass;
			this.xCoordinate=xCoordinate;
			this.yCoordinate=yCoordinate;
			this.xDirectVelocity=xDirectVelocity;
			this.yDirectVelocity=yDirectVelocity;
			this.bodySize=bodySize;
		}
		public String giveName(){
			return this.name;
		}
	}
	public static void main(String[] args){
		CelestBody p1= new CelestBody("Earth", 20000, 20, 5,16, 3000, 20000);
		System.out.println(p1.giveName());

	}

}