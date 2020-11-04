import java.util.*;

import javax.swing.JPanel;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.io.IOException;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class NBodies extends JPanel implements ActionListener{
	private String name;
	private double mass;
	private int xCoordinate;
	private int yCoordinate;
	private double xDirectVelocity;
	private double yDirectVelocity;
	private int bodySize;
	private List<String[]> storage;
	private List<CelestBody> eList;
	private double scale;
	private double xforce;
	private	double yforce;
	//Gravity is the universal gravity constant
	private double gravity=6.67*Math.pow(10,-11);


	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data=value;
			next=null;
		}
	}

	
	//timer that is used to update/ aniamte planets, set o 500 because if at 5, planets immidiately dispappear after 1 second where 500 you can see 1 planet for 1 to 2 frames
	Timer tm= new Timer(50, this);

	//this method bascially initializes the shapes onto the Jframe
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		for(int i=0;i<eList.size();i++){
			CelestBody holder=eList.get(i);
			//System.out.println(holder.getXPos());
			g.setColor(Color.RED);
			g.fillOval(holder.getXPos(),holder.getYPos(),holder.bodySize(),holder.bodySize());
			//System.out.println(holder.getXPos());
			//System.out.println(holder.getYPos());
			//System.out.println(holder.bodySize());

		}
		//planets from eList sin't being printed onto Jframe yet get functions do return values
		
		
		
		tm.start();
	}
	//finds the x distance bteween tow planets to scale i.e. distance is multiplied by scale
	public double findXDistance(CelestBody b1,CelestBody b2){
		return (b1.getXPos()-b2.getXPos())*scale;
	}

	//does the same as the FindX but find y distance isntead 
	public double findYDistance(CelestBody b1,CelestBody b2){
		return (b1.getYPos()-b2.getYPos())*scale;
	}

	//find hypothenus distance to be used in Netwen's gravitation equation since that is correct radius distance
	public double findZDistance(double distance1, double distance2){
		double zDistance=Math.pow(distance1,2)+Math.pow(distance2,2);
		zDistance=Math.sqrt(zDistance);
		return zDistance;

	}

	//this method basically updates each x and y coordinates of planet on Jframe by doing Newton's Gravity equation
	public void actionPerformed(ActionEvent e){
		//for loop used to get gravitation/ force exprienced on each planet to other on the frame
		for(int i=0; i<eList.size(); i++){

			//this will be holders for velocity after calculated
			double xVelocityC=0.0;
			double yVelocityC=0.0;
			CelestBody planet1= eList.get(i);
			for(int m=0; m<eList.size(); m++){
				if(i!=m){
					CelestBody planet2= eList.get(m);
					double xDist= findXDistance(planet1,planet2);
					double yDist= findYDistance(planet1,planet2);
					//try using hypothenus instead of xdist and ydist
					double zDist= findZDistance(xDist,yDist);

					//Does the math for x force and y force but will be same since hypotnuses distacne will be used for radius
					double xForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(xDist,2));
					double yForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(yDist,2));
					//using xDist, yDist or zDist yields same results

					if(planet1.getXPos()-planet2.getXPos()==0){
						xForce=0.0;
					}
					if(planet1.getXPos()<planet2.getXPos()){
						xVelocityC-= xForce;
					}else{
						xVelocityC+= xForce;
					}

					if(planet1.getYPos()-planet2.getYPos()==0){
						yForce=0.0;
					}

					if(planet1.getYPos()<planet2.getYPos()){
						yVelocityC-=yForce;
					}else{
						yVelocityC+=yForce;
					}

				}
			}
			//planet1.setXVelocity cannot be removed becuase it won't update without it
			planet1.setXVelocity(planet1.getXVelocity()+(xVelocityC/scale)/planet1.getMass());

			//Same case for setYVelocity
			planet1.setYVelocity(planet1.getYVelocity()+(yVelocityC/scale)/planet1.getMass());


			System.out.println("Origional X coordinate:");
			System.out.println(planet1.getXPos());

			//the planet1.getXPos()+ can be removed
			planet1.setXCoordinate((int)planet1.getXVelocity());
			System.out.println("X place Changes to:");
			System.out.println(planet1.getXPos());

			System.out.println("Origional Y Coordinate:");
			System.out.println(planet1.getYPos());


			//the planet1.getYPos()+ can be removed
			planet1.setYCoordinate((int)planet1.getYVelocity());
			System.out.println("New Y Coordinate:");
			System.out.println(planet1.getYPos());

		}
		repaint();
	}
	//stopped here @10:49 10/23 making file reader
	// @10/26/2020 made working file reader

	public void NBodiesCreator(String fileName) throws IOException{

		String fileInput= fileName;
		storage = new ArrayList<String[]>();
		try{
			BufferedReader read= new BufferedReader(new FileReader(fileInput));
			String line="";
			while((line=read.readLine())!=null){
				storage.add(line.split(","));
			}
		}catch(Exception ex){
			System.out.println("File Cannot be Found");
		}
		System.out.println(storage.get(0)[0]);

		//checks if first line states make LL or Arraylist
		if(storage.get(0)[0].equals("ArrayList")){
			System.out.println("Creating ArrayList");
			eList= new ArrayList<CelestBody>();
		}
		if(storage.get(0)[0].equals("LinkedList")){
			System.out.println("Creating LinkedList");
			eList=new LinkedList1<CelestBody>();
		}

		//Set constant scale to second item in file
		scale=Double.parseDouble(storage.get(1)[0]);

		//Everythng after first 2 lines are assumed items for a planet thus creating planets
		for(int i=2; i<storage.size(); i++){
			name=storage.get(i)[0];
			mass= Double.parseDouble(storage.get(i)[1]);
			xCoordinate=Integer.parseInt(storage.get(i)[2]);
			yCoordinate=Integer.parseInt(storage.get(i)[3]);
			xDirectVelocity=Double.parseDouble(storage.get(i)[4]);
			yDirectVelocity=Double.parseDouble(storage.get(i)[5]);
			bodySize=Integer.parseInt(storage.get(i)[6]);
			CelestBody planet= new CelestBody(name,mass,xCoordinate,yCoordinate,xDirectVelocity,yDirectVelocity,bodySize);
			eList.add(planet);
		}

	}
	



	public static void main(String[] args) throws IOException{
		//int number=(int)p1.getXVelocity()+25;
		//System.out.println((int)p1.getXVelocity()+25);

		String fileName="nbody_input.txt";

		NBodies t= new NBodies();

		t.NBodiesCreator(fileName);

		JFrame jf= new JFrame();

		jf.setTitle("Canvas");
		jf.setSize(768,768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}