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
		this.bodySize=size;
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

	public double getMass(){
		return	this.mass;
	}

	public double getXVelocity(){
		return this.xDirectVelocity;

	}
	public double getYVelocity(){
		return this.yDirectVelocity;
	}

	public void setXCoordinate(int position){
		this.xCoordinate=position;
	}

	public void setYCoordinate(int position){
		this.yCoordinate=position;
	}

	//public void setXVelocity(double velocity){
	//	this.xDirectVelocity=velocity;
	//}
	//public void setYVelocity(double	velocity){
	//	this.yDirectVelocity=velocity;
	//}
}