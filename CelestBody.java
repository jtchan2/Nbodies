import java.util.*;

public class CelestBody{
	private String name;
	private double mass;
	private int xCoordinate;
	private int yCoordinate;
	private double xDirectVelocity;
	private double yDirectVelocity;
	private int bodySize;
	public CelestBody(String name, double mass, int xCoordinate, int yCoordinate, double xDirectVelocity, double yDirectVelocity, int size){
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
	public int getXPos(){
		return this.xCoordinate;
	}
	public int getYPos(){
		return this.yCoordinate;
	}
	public int bodySize(){
		return this.bodySize;
	}
	public double getXVelocity(){
		return this.xDirectVelocity;

	}
	public void setXVelocity(double velocity){
		this.xDirectVelocity=velocity;
	}
}