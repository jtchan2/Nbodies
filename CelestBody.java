import java.util.*;

public class CelestBody{
	private String name;
	private double mass;
	private double xCoordinate;
	private double yCoordinate;
	private double xDirectVelocity;
	private double yDirectVelocity;
	private int bodySize;
	public CelestBody(String name, double mass, double xCoordinate, double yCoordinate, double xDirectVelocity, double yDirectVelocity, int size){
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
	public double getXPos(){
		return this.xCoordinate;
	}
	public double getYPos(){
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

	public void setXCoordinate(double position){
		this.xCoordinate=position;
	}

	public void setYCoordinate(double position){
		this.yCoordinate=position;
	}

	public void setXVelocity(double velocity){
		this.xDirectVelocity=velocity;
	}
	public void setYVelocity(double	velocity){
		this.yDirectVelocity=velocity;
	}
}