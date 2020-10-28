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


	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data=value;
			next=null;
		}
	}

	
	
	Timer tm= new Timer(5, this);
	int x=384, velx=2;
	int y=348, vely=12;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		//for(int i=0;i<eList.size();i++){
			//CelestBody holder=eList.get(i);
			//System.out.println(holder.getXPos());
			//g.setColor(Color.RED);
			//g.fillOval(holder.getXPos(),holder.getYPos(),holder.bodySize(),holder.bodySize());
			//System.out.println("Hello");
		//}
		g.fillOval(x,y,20,20);
		
		tm.start();
	}

	public void actionPerformed(ActionEvent e){
		if(x<0||x>748){
			velx= -velx;
		}
		if(y<0||y>748){
			vely= -vely;
		}
		x=x+velx;
		y=y+vely;
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
		System.out.println(eList.size());

	}
	



	public static void main(String[] args) throws IOException{
		CelestBody p1= new CelestBody("Earth", 20000.0, 20, 5, 16.0, 3000.0, 20000);
		System.out.println(p1.getXVelocity());
		System.out.println(p1.getXPos());
		p1.setXVelocity(20.0);
		System.out.println(p1.getXVelocity());

		System.out.println(p1.giveName());

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