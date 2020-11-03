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
	private double gravity=6.67*Math.pow(10,-11);


	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data=value;
			next=null;
		}
	}

	
	
	Timer tm= new Timer(500, this);

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

	public double findXDistance(CelestBody b1,CelestBody b2){
		return (b1.getXPos()-b2.getXPos())*scale;
	}

	public double findYDistance(CelestBody b1,CelestBody b2){
		return (b1.getYPos()-b2.getYPos())*scale;
	}
	public double findZDistance(double distance1, double distance2){
		double zDistance=Math.pow(distance1,2)+Math.pow(distance2,2);
		zDistance=Math.sqrt(zDistance);
		return zDistance;

	}

	public void actionPerformed(ActionEvent e){
		
		for(int i=0; i<eList.size(); i++){
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
					double xForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(zDist,2));
					double yForce= gravity*((planet1.getMass()*planet2.getMass())/Math.pow(zDist,2));

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
			planet1.setXVelocity(planet1.getXVelocity()+(xVelocityC/scale)/planet1.getMass());
			planet1.setYVelocity(planet1.getYVelocity()+(yVelocityC/scale)/planet1.getMass());

			System.out.println("Origional X coordinate:");
			System.out.println(planet1.getXPos());
			planet1.setXCoordinate(planet1.getXPos()+(int)planet1.getXVelocity());
			System.out.println("X place Changes to:");
			System.out.println(planet1.getXPos());

			System.out.println("Origional Y Coordinate:");
			System.out.println(planet1.getYPos());
			planet1.setYCoordinate(planet1.getYPos()+(int)planet1.getYVelocity());
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

		if(storage.get(0)[0].equals("ArrayList")){
			System.out.println("Creating ArrayList");
			eList= new ArrayList<CelestBody>();
		}
		if(storage.get(0)[0].equals("LinkedList")){
			System.out.println("Creating LinkedList");
			eList=new LinkedList1<CelestBody>();
		}

		scale=Double.parseDouble(storage.get(1)[0]);


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